package com.homework.controller;

import com.homework.entity.Person;
import com.homework.mail.Mail;
import com.homework.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MailController {

    @Autowired
    private EmailService service;

    @GetMapping("/mail")
    public String mailForm(Model model) {
        model.addAttribute("mail", new Mail());
        return "mail";
    }

    @PostMapping("/mail")
    public String sendEmail(@ModelAttribute Person person, @ModelAttribute Mail mail, HttpServletRequest req) {
        HttpSession session = req.getSession();
        service.sendSimpleMessage((String)session.getAttribute("email"), mail.getTitle(), mail.getMessage());
        return "email_sent";
    }


}
