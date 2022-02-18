package com.bnamericas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;



@Entity
@Table(name = "data")
public class Data {
// entidad encargada de generar la tabla de base de datos	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(name= "date")
	private Date date;
	
	@Column(name= "title")
	private String title; 
	
	@Column(name= "extra")
    private String extra;
	
    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getExtra() {
		return extra;
	}


	public void setExtra(String extra) {
		this.extra = extra;
	}


	public Data(long id, Date date, String title, String extra) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.extra = extra;
	}


	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}



			

}
