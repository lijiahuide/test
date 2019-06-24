package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "upload", urlPatterns = "/")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到解析器工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fileList = upload.parseRequest(request);
			for (FileItem fileItem : fileList) {
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + fileItem.getString());
				} else {
					String filename = fileItem.getName();
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					System.out.println("文件名称:" + filename);
					InputStream in = fileItem.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					String savePath = this.getServletContext().getRealPath("/upload");
					System.out.println("savePath:" + savePath);
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					// 把文件读取到数组里面
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					// 关闭读取的
					in.close();
					out.close();
					fileItem.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
