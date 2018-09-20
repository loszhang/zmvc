package demo.role;

import cn.zaichi.zmvc.aop.Before;
import cn.zaichi.zmvc.core.Controller;
import demo.interceptor.LoginInterceptor;

public class RoleController extends Controller {
	
	@Before(LoginInterceptor.class)
	public void index() {
		
	}
}
