package com.knrygrp.kanarinu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knrygrp.kanarinu.api.ProjectAPI;
import com.knrygrp.kanarinu.constants.KTBConstants;
import com.knrygrp.kanarinu.exception.KPTException;
import com.knrygrp.kanarinu.form.LoginForm;
import com.knrygrp.kanarinu.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
		
	@Autowired
	ProjectAPI projectAPI;

	/**
	 * {@inheritDoc}
	 */
	public void login(LoginForm form) throws KPTException {
		if(!projectAPI.projectExistCheck(form)) {
			throw new KPTException(KTBConstants.MSG_SIGNIN_FAILED);
		}
	}

}
