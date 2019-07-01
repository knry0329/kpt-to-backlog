package com.knrygrp.kanarinu.service;

import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;

public interface LoginService {
	public void login(LoginForm form) throws KPTException;
}
