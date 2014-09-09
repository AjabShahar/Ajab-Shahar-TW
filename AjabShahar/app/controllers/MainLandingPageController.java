package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.MainNavigationPage;

public class MainLandingPageController extends Controller {

  public static Result getLandingPage(){
    return ok(MainNavigationPage.render());
  }
  
}