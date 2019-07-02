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
	
	/**
	 * 課題を登録します。
	 * @param issueForm
	 */
	public void registIssue(LoginForm loginForm, IssueForm issueForm) ;

	/**
	 * 課題の更新を実行します。
	 * @param issueKey
	 * @param issueForm
	 */
	public void updateIssue(LoginForm loginForm, String issueKey, IssueForm issueForm) ;

	/**
	 * 課題をKEEP,PROBLEM,TRYに分解したListを取得します。
	 * @param issueList
	 * @return
	 */
	public KPTListForm getKptList(List<IssueDto> issueList) ;

	/**
	 * ステータス一覧を取得します。
	 * @return
	 */
	public List<StatusDto> getStatuses(LoginForm loginForm) ;

	/**
	 * カテゴリー一覧を取得します。
	 * @return
	 */
	public List<CategoryDto> getCategories(LoginForm loginForm) ;

	/**
	 * 課題一覧を取得します。
	 * @return
	 */
	public List<IssueDto> getIssueList(LoginForm loginForm) ;

	/**
	 * カテゴリーの名称とIDのMapオブジェクトを取得します。
	 * @param categoryList
	 * @return
	 */
	public Map<String, Long> getKptIdMap(List<CategoryDto> categoryList) ;

	/**
	 * ステータスの名称とIDのMapオブジェクトを取得します。
	 * @param statusList
	 * @return
	 */
	public Map<String, Integer> getKptStatusMap(List<StatusDto> statusList); 
}
