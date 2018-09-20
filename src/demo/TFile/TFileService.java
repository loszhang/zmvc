package demo.TFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import cn.zaichi.zmvc.plugin.activerecord.Db;
import cn.zaichi.zmvc.plugin.activerecord.Page;
import cn.zaichi.zmvc.plugin.activerecord.Record;
import cn.zaichi.zmvc.upload.UploadFile;
import demo.model.base.TFile;

public class TFileService {
	public static final TFileService me = new TFileService();
	private TFile tFile = new TFile().dao;
	
	public void upload(UploadFile uploadFile) {
		TFile file = new TFile();
		file.set("file_name", uploadFile.getFileName());
		file.set("file_path", uploadFile.getUploadPath());
		file.set("file_contenttype", uploadFile.getContentType());
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		file.set("create_date", sdFormat.format(date));
		sdFormat = new SimpleDateFormat("HH:mm:ss");
		file.set("create_time", sdFormat.format(date));
		file.save();
	}
	public void uploads(List<UploadFile> uploadFile) {
		for (UploadFile file : uploadFile) {
			TFile tfile = new TFile();
			tfile.set("file_name", file.getFileName());
			tfile.set("file_path", file.getUploadPath());
			tfile.set("file_contenttype", file.getContentType());
			Date date = new Date();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			tfile.set("create_date", sdFormat.format(date));
			sdFormat = new SimpleDateFormat("hh:mm:ss");
			tfile.set("create_time", sdFormat.format(date));
			tfile.save();
		}
	}
	
	public Record getFileById(Integer id) {
		Record record = Db.findFirst("select * from t_file where id=?",id);
		return record;
	}
	
	public List<Record> getFiles() {
		List<Record> list = Db.find("select * from t_file");
		return list;
	}
	
	public Page<Record> getFilesByPage(Integer page, Integer total) {
		Page<Record> pages = Db.paginate(page, total, "select *", "from t_file");
		
		return pages;
	}
	
	public void deleteById(Integer id) {
		Db.deleteById("t_file", id);
		//tFile.deleteById(id);
	}
}
