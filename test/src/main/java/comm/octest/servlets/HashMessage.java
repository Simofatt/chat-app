package comm.octest.servlets;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.octest.security.Hash;

/**
 * Servlet implementation class HashMessage
 */
@WebServlet("/HashMessage")
public class HashMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HashMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session =request.getSession () ;
        String email =  (String) session.getAttribute("email") ;
       String key2= email + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" ; 
		try {
		   String message = request.getParameter("message");
		   // Perform some treatment on myVariable
		   SecretKeySpec key = new SecretKeySpec(key2.getBytes(), "AES");
      		Hash hash = new Hash() ;
      		byte[] messageChiffre = hash.chiffrementAES(message, key);
      		
      		
		   // Return the treated variable
		   response.getWriter().write(messageChiffre.toString());
		} catch(Exception e) { 
			System.out.print(e) ; 
		}

	}

}
