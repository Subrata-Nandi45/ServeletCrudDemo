package org.subrata.Service;

import org.subrata.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService
{
    private Map<Integer, User> UserDB;

    public UserService()
    {
        UserDB=new HashMap<>();
    }
    public User createUser(User userReq)
    {
        UserDB.put(userReq.getId(),userReq);
        return userReq;
    }

    public List<User> getAllUsers()
    {
        return (List<User>)UserDB.values();
    }

    public User getUserById(Integer id)
    {
        return UserDB.getOrDefault(id,null);
    }





}
