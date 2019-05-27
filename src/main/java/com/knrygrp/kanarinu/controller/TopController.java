package com.knrygrp.kanarinu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knrygrp.kanarinu.form.IssueForm;
import com.knrygrp.kanarinu.form.KPTListForm;
import com.knrygrp.kanarinu.service.TopService;
import com.nulabinc.backlog4j.Category;
import com.nulabinc.backlog4j.Issue;
import com.nulabinc.backlog4j.ResponseList;
import com.nulabinc.backlog4j.Status;

@Controller
public class TopController {
	
	@Autowired
	TopService topService;
	
	/**
	 * TOP画面初期表示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public String top(Model model) {
		
		//画面上でパースしやすいように
		//KEEP,TRY,PROBLEMごとにインスタンス作成
		ResponseList<Issue> responseList = topService.getResponseList();
		KPTListForm kptList = topService.getKptList(responseList);
		model.addAttribute("kptList", kptList);
		List<Status> statusList = topService.getStatuses();
		model.addAttribute("statusList", statusList);
		List<Category> categoryList = topService.getCategories();
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
		
		topService.updateIssue(issueKey, issueForm);
		
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
		
		topService.registIssue(issueForm);
		
		return "redirect:/top";
	}

}
