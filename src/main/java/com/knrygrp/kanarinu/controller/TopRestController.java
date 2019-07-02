package com.knrygrp.kanarinu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.TopRestService;

@RestController
@SessionAttributes(names="loginForm")
public class TopRestController {
	
	@Autowired
	TopRestService topRestService;
	
	/**
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	@RequestMapping(value = "/issue/{issueKey}", method = RequestMethod.GET)
	public IssueDto getIssue(@PathVariable("issueKey") String issueKey, 
			@ModelAttribute("loginForm") LoginForm loginForm) {
		IssueDto issue = topRestService.getIssue(loginForm, issueKey);
		return issue;
	}

}
