package com.duolingo.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "levels")
public class Level {
	
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

	@Override
	public String toString() {
		return codeLevel;
	}
}
