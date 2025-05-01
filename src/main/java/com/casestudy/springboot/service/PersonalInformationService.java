package com.casestudy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.model.PersonalInformation;
import com.casestudy.springboot.repository.PersonalInformationRepository;

@Service
public class PersonalInformationService {

	@Autowired
	private PersonalInformationRepository personalinforepo;

	public PersonalInformation addpersonal(PersonalInformation p) {
		// TODO Auto-generated method stub
		return personalinforepo.save(p);
	}
}
