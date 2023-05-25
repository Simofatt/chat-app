package comm.octest.servlets;

import comm.octest.beans.User;
import comm.octest.db.Pseudo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SignUp() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response) ;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordc = request.getParameter("passwordc");


			User user = new User() ;
			user.setUsername(username);
			user.setEmail(email);
	        user.setPassword(password) ;
			System.out.println(username) ;

	        Pseudo pseudo = new Pseudo();

			boolean uniq = false;
			try {
				uniq = pseudo.validerInput(username, email, password, passwordc);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

			if (uniq){
					pseudo.insert(user);
					response.sendRedirect("SignIn");
			}else {
					response.sendRedirect("SignUp");
			}





	}

}
  