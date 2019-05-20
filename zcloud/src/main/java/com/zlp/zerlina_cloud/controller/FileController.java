package com.zlp.zerlina_cloud.controller;

import com.zlp.zerlina_cloud.domain.FilesEntity;
import com.zlp.zerlina_cloud.domain.UsersEntity;
import com.zlp.zerlina_cloud.jpa.FilesRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController extends BaseController {
	@Autowired
	FilesRepository filesRepository;

	/**
	 * 初始化上传文件界面，跳转至index.jsp
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request){
		Object o = request.getSession().getAttribute("user");
		if((o != null) && (o instanceof UsersEntity)){
			String uid = ((UsersEntity) o).getUid();
			model.addAttribute("filesEntity",filesRepository.findByUid(uid));
//			model.addAttribute("user",request.getSession().getAttribute("user"));
		}

		return "index";
	}
	private String executeUpload(String uploadDir, MultipartFile file,String uid) throws Exception
	{

		String[] filenameStr = getFromUpload(uploadDir);
		String filename = file.getOriginalFilename();
		//服务器端保存的文件对象
		File serverFile = new File(uploadDir + filename);
		System.out.println("line 50"+serverFile.getAbsolutePath());
		// 放到了files表里面
		FilesEntity filesEntity = new FilesEntity();
		filesEntity.setUid(uid);
		filesEntity.setFilename(filename);
		filesEntity.setFilepath(uploadDir);
		filesRepository.save(filesEntity);
		//将上传的文件写入到服务器端文件内
		/**
		 * MultipartFile对象内置的方法transferTo()
		 *  可以实现JSP页面上传到request内的文件对象直接存储到指定文件File对象内，以此来完成上传文件的存储
		 */
		file.transferTo(serverFile);
		return filename;
	}

	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public @ResponseBody String upload(HttpServletRequest request,MultipartFile[] file,HttpServletResponse response) throws IOException {
		Object o = request.getSession().getAttribute("user");
		if(!(o instanceof UsersEntity)){
			getSession().setAttribute("error","用户不存在");
			return "error";
		}
		String uid = ((UsersEntity) o).getUid();

		try {
			//上传目录地址
			String uploadDir = request.getSession().getServletContext().getRealPath("/") +"upload/";
			//如果目录不存在，自动创建文件夹
			File dir = new File(uploadDir);
			if(!dir.exists())
			{
				dir.mkdir();
			}
			//如果只是上传单个文件
			if(file.length==1){
				executeUpload(uploadDir,file[0],uid);
			}else {
				//遍历文件数组执行上传
				for (int i =0;i<file.length;i++) {
					if(!file[i].isEmpty()) {
						//调用上传方法
						executeUpload(uploadDir, file[i],uid);
					}
				}
			}
		}catch (Exception e){
			//打印错误堆栈信息
			e.printStackTrace();
			getSession().setAttribute("error","上传失败");
			return "error";
		}
		response.sendRedirect("/index");
		return "index";
	}

	public String[] getFromUpload(String uploadDir){
		File file = new File(uploadDir);
		String[] filenameStr = file.list();
		return filenameStr;

	}


	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, HttpServletResponse response,String filename, Model model) throws IOException {
		//上传目录地址
		String loadPath = request.getSession().getServletContext().getRealPath("/") +"upload/";
		File file = new File(loadPath+File.separator+filename);
		HttpHeaders headers = new HttpHeaders();
//		System.out.println(filename);
		String downloadFileName = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		//告知浏览器以下载方式打开
		headers.setContentDispositionFormData("attachment",downloadFileName);
		//设置MIME类型
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//用FileUpload组件的FileUtils读取文件，并构建成ResponseEntity<byte[]>返回给浏览器
		//HttpStatus.CREATED是HTTP的状态码201
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);

	}
}
