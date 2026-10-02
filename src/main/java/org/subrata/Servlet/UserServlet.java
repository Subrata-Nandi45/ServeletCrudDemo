package org.subrata.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.subrata.Service.UserService;
import org.subrata.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet
{
    private UserService userService=new UserService();

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
       Integer id=Integer.parseInt(httpServletRequest.getParameter("id"));
       String name=httpServletRequest.getParameter("name");
       String email=httpServletRequest.getParameter("email");
       String mobile=httpServletRequest.getParameter("mobile");

       if(id==null || email== null || name==null || mobile==null)
       {
         httpServletResponse.setStatus(400);
         httpServletResponse.setContentType("applicaion/json");
         httpServletResponse.getWriter().write(
                 "{\n" +
                         "    \"message\":\"some fields are missing\"\n" +
                         "}"
         );

       }
       User user=new User(id,name,email,mobile);
       User createdUser=userService.createUser(user);

        httpServletResponse.setStatus(201);
        httpServletResponse.setContentType("applicaion/json");
        httpServletResponse.getWriter().write(
                "{\n" +
                        "    \"message\":\"User added sucessfully\"\n" +
                        "}"
        );
       //RETURN JSON USER
    }
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String idparam=httpServletRequest.getParameter("id");
        if(idparam==null)
        {
            //get all
            List<User> user=userService.getAllUsers();
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("applicaion/json");
            httpServletResponse.getWriter().write(usersToJson(user));

            //return users
        }
        Integer id=Integer.parseInt(idparam);
        User userResp=userService.getUserById(id);
        if(userResp==null)
        {
            //status=404K
            httpServletResponse.setStatus(404);
            httpServletResponse.setContentType("applicaion/json");

        }
        //return user
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("applicaion/json");
        httpServletResponse.getWriter().write(userToJson(userResp));

    }
    @Override
    public void doPut(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
    {

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {

    }

    private String userToJson(User user)
    {
        return "{\n" +
                "    \"id\":"+ user.getId()+",\n" +
                "    \"name\":"+user.getName()+" ,\n" +
                "    \"email\":"+user.getEmail()+",\n" +
                "    \"mobile\":"+user.getMobile()+"\n" +
                "}";
    }
    private String usersToJson(List<User> users)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");

        for(int i=0;i<users.size();i++)
        {
            stringBuilder.append(userToJson(users.get(i)));

            if(i<users.size()-1){
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
