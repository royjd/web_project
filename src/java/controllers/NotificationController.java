/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MessageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class NotificationController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "notification", method = RequestMethod.GET)
    public ModelAndView notification(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "notification");
        return model;
    }
}
