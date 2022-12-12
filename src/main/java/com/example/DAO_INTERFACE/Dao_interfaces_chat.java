package com.example.DAO_INTERFACE;

import java.util.List;

import com.example.MODELS.chat;

public interface Dao_interfaces_chat {
    public List<chat> getAllMessages();
    public List<chat> getMsg(String mail,String sender);
    public void addMsg(chat chat_cl);
    public void dropMsg(chat chat_cl);
}
