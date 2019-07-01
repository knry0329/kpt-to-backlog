package com.knrygrp.kanarinu.service;

import java.util.List;
import java.util.Map;
import com.knrygrp.kanarinu.dto.CategoryDto;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.dto.StatusDto;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.KPTListForm;
import com.knrygrp.kanarinu.form.LoginForm;

public interface TopService {
	
	public void registIssue(LoginForm loginForm, IssueForm issueForm) ;
	public void updateIssue(LoginForm loginForm, String issueKey, IssueForm issueForm) ;
	public KPTListForm getKptList(List<IssueDto> issueList) ;
	public List<StatusDto> getStatuses(LoginForm loginForm) ;
	public List<CategoryDto> getCategories(LoginForm loginForm) ;
	public List<IssueDto> getIssueList(LoginForm loginForm) ;
	public Map<String, Long> getKptIdMap(List<CategoryDto> categoryList) ;
	public Map<String, Integer> getKptStatusMap(List<StatusDto> statusList); 
}
