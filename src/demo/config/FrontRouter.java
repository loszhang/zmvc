package demo.config;

import cn.zaichi.zmvc.config.Routes;
import demo.TFile.TFileController;
import demo.account.AccountController;
import demo.login.LoginController;
import demo.user.UserController;

public class FrontRouter extends Routes{

	@Override
	public void config() {
		add("account", AccountController.class);
		add("login", LoginController.class);
		add("file",TFileController.class);
	}

}
