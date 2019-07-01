package com.knrygrp.kanarinu.dto;

/**
 * Statusを管理するクラス
 * View,Controller,Service層ではこちらのクラスを使用する。
 * API層にて、パースを行う。
 * @author kanariyusuke
 *
 */
public class StatusDto {

	private int Id;

	private String name;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
