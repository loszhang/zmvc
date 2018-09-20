package demo.user;

import java.util.List;

import cn.zaichi.zmvc.plugin.activerecord.Db;
import cn.zaichi.zmvc.plugin.activerecord.Record;

import demo.model.base.User;



public class UserService {

	public static final UserService me=new UserService();

	private User dao = new User();
	
	public void addUser(User user) {
		user.save();
	}
	
	public User getUser(User user) {
		return dao.findFirst("select * from t_user where username= ? and password = ?",user.get("username"),
				user.get("password"));
	}
	
	public User getUserById(Integer id) {
		return dao.findById(id);
	}
	
	public List<User> getUsers() {
		return dao.find("select * from t_user");
	}
	
	public List<Record> getUsersAndAccount() {
		return Db.find("select u.id,u.username,u.age,IFNULL(a.account,0) account from t_user u  left join t_account a on u.id = a.user_id");
	}
	
	public boolean updateById(User user) {
		return user.update();
	}
	
	public boolean deleteById(Integer id) {
		return dao.deleteById(id);
	}
	
	public List<Record> exportFor() {
		return Db.find("select * from t_user");
	}
	
}
