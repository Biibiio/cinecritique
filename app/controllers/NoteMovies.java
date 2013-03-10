package controllers;

import java.util.List;

import models.Critical;
import models.Movie;
import models.NoteMovie;
import models.User;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class NoteMovies extends Controller{

	public static void addNote(@Min(0) @Max(20) @Required int note, String user, String movie){
		
		if(validation.hasErrors()){
			Application.search(movie);
		}
		User usr = User.find("byEmail", user).first();
		Movie mvie = Movie.find("byTitle", movie).first();
		NoteMovie newNote = new NoteMovie(note, usr, mvie);
		newNote.save();
		flash.success("Merci pour votre contribution !");
		Application.search(movie);
	}
	
	public static void myNotes(){
		User user = User.find("byEmail", session.get("username")).first();
		List<NoteMovie> notes = NoteMovie.find("byUser_id", user.id).fetch();
		
		render("NoteMovies/myNotes.html", notes);
	}
	
	public static void delete(long idNote){
		NoteMovie noteSup = NoteMovie.findById(idNote);
		noteSup.delete();
		myNotes();
	}
	
	public static double moyenne(Movie movie){
		List<NoteMovie> lstNote = NoteMovie.find("byMovie_id", movie).fetch();
		
		if(lstNote.size() > 0){
			double sum = 0;
			for(int i = 0; i<lstNote.size(); i++){
				sum += (double)lstNote.get(i).Note;
			}
			return sum/lstNote.size();
		}
		return 21;
	}
}
