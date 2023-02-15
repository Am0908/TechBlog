package com.techblog.servlets;

import com.techblog.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.User;
import com.techblog.helper.connectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class editServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html:charset=UTF-8");
        try(PrintWriter out = resp.getWriter())
        {
            // fetching data
            String user_email = req.getParameter("user_email");
            String user_name = req.getParameter("user_name");
            String user_password = req.getParameter("user_password");
            String user_about = req.getParameter("user_about");
//            Part inputPart= req.getPart("image");
//            String imageName = inputPart.getSubmittedFileName();

            // get the user from the session
            HttpSession s = req.getSession();
            User user = (User) s.getAttribute("currentUser");
            user.setEmail(user_email);
            user.setName(user_name);
            user.setAbout(user_about);
            user.setPassword(user_password);

            out.println("Profile updated.....");
            Message msg = new Message("Profile details updated...", "success", "alert-success");
            s.setAttribute("msg", msg);
//            String oldProfile=user.getProfile();
//            user.setProfile(imageName);


//             updating the database
            UserDao userDao = new UserDao(connectionProvider.connectionProvider());
            userDao.updateUser(user);
//            if(ans)
//            {
//
//
//                String oldPath = "src/main/webapp/pics"+ File.separator+oldProfile;
//                System.out.println("old path....");
//                String path = "src/main/webapp/pics"+ File.separator+user.getProfile();
//                System.out.println(path);
//
//                if(!oldProfile.equals("default.png"));
//                Helper.deleteFile(oldPath);
//
//
//                if(Helper.saveFile(inputPart.getInputStream(), path))
//                {
//                    out.println("Profile updated.....");
//                    Message msg = new Message("Profile details updated...", "success", "alert-success");
//                    s.setAttribute("msg", msg);
//                }
//                else {
//                    //////////////
//                    System.out.println("Couldn't add the profile pic....");
//                    Message msg = new Message("Something went wrong..", "error", "alert-danger");
//                    s.setAttribute("msg", msg);
//                }
//            }

//            else {
//                out.println("not updated..");
//                Message msg = new Message("Something went wrong..", "error", "alert-danger");
//                s.setAttribute("msg", msg);
//
//            }

            resp.sendRedirect("profile.jsp");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
