/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MessageEntity;
import dao.MessageUserEntity;
import dao.UserEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;
import services.UserService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    private String listToJson(List l) {
        String returnValue = "{\"user\": [";
        int count = 0;
        for (Object f : l) {
            if (count > 0) {
                returnValue += ",";
            }
            returnValue += "{\"email\" : \"" + ((UserEntity) f).getEmail() + "\","
                    + "\"username\" : \"" + ((UserEntity) f).getUsername() + "\","
                    + "\"firstname\" : \"" + ((UserEntity) f).getProfile().getFirstName() + "\","
                    + "\"lastname\" : \"" + ((UserEntity) f).getProfile().getLastName() + "\""
                    + "}";
            count++;

        }
        returnValue += "]}";
        return returnValue;
    }

    //TODO comment here to explain the ajax use to get the list of user from search // do be used for the search bar too
    @RequestMapping(value = "/messageajaxTest.htm", method = RequestMethod.POST)
    public @ResponseBody
    String addUser(@ModelAttribute(value = "user") UserEntity user, BindingResult result) {
        String returnText = "";
        returnText = this.listToJson(this.userService.search(user.getEmail()));
        return returnText;
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public ModelAndView send(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/message.htm");

        UserEntity ue = (UserEntity) session.getAttribute("user");
        String emails = (String) request.getParameter("emails");
        List<String> lr = new ArrayList<>();
        lr = Arrays.asList(emails.split("\\s*,\\s*"));
        MessageEntity mId = this.messageService.add(request.getParameter("message"), request.getParameter("subject"), ue.getId());
        this.messageService.sendToMails(mId, lr);
        return model;
    }

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public ModelAndView message(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "message");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        List<Object> l = messageService.getGListPlusNewListFromUserID(ue.getId());

        model.addObject("groupList", l.get(0));
        model.addObject("newMessageGroupList", l.get(1));
        return model;
    }

    @RequestMapping(value = "/message/{groupMessage}", method = RequestMethod.GET)
    public ModelAndView message(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String groupMessage) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "message");
        model.addObject("currentGroupMessage", groupMessage);
        UserEntity ue = (UserEntity) session.getAttribute("user");
        ue = userService.findByID(ue.getId());
        messageService.messageRead(ue,groupMessage);
        List<Object> l = messageService.getGListPlusNewListFromUserID(ue.getId(),groupMessage);

        model.addObject("groupList", l.get(0));
        model.addObject("newMessageGroupList", l.get(1));
        model.addObject("messages", l.get(2));
        
        return model;
    }
}
