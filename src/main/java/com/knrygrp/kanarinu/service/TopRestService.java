package com.knrygrp.kanarinu.service;

import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.form.LoginForm;


public interface TopRestService {

	public IssueDto getIssue (LoginForm loginForm, String issueKey); 

}
