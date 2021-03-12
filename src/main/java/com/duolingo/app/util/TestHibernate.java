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

		LevelImpl levelManager = new LevelImpl();
		// Level level = levelManager.getUserNextLevel(1, )

	}

}
