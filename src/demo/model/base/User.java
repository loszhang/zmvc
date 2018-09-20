package demo.model.base;

import cn.zaichi.zmvc.plugin.activerecord.Model;

public class User extends Model<User>{
	public static final User dao = new User().dao;
	
}
