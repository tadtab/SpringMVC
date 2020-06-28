package com.tadtab.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadtab.core.pojo.ContactUsMessage;
import com.tadtab.dao.ContactMessageDAO;

@Controller
@RequestMapping("/contactUs")
public class ContactMessageHandler {
	
	@Autowired
	private ContactMessageDAO contactMessageDAO;
	
	 @Autowired
	 ContactUsMessage feedbackMessage;
	
	@RequestMapping("/FeedBack")
	public String getFeedBackPage(Model model) {
		 model.addAttribute("contactMessage", feedbackMessage);
		return "FeedBack";
	}
	
	@RequestMapping("/message")
	public String handleMessage(@ModelAttribute("contactMessage") ContactUsMessage contactUsMessage, Model model) {
		
		contactMessageDAO.persistMessage(contactUsMessage);
		
		if(contactUsMessage.getMessage() != "") {
			model.addAttribute("testMessage1119", "Thank You! Your Message is Delivered and In Case You Need Our Assistance, We WIll Get Back To You At The Earliest Time Possible");
		}else {
			model.addAttribute("testMessage1120", "Sorry your Message is Not Delivered Yet. Please Try Again.");
		}
		
		return "FeedBack";
	}

}
