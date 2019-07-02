package com.knrygrp.kanarinu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.ProjectAPI;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.TopRestService;


@Service
public class TopRestServiceImpl implements TopRestService {

	@Autowired
	ProjectAPI projectAPI;

	/**
	 * {@inheritDoc}
	 */
	public IssueDto getIssue (LoginForm loginForm, String issueKey) {
		return projectAPI.getIssue(loginForm, issueKey);
	}

}
