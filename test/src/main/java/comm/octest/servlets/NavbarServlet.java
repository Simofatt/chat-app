package comm.octest.servlets;

import comm.octest.beans.User;
import comm.octest.db.Pseudo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "NavbarServlet", value = "/NavbarServlet")
public class NavbarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/navbar.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchInput = request.getParameter("searchInput");
        System.out.println(searchInput);
        HttpSession session =request.getSession () ;
        String email =  (String) session.getAttribute("email");
        Pseudo pseudo = new Pseudo(email);
        User searchUser = pseudo.checkUserExist(searchInput);
        if (searchUser.getUsername()!=null){
            request.setAttribute("user",searchUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/visiterProfil.jsp");
            dispatcher.forward(request, response);
            System.out.println("user found : " + searchUser.getUsername());
        }
        else {
            System.out.println("User not found");
        }
    }
}
