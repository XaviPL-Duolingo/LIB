package com.duolingo.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCourse")
	private int idCourse;
	
	@ManyToOne()
	@JoinColumn(name = "idOriginLang")
	private Language idOriginLang;
	
	@ManyToOne()
	@JoinColumn(name = "idDestLang")
	private Language idDestLang;
	
	@OneToMany(mappedBy = "idCourse", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Category> categories;
	
	public Course () {}

	public Course(int idCourse, Language idOriginLang, Language idDestLang) {
		super();
		this.idCourse = idCourse;
		this.idOriginLang = idOriginLang;
		this.idDestLang = idDestLang;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public Language getIdOriginLang() {
		return idOriginLang;
	}

	public void setIdOriginLang(Language idOriginLang) {
		this.idOriginLang = idOriginLang;
	}

	public Language getIdDestLang() {
		return idDestLang;
	}

	public void setIdDestLang(Language idDestLang) {
		this.idDestLang = idDestLang;
	}

	@Override
	public String toString() {
		return idOriginLang.getNameLanguage() + " → " + idDestLang.getNameLanguage();
	}
}
