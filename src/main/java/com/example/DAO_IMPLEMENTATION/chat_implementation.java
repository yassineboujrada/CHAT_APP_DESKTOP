package com.example.DAO_IMPLEMENTATION;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.DAO_INTERFACE.Dao_interfaces_chat;
import com.example.DB_CONNECTION.db_connect;
import com.example.MODELS.chat;

public class chat_implementation implements Dao_interfaces_chat{
    private Connection con= db_connect.getConnection();

    @Override
    public List<chat> getAllMessages() {
        return null;
    }

    @Override
    public List<chat> getMsg(String receiver,String sender) {
        List<chat> chat_list = new ArrayList<chat>();
        PreparedStatement ps;
        ResultSet res;
        try{
            ps= con.prepareStatement("select * from msg_table where (lower(reciver_id)=? and lower(sender_id)=?) or (lower(sender_id)=? and lower(reciver_id)=?)");
            ps.setString(1,receiver.toLowerCase());
            ps.setString(2,sender.toLowerCase());
            ps.setString(3,receiver.toLowerCase());
            ps.setString(4,sender.toLowerCase());
            res=ps.executeQuery();
            while (res.next()){
                // System.out.println("hi "+res.getDate(4));
                // System.out.println(new chat(res.getString(1),res.getString(2),res.getString(3),res.getDate(4)));
                chat_list.add(new chat(res.getString(2),res.getString(3),res.getString(4),res.getDate(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return chat_list;
    }

    @Override
    public void addMsg(chat chat_cl) {
        try{
            PreparedStatement ps=con.prepareStatement("insert into msg_table (id,sender_id,reciver_id,message) values ((select max(id) from msg_table)+1,?,?,?)");
            ps.setString(1,chat_cl.getSender_id());
            ps.setString(2,chat_cl.getReciver_id());
            ps.setString(3,chat_cl.getMessage());
            ps.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }

    @Override
    public void dropMsg(chat chat_cl) {
        
    }
    
}
