package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class NoteMovie extends Model implements Comparable{

	@Min(0)
	@Max(20)
	@Required
 	public int Note; // Note Critical Between 0 and 20
	
	@Required
	@OneToOne
	public User User; //User Note Movie
	
	@Required
	@OneToOne
	public Movie Movie;  //Movie Note Movie
	
	public NoteMovie(int note, User user, Movie movie) {
		this.Note = note;
		this.User = user;
		this.Movie = movie;	
	}
	
	 public int compareTo(Object other) { 
	      int nombre1 = ((NoteMovie) other).Note; 
	      int nombre2 = this.Note; 
	      if (nombre1 > nombre2)  return -1; 
	      else if(nombre1 == nombre2) return 0; 
	      else return 1; 
	   } 

}
