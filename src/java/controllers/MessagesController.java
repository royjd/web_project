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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public ModelAndView send(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/message.htm");
        /*List<Long> lr = new ArrayList<>();
        lr.add(Long.parseLong(request.getParameter("targets")));
        MessageEntity mId = this.messageService.add(request.getParameter("message"), request.getParameter("subject"), Long.parseLong(request.getParameter("sender")));
        this.messageService.send(mId, lr);*/
        //TEST
        UserEntity ue = (UserEntity) session.getAttribute("user");
        List<Long> lr = new ArrayList<>();
        lr.add(2L);
        MessageEntity mId = this.messageService.add(request.getParameter("message"), request.getParameter("subject"), ue.getId());
        this.messageService.send(mId, lr);
        return model;
    }

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public ModelAndView message(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "message");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        ue = userService.findByID(ue.getId());
        List<MessageUserEntity> fta = ue.getMessageR();
        HashMap<String, List<MessageUserEntity>> hmmue = new HashMap<>();
        for (MessageUserEntity mue : fta) {

            hmmue.putIfAbsent(mue.getMessage().getGroupName(), mue.getMessage().getTarget());

        }

        //List<FriendEntity> friends = ue.getFriends();
        /*friends.addAll(ue.getFriendedBy());*/
        model.addObject("groupList", hmmue);
        return model;
    }

    @RequestMapping(value = "/message/{groupMessage}", method = RequestMethod.GET)
    public ModelAndView message(HttpServletRequest request, HttpServletResponse response, HttpSession session,@PathVariable String groupMessage) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "message");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        ue = userService.findByID(ue.getId());
        List<MessageUserEntity> fta = ue.getMessageR();
        HashMap<String, List<MessageUserEntity>> hmmue = new HashMap<>();
        List<MessageEntity> me = new ArrayList<>();
        for (MessageUserEntity mue : fta) {
            if(mue.getMessage().getGroupName().equals(groupMessage)){
                me.add(mue.getMessage());
            }
            hmmue.putIfAbsent(mue.getMessage().getGroupName(), mue.getMessage().getTarget());

        }

        //List<FriendEntity> friends = ue.getFriends();
        /*friends.addAll(ue.getFriendedBy());*/
        model.addObject("groupList", hmmue);
        model.addObject("messages", me);
        
        return model;
    }
}
