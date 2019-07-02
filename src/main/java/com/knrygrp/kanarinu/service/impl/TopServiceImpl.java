package com.knrygrp.kanarinu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.ProjectAPI;
import com.knrygrp.kanarinu.constants.KTBConstants;
import com.knrygrp.kanarinu.dto.CategoryDto;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.dto.StatusDto;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.KPTListForm;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.TopService;

@Service
public class TopServiceImpl implements TopService {
	
	@Autowired
	ProjectAPI projectAPI;

	/**
	 * {@inheritDoc}
	 */
	public void registIssue(LoginForm loginForm, IssueForm issueForm) {
		projectAPI.registIssue(issueForm, loginForm);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateIssue(LoginForm loginForm, String issueKey, IssueForm issueForm) {
		projectAPI.updateIssue(issueKey, issueForm, loginForm);
	}

	/**
	 * {@inheritDoc}
	 */
	public KPTListForm getKptList(List<IssueDto> issueList) {
		KPTListForm ret = new KPTListForm();
		List<IssueDto> retKeepList = new ArrayList<IssueDto>();
		List<IssueDto> retProblemList = new ArrayList<IssueDto>();
		List<IssueDto> retTryList = new ArrayList<IssueDto>();
		
		for(IssueDto i : issueList) {
			List<String> nameList = 
					i.getCategory().stream()
					.map(s -> s.getName())
					.collect(Collectors.toList());

			if(nameList.contains(KTBConstants.KEEP)) {
				retKeepList.add(i);
			}
			if(nameList.contains(KTBConstants.PROBLEM)) {
				retProblemList.add(i);
			}
			if(nameList.contains(KTBConstants.TRY)) {
				retTryList.add(i);
			}
		}
		ret.setKeepList(retKeepList);
		ret.setProblemList(retProblemList);
		ret.setTryList(retTryList);
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StatusDto> getStatuses(LoginForm loginForm) {
		return projectAPI.getStatuses(loginForm);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CategoryDto> getCategories(LoginForm loginForm) {
		return projectAPI.getCategories(loginForm);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<IssueDto> getIssueList(LoginForm loginForm) {
		return projectAPI.getIssueList(loginForm);
	}
		
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Long> getKptIdMap(List<CategoryDto> categoryList) {
		Map<String, Long> kptIdMap = new HashMap<String, Long>();
		for(CategoryDto cat : categoryList) {
			kptIdMap.put(cat.getName(), cat.getId());
		}
		return kptIdMap;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Integer> getKptStatusMap(List<StatusDto> statusList) {
		Map<String, Integer> kptStatusMap = new HashMap<String, Integer>();
		for(StatusDto sta : statusList) {
			kptStatusMap.put(sta.getName(), sta.getId());
		}
		return kptStatusMap;
	}
}
