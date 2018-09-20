package demo.model.base;

import cn.zaichi.zmvc.plugin.activerecord.Model;

public class Account extends Model<Account>{
	public static final Account dao = new Account().dao;
	
}
