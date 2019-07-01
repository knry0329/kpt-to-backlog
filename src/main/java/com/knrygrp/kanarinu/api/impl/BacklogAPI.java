package com.knrygrp.kanarinu.api.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.knrygrp.kanarinu.api.ProjectAPI;
import com.knrygrp.kanarinu.constants.KTBConstants;
import com.knrygrp.kanarinu.dto.CategoryDto;
import com.knrygrp.kanarinu.dto.CreatedUserDto;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.dto.StatusDto;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.LoginForm;
import com.nulabinc.backlog4j.BacklogAPIException;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.BacklogClientFactory;
import com.nulabinc.backlog4j.Category;
import com.nulabinc.backlog4j.Issue;
import com.nulabinc.backlog4j.Issue.PriorityType;
import com.nulabinc.backlog4j.IssueType;
import com.nulabinc.backlog4j.Priority;
import com.nulabinc.backlog4j.Project;
import com.nulabinc.backlog4j.ResponseList;
import com.nulabinc.backlog4j.Status;
import com.nulabinc.backlog4j.api.option.CreateIssueParams;
import com.nulabinc.backlog4j.api.option.GetIssuesParams;
import com.nulabinc.backlog4j.api.option.UpdateIssueParams;
import com.nulabinc.backlog4j.conf.BacklogComConfigure;
import com.nulabinc.backlog4j.conf.BacklogConfigure;

/**
 * backlogAPI4jとサービスの橋渡しを行うクラスです。
 * @author bxnxs
 *
 */
@Component
public class BacklogAPI implements ProjectAPI {


	/**
	 * 課題を更新します。
	 * @param issueKey
	 * @param issueForm
	 * @param loginForm
	 */
	public void updateIssue(String issueKey, IssueForm issueForm, LoginForm loginForm) {
		BacklogClient blc = this.getBacklogClient(loginForm.getApikey(), loginForm.getSpacekey());
		UpdateIssueParams param = new UpdateIssueParams(issueKey);
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(issueForm.getCategory());
		param.categoryIds(categoryIds);
		for(Status entity : blc.getStatuses()) {
			if(entity.getId()==issueForm.getStatus()) {
				param.status(entity.getStatusType());
			}
		}
		param.summary(issueForm.getSummary());
		param.description(issueForm.getDescription());
		param.comment(KTBConstants.DEFAULT_REGIST_COMMENT);
		blc.updateIssue(param);
	}

	/**
	 * 課題を登録します。
	 * @param issueForm
	 * @param loginForm
	 */
	public void registIssue(IssueForm issueForm, LoginForm loginForm) {
		BacklogClient blc = this.getBacklogClient(loginForm.getApikey(), loginForm.getSpacekey());
		Project project = this.getProject(blc, loginForm.getProjectkey());

		CreateIssueParams param = new CreateIssueParams(
				project.getId(), 
				issueForm.getSummary(),
				this.getIssueTypeId(blc, project.getProjectKey(), KTBConstants.DEFAULT_ISSUETYPEID), 
				this.getPriority(blc, KTBConstants.DEFAULT_PRIORITY));
		param.description(issueForm.getDescription());
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(issueForm.getCategory());
		param.categoryIds(categoryIds);
		
		blc.createIssue(param);
		
	}
	
	/**
	 * 課題キーから課題情報の取得
	 * @param blc
	 * @param issueKey
	 * @return
	 */
	public IssueDto getIssue(LoginForm loginForm, String issueKey) {
		BacklogClient blc = this.getBacklogClient(loginForm.getApikey(), loginForm.getSpacekey());

		if(issueKey == null) {
			return null;
		}
		Issue issue = blc.getIssue(issueKey);
		return this.parseIssue(issue);
	}
	
	/**
	 * 課題一覧の取得
	 * @param loginForm
	 * @return
	 */
	public List<IssueDto> getIssueList(LoginForm loginForm) {
		BacklogClient blc = this.getBacklogClient(loginForm.getApikey(), loginForm.getSpacekey());
		Project project = this.getProject(blc, loginForm.getProjectkey());
		List<Long> projectIdList = new ArrayList<Long>();
		projectIdList.add(project.getId());
		GetIssuesParams params = new GetIssuesParams(projectIdList);
		ResponseList<Issue> rl = blc.getIssues(params);
		//BacklogのISSUEクラスから独自Issueクラスへとパース
		return this.parseIssueList(rl);
	}


	/**
	 * ステータスを取得します。
	 * @param form
	 * @return
	 */
	public List<StatusDto> getStatuses(LoginForm form) {
		BacklogClient blc = this.getBacklogClient(form.getApikey(), form.getSpacekey());
		List<Status> statusList = blc.getStatuses();

		return this.parseStatuses(statusList);
	}

