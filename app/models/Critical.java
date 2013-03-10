package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Critical extends Model{
	
	@MaxSize(128)
	@Required
	public String Title;
	
	@Lob
	@Required
	@MaxSize(10000)
	public String Content; 
	
	@Required
	public Date Date; 
	
	@Required
	@ManyToOne
	public Movie Movie;
	
	@Required
	@ManyToOne
	public User User;
	
	public Critical(String title, String content, Movie movie, User user){

		this.Title = title;
		this.Content = content;
		this.Date = new Date();
		this.Movie = movie;
		this.User = user;
		
	}
}
