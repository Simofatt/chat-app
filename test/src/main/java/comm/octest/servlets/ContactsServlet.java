package comm.octest.servlets;

import comm.octest.beans.ChatRoom;
import comm.octest.db.Pseudo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ContactsServlet", value = "/ContactsServlet")
public class ContactsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	  HttpSession session =request.getSession () ;
          String email =  (String) session.getAttribute("email") ;
          if (email != null) {
          Pseudo pseudo = new Pseudo(email);
          ArrayList<String> contacts = pseudo.getContacts();
          ChatRoom chatRoom = new ChatRoom();
          request.setAttribute("chatRoom",chatRoom);
          request.setAttribute("contacts",contacts);
          
          ArrayList<String> onlineFriends =  chatRoom.connected(email) ;
          request.setAttribute("onlineFriends",onlineFriends);
          this.getServletContext().getRequestDispatcher("/WEB-INF/contacts.jsp").forward(request, response) ;
          }else { 
        	  response.sendRedirect("SignIn");
          }
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
