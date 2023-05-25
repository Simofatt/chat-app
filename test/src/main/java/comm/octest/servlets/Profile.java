package comm.octest.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "Profile", value = "/Profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	  HttpSession session =request.getSession () ;
      String email =  (String) session.getAttribute("email") ;
	   if (email != null) {
		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
	        dispatcher.forward(request, response);
       }
       else {
           response.sendRedirect("SignIn");
       }
   }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);

    }
}
