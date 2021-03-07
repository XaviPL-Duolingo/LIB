package com.duolingo.app.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.duolingo.app.interfaces.impl.*;
import com.duolingo.app.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

	public static void main(String[] args) {

		UserImpl userList = new UserImpl();
		User userData = userList.getUserData("Buholingo");
		System.out.println(userData.getUserItems());


	}

}
