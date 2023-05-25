package comm.octest.servlets;


import comm.octest.db.Pseudo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "editerProfil", value = "/editerProfil") 
public class editerProfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  HttpSession session =request.getSession () ;
          String email =  (String) session.getAttribute("email") ;
    	   if (email != null) {
               RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editerProfil.jsp");
               dispatcher.forward(request, response);
           }
           else {
               response.sendRedirect("SignIn");
           }
       }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession () ;
        String email =  (String) session.getAttribute("email");
        
        Pseudo pseudo = new Pseudo(email);
        String change_form = request.getParameter("save");
        String remove_form = request.getParameter("remove");

        if (change_form != null) {
            System.out.println("Updated Username");
            String username = request.getParameter("username");
            pseudo.updateUsername(username);
        }

        if (remove_form != null) {
            System.out.println("removed");
            String removedUser = request.getParameter("remove");
            System.out.println(removedUser);
            pseudo.removeContact(removedUser);
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/editerProfil.jsp").forward(request, response);

    }
}
