package demo.user;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.zaichi.zmvc.aop.Before;
import cn.zaichi.zmvc.core.Controller;
import cn.zaichi.zmvc.json.Json;
import cn.zaichi.zmvc.kit.PathKit;
import cn.zaichi.zmvc.plugin.activerecord.Db;
import cn.zaichi.zmvc.plugin.activerecord.Model;
import cn.zaichi.zmvc.plugin.activerecord.Record;
import cn.zaichi.zmvc.upload.UploadFile;
import demo.model.base.User;
import demo.utils.excel.ExcelUtils;

public class UserController extends Controller{
	public static final UserService userService = UserService.me;
	
	public void index() {
		List<User> usersList = userService.getUsers();
		List<Record> records = userService.getUsersAndAccount();
		setAttr("userAccount", records);
		setAttr("userList", usersList);
		render("userList.html");
	}
	public void addSaveIndex() {
		render("addUser.html");
	}
	
	@Before(UserValidator.class)
	public void save() {
		User user = getModel(User.class);
		userService.addUser(user);
		setAttr("msg", "success");
		render("add_success.html");
	}
	
	public void getUserById() {
		Integer id = getParaToInt();
		User user = userService.getUserById(id);
		setAttr("user", user);
		renderJson(user);
	}
	
	public void update() {
		User user = getModel(User.class);
		boolean result = userService.updateById(user);
		setAttr("msg", result);
		redirect("/user/");
	}
	
	public void delete() {
		Integer id = getParaToInt();
		boolean result = userService.deleteById(id);
		renderText(String.valueOf(result));
		//redirect("/user/index");
	}
	
	public void export() throws Exception {
		List<User> usersList = userService.getUsers();
		
		String path = "demo/utils/excel/excelmodel/user.xlsx";
		String excelName = "用户信息表";
		ExcelUtils eUtils = new ExcelUtils();
		eUtils.doExcel(path, usersList, User.class, excelName, getResponse());
		renderNull();
	}
	
	public void exportByCulmn() {
		System.out.println(getPara());
		String[] strings = getPara().split("-");
		
		List<User> usersList = userService.getUsers();
		
		renderNull();
	}
	
	public void upload12() {
		List<UploadFile> uploadFile =getFiles();
		UploadFile uploadFile2 = uploadFile.get(0);
		System.out.println(uploadFile2.getUploadPath() + uploadFile2.getFileName());
		System.out.println(uploadFile2.getUploadPath() + uploadFile2.getOriginalFileName());
		System.out.println(uploadFile2.getContentType());
		renderNull();
	}
	
	

}
