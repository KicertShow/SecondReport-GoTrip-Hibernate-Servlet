package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.impl.interface_DAO;
import model.CommentBean;
import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("/comment")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
				 maxFileSize = 1024 * 1024 * 10, // 10MB
				 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentService service = new CommentServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Optional<Object> token = Optional.ofNullable(session.getAttribute("login"));

//		if(token.isPresent()) { // 判斷client端有無token
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action == null || action.equals("query")) {
			getAllComments(request, response);
		} else if (action.equals("save")) {
			saveComment(request, response);
		} else if (action.equals("updateForm")) {
			showUpdateForm(request, response);
		} else if (action.equals("update")) {
			updateComment(request, response);
		} else if (action.equals("delete")) {
			deleteComment(request, response);
		}
//		} else {
//			Boolean isLoggedIntoComment = false;
//			request.setAttribute("isLoggedIntoComment", isLoggedIntoComment);
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
//			requestDispatcher.forward(request, response);
//        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void getAllComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CommentBean> comments = service.getAllComments();
		request.setAttribute("comments", comments);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/comment.jsp");
		dispatcher.forward(request, response);
	}

	public void saveComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentBean comment = new CommentBean();
		comment.setItemTb(request.getParameter("itemTb").trim());
		comment.setItemId(Integer.parseInt(request.getParameter("itemId")));
		comment.setUserId(request.getParameter("userId").trim());
		comment.setDate(new Date());
		comment.setRating(Integer.parseInt(request.getParameter("rating")));
		comment.setContent(request.getParameter("content").trim());
		List<String> images = uploadImages(request);
		if (!images.isEmpty()) 
			comment.setImage1(images.get(0));
		if (images.size() >= 2) 
			comment.setImage2(images.get(1));
		if (images.size() >= 3) 
			comment.setImage3(images.get(2));
		service.saveComment(comment);
		request.setAttribute("comment", comment);
		response.sendRedirect("comment");
	}

	public void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CommentBean comment = service.getComment(id);
		request.setAttribute("comment", comment);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comment-update.jsp");
		dispatcher.forward(request, response);
	}

	public void updateComment(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CommentBean comment = new CommentBean();
		comment.setId(Integer.parseInt(request.getParameter("id")));
		comment.setUserId(request.getParameter("userId").trim());
		comment.setRating(Integer.parseInt(request.getParameter("rating")));
		comment.setContent(request.getParameter("content").trim());
		List<String> images = updateImages(request);
		if (!images.isEmpty()) 
			comment.setImage1(images.get(0));
		if (images.size() >= 2) 
			comment.setImage2(images.get(1));
		if (images.size() >= 3) 
			comment.setImage3(images.get(2));
		service.updateComment(comment);
		response.sendRedirect("comment");
	}

	public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		service.deleteComment(id);
		response.sendRedirect("comment");
	}

	public static List<String> uploadImages(HttpServletRequest request){
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + "images" + File.separator;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
//      String savePath = "C:\\_SpringBoot\\workspace\\GoTrip\\src\\main\\webapp\\images" + File.separator;
		List<String> fileNames = new ArrayList<String>();
		try {
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				if (part.getName().equalsIgnoreCase("images") && !getFileName(part).equals("")) {
					String fileName = System.currentTimeMillis() + "_" + getFileName(part);
					fileNames.add(fileName);
					File outputFilePath = new File(savePath + fileName);
					try (InputStream is = part.getInputStream();
		                 OutputStream os = new FileOutputStream(outputFilePath);
		                 BufferedOutputStream bos = new BufferedOutputStream(os);){
	                    byte[] buffer = new byte[1024];
	                    while (is.read(buffer) != -1) {
	                        bos.write(buffer);
	                    }
	                    bos.flush();
					} catch (Exception e) {
						fileName = null;
						System.out.println("excption!!!!!!");
						fileNames.remove(0);
					} 
				}
			}
		} catch (IOException | ServletException e) {
			fileNames = null;
		} 
		return fileNames;
	}
	
	public static List<String> updateImages(HttpServletRequest request) {
		
		List<String> existedImages = new ArrayList<String>();
		for (int i = 1 ; i <= 3; i++) {
			if (!request.getParameter("image"+i).equals("null"))
	        	existedImages.add(request.getParameter("image"+i));
		}
		String[] deleteImagesArr = request.getParameterValues("deleteImages");
		if (deleteImagesArr!= null) {
			List<String> deleteImagesList = Arrays.asList(deleteImagesArr);
			for (int i = 1 ; i <= 3; i++) {
				if (deleteImagesList.contains("image"+i))
		        	existedImages.remove(request.getParameter("image"+i));
			}
		}
		List<String> uploadImages = uploadImages(request);
		List<String> updateImages = existedImages;
		updateImages.addAll(uploadImages);
		return updateImages;
	}

	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}

