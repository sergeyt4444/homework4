package com.homework.controller;

import com.homework.entity.Person;
import com.homework.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Controller
public class PersonQueryController {

    @Autowired
    private PersonService service;

    @GetMapping("/query")
    public String queryForm(Model model) {
        model.addAttribute("person", new Person());
        return "query_form";
    }

    @PostMapping("/query")
    public String querySubmit(Model model, @ModelAttribute Person person,
                              HttpServletRequest req) {
        Person result = service.getPersonByFnameAndLname(person.getFname(), person.getLname());
        model.addAttribute("result", result);
        if (result != null) {
            HttpSession session = req.getSession(true);
            Date currentTime = new Date();
            String browser = req.getHeader("user-agent");
            session.setAttribute("email", result.getEmail());
            model.addAttribute("currentTime", currentTime);
            model.addAttribute("browser", browser);
            return "query_result";
        }
        else {
            return "redirect:/404";
        }
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

}
