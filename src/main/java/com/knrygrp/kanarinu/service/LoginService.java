package com.knrygrp.kanarinu.service;

import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;

public interface LoginService {
	/**
	 * APIKEY、スペースキー、プロジェクトキーをもとに存在チェックを実施する。
	 * 存在しない場合、独自例外をThrowする。
	 * @param form
	 * @throws KPTException
	 */
	public void login(LoginForm form) throws KPTException;
}
