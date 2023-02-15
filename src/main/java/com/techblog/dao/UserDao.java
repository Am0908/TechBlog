package com.techblog.dao;

import com.techblog.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }
    // method to insert user to database
    public boolean saveUser(User user)
    {
        boolean f = false;
        try
        {
            // user---->database
            String query = "insert into user(name, email, password, gender, about) values(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());
            pstmt.executeUpdate();
            f=true;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    // Get user by user_email and user_password
    public User getUserByEmailAndPassword(String email, String password)
    {
        User user=null;
        try{
            String query = "select * from user where email=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet set = pstmt.executeQuery();
            if(set.next())
            {
                user = new User();
                // data from db
                String name = set.getString("name");
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setId(set.getInt("id"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user)
    {
        boolean f = false;
        try
        {
            String query = "update user set name=?, email=?, password=?, about=?, profile=? where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAbout());
            pstmt.setString(5, user.getProfile());
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
}
