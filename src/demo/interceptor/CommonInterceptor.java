package demo.interceptor;

import cn.zaichi.zmvc.aop.Interceptor;
import cn.zaichi.zmvc.aop.Invocation;


public class CommonInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("全局拦截器 -- start --");
		inv.invoke();
		System.out.println("全局拦截器 -- end --");
	}

}
