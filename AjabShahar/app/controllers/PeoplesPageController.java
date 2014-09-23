package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.PeoplesPage;

public class PeoplesPageController extends Controller {

  public static Result getPeoplesPage(){
    return ok(PeoplesPage.render());
  }
  
}