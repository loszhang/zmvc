package demo.utils.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import cn.zaichi.zmvc.core.Controller;

public final class ExcelUtils extends Controller {

	public Boolean doExcel(String path, List objs, Class formClass,
			String filename, HttpServletResponse response) throws Exception {
		Date dte = new Date();
		DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");// 设置显示格式
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		// 使用excel_名字加时间作为导出excel的名字
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(filename.getBytes("gb2312"), "ISO8859-1")
					+ dfm.format(dte) + ".xls\"");
		} catch (Exception e1) {
			throw e1;
		}
		InputStream in = null;
		OutputStream out = null;
		Workbook workbook;
		try {
			// 路径必须使用utf-8解码
			String newpath = "";
			newpath = java.net.URLDecoder.decode(formClass.getClassLoader()
					.getResource("").getPath(), "utf-8");
			in = new FileInputStream((newpath + path));
			Map beans = new HashMap();
			beans.put("listdata", objs);
			@SuppressWarnings("unused")
			String sessname = formClass.toString();
			XLSTransformer transformer = new XLSTransformer();
			workbook = transformer.transformXLS(in, beans);
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			return true;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return false;
	}

}
