package com.homework.repository;

import com.homework.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByFnameAndLname(String fname, String lname);

}
