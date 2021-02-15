package com.duolingo.app.model;

import javax.persistence.*;

@Entity
@Table(name = "exercices")
public class Exercice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idExercice")
	private int idExercice;
	
	@ManyToOne()
	@JoinColumn(name = "idLevel")
	private Level idLevel;
	
	@ManyToOne()
	@JoinColumn(name = "idTypeExercice")
	private TypeExercice idTypeExercice;

	@Column(name = "contentExercice")
	private String contentExercice;
	
	@Column(name = "isHard")
	private boolean isHard;
	
	public Exercice () {}

	public Exercice(int idExercice, Level idLevel, TypeExercice idTypeExercice, String contentExercice, boolean isHard) {
		super();
		this.idExercice = idExercice;
		this.idLevel = idLevel;
		this.idTypeExercice = idTypeExercice;
		this.contentExercice = contentExercice;
		this.isHard = isHard;
	}

	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

	public Level getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(Level idLevel) {
		this.idLevel = idLevel;
	}

	public TypeExercice getIdTypeExercice() {
		return idTypeExercice;
	}

	public void setIdTypeExercice(TypeExercice idTypeExercice) {
		this.idTypeExercice = idTypeExercice;
	}


	public boolean isHard() {
		return isHard;
	}

	public String getContentExercice() {
		return contentExercice;
	}

	public void setContentExercice(String contentExercice) {
		this.contentExercice = contentExercice;
	}

	public void setHard(boolean isHard) {
		this.isHard = isHard;
	}

	@Override
	public String toString() {
		return "Ex: ["+idExercice+"] // TypeEx: ["+idTypeExercice.getNameTypeExercice()+"]";
	}
}
