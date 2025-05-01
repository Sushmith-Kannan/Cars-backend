package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.model.PersonalInformation;
import com.casestudy.springboot.service.PersonalInformationService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/"})
public class PersonalInformationController {

	@Autowired
	private PersonalInformationService personalinfoservice;
	@PostMapping("/api/addpersonal")
	public PersonalInformation addpersonal(@RequestBody PersonalInformation p)
	{
		return personalinfoservice.addpersonal(p);
	}
}
