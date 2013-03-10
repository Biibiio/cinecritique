package controllers;

import play.cache.Cache;
import play.data.validation.*;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.*;
import java.util.*;

import models.*;

public class Application extends Controller {

	public static void Inscription(
			@MaxSize(64) @MinSize(2) @Required(message = "Le Nom doit être renseigné.") String name,
			@MaxSize(32) @MinSize(2) @Required(message = "Le Prénom doit être renseigné.") String firstname,
			@Email @Required(message = "L'email doit être renseigné.") String email,
			@Required(message = "La date de naissance doit être renseigné.") String date,
			@MinSize(6) @MaxSize(32) @Required(message = "Le mot de passe doit être renseigné.") String password,
			@Required(message = "La confirmation du mot de passe doit être renseigné.") String confirmePassword,
			@Required(message = "Veuillez entrer le code") String code,
			String randomID) {

		validation.equals(code, Cache.get(randomID)).message(
				"Code invalide. Veuillez le retapez");
		validation.equals(password, confirmePassword).message(
				"La confirmation du mot de passe ne correspond pas.");

		User user = User.find("byEmail", email).first();
		if (user != null)
			validation
					.addError(email, "Vous êtes déja inscrit sur notre site.");

		if (validation.hasErrors())
			render("Secure/login.html", randomID);

		User newUser = new User(name, firstname, email, password, date);
		newUser.save();
		flash.success("Merci pour votre inscription ! %s", firstname);

		Cache.delete(randomID);
		try {
			Secure.login();
		} catch (Throwable e) {
			render("Secure/login.html");
		}
	}

	public static void search(
			@MaxSize(128) @Required(message = "Le Titre est inexistante ou trop long.") String keywords) {

		Movie movie = Movie.find("byTitle", keywords).first();

		if (movie != null) {
			
			double noteMoyMovie = NoteMovies.moyenne(movie);
			
			if (session.contains("username")) {
				User me = User.find("byEmail", session.get("username")).first();
				Critical critical = Critical.find("byUser_idAndMovie_id", me.id, movie.id).first();
				NoteMovie note = NoteMovie.find("byUser_idAndMovie_id", me.id, movie.id).first();
				List<Critical> criticals = Critical
						.find("select c from Critical c where c.User != :id and c.Movie = :movie")
						.bind("id", me).bind("movie", movie).fetch();
				render("Application/search.html", movie, critical,
						criticals, me, note, noteMoyMovie);
			}
			else{
				List<Critical> criticals = Critical.find("byMovie", movie).fetch();
				render("Application/search.html", movie, criticals, noteMoyMovie);
			}
		}

		render("Application/search.html", movie);
	}
}