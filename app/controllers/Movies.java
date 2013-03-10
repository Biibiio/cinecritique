package controllers;

import java.util.Date;

import models.Actor;
import models.Movie;
import models.Producer;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Movies extends Controller {

	public static void CreateMovie(
			@MaxSize(128) @Required(message = "Veuillez renseigner le titre du film.") String Title,
			@MaxSize(1024) @Required(message = "Veuillez renseigner le synopsis.") String Synopsis,
			@Required(message = "Veuillez renseigner la date de sortie.") Date date,
			@MaxSize(64) @Required(message = "Veuillez renseigner le nom de l'acteur principal.") String ActorName,
			@MaxSize(32) @Required(message = "Veuillez renseigner le prénom de l'acteur principal.") String ActorFirstName,
			@Required(message = "Veuillez renseigner le pays d'origine du film.") String country,
			@MaxSize(64) @Required(message = "Veuillez renseigner nom du producteur.") String ProducerName,
			@MaxSize(32) @Required(message = "Veuillez renseigner le prénom du producteur.") String ProducerFirstName) {

		Movie movieCheck = Movie.find("byTitle", Title).first();

		if (movieCheck != null) {
			validation.addError(Title, "Ce film existe déjà.");
			ShowCreateMovie();
		}

		Actor actorData;
		Actor actorCheck = Actor.find("byFirstNameAndName", ActorFirstName, ActorName)
				.first();
		if (actorCheck != null) {
			actorData = actorCheck;
		} else {
			actorData = new Actor(ActorName, ActorFirstName);
			actorData.save();
		}

		Producer producerData;
		Producer producerCheck = Producer.find("byFirstNameAndName", ProducerFirstName,
				ProducerName).first();

		if (producerCheck != null) {
			producerData = producerCheck;
		} else {
			producerData = new Producer(ProducerName, ProducerFirstName);
			producerData.save();
		}

		Movie newMovie = new Movie(Title, Synopsis, date, actorData,
				country, producerData);
		newMovie.save();
		flash.success("Merci pour votre contribution ! ");

		ShowCreateMovie();

	}
	
	public static void ShowCreateMovie() {

		render("Movies/CreateMovie.html");
	}
}
