package com.harshalit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harshalit.entity.Contact;
import com.harshalit.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	
	@GetMapping({"/","/home"})
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/contactInfo")
	public String contactInfoPage(Model model) {
		model.addAttribute("contactCmd", new Contact());
		return "contact";
	}
	
	@PostMapping("/saveContact")
	public String saveContact(Contact contact, RedirectAttributes attributes) {
		//contactService.
		//attributes.addAttribute("resultMsg", resultMsg);
		return "Redirect:/contactInfo";
	}
	
}
