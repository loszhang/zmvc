package demo.login;

import cn.zaichi.zmvc.core.Controller;
import demo.model.base.User;
import demo.user.UserService;

public class LoginController extends Controller{
	public static final UserService userService = UserService.me;
	
	public void index() {
		render("login.html");
	}
	public void toLogin() {
		User user = userService.getUser(getModel(User.class));
		if (user == null) {
			setAttr("errMsg", "用户名或密码错误");
			render("login.html");
			//redirect("/login", true);
		}else {
			getSession().setAttribute("user", user);
			setAttr("user", user);
			redirect("/account/");
		}
		
	}
	
	public void logout() {
		render("login.html");
	}
	
	
	

}
