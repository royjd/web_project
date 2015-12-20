/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ExperienceEntity;
import dao.PhysicalDAO;
import dao.PhysicalEntity;
import dao.ProfileEntity;
import dao.UserEntity;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "{username}/profile", method = RequestMethod.GET)
    public ModelAndView init(@PathVariable String username, HttpSession session) {

        ModelAndView mv = new ModelAndView("page");

        UserEntity u = userService.findByUsername(username);

        if (u != null) {

            UserEntity user = (UserEntity) session.getAttribute("user");
            List<ExperienceEntity> l = experienceService.findExperiencesForProfil(u.getProfile().getId());

            if (user == null || !Objects.equals(user.getId(), u.getId())) {  // Je visite le mur d'un autre utilisateur
                mv.addObject("user", u);
                u.getProfile().setExperiences(l);
                mv.addObject("canModify", false);
            } else {   // Je visite mon mur
                mv.addObject("canModify", true);
                user.getProfile().setExperiences(l);
            }
            mv.addObject("content", "wall");
            mv.addObject("wallContent", repertoryName + "profile");
            mv.addObject("profileContent", "displayProfile");
        } else {   // L'utilisateur n'existe pas dans la base de données.

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
                mv.addObject("content", "wall");
                mv.addObject("editUser", user);
                mv.addObject("wallContent", repertoryName + "profile");
                mv.addObject("profileContent", "edit");
            }
        }

        return mv;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ModelAndView editProfile1(@ModelAttribute("editUser") UserEntity editUser, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");

        // Edit profile 
        ProfileEntity profile = user.getProfile();
        profile.setData(editUser.getProfile());
        profileService.update(profile);

        //Edit physical
        PhysicalEntity physical = profile.getPhysical();
        physical.setData(editUser.getProfile().getPhysical());
        physicalService.update(physical);

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("content", "wall");
        mv.addObject("wallContent", repertoryName + "profile");
        mv.addObject("profileContent", "edit");
        mv.addObject("editUser", editUser);

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
            mv.addObject("content", "wall");
            mv.addObject("wallContent", repertoryName + "profile");
            mv.addObject("profileContent", "displayExperience");
        } else {   // L'utilisateur n'existe pas dans la base de données.

        }
        return mv;
    }

    @RequestMapping(value = "{username}/experience/add", method = RequestMethod.GET)
    public ModelAndView addExperience(@PathVariable String username, HttpSession session) {
        UserEntity u = userService.findByUsername(username);
        ModelAndView mv = new ModelAndView("page");
        if (u != null) {
            UserEntity user = (UserEntity) session.getAttribute("user");

            if (user != null && Objects.equals(user.getId(), u.getId())) {
                mv.addObject("content", "wall");
                mv.addObject("wallContent", repertoryName + "profile");
                mv.addObject("profileContent", "addExperience");
                mv.addObject("newExperience", new ExperienceEntity());
            }
        }

        return mv;
    }

    @RequestMapping(value = "{username}/experience/add", method = RequestMethod.POST)
    public ModelAndView addExperience(@ModelAttribute("newExperience") ExperienceEntity newExperience, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");
        newExperience.setProfile(user.getProfile());
        experienceService.save(newExperience);
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("content", "wall");
        mv.addObject("wallContent", repertoryName + "profile");
        mv.addObject("profileContent", "displayProfile");
        //mv.addObject("newExperience", newExperience);
        return mv;
    }

    @RequestMapping(value = "{username}/experience/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editExperience(@PathVariable String username, @PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("page");
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null && Objects.equals(user.getUsername(), username)) {
            ExperienceEntity experience = experienceService.findById(id);
            if (experience != null) {
                mv.addObject("wallContent", repertoryName + "profile");
                mv.addObject("profileContent", "editExperience");
                mv.addObject("content", "wall");
                mv.addObject("experience", experience);
            }
            //mv.addObject("newExperience", new ExperienceEntity());
        }
        return mv;
    }

    @RequestMapping(value = "{username}/experience/edit", method = RequestMethod.POST)
    public ModelAndView editExperience(@ModelAttribute("experience") ExperienceEntity experience, HttpSession session) {
        ModelAndView mv = new ModelAndView("page");
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null) {
            experience.setProfile(user.getProfile());
            experienceService.update(experience);
            localisationService.update(experience.getLocalisation());
            mv.addObject("wallContent", repertoryName + "profile");
            mv.addObject("profileContent", "editExperience");
            mv.addObject("content", "wall");
            mv.addObject("experience", experience);
            //mv.addObject("newExperience", new ExperienceEntity());
        }
        return mv;
    }
    
    @RequestMapping(value = "{username}/experience/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExperience(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("page");
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null) {
            
            
            mv.addObject("wallContent", repertoryName + "profile");
            mv.addObject("profileContent", "displayExperience");
            mv.addObject("content", "wall");

            //mv.addObject("newExperience", new ExperienceEntity());
        }
        else{
            
        }
        return mv;
    }
}
