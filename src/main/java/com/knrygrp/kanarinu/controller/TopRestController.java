package com.knrygrp.kanarinu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.TopRestService;

@RestController
public class TopRestController {
	
	@Autowired
	TopRestService topRestService;
	@Autowired
	HttpSession session;
	
	/**
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	@RequestMapping(value = "/issue/{issueKey}", method = RequestMethod.GET)
	public IssueDto getIssue(@PathVariable("issueKey") String issueKey) {
		LoginForm loginForm = (LoginForm)session.getAttribute("loginForm");

		IssueDto issue = topRestService.getIssue(loginForm, issueKey);

		return issue;
	}

}
