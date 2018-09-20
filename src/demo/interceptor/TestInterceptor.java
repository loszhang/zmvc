package demo.interceptor;

import cn.zaichi.zmvc.aop.Interceptor;
import cn.zaichi.zmvc.aop.Invocation;

public class TestInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("方法拦截器 -- start --");
		inv.invoke();
		System.out.println("方法拦截器 -- end --");

	}

}
