package controllers;

import creamy.annotation.Bind;
import creamy.mvc.Controller;
import creamy.mvc.Result;
import models.User;

public class ApplicationController extends Controller {
    public Result login() {
        return ok(this);
    }
    
    public Result authenticate(@Bind("email") String email, @Bind("password") String password) {
        if (User.authenticate(email, password) != null) {
            getContinualData().put("email", email);
            return redirect("/ProjectController/index");
        } else {
            getApplicationData().put("errormessage", "Invalid user or password");
            return redirect("/ApplicationController/login");
        }
    }
    
    public Result logout() {
        getContinualData().clear();
        getApplicationData().put("message", "You've been logged out");
        return redirect("/ApplicationController/login");
    }
}
