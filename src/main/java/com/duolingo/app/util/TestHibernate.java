package com.duolingo.app.util;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.duolingo.app.interfaces.impl.*;
import com.duolingo.app.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

	public static void main(String[] args) {

		UserImpl userManager = new UserImpl();
		User userObj = userManager.getUserByID(9);
		userObj.setMoney(1500);
		userManager.updateUser(userObj);

	}

}
