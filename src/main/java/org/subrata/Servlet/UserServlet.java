package org.subrata.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.subrata.model.User;

@WebServlet("/users")
public class UserServlet extends HttpServlet
{
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
    }
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {

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
