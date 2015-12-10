package com.ruanko.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Field;
import com.ruanko.model.TourismImage;
import com.ruanko.service.TourismService;

/**
 * Servlet implementation class AddTourismImagesServlet
 */
public class AddTourismImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService = new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTourismImagesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 上传旅游图片
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 上传旅游图片
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream in = request.getInputStream();
		String realPath = request.getSession().getServletContext()
				.getRealPath("upload");
		// step1,创建一个DiskFileItemFactory对象
		// 为解析器提供解析时的缺省的配置。
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// step2,创建一个解析器
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// step3,使用解析器解析
		try {
			List<FileItem> items = sfu.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				// 要判断是一个普通的表单域还是
				// 上传文件域
				if (item.isFormField()) {

					// 是一个普通的表单域
					String name = item.getFieldName();
					String value = item.getString();
				} else {
					// 上传文件域,要将文件保存在服务器端
					ServletContext sc = this.getServletContext();

					// 获得实际部署时物理路径
					String path = sc.getRealPath("upload");
					// 获得上传文件的名称
					String fileName = item.getName();
					File file = new File(path + "//" + fileName);
					item.write(file);
					// 创建一条旅游图片记录
					// 准备相关数据
					Date date = new Date();
					DateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String time = format.format(date);
					Timestamp uploadTime = Timestamp.valueOf(time);
					// 设置旅游图片属性
					TourismImage tourismImage = new TourismImage();
					tourismImage.setLast_modify(date);
					tourismImage.setOrders(new Long(date.getTime()).intValue());
					tourismImage.setUpload_time(uploadTime);
					
					//TODO 
					tourismImage.setBig("upload/"+file.getName());
					tourismImage.setSmall("upload/"+file.getName());
					// 调用service层进行存储 
					boolean flag = tourismService.addTourismImages(
							tourismImage, request);
					if (flag) {
						// 添加成功
						request.getRequestDispatcher("addImagesSuccess.jsp")
								.forward(request, response);
					} else {
						// 添加失败
						request.getRequestDispatcher("addImagesFailed.jsp")
								.forward(request, response);
					}
				} 
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
