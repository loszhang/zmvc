package demo.account;

import java.util.List;
import java.util.UUID;

import cn.zaichi.zmvc.aop.Before;
import cn.zaichi.zmvc.core.Controller;
import cn.zaichi.zmvc.plugin.activerecord.Record;
import cn.zaichi.zmvc.plugin.activerecord.tx.Tx;
import demo.interceptor.LoginInterceptor;
import demo.model.base.Account;
import demo.model.base.User;

public class AccountController extends Controller {
	
	private static final AccountService accountService = AccountService.me;
	@Before(LoginInterceptor.class)
	public void index() {
		User user = (User) getSession().getAttribute("user");
		List<Record> record = accountService.getUsersAccount(user);
		setAttr("userId", user.get("id"));
		setAttr("useraccount", record);
		render("account.html");
	}
	
	@Before(LoginInterceptor.class)
	public void account() {
		User user = (User) getSession().getAttribute("user");
		List<Record> record = accountService.getUsersAccount(user);
		setAttr("userId", user.get("id"));
		setAttr("useraccount", record);
		redirect("account.html");
	}
	
	public void walletIndex() {
		Integer userId = getParaToInt(0);
		String accountId = getPara(1);
		Account account = new Account();
		account.set("user_id", userId);
		account.set("account_id", accountId);
		Record record = accountService.getUserAccount(account);
		setAttr("userAccount", record);
		render("recharge.html");
	}
	public void recharge() {
		/*String userId = getPara(0);
		String accountIdString = getPara(1);
		String txnamt = getPara(2);*/
		String userId = getPara("userId");
		String accountIdString = getPara("account_id");
		String txnamt = getPara("account");
		Account account = new Account();
		account.set("user_id", userId);
		account.set("account_id", accountIdString);
		account.set("account", txnamt);
		boolean result = accountService.recharge(account);
		String msg= "";
		if (result) {
			msg = "充值成功";
		}else {
			msg ="充值失败";
		}
		setAttr("msg", msg);
		render("recharge_success.html");
	
	}
	
	public void openAccount() {
		String userId = getPara(0);
		Account account = new Account();
		account.set("user_id", userId);
		account.set("account_id", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 17));
		accountService.openAccount(account);
		redirect("/account/");
	}
	
	@Before(Tx.class)
	public void consume() {
		
	}
}
