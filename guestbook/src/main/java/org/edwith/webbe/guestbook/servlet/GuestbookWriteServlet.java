package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String name = (String) request.getParameter("name");
    	String content = (String) request.getParameter("content");
    	
    	GuestbookDao dao = new GuestbookDao();
    	int result = dao.addGuestbook(new Guestbook(name, content));
    	
    	if(result == 0) {
    		PrintWriter out = response.getWriter();
    		out.print("<script>alert('저장되지 않았습니다!');</script>");
    		if(out != null) {
    			out.close();
    		}
    	}
    	
    	response.sendRedirect("/guestbook/guestbooks");
    }

}
