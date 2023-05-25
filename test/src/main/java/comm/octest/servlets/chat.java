package comm.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.octest.beans.ChatRoom;
import comm.octest.db.Pseudo;


@WebServlet(name = "chat", value = "/chat")
public class chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public chat() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	      Pseudo pseudo  = new Pseudo() ;
	      HttpSession session =request.getSession () ; 
		  String email =  (String) session.getAttribute("email") ;
	     

	   
		this.getServletContext().getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response) ; 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		
		// String nom = request.getParameter("nom");
	     //request.setAttribute("nom", nom);
	        
	        
	    this.getServletContext().getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response);
		    //doGet(request, response);
	}

}
