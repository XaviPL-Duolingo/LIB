package com.duolingo.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "languages")
public class Language implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLanguage")
	private int idLanguage;

	@Column(name = "nameLanguage")
	private String nameLanguage;
	
	@Column(name = "codeLanguage")
	private String codeLanguage;
	
	@OneToMany(mappedBy = "idOriginLang", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "idOriginLang", referencedColumnName="idLanguage"),
		@JoinColumn(name = "idDestLang", referencedColumnName="idLanguage")
	})
	private List<Course> courses;

	public Language() {}
	
	public Language(int idLanguage, String nameLanguage, String codeLanguage) {
		super();
		this.idLanguage = idLanguage;
		this.nameLanguage = nameLanguage;
		this.codeLanguage = codeLanguage;
	}

	public int getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getNameLanguage() {
		return nameLanguage;
	}

	public void setNameLanguage(String nameLanguage) {
		this.nameLanguage = nameLanguage;
	}

	public String getCodeLanguage() {
		return codeLanguage;
	}

	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	@Override
	public String toString() {
		return nameLanguage.toUpperCase();
	}
}
