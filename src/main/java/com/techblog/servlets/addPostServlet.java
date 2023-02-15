package com.techblog.servlets;

import com.techblog.dao.postDao;
import com.techblog.entities.User;
import com.techblog.entities.post;
import com.techblog.helper.Helper;
import com.techblog.helper.connectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class addPostServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html:charset=UTF-8");
        try(PrintWriter out = resp.getWriter())
        {
            int cid = Integer.parseInt(req.getParameter("cid"));
            String ptitle = req.getParameter("ptitle");
            String pcontent = req.getParameter("pcontent");
            String pcode = req.getParameter("pcode");
            Part part = req.getPart("pic");
//            getting current user id
            HttpSession session = req.getSession();
            User user = (User)session.getAttribute("currentUser");
            post p = new post(ptitle, pcontent, pcode,part.getSubmittedFileName(), cid, user.getId());
            postDao pdao = new postDao(connectionProvider.connectionProvider());
            if(pdao.savePost(p))
            {
                String path = "/Users/amrita/Documents/javaProjects/TechBlog/src/main/webapp/blog_pics"+ File.separator+part.getSubmittedFileName();
                Helper.saveFile(part.getInputStream(), path);

                out.println("done");
            }

            else
                out.println("error");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
