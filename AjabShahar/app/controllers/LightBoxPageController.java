package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.LightBoxPage;

public class LightBoxPageController extends Controller {

    public static Result getLightBoxPage(){
        return ok(LightBoxPage.render());
    }

}