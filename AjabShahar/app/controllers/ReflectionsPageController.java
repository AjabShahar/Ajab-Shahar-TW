package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.ReflectionsPage;

public class ReflectionsPageController extends Controller {

  public static Result getReflectionsPage(){
    return ok(ReflectionsPage.render());
  }
  
}