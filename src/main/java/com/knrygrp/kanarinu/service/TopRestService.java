package com.knrygrp.kanarinu.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.BacklogAPI;
import com.knrygrp.kanarinu.form.LoginForm;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.Issue;


@Service
public class TopRestService {

	@Autowired
	HttpSession session;

	/**
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	public Issue getIssue (String issueKey) {
		LoginForm form = (LoginForm)session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		Issue ret = backlogAPI.getIssue(blc, issueKey);

		return ret;
	}

}
