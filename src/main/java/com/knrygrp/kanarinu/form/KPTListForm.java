package com.knrygrp.kanarinu.form;

import java.util.List;

import com.knrygrp.kanarinu.dto.IssueDto;

//import com.nulabinc.backlog4j.IssueDto;

public class KPTListForm {

	private List<IssueDto> keepList;
	private List<IssueDto> problemList;
	private List<IssueDto> tryList;
	public List<IssueDto> getKeepList() {
		return keepList;
	}
	public void setKeepList(List<IssueDto> keepList) {
		this.keepList = keepList;
	}
	public List<IssueDto> getProblemList() {
		return problemList;
	}
	public void setProblemList(List<IssueDto> problemList) {
		this.problemList = problemList;
	}
	public List<IssueDto> getTryList() {
		return tryList;
	}
	public void setTryList(List<IssueDto> tryList) {
		this.tryList = tryList;
	}
	
}
