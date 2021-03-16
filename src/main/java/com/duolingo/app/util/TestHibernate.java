package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.UserImpl;
import com.duolingo.app.model.User;

public class TestHibernate {

	public static void main(String[] args) {

		UserImpl userManager = new UserImpl();
		User userObj = userManager.getUserByID(9);
		userObj.setMoney(1500);
		userManager.updateUser(userObj);

	}

}
