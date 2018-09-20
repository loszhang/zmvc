package demo.user;

import cn.zaichi.zmvc.core.Controller;
import cn.zaichi.zmvc.validate.Validator;

public class UserValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("user.username", "namemsg", "请输入姓名");
		validateRequiredString("user.password", "passwdmsg", "请输入密码");
		
	}

	@Override
	protected void handleError(Controller c) {
		c.render("addUser.html");
		
		
	}

}
