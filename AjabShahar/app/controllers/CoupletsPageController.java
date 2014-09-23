package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.CoupletsPage;

public class CoupletsPageController extends Controller {

  public static Result getCoupletsPage(){
    return ok(CoupletsPage.render());
  }
  
}