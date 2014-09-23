package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.SongsPage;

public class SongsPageController extends Controller {

  public static Result getSongsPage(){
    return ok(SongsPage.render());
  }
  
}