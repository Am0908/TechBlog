package com.techblog.servlets;

import com.techblog.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.User;
import com.techblog.helper.connectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class loginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html:charset=UTF-8");
        try(PrintWriter out = resp.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");

            // fetch all form data

            // String check = req.getParameter("check");
            String userEmail = req.getParameter("email");
            String userPassword = req.getParameter("password");

            // check if the user is registered
            UserDao dao = new UserDao(connectionProvider.connectionProvider());
            User u = dao.getUserByEmailAndPassword(userEmail, userPassword);
            if(u==null)
            {
                // login......
                //error
                Message msg = new Message("Invalid credentials! Please try again....", "error","alert-danger");
                HttpSession s = req.getSession();
                s.setAttribute("msg", msg);
                resp.sendRedirect("login_page.jsp");
                System.out.println("Login unsuccessful...Invalid details..try again...");
            }
            else
            {
                System.out.println("Login successful");
                HttpSession s = req.getSession();
                s.setAttribute("currentUser", u);
                resp.sendRedirect("profile.jsp");
            }


            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
