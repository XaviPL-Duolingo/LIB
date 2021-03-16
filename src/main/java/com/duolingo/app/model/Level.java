package com.duolingo.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "levels")
public class Level implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLevel")
	private int idLevel;
	
	@ManyToOne()
	@JoinColumn(name = "idCategory")
	private Category idCategory;
	
	@Column(name = "isDone")
	private boolean isDone;

	@Column(name = "codeLevel")
	private String codeLevel;
	
	@OneToMany(mappedBy = "idLevel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Exercice> exercices;

	@ManyToMany(mappedBy = "userItems")
	private Set<User> itemsUser = new HashSet<User>();
	
	public Level () {}

	public Level(int idLevel, Category idCategory, boolean isDone, String codeLevel) {
		super();
		this.idLevel = idLevel;
		this.idCategory = idCategory;
		this.isDone = isDone;
		this.codeLevel = codeLevel;
	}

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public Category getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Category idCategory) {
		this.idCategory = idCategory;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public List<Exercice> getExercices() {
		return exercices;
	}

	public void setExercices(List<Exercice> exercices) {
		this.exercices = exercices;
	}

	public Set<User> getItemsUser() {
		return itemsUser;
	}

	public void setItemsUser(Set<User> itemsUser) {
		this.itemsUser = itemsUser;
	}

	@Override
	public String toString() {
		return codeLevel;
	}
}
