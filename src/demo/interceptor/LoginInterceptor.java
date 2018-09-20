package demo.interceptor;

import cn.zaichi.zmvc.aop.Interceptor;
import cn.zaichi.zmvc.aop.Invocation;
import cn.zaichi.zmvc.core.Controller;
import cn.zaichi.zmvc.render.Render;
import demo.model.base.User;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		System.out.println(LoginInterceptor.class + "类级拦截器 -- start --");
		System.out.println("拦截方法："+ inv.getMethod().getName());
		User user = (User) inv.getController().getSession().getAttribute("user");
		if (user != null) {
			inv.invoke();
		}else {
			Controller controller = inv.getController();
			controller.render("/login/login.html");
		}
		System.out.println("类级拦截器 -- end --");
		
	}

}
