package demo.config;

import cn.zaichi.zmvc.config.Routes;
import demo.user.UserController;

public class AdminRouter extends Routes{

	@Override
	public void config() {
		add("user", UserController.class);
	}

}
