package com.knrygrp.kanarinu.dto;

import java.util.List;

/**
 * Statusを管理するクラス
 * View,Controller,Service層ではこちらのクラスを使用する。
 * API層にて、パースを行う。
 * @author kanariyusuke
 *
 */
public class IssueDto {
	private String issueKey;
	private String summary;
	private CreatedUserDto createdUser;
	private List<CategoryDto> category;
	private String description;
	private StatusDto status;

	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public CreatedUserDto getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(CreatedUserDto createdUser) {
		this.createdUser = createdUser;
	}
	public List<CategoryDto> getCategory() {
		return category;
	}
	public void setCategory(List<CategoryDto> category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusDto getStatus() {
		return status;
	}
	public void setStatus(StatusDto status) {
		this.status = status;
	}
}
