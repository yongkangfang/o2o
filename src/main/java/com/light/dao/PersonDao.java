package com.light.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.light.model.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {

}
