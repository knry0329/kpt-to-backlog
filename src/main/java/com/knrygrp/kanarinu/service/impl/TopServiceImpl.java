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
	 * 課題を登録します。
	 * @param issueForm
	 */
	public void registIssue(LoginForm loginForm, IssueForm issueForm) {
//		LoginForm loginForm = (LoginForm)session.getAttribute("loginForm");
//		BacklogAPI backlogAPI = new BacklogAPI();
		projectAPI.registIssue(issueForm, loginForm);
	}

	/**
	 * 課題の更新を実行します。
	 * @param issueKey
	 * @param issueForm
	 */
	public void updateIssue(LoginForm loginForm, String issueKey, IssueForm issueForm) {
//		BacklogAPI backlogAPI = new BacklogAPI();
		projectAPI.updateIssue(issueKey, issueForm, loginForm);
	}

	/**
	 * 課題をKEEP,PROBLEM,TRYに分解したListを取得します。
	 * @param issueList
	 * @return
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

			//TODO KEEP,Keep,keepも許可する
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
	 * ステータス一覧を取得します。
	 * @return
	 */
	public List<StatusDto> getStatuses(LoginForm loginForm) {
//		BacklogAPI backlogAPI = new BacklogAPI();
		return projectAPI.getStatuses(loginForm);
	}

	/**
	 * カテゴリー一覧を取得します。
	 * @return
	 */
	public List<CategoryDto> getCategories(LoginForm loginForm) {
//		BacklogAPI backlogAPI = new BacklogAPI();
		return projectAPI.getCategories(loginForm);
	}

	
	/**
	 * 課題一覧を取得します。
	 * @return
	 */
	public List<IssueDto> getIssueList(LoginForm loginForm) {
//		BacklogAPI backlogAPI = new BacklogAPI();
		return projectAPI.getIssueList(loginForm);
	}
		
	/**
	 * カテゴリーの名称とIDのMapオブジェクトを取得します。
	 * @param categoryList
	 * @return
	 */
	public Map<String, Long> getKptIdMap(List<CategoryDto> categoryList) {
		Map<String, Long> kptIdMap = new HashMap<String, Long>();
		for(CategoryDto cat : categoryList) {
			kptIdMap.put(cat.getName(), cat.getId());
		}
		return kptIdMap;
	}

	/**
	 * ステータスの名称とIDのMapオブジェクトを取得します。
	 * @param statusList
	 * @return
	 */
	public Map<String, Integer> getKptStatusMap(List<StatusDto> statusList) {
		Map<String, Integer> kptStatusMap = new HashMap<String, Integer>();
		for(StatusDto sta : statusList) {
			kptStatusMap.put(sta.getName(), sta.getId());
		}
		return kptStatusMap;
	}


}
