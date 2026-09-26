package org.subrata.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.subrata.Service.UserService;
import org.subrata.model.User;

import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet
{
    private UserService userService=new UserService();

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
       Integer id=Integer.parseInt(httpServletRequest.getParameter("id"));
       String name=httpServletRequest.getParameter("name");
       String email=httpServletRequest.getParameter("email");
       String mobile=httpServletRequest.getParameter("mobile");

       if(id==null || email== null || name==null || mobile==null)
       {
           //return;
       }
       User user=new User(id,name,email,mobile);
       User createdUser=userService.createUser(user);

       //RETURN JSON USER
    }
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        String idparam=httpServletRequest.getParameter("id");
        if(idparam==null)
        {
            //get all
            List<User> user=userService.getAllUsers();
            //return users
        }
        Integer id=Integer.parseInt(idparam);
        User user=userService.getUserById(id);
        if(user==null)
        {
            //status=404K
        }
        //return user

    }
    @Override
    public void doPut(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
    {

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {

    }
}
