package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.mapping.Collection;

import models.Critical;
import models.NoteMovie;
import models.Movie;
import models.Ranking;
import models.User;


import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.With;



@With(Secure.class)
public class Top extends Controller{

	
	
	public static void TopFilm(){
	
		Query query = JPA.em().createQuery("SELECT n FROM NoteMovie AS n");
		
		
		List<NoteMovie> topFilm = query.getResultList();
		List<NoteMovie> topFilm2 = new ArrayList<NoteMovie>();
		
				
		if(topFilm.size() > 0){
			for(int i = 0; i<topFilm.size(); i++){
				if(topFilm2.size() == 0)
					topFilm2.add(new NoteMovie((int) NoteMovies.moyenne(topFilm.get(i).Movie), new User("1", "2", "3", "4", "5") , topFilm.get(i).Movie));
				else{
					boolean deja = false;
					for(int j=0; j<topFilm2.size(); j++){
						if(topFilm2.get(j).Movie == topFilm.get(i).Movie)
							deja = true;
					}
					if(!deja)
						topFilm2.add(new NoteMovie((int) NoteMovies.moyenne(topFilm.get(i).Movie), new User("1", "2", "3", "4", "5") , topFilm.get(i).Movie));
				}	
			}
		}
		Collections.sort(topFilm2, Collections.reverseOrder());
		
		render("Top/TopFilm.html", topFilm2);
	}
	
	
	public static void TopUser(){
		
		Query query = JPA.em().createQuery("SELECT n FROM NoteMovie AS n");
		Query query2 = JPA.em().createQuery("SELECT c FROM Critical AS c");
		
		List<NoteMovie> topUser = query.getResultList();
		List<Critical> topUser2 = query2.getResultList();
		List<Ranking> topUser3 = new ArrayList<Ranking>();
		
		
			for(int i = 0; i<topUser.size(); i++){
				if(topUser3.size() == 0)
					topUser3.add(new Ranking(topUser.get(i).User, 1));
				else{
					boolean deja = false;
					for(int j = 0; j<topUser3.size(); j++){
						if(topUser3.get(j).getUser() == topUser.get(i).User){
							topUser3.set(j, new Ranking(topUser.get(i).User, topUser3.get(j).getPts() + 1));
							deja = true;
						}
					}
					if(!deja){
						topUser3.add(new Ranking(topUser.get(i).User, 1));
					}
				}
			}
			
			System.out.println(topUser3);
			
			for(int i = 0; i<topUser2.size(); i++){
				if(topUser3.size() == 0)
					topUser3.add(new Ranking(topUser2.get(i).User, 2));
				else{
					boolean deja = false;
					for(int j = 0; j<topUser3.size(); j++){
						if(topUser3.get(j).getUser() == topUser2.get(i).User){
							topUser3.set(j, new Ranking(topUser2.get(i).User, topUser3.get(j).getPts() + 2));
							deja = true;
						}
					}
					if(!deja){
						topUser3.add(new Ranking(topUser2.get(i).User, 2));
					}
				}
			}
			
			
		
		Collections.sort(topUser3, Collections.reverseOrder());
		
			
		render("Top/TopUser.html", topUser3);
	
	}
	
	
}

