package com.knrygrp.kanarinu.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.impl.BacklogAPI;
import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
		
	@Autowired
	HttpSession session;

	/**
	 * APIKEY、スペースキー、プロジェクトキーをもとに存在チェックを実施する。
	 * 存在しない場合、独自例外をThrowする。
	 * @param form
	 * @throws KPTException
	 */
	public void login(LoginForm form) throws KPTException {
		
		//TODO new する時点でAPIKEYをわたす
		BacklogAPI backlogAPI = new BacklogAPI();
		if(!backlogAPI.projectExistCheck(form)) {
			throw new KPTException("認証エラー");
		}
		session.setAttribute("loginForm", form);
		
	}

}
