package com.duolingo.app.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "users_items")
public class User_Item {
	
	@Column(name = "idUser")
	private User idUser;
	
	@Column(name = "idItem")
	private Item idItem;
	
	public User_Item() {}

	public User_Item(User idUser, Item idItem) {
		super();
		this.idUser = idUser;
		this.idItem = idItem;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Item getIdItem() {
		return idItem;
	}

	public void setIdItem(Item idItem) {
		this.idItem = idItem;
	}

}
