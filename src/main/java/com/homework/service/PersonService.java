package com.homework.service;

import com.homework.entity.Person;
import com.homework.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PersonService {

    private final String UPLOAD_DIR = "src/main/resources/uploads/";

    @Autowired
    private PersonRepository repository;

    public void savePerson(Person person) {
        repository.save(person);
    }

    public Person getPersonByFnameAndLname(String fname, String lname) {
        List<Person> res = repository.findByFnameAndLname(fname,lname);
        if (res.isEmpty()) {
            return null;
        }
        else {
            return res.get(0);
        }
    }

    public boolean validatePerson(Person person) {
        return validatePersonAge(person.getAge()) && validatePersonFname(person.getFname()) &&
                validatePersonLname(person.getLname()) && validatePersonPatron(person.getPatron()) &&
                validatePersonEmail(person.getEmail());
    }

    public Person savePersonFromFile(MultipartFile file) throws IOException {
        File uploadedFile = new File(UPLOAD_DIR + file.getOriginalFilename());
        List<String> lines = Files.readAllLines(uploadedFile.toPath());
        Person person = readPerson(lines);
        if (validatePerson(person)) {
            repository.save(person);
            return person;
        }
        else {
            return null;
        }
    }

    private boolean validatePersonFname(String fname) {
        return fname.length() > 1 && Pattern.matches("[a-zA-Z]+", fname);
    }

    private boolean validatePersonLname(String lname) {
        return lname.length() > 1 && Pattern.matches("[a-zA-Z]+", lname);
    }

    private boolean validatePersonPatron(String patron) {
        return patron.length() > 1 && Pattern.matches("[a-zA-Z]+", patron);
    }

    private boolean validatePersonEmail(String email) {
        return Pattern.matches("\\S+@[a-zA-Z]+\\.(ru|com)", email);

    }

    private boolean validatePersonAge(int age) {
        return age >= 0 && age <= 150;
    }

    private Person readPerson(List<String> list) {
        if (list.size() != 7)
            return null;
        Person person = new Person();
        person.setLname(list.get(0));
        person.setFname(list.get(1));
        person.setPatron(list.get(2));
        person.setAge(Integer.parseInt(list.get(3)));
        person.setSalary(Double.parseDouble(list.get(4)));
        person.setEmail(list.get(5));
        person.setWorkplace(list.get(6));
        return person;
    }

}
