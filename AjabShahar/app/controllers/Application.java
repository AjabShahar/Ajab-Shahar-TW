package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.Index;

public class Application extends Controller {

  public static Result index(){
    return ok(Index.render());
  }
  
}