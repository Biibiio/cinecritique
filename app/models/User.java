package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model{
	
	@MaxSize(64)
	@Required
	public String Name; 
	
	@MaxSize(32)
	@Required
	public String Firstname; 
	
	@MaxSize(128)
	@Email
    @Required
	public String Email;
	
	@MinSize(6)
	@MaxSize(32)
	@Required
	public String Password;
	
	@Required
	public String Date;
	
	public boolean isAdmin;
	
	public User( String Name, String Firstname, String Email, String Password, String date){
		this.Name = Name;
		this.Firstname = Firstname;
		this.Email = Email;
		this.Password = Password;
		this.Date = date;
		this.isAdmin = false;
	}
 
	public static User connect(String Email, String Password) {
	    return find("byEmailAndPassword", Email, Password).first();
	}
	
	public void setAdminTrue(User user){
		user.isAdmin= true;
	}
	
	public String toString() {
	    return Email;
	}
}
