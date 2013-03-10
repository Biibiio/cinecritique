package controllers;

import models.User;
import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;


public class Users extends Controller {

	 @Before
	    static void setConnectedUser() {
	        if(Security.isConnected()) {
	            User user = User.find("byEmail", Security.connected()).first();
	            renderArgs.put("user", user.Firstname);
	        }
	    }

	public static void index() {
		render();
	}
}
