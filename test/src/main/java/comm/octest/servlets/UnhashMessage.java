package comm.octest.servlets;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.octest.security.Hash;

/**
 * Servlet implementation class UnhashMessage
 */
@WebServlet("/UnhashMessage")
public class UnhashMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UnhashMessage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			   String message = request.getParameter("message");
			   String from = request.getParameter("from");
			   System.out.println("MESSAGE *******"+message) ;
			   String key2= from + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" ;
			   
			   // Perform some treatment on myVariable
			   SecretKeySpec key = new SecretKeySpec(key2.getBytes(), "AES");
	      		Hash hash = new Hash() ;
	      		String messageDechiffre = hash.dechiffrementAES(message.getBytes(), key);
	      		 System.out.println("MESSAGE *******"+messageDechiffre) ;
	      		
	      		
	      		
			   // Return the treated variable
			   response.getWriter().write(messageDechiffre);
			} catch(Exception e) { 
				System.out.print(e) ; 
			}

	}

}
