package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Comment extends Model{

	@Required
	@ManyToOne
	public User User; //Id User who has Comment
	
	@Required
	public Date Date; //Date Post Comment
	
	@MaxSize(512)
	@Required
	public String Content; //Content of the Comment  
	 	
	@Required
	@ManyToOne
	public Critical Critical; //Id Critical that has been Comment
	
	public Comment(User user, String content, Critical critical){
		this.User = user;
		this.Date = new Date();
		this.Content = content;
		this.Critical = critical;	
	}
}

