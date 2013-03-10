package controllers;

import java.util.List;

import models.Critical;
import models.Movie;
import models.User;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Criticals extends Controller{

	public static void addCritical(String user, String movie,  @Required(message = "Critique vide !")String content, String title){
		
		if(validation.hasErrors()){
			Application.search(movie);
		}
		User usr = User.find("byEmail", user).first();
		Movie mvie = Movie.find("byTitle", movie).first();
		Critical newCritical = new Critical(title, content, mvie, usr);
		newCritical.save();
		flash.success("Merci pour votre contribution !");
		Application.search(movie);
	}
	
	public static void myCriticals(){
		User user = User.find("byEmail", session.get("username")).first();
		List<Critical> criticals = Critical.find("byUser_id", user.id).fetch();
		
		render("Criticals/myCriticals.html", criticals);
	}
	
	public static void delete(long idCritical){
		Critical crtlSup = Critical.findById(idCritical);
		crtlSup.delete();
		myCriticals();
	}
}
