package com.casestudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.springboot.model.PersonalInformation;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Integer> {

}
