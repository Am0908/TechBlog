package com.techblog.dao;

import com.techblog.entities.category;
import com.techblog.entities.post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class postDao {
    Connection con;

    public postDao(Connection con) {
        this.con = con;
    }
    public ArrayList<category> getAllCategories()
    {
        ArrayList<category> list=new ArrayList<category>();
        try
        {
            String q = "select*from categories";
            Statement st = this.con.createStatement();
            ResultSet set = st.executeQuery(q);
            while (set.next())
            {
                 int cid = set.getInt("cid");
                 String name=set.getString("name");
                 String description=set.getString("description");
                 category c = new category(cid, name, description);
                 list.add(c);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public boolean savePost(post p)
    {
        boolean f = false;
        try
        {
            String q = "insert into posts(ptitle, pcontent, pcode, ppic, catid, userid) values(?,?, ?, ?, ?, ?)";
            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setString(1, p.getPtitle());
            pstmt.setString(2, p.getPcontent());
            pstmt.setString(3, p.getPcode());
            pstmt.setString(4, p.getPpic());
            pstmt.setInt(5, p.getCatid());
            pstmt.setInt(6, p.getUserid());
            pstmt.executeUpdate();
            f=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

//    get all posts
     public List<post> getAllPosts()
     {
        List<post> list= new ArrayList<post>();
        // fetch all the posts
        try
        {
            PreparedStatement pstmt = con.prepareStatement("select * from posts order by pid");
            ResultSet set = pstmt.executeQuery();
            while(set.next())
            {
                int pid = set.getInt("pid");
                String ptitle = set.getString("ptitle");
                String pcontent  = set.getString("pcontent");
                String pcode = set.getString("pcode");
                int userid = set.getInt("userid");
                String ppic = set.getString("ppic");
                Timestamp pdate = set.getTimestamp("pdate");
                int catid = set.getInt("catid");
                post p = new post(pid, ptitle, pcontent, pcode, ppic, pdate, catid, userid);
                list.add(p);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
     }

     public List<post> getPostByCatId(int catid)
     {
         List<post> list= new ArrayList<post>();
         // get post by category
         try
         {
             PreparedStatement pstmt = con.prepareStatement("select * from posts where catid=?");
             pstmt.setInt(1, catid);
             ResultSet set = pstmt.executeQuery();
             while(set.next())
             {
                 int pid = set.getInt("pid");
                 String ptitle = set.getString("ptitle");
                 String pcontent  = set.getString("pcontent");
                 String pcode = set.getString("pcode");
                 int userid = set.getInt("userid");
                 String ppic = set.getString("ppic");
                 Timestamp pdate = set.getTimestamp("pdate");
                 post p = new post(pid, ptitle, pcontent, pcode, ppic, pdate, catid, userid);
                 list.add(p);
             }
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }

         return list;
     }

     public post fetchPost(int pid)
     {
         post p=null;
         try
         {
             String q = "select * from posts where pid=?";
             PreparedStatement pstmt = con.prepareStatement(q);
             pstmt.setInt(1, pid);
             ResultSet set = pstmt.executeQuery();
             if(set.next())
             {
                 String ptitle = set.getString("ptitle");
                 String pcontent = set.getString("pcontent");
                 String pcode = set.getString("pcode");
                 String ppic = set.getString("ppic");
                 Timestamp pdate = set.getTimestamp("pdate");
                 int userid = set.getInt("userid");
                 int catid = set.getInt("catid");
                 p=new post(pid, ptitle, pcontent, pcode, ppic, pdate, catid, userid);
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

         return p;
     }

     public String getUserName(int userid)
     {
         String name = "";
         try
         {
             String q = "select name from user where id=?";
             PreparedStatement ps = con.prepareStatement(q);
             ps.setInt(1, userid);
             ResultSet rs = ps.executeQuery();
             if(rs.next())
             {
                 name = rs.getString("name");
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         return name;
     }
}

