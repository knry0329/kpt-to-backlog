package com.knrygrp.kanarinu.service;

import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.form.LoginForm;


public interface TopRestService {

	/**
	 * 課題のキーから、課題オブジェクトを取得します。
	 * @param issueKey
	 * @return
	 */
	public IssueDto getIssue (LoginForm loginForm, String issueKey); 

}
