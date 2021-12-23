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

@Controller
public class PersonInputController {

    private final String UPLOAD_DIR = "src/main/resources/uploads/";

    @Autowired
    private PersonService service;

    @GetMapping("/input")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "input_form";
    }

    @PostMapping("/input")
    public String personSubmit(@ModelAttribute Person person, HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = service.validatePerson(person);
        if (valid) {
            service.savePerson(person);
            HttpSession session = req.getSession();
            session.setAttribute("email", person.getEmail());
            return "input_result";
        }
        else {
            return "redirect:/invalid";
        }
    }

    @PostMapping("/upload")
    public String uploadPerson(Model model, @RequestParam("file") MultipartFile file,
                               HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (file.isEmpty()) {
            return "redirect:/invalid";
        }

        uploadFile(file);

        Person person = service.savePersonFromFile(file);
        model.addAttribute("person", person);
        if (person == null) {
            return "redirect:/invalid";
        }
        service.savePerson(person);
        HttpSession session = req.getSession();
        session.setAttribute("email", person.getEmail());
        return "input_result";
    }

    @GetMapping("/invalid")
    public String invalid() {
        return "invalid";
    }

    private void uploadFile(MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
        File uploadedFile = new File(UPLOAD_DIR + fileName);
        uploadedFile.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(uploadedFile);
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
