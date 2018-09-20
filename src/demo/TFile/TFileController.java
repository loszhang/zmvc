package demo.TFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zaichi.zmvc.core.Controller;
import cn.zaichi.zmvc.kit.PathKit;
import cn.zaichi.zmvc.plugin.activerecord.Page;
import cn.zaichi.zmvc.plugin.activerecord.Record;
import cn.zaichi.zmvc.upload.UploadFile;

public class TFileController extends Controller {
	
	public static final TFileService SERVICE= TFileService.me;
	public void index() {
		render("index.html");
	}
	
	public void look() {
		//List<Record> list = SERVICE.getFiles();
		Integer page = getParaToInt("pageNumber",1);
		Integer total = getParaToInt("pageTotal",5);
		Page<Record> pages = SERVICE.getFilesByPage(page, total);
		List<Record> list = pages.getList();
		setAttr("page", pages);
		setAttr("list", list);
		render("fileList.html");
	}
	
	public void upload() {
		UploadFile uploadFile =getFile();
		SERVICE.upload(uploadFile);
		redirect("/file/index");
	}
	
	public void uploads() {
		List<UploadFile> uploadFile =getFiles();
		SERVICE.uploads(uploadFile);
		redirect("/file/index");
	}
	
	public void downloadFile() throws IOException{
		String file_name = getPara("file_name");
		String file_path = getPara("file_path");
		String file_contenttype = getPara("file_contenttype");
		
        //通过pathkit.getwebrootpath获取项目的根目录，或者在config配置默认的文件下载根路径
        //String basePath = PathKit.getWebRootPath() +"/download";

        //获取前台的传递的文件名（包括文件格式，例如"test.jpg"）
        String fileName = getPara("downFileName");

        //根据根目录和文件名，拼接成完整的file路径
        String targetPath = file_path+File.separator+file_name;

        //普通IO流实现下载的功能
        HttpServletResponse res = getResponse(); //创建response回应
        res.setContentType("text/html; charset=UTF-8"); //设置编码字符
        res.setContentType(file_contenttype); //设置内容类型
        res.setHeader("Content-disposition", "attachment;filename="+fileName);//设置下载的文件名称
        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，可弹出下载框
        
        //创建输入流读取文件
       /* File file = new File(targetPath);
        InputStream is = new FileInputStream(file); 
        byte[] Buffer = new byte[4096];
        int size = 0; 
       
        while((size=is.read(Buffer)) != -1){
            out.write(Buffer, 0, size); 
        }

        //关闭和释放流
        out.flush();
        out.close();
        is.close();*/
        renderFile(new File(targetPath));
        //renderNull();
    }
	
	public void downloadByZmvc() {
		String idsString = getPara();
		Integer id = getParaToInt(0);
		Record fileRecord = SERVICE.getFileById(id);
		String targetpath = fileRecord.getStr("file_path") +File.separator + fileRecord.getStr("file_name");
		renderFile(new File(targetpath));
	}
	
	public void batchDownload() {
		String param = getPara();
		String[] ids = param.split("-");
		for (String id : ids) {
			Record fileRecord = SERVICE.getFileById(Integer.valueOf(id));
			String targetpath = fileRecord.getStr("file_path") +File.separator + fileRecord.getStr("file_name");
			renderFile(new File(targetpath));
		}
	}
	
	public void delete() {
		Integer idInteger= getParaToInt(0);
		SERVICE.deleteById(idInteger);
		redirect("/file/look");
	}
}
