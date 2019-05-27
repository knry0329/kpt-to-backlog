package com.knrygrp.kanarinu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knrygrp.kanarinu.service.TopRestService;
import com.nulabinc.backlog4j.Issue;

@RestController
public class TopRestController {
	
	@Autowired
	TopRestService topRestService;
	
	/**
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	@RequestMapping(value = "/issue/{issueKey}", method = RequestMethod.GET)
	public Issue getIssue(@PathVariable("issueKey") String issueKey) {
		
		Issue issue = topRestService.getIssue(issueKey);

		return issue;
	}

}
