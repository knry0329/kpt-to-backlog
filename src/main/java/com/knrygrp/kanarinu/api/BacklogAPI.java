package com.knrygrp.kanarinu.api;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.nulabinc.backlog4j.BacklogAPIException;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.BacklogClientFactory;
import com.nulabinc.backlog4j.Issue;
import com.nulabinc.backlog4j.Issue.PriorityType;
import com.nulabinc.backlog4j.Project;
import com.nulabinc.backlog4j.Status;
import com.nulabinc.backlog4j.api.option.CreateIssueParams;
import com.nulabinc.backlog4j.api.option.UpdateIssueParams;
import com.nulabinc.backlog4j.conf.BacklogComConfigure;
import com.nulabinc.backlog4j.conf.BacklogConfigure;

/**
 * backlogAPI4jとサービスの橋渡しを行うクラスです。
 * @author bxnxs
 *
 */
public class BacklogAPI {

	/**
	 * backlogClientの取得
	 * @param apiKey
	 * @param spaceKey
	 * @return
	 */
	public BacklogClient getBacklogClient(String apiKey, String spaceKey) {
		BacklogClient bc = null;
		try {
			BacklogConfigure configure = new BacklogComConfigure(spaceKey).apiKey(apiKey);
			bc = new BacklogClientFactory(configure).newClient();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return bc;
	}
	
	/**
	 * プロジェクト情報の取得
	 * @param blc
	 * @param projectKey
	 * @return
	 */
	public Project getProject(BacklogClient blc, String projectKey) {
		//TODO 適切な判定方法
		Project pj = null;
		try {
			pj = blc.getProject(projectKey);
		} catch(BacklogAPIException e) {
			return null;
		}
		return pj;
	}
	
	/**
	 * 課題キーから課題情報の取得
	 * @param blc
	 * @param issueKey
	 * @return
	 */
	public Issue getIssue(BacklogClient blc, String issueKey) {
		if(issueKey == null) {
			return null;
		}
		return blc.getIssue(issueKey);
	}
	
	/**
	 * 課題の更新
	 * @param blc
	 * @param issueKey
	 * @param summary
	 * @param description
	 * @param categoryId
	 * @param status
	 */
	public void updateIssue(
			BacklogClient blc, String issueKey, 
			String summary, String description, 
			Long categoryId, int status) {
		UpdateIssueParams param = new UpdateIssueParams(issueKey);
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(categoryId);
		param.categoryIds(categoryIds);
		for( Status entity : blc.getStatuses()) {
			if(entity.getId()==status) {
				param.status(entity.getStatusType());
			}
		}
		param.summary(summary);
		param.description(description);
		param.comment("Updated On KPT on Backlog.");
		blc.updateIssue(param);
	}

	/**
	 * 課題の登録
	 * @param blc
	 * @param projectId
	 * @param issueTypeId
	 * @param priority
	 * @param summary
	 * @param description
	 * @param category
	 * @param status
	 */
	public void registIssue(
			BacklogClient blc, Long projectId,
			Long issueTypeId, PriorityType priority,
			String summary, String description, 
			Long category, int status) {
		CreateIssueParams param = new CreateIssueParams(projectId, summary, issueTypeId, priority);
		param.description(description);
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(category);
		param.categoryIds(categoryIds);
		
		blc.createIssue(param);
		
	}
}
