package demo.account;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import cn.zaichi.zmvc.aop.Before;
import cn.zaichi.zmvc.plugin.activerecord.Db;
import cn.zaichi.zmvc.plugin.activerecord.IAtom;
import cn.zaichi.zmvc.plugin.activerecord.Record;
import cn.zaichi.zmvc.plugin.activerecord.tx.Tx;

import demo.model.base.Account;
import demo.model.base.User;



public class AccountService {

	public static final AccountService me=new AccountService();

	private User dao = new User();
	private Account accountDao = new Account();
	
	public Record getUserAccount(Account account) {
		return Db.findFirst("select u.id,a.account_id ,u.username,u.age,IFNULL(a.account,0) account from t_user u  left join t_account a on u.id = a.user_id where u.id= ? and a.account_id=?", account.get("user_id"), account.get("account_id"));
	}
	
	public Record getUserAccountById(Integer id) {
		return Db.findFirst("select u.id,a.account_id ,u.username,u.age,IFNULL(a.account,0) account from t_user u  left join t_account a on u.id = a.user_id where u.id= ?", id);
	}
	public List<Record> getUsersAccount(User user) {
		return Db.find("select u.id, a.account_id,u.username,u.age,IFNULL(a.account,0) account from t_user u  right join t_account a on u.id = a.user_id where u.id= ?", user.getInt("id"));
	}
	public List<Record> getUsersAndAccount() {
		return Db.find("select u.id,u.username,u.age,IFNULL(a.account,0) account from t_user u  left join t_account a on u.id = a.user_id");
	}
	
	public void openAccount(Account account) {
		account.save();
	}
	
	@Before(Tx.class)
	public boolean recharge(final Account account) {
		Account userAccount =accountDao.find("select a.id,u.id user_id,a.account_id ,u.username,u.age,IFNULL(a.account,0) account from t_user u  left join t_account a on u.id = a.user_id where u.id= ? and a.account_id=?",account.get("user_id"),account.get("account_id")).get(0);
		Double beAccountDouble = userAccount.get("account");
		Double accDouble = Double.valueOf((String) account.get("account"));
		return userAccount.set("account", String.valueOf(beAccountDouble + accDouble)).update();
	}
	
	
	public List<Record> exportFor() {
		return Db.find("select * from t_user");
	}
	
}
