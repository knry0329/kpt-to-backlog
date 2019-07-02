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
	/**
	 * 課題を更新します。
	 * @param issueKey
	 * @param issueForm
	 * @param loginForm
	 */
	public void updateIssue(String issueKey, IssueForm issueForm, LoginForm loginForm);

	/**
	 * 課題を登録します。
	 * @param issueForm
	 * @param loginForm
	 */
	public void registIssue(IssueForm issueForm, LoginForm loginForm);

	/**
	 * 課題キーから課題情報の取得
	 * @param blc
	 * @param issueKey
	 * @return
	 */
	public IssueDto getIssue(LoginForm loginForm, String issueKey);

	/**
	 * 課題一覧の取得
	 * @param loginForm
	 * @return
	 */
	public List<IssueDto> getIssueList(LoginForm loginForm);
	
	/**
	 * ステータスを取得します。
	 * @param form
	 * @return
	 */
	public List<StatusDto> getStatuses(LoginForm form);

	/**
	 * カテゴリーを取得します。
	 * @param form
	 * @return
	 */
	public List<CategoryDto> getCategories(LoginForm form);

	/**
	 * プロジェクトの存在チェック
	 * @param form
	 * @return
	 */
	public boolean projectExistCheck(LoginForm form);
	
	
	
}
