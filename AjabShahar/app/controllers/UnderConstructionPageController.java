package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.UnderConstructionPage;

public class UnderConstructionPageController extends Controller {

  public static Result getUnderConstructionPage(){
    return ok(UnderConstructionPage.render());
  }
  
}