package demo.config;

import cn.zaichi.zmvc.config.Constants;
import cn.zaichi.zmvc.config.Handlers;
import cn.zaichi.zmvc.config.Interceptors;
import cn.zaichi.zmvc.config.Plugins;
import cn.zaichi.zmvc.config.Routes;
import cn.zaichi.zmvc.config.ZmvcConfig;
import cn.zaichi.zmvc.ext.handler.ContextPathHandler;
import cn.zaichi.zmvc.kit.PropKit;
import cn.zaichi.zmvc.plugin.activerecord.ActiveRecordPlugin;
import cn.zaichi.zmvc.plugin.activerecord.CaseInsensitiveContainerFactory;
import cn.zaichi.zmvc.plugin.druid.DruidPlugin;
import cn.zaichi.zmvc.render.ViewType;
import demo.interceptor.CommonInterceptor;
import demo.interceptor.LoginInterceptor;

public class DemoConfig extends ZmvcConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.FREE_MARKER);
		//me.setBaseUploadPath("D:\\upload");
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new AdminRouter());
		me.add(new FrontRouter());
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.use("config.properties").get("dp.url"),
				PropKit.get("dp.username"), PropKit.get("dp.password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin dPlugin = createDruidPlugin();
		me.add(dPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dPlugin);
		arp.setShowSql(true);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx"));
	}

}
