package comm.octest.servlets;

import comm.octest.beans.ChatRoom;
import comm.octest.beans.User;
import comm.octest.db.Pseudo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SignIn() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session =request.getSession () ; 
		  String email =  (String) session.getAttribute("email") ; 
			ChatRoom chatRoom = new ChatRoom();
			chatRoom.removeSession(email);
			session = request.getSession(false); // get the current session if it exists
			if (session != null) {
				session.invalidate(); // invalidate the session to log out the user
			}
		this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //CREATION DUN OBJET USER 
		    String email    = request.getParameter("email");
	        String password = request.getParameter("password");
	        User user = new User() ; 
	        user.setEmail(email);
	        user.setPassword(password) ;

	        
	        // AUTHENTIFICATION 
	        Pseudo pseudo = new Pseudo() ;
		
	
	        boolean auth =  pseudo.authentification(user) ;
	        request.setAttribute("auth", auth);
	        
	        //SESSION :
	        //SESSION :
	        if(auth) { 
	        	//Pseudo pseudo2 = new Pseudo(email) ;
	        	ChatRoom chatroom = new ChatRoom() ; 
	            HttpSession session =request.getSession () ; 
	 	        session.setAttribute("email", email ) ;
	 	        chatroom.setSessions(email);
	 	       this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);   
	         
	          }else { 
	        	    	 this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp?error=1").forward(request, response);
	        	    }
	     
	       

	}

}
