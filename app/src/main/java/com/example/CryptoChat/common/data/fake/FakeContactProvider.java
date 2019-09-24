package com.example.CryptoChat.common.data.fake;

import com.example.CryptoChat.common.data.models.User;
import com.example.CryptoChat.common.data.provider.ContactProvider;

import java.util.ArrayList;

public class FakeContactProvider extends ContactProvider {

    private ArrayList<User> users;

    public FakeContactProvider(){
        super();
        users = new ArrayList<>();
        for (int i=0;i<100;i++) {
            users.add(new User(Integer.valueOf(i).toString(),"Contact - " +i,"", true));
        }
    }

    @Override
    public ArrayList<User> getUsers(){
        return this.users;
    }
}