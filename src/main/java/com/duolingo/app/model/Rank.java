package com.duolingo.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ranks")
public class Rank implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRank")
	private int idRank;
	
	@Column(name = "nameRank")
	private String nameRank;
	
	@Column(name = "eloRank")
	private int eloRank;
	
	@OneToMany(mappedBy = "idRank", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users;
	
	public Rank() {}

	public Rank(int idRank, String nameRank, int eloRank) {
		super();
		this.idRank = idRank;
		this.nameRank = nameRank;
		this.eloRank = eloRank;
	}

	public int getIdRank() {
		return idRank;
	}

	public void setIdRank(int idRank) {
		this.idRank = idRank;
	}

	public String getNameRank() {
		return nameRank;
	}

	public void setNameRank(String nameRank) {
		this.nameRank = nameRank;
	}

	public int getEloRank() {
		return eloRank;
	}

	public void setEloRank(int eloRank) {
		this.eloRank = eloRank;
	}
	
	
	
}
