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
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	public IssueDto getIssue (LoginForm loginForm, String issueKey) {
//		BacklogAPI backlogAPI = new BacklogAPI();
		return projectAPI.getIssue(loginForm, issueKey);
	}

}
