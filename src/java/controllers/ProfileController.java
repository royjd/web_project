/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commun.ExperienceValidator;
import commun.UserValidator;
import dao.ExperienceEntity;
import dao.LocalisationEntity;
import dao.PhysicalEntity;
import dao.ProfileEntity;
import dao.UserEntity;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.ExperienceService;
import services.LocalisationService;
import services.PhysicalService;
import services.ProfileService;
import services.UserService;

/**
 *
 * @author zakaridia
 */
@Controller
public class ProfileController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    ExperienceValidator experienceValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private PhysicalService physicalService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private LocalisationService localisationService;

    private static final String repertoryName = "profile/";

    // To add attribute in all model in this controllers
    @ModelAttribute(value = "{username}")
    public void common(Model mv , @PathVariable String username) {
        mv.addAttribute("wallContent", repertoryName + "profile");
        mv.addAttribute("username", username);
        mv.addAttribute("content", "wall");
    }

    @RequestMapping(value = "{username}/profile", method = RequestMethod.GET)
    public ModelAndView init(@PathVariable String username, HttpSession session) {
        
        ModelAndView mv = new ModelAndView("page");

        UserEntity u = userService.findByUsername(username);

        if (u != null) {
            UserEntity user = (UserEntity) session.getAttribute("user");
            List<ExperienceEntity> l = experienceService.findExperiencesForProfil(u.getProfile().getId() , 3);
            u.getProfile().setExperiences(l);
            if (user == null || !Objects.equals(user.getId(), u.getId())) {  // Je visite le mur d'un autre utilisateur
                mv.addObject("canModify", false);
            } else {   // Je visite mon mur
                mv.addObject("canModify", true);
            }
            mv.addObject("u", u);
            mv.addObject("profileContent", "displayProfile");
        } else {   // L'utilisateur n'existe pas dans la base de données.
            mv.addObject("profileContent", "NotFound");
        }

        return mv;
    }

    @RequestMapping(value = "{username}/profile/edit", method = RequestMethod.GET)
    public ModelAndView editProfile(@PathVariable String username, HttpSession session) {
        UserEntity u = userService.findByUsername(username);
        ModelAndView mv = new ModelAndView("page");
        if (u != null) {
            UserEntity user = (UserEntity) session.getAttribute("user");
            // On peut editer
            if (user != null && Objects.equals(user.getId(), u.getId())) {
                mv.addObject("editUser", user);
                mv.addObject("msg", "");
                mv.addObject("profileContent", "editProfile");
            }
        }

        return mv;
    }

    @RequestMapping(value = "{username}/profile/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("editUser") @Validated UserEntity editUser, BindingResult result,
            HttpSession session, RedirectAttributes redirectAttributes) {

        userValidator.validate(editUser, result);

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("page");
            mv.addObject("profileContent", "editProfile");
            mv.addObject("msg", "There some erros, fix them");
            return mv;

        }
        UserEntity user = (UserEntity) session.getAttribute("user");

        // Edit profile 
        ProfileEntity profileUser = user.getProfile();
        profileUser.setData(editUser.getProfile());
        profileService.update(profileUser);

        //Edit physical
        PhysicalEntity physicalUser = profileUser.getPhysical();
        physicalUser.setData(editUser.getProfile().getPhysical());
        physicalService.update(physicalUser);

        ModelAndView mv = new ModelAndView("redirect:/" + user.getUsername() + "/profile.htm");
        redirectAttributes.addFlashAttribute("msg", "Modifications are saved");
        return mv;
    }

    @RequestMapping(value = "{username}/experience", method = RequestMethod.GET)
    public ModelAndView displayExperience(@PathVariable String username, HttpSession session) {

        ModelAndView mv = new ModelAndView("page");

        UserEntity u = userService.findByUsername(username);

        if (u != null) {

            UserEntity user = (UserEntity) session.getAttribute("user");
            if (user == null || !Objects.equals(user.getId(), u.getId())) {  // Je visite le mur d'un autre utilisateur
                mv.addObject("canModify", false);
            } else {   // Je visite mon mur
                mv.addObject("canModify", true);
            }
            List<ExperienceEntity> experiences = experienceService.findExperiencesForProfil(u.getProfile().getId());
            mv.addObject("experiences", experiences);
            mv.addObject("username", username);
            mv.addObject("profileContent", "displayExperience");
        } else {   // L'utilisateur n'existe pas dans la base de données.

        }
        return mv;
    }

    @RequestMapping(value = "{username}/experience/manage/{id}", method = RequestMethod.GET)
    public ModelAndView manageExperience(@PathVariable String username, @PathVariable Long id, HttpSession session) {
        UserEntity u = userService.findByUsername(username);
        ModelAndView mv = new ModelAndView("page");
        if (u != null) {
            UserEntity user = (UserEntity) session.getAttribute("user");
            if (user != null && Objects.equals(user.getId(), u.getId())) {
                
                mv.addObject("msg", "");
                if (id == 0) { // We want to add
                    ExperienceEntity e = new ExperienceEntity();
                    e.setLocalisation(new LocalisationEntity());
                    mv.addObject("newExperience", e);
                    mv.addObject("submitValue", "Save experience");
                    mv.addObject("Title", "Add an experience");
                    
                } else {
                    ExperienceEntity experience = experienceService.findById(id);
                    
                    if (experience != null) {
                        mv.addObject("profileContent", "editExperience");
                        mv.addObject("newExperience", experience);
                        mv.addObject("submitValue", "Save Changes");
                        mv.addObject("Title", "Edit your experience");
                    }
                    else{
                        
                    }
                }
                mv.addObject("profileContent", "manageExperience");

            }
        }

        return mv;
    }

    @RequestMapping(value = "{username}/experience/manage", method = RequestMethod.POST)
    public ModelAndView manageExperience(@ModelAttribute("newExperience") @Validated ExperienceEntity newExperience, BindingResult result,
            HttpSession session, RedirectAttributes redirectAttributes) {

        experienceValidator.validate(newExperience, result);
        if (result.hasErrors()) {

            ModelAndView mv = new ModelAndView("page");
            mv.addObject("profileContent", "manageExperience");
            mv.addObject("msg", "There some erros, fix them");
            if(newExperience.getId() == null){
                mv.addObject("submitValue", "Add experience");
                mv.addObject("Title", "Add an experience");
            }
            else{
                mv.addObject("submitValue", "Save Changes");
                mv.addObject("Title", "Edit your experience");
            }
            return mv;
        }
        UserEntity user = (UserEntity) session.getAttribute("user");
        newExperience.setProfile(user.getProfile());
        if (newExperience.getId() == null) {
            experienceService.save(newExperience);
            redirectAttributes.addFlashAttribute("msg", "Experience is added");
        } else {
            experienceService.update(newExperience);
            localisationService.update(newExperience.getLocalisation());
            redirectAttributes.addFlashAttribute("msg", "Modifications are saved");
        }
        ModelAndView mv = new ModelAndView("redirect:/" + user.getUsername() + "/experience.htm");
        mv.addObject("profileContent", "displayExperience");
        return mv;
    }

    @RequestMapping(value = "{username}/experience/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExperience(@PathVariable Long id, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null) {
            ExperienceEntity experience = experienceService.findById(id);
            if(experience != null){
                experienceService.delete(experience);
                ModelAndView mv = new ModelAndView("redirect:/" + user.getUsername() + "/experience.htm");
                return mv;
            }
            else{
                
            }

        } else {

        }
        return null;
    }
}
