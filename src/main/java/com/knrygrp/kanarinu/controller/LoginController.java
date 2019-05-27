package com.knrygrp.kanarinu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model, RedirectAttributes attributes) {
		try {
			loginService.login(form);
			
			return "redirect:top";
		} catch(KPTException e) {
			attributes.addFlashAttribute("errorMsg", "Sign in failed.");
			return "redirect:/";
		}
	}

}
