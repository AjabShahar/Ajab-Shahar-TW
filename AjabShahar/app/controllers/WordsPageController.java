package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.WordsPage;

public class WordsPageController extends Controller {

  public static Result getWordsPage(){
    return ok(WordsPage.render());
  }
  
}