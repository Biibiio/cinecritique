package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class NoteCritical extends Model {

	@Min(0)
	@Max(10)
	@Required
 	public int Note; // Note Critical Between 0 and 10
	
	@Required
	@OneToOne
	public User User; //User Note Critical
	 
	@Required
	@OneToOne
	public Critical Critical; //Critical Note Critical
	
	public NoteCritical(int note, User user, Critical critical) {
		this.Note = note;
		this.User = user;
		this.Critical = critical;
	}
}
