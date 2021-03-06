package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Movie extends Model{

	@MaxSize(128)
	@Required
	public String Title; 
	
	@Lob
	@Required
	@MaxSize(10000)
	public String Synopsis; 
	
	@Required
	public Date Date; 
	
	@Required
	@ManyToOne
	public Actor Actor;  
	
	@Required
	public String Country;
	
	@Required
	@ManyToOne
	public Producer Producer;
	
	public Movie(String title, String synopsis, Date date, Actor actor, String country, Producer producer){
		this.Title = title;
		this.Synopsis = synopsis;
		this.Date = date;
		this.Country = country;
		this.Actor = actor;
		this.Producer = producer;
	}
	
	public String toString(){
		return Title;
	}
	 	
}
