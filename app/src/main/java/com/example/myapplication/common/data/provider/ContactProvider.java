package com.example.myapplication.common.data.provider;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.common.data.exceptions.DuplicatedException;
import com.example.myapplication.common.data.exceptions.ObjectNotExistException;
import com.example.myapplication.common.data.models.User;

import java.util.ArrayList;
import java.util.List;

public abstract class ContactProvider {

    private ArrayList<User> users;

    public ContactProvider(){
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void deleteUser(String uid) {
        getUsers().removeIf(u -> u.getId().equals(uid));
    }

    public void deleteUser(int idx) {
        getUsers().remove(idx);
    }

    public void addUser(String id, String name) throws DuplicatedException {
        for (User u:getUsers()) {
            if(u.getId().equals(id)) {
                throw new DuplicatedException("User already exists!");
            }
        }
        getUsers().add(new User(id, name, "", true));
    }

    public void addUser(User user) throws DuplicatedException {
        for (User u:getUsers()) {
            if (u.getId().equals(user.getId())) {
                throw new DuplicatedException("User already exists!");
            }
        }
        getUsers().add(user);
    }

    public User getUser(String id) throws ObjectNotExistException {
        for (User u:getUsers()) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        throw new ObjectNotExistException("User object does not exist");
    }

    public User getUser(int idx){
        return getUsers().get(idx);
    }

    public int getCount(){
        return getUsers().size();
    }
}