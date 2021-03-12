package com.duolingo.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItem")
	private int idItem;
	
	@Column(name = "nameItem")
	private String nameItem;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priceItem")
	private int priceItem;

	@ManyToMany(mappedBy = "userLevels")
	private Set<User> itemsUser = new HashSet<User>();
	
	public Item() {}

	public Item(int idItem, String nameItem, String description, int priceItem) {
		super();
		this.idItem = idItem;
		this.nameItem = nameItem;
		this.description = description;
		this.priceItem = priceItem;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(int priceItem) {
		this.priceItem = priceItem;
	}

	public Set<User> getItemsUser() {
		return itemsUser;
	}

	public void setItemsUser(Set<User> itemsUser) {
		this.itemsUser = itemsUser;
	}

	@Override
	public String toString() {
		return nameItem;
	}
}
