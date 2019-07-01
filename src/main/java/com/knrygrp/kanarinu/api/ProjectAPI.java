package com.knrygrp.kanarinu.api;

import java.util.List;

import com.knrygrp.kanarinu.dto.CategoryDto;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.dto.StatusDto;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.LoginForm;

/**
 * プロジェクト管理のAPIを呼び出す際のInterface
 * @author kanariyusuke
 *
 */
public interface ProjectAPI {
	public void updateIssue(String issueKey, IssueForm issueForm, LoginForm loginForm);
	public void registIssue(IssueForm issueForm, LoginForm loginForm);
	public IssueDto getIssue(LoginForm loginForm, String issueKey);
	public List<IssueDto> getIssueList(LoginForm loginForm);
	public List<StatusDto> getStatuses(LoginForm form);
	public List<CategoryDto> getCategories(LoginForm form);
	public boolean projectExistCheck(LoginForm form);
	
	
	
}
