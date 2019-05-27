package com.knrygrp.kanarinu.form;

import java.util.List;

import com.nulabinc.backlog4j.Issue;

public class KPTListForm {

	private List<Issue> keepList;
	private List<Issue> problemList;
	private List<Issue> tryList;
	public List<Issue> getKeepList() {
		return keepList;
	}
	public void setKeepList(List<Issue> keepList) {
		this.keepList = keepList;
	}
	public List<Issue> getProblemList() {
		return problemList;
	}
	public void setProblemList(List<Issue> problemList) {
		this.problemList = problemList;
	}
	public List<Issue> getTryList() {
		return tryList;
	}
	public void setTryList(List<Issue> tryList) {
		this.tryList = tryList;
	}
	
}
