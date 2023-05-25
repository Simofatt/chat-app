package comm.octest.servlets;

import comm.octest.db.Pseudo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "AddContactServlet", value = "/AddContactServlet")
public class AddContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  HttpSession session =request.getSession () ;
          String email =  (String) session.getAttribute("email") ;
    	   if (email != null) {
               RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/visiterProfil.jsp");
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
        String emailContact = request.getParameter("addContact");
        System.out.println(emailContact);
        pseudo.addContact(emailContact);
        response.sendRedirect(request.getContextPath() + "/ContactsServlet");
    }
}
