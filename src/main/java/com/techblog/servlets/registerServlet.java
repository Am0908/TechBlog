package com.techblog.servlets;

import com.techblog.dao.UserDao;
import com.techblog.entities.User;
import com.techblog.helper.connectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class registerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html:charset=UTF-8");
        try(PrintWriter out = resp.getWriter())
        {


            // fetch all form data

            // String check = req.getParameter("check");
            String name = req.getParameter("user_name");
            String email = req.getParameter("user_email");
            String password = req.getParameter("user_password");
            String gender  = req.getParameter("gender");
            String about = req.getParameter("about");

            // create a user
            User user = new User(name, email, password, gender, about);


            // create a user dao object
            UserDao dao = new UserDao(connectionProvider.connectionProvider());
            if(dao.saveUser(user))
            {
                out.println("done");
                System.out.println("User registered successfully.");
            }
            else
            {
                out.println("error");
                System.out.println("User not registered!!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
