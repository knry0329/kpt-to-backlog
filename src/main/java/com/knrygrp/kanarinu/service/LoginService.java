package com.knrygrp.kanarinu.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.BacklogAPI;
import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.Project;

@Service
public class LoginService {
		
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
		BacklogClient blc = backlogAPI.getBacklogClient(form.getApikey(), form.getSpacekey());
		Project project = backlogAPI.getProject(blc, form.getProjectkey());
		if(project == null) {
			throw new KPTException("認証エラー");
		}
		session.setAttribute("loginForm", form);
		
	}

}
