package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.LevelImpl;
import com.duolingo.app.interfaces.impl.UserImpl;
import com.duolingo.app.model.Level;
import com.duolingo.app.model.User;

public class TestHibernate {

	public static void main(String[] args) {

		UserImpl userManager = new UserImpl();
		System.out.println(userManager.getUserProgressOnCategory(2, 15));
	}

}
