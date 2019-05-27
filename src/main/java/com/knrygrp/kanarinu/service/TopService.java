package com.knrygrp.kanarinu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.BacklogAPI;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.KPTListForm;
import com.knrygrp.kanarinu.form.LoginForm;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.Category;
import com.nulabinc.backlog4j.Issue;
import com.nulabinc.backlog4j.Issue.PriorityType;
import com.nulabinc.backlog4j.IssueType;
import com.nulabinc.backlog4j.Priority;
import com.nulabinc.backlog4j.Project;
import com.nulabinc.backlog4j.ResponseList;
import com.nulabinc.backlog4j.Status;
import com.nulabinc.backlog4j.api.option.GetIssuesParams;


@Service
public class TopService {
	
	@Autowired
	HttpSession session;

	private static final String KEEP = "KEEP";
	private static final String PROBLEM = "PROBLEM";
	private static final String TRY = "TRY";
	
	/**
	 * 課題一覧を、KEEPの課題、PROBLEMの課題、TRYの課題に振り分けます。
	 * @param responseList
	 * @return
	 */
	public KPTListForm getKptList (ResponseList<Issue> responseList) {
		KPTListForm ret = new KPTListForm();
		List<Issue> retKeepList = new ArrayList<Issue>();
		List<Issue> retProblemList = new ArrayList<Issue>();
		List<Issue> retTryList = new ArrayList<Issue>();
		for(Issue i : responseList) {
			List<String> nameList = 
					i.getCategory().stream()
					.map(s -> s.getName())
					.collect(Collectors.toList());

			//TODO KEEP,Keep,keepも許可する
			if(nameList.contains(KEEP)) {
				retKeepList.add(i);
			}
			if(nameList.contains(PROBLEM)) {
				retProblemList.add(i);
			}
			if(nameList.contains(TRY)) {
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
	public List<Status> getStatuses() {
		//TODO APIに移譲
		LoginForm form = (LoginForm) session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		return blc.getStatuses();
	}

	/**
	 * カテゴリー一覧を取得します。
	 * @return
	 */
	public List<Category> getCategories() {
		//TODO APIに移譲
		LoginForm form = (LoginForm) session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = backlogAPI.getProject(blc, form.getProjectkey());

		return blc.getCategories(project.getId());
	}
	
	/**
	 * 課題一覧を取得します。
	 * @return
	 */
	public ResponseList<Issue> getResponseList() {
		LoginForm form = (LoginForm)session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = backlogAPI.getProject(blc, form.getProjectkey());
		List<Long> projectIdList = new ArrayList<Long>();
		projectIdList.add(project.getId());
		GetIssuesParams params = new GetIssuesParams(projectIdList);
		
		ResponseList<Issue> rl = blc.getIssues(params);
		
		return rl;

	}
	
	/**
	 * 課題を登録します。
	 * @param issueForm
	 */
	public void registIssue(IssueForm issueForm) {
		LoginForm form = (LoginForm)session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = backlogAPI.getProject(blc, form.getProjectkey());
		backlogAPI.registIssue(blc, project.getId(), 
				getIssueTypeId(blc, project.getProjectKey(), "タスク"), getPriority(blc, "中"),
				issueForm.getSummary(), issueForm.getDescription(), issueForm.getCategory(), issueForm.getStatus());
		
	}

	/**
	 * 課題の更新を実行します。
	 * @param issueKey
	 * @param issueForm
	 */
	public void updateIssue(String issueKey, IssueForm issueForm) {
		LoginForm form = (LoginForm)session.getAttribute("loginForm");
		BacklogAPI backlogAPI = new BacklogAPI();
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		backlogAPI.updateIssue(blc, issueKey, issueForm.getSummary(), issueForm.getDescription(), issueForm.getCategory(), issueForm.getStatus());
	}
	
	/**
	 * カテゴリーの名称とIDのMapオブジェクトを取得します。
	 * @param categoryList
	 * @return
	 */
	public Map<String, Long> getKptIdMap(List<Category> categoryList) {
		Map<String, Long> kptIdMap = new HashMap<String, Long>();
		for(Category cat : categoryList) {
			kptIdMap.put(cat.getName(), cat.getId());
		}
		return kptIdMap;
	}

	/**
	 * ステータスの名称とIDのMapオブジェクトを取得します。
	 * @param statusList
	 * @return
	 */
	public Map<String, Integer> getKptStatusMap(List<Status> statusList) {
		Map<String, Integer> kptStatusMap = new HashMap<String, Integer>();
		for(Status sta : statusList) {
			kptStatusMap.put(sta.getName(), sta.getId());
		}
		return kptStatusMap;
	}

	
	/**
	 * カテゴリー名に紐づくIDを取得します。
	 * @param blc
	 * @param projectId
	 * @param targetName
	 * @return
	 */
	private long getIssueTypeId (BacklogClient blc, String projectId, String targetName) {
		for(IssueType it : blc.getIssueTypes(projectId)) {
			if(it.getName().equals(targetName)) {
				return it.getId();
			}
		}
		return 0;
	}

	/**
	 * 優先度名に紐づく優先度IDを取得します。
	 * @param blc
	 * @param targetName
	 * @return
	 */
	private PriorityType getPriority (BacklogClient blc, String targetName) {
		for(Priority pri : blc.getPriorities()) {
			if(pri.getName().equals(targetName)) {
				return pri.getPriorityType();
			}
		}
		return null;
	}

}
