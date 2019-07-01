package com.knrygrp.kanarinu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knrygrp.kanarinu.dto.CategoryDto;
import com.knrygrp.kanarinu.dto.IssueDto;
import com.knrygrp.kanarinu.dto.StatusDto;
import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.KPTListForm;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.TopService;

@Controller
public class TopController {
	
	@Autowired
	TopService topService;
	@Autowired
	HttpSession session;

	
	/**
	 * TOP画面初期表示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public String top(Model model) {
		
		LoginForm loginForm = (LoginForm)session.getAttribute("loginForm");
		//TODO loginFormが取得できなかった場合、エラー遷移

		//画面上でパースしやすいように
		//KEEP,TRY,PROBLEMごとにインスタンス作成
		List<IssueDto> issueList = topService.getIssueList(loginForm);
		KPTListForm kptList = topService.getKptList(issueList);
		model.addAttribute("kptList", kptList);
		List<StatusDto> statusList = topService.getStatuses(loginForm);
		model.addAttribute("statusList", statusList);
		List<CategoryDto> categoryList = topService.getCategories(loginForm);
		model.addAttribute("categoryList", categoryList);
		Map<String, Long> kptIdMap = topService.getKptIdMap(categoryList);
		model.addAttribute("kptIdMap", kptIdMap);
		Map<String, Integer> kptStatusMap = topService.getKptStatusMap(statusList);
		model.addAttribute("kptStatusMap", kptStatusMap);
		return "top";
	}
	
	/**
	 * 更新
	 * @param issueKey
	 * @param issueForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issue/{issueKey}", method = RequestMethod.POST)
	public String updateIssue(
			@PathVariable("issueKey") String issueKey, 
			@ModelAttribute("issueForm") IssueForm issueForm, 
			Model model) {
		LoginForm loginForm = (LoginForm)session.getAttribute("loginForm");

		topService.updateIssue(loginForm, issueKey, issueForm);
		
		return "redirect:/top";
	}

	/**
	 * 新規登録
	 * @param issueForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issue", method = RequestMethod.POST)
	public String RegistIssue(
			@ModelAttribute("issueForm") IssueForm issueForm, 
			Model model) {
		LoginForm loginForm = (LoginForm)session.getAttribute("loginForm");
		
		topService.registIssue(loginForm, issueForm);
		
		return "redirect:/top";
	}

}
