package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.LevelImpl;
import com.duolingo.app.interfaces.impl.UserImpl;
import com.duolingo.app.model.Level;
import com.duolingo.app.model.User;

public class TestHibernate {

	public static void main(String[] args) {

		LevelImpl levelManager = new LevelImpl();
		Level levelObj = levelManager.getUserNextLevel(1, 15);
		System.out.println(levelObj.toString());

		UserImpl userManager = new UserImpl();
		User isDone = userManager.completeLevel(1, 14);

		levelObj = levelManager.getUserNextLevel(1, 15);
		System.out.println(levelObj.toString());

	}

}