	/**
	 * カテゴリーを取得します。
	 * @param form
	 * @return
	 */
	public List<CategoryDto> getCategories(LoginForm form) {
		BacklogClient blc = this.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = this.getProject(blc, form.getProjectkey());
		List<Category> categoryList = blc.getCategories(project.getId());

		return parseCategory(categoryList);
	}

	/**
	 * プロジェクトの存在チェック
	 * @param form
	 * @return
	 */
	public boolean projectExistCheck(LoginForm form) {
		BacklogClient blc = this.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = this.getProject(blc, form.getProjectkey());
		if(project == null) {
			return false;
		}
		return true;
	}


	/**
	 * Backlog4JのStatusインスタンスからKTBのStatusインスタンスへのパースを行います。
	 * @param statusList
	 * @return
	 */
	private List<StatusDto> parseStatuses(List<Status> statusList) {
		List<StatusDto> retList = new ArrayList<StatusDto>();
		for(Status stat : statusList) {
			StatusDto ret = new StatusDto();
			ret.setId(stat.getId());
			ret.setName(stat.getName());
			retList.add(ret);
		}
		
		return retList;
	}

	/**
	 * Backlog4jのIssueインスタンスからKTBのIssueインスタンスへのパースを行います。（List）
	 * @param rl
	 * @return
	 */
	private List<IssueDto> parseIssueList(ResponseList<Issue> rl) {
		List<IssueDto> ret = new ArrayList<com.knrygrp.kanarinu.dto.IssueDto>();
		for(Issue issueBL : rl) {
			ret.add(this.parseIssue(issueBL));
		}
		return ret;
	}

	/**
	 * Backlog4jのIssueインスタンスからKTBのIssueインスタンスへのパースを行います。
	 * @param issueBL
	 * @return
	 */
	private IssueDto parseIssue(Issue issueBL) {
		IssueDto issueDto = new IssueDto();
		issueDto.setIssueKey(issueBL.getIssueKey());
		issueDto.setSummary(issueBL.getSummary());
		issueDto.setDescription(issueBL.getDescription());
		CreatedUserDto createdUserDto = new CreatedUserDto();
		createdUserDto.setName(issueBL.getCreatedUser().getName());
		issueDto.setCreatedUser(createdUserDto);
		StatusDto statusDto = new StatusDto();
		statusDto.setId(issueBL.getStatus().getId());
		statusDto.setName(issueBL.getStatus().getName());
		issueDto.setStatus(statusDto);
		List<CategoryDto> retCategoryList = new ArrayList<CategoryDto>();
		for(Category cat : issueBL.getCategory()) {
			CategoryDto catDto = new CategoryDto();
			catDto.setId(cat.getId());
			catDto.setName(cat.getName());
			retCategoryList.add(catDto);
		}
		issueDto.setCategory(retCategoryList);
		return issueDto;
	}

	/**
	 * Backlog4jのCategoryインスタンスからKTBのCategoryインスタンスへのパースを行います。
	 * @param categoryList
	 * @return
	 */
	private List<CategoryDto> parseCategory(List<Category> categoryList) {
		List<CategoryDto> retList = new ArrayList<CategoryDto>();
		for(Category cat : categoryList) {
			CategoryDto ret = new CategoryDto();
			ret.setId(cat.getId());
			ret.setName(cat.getName());
			retList.add(ret);
		}
		return retList;
	}

	/**
	 * 優先度について、名称からインスタンスを取得します。
	 * @param blc
	 * @param targetName
	 * @return
	 */
	private PriorityType getPriority(BacklogClient blc, String targetName) {
		for(Priority pri : blc.getPriorities()) {
			if(pri.getName().equals(targetName)) {
				return pri.getPriorityType();
			}
		}
		return null;
	}

	/**
	 * 課題種別について、名称からIDを取得します。
	 * @param blc
	 * @param projectKey
	 * @param targetName
	 * @return
	 */
	private long getIssueTypeId(BacklogClient blc, String projectKey, String targetName) {
		for(IssueType it : blc.getIssueTypes(projectKey)) {
			if(it.getName().equals(targetName)) {
				return it.getId();
			}
		}
		return 0;
	}
	/**
	 * backlogClientの取得
	 * @param apiKey
	 * @param spaceKey
	 * @return
	 */
	private BacklogClient getBacklogClient(String apiKey, String spaceKey) {
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
	private Project getProject(BacklogClient blc, String projectKey) {
		//TODO 適切な判定方法
		Project pj = null;
		try {
			pj = blc.getProject(projectKey);
		} catch(BacklogAPIException e) {
			return null;
		}
		return pj;
	}

}
