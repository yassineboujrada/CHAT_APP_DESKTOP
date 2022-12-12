package com.example.DAO_IMPLEMENTATION;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.DAO_INTERFACE.Dao_interfaces_classe;
import com.example.DB_CONNECTION.db_connect;
import com.example.MODELS.login;

/**
 * login_implementation
 */
public class login_implementation implements Dao_interfaces_classe<login>{
    private Connection con= db_connect.getConnection();
    
    @Override
    public login getData(String mail) {
        login account = null;
        try {
            PreparedStatement ps=con.prepareStatement("select * from accounts where lower(email)=?");
            ps.setString(1,mail);
            ResultSet result =ps.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(1)+result.getString(2));
                account = new login(result.getString(1),result.getString(2),result.getString(3),result.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void addData(login classe_plz) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<login> getAllData() {
        List<login> login_list = new ArrayList<login>();
        PreparedStatement ps;
        ResultSet res;
        try{
            ps= con.prepareStatement("select * from accounts");
            res=ps.executeQuery();
            while (res.next()){
                login_list.add(new login(res.getString(1),res.getString(2),res.getString(3),res.getString(4)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return login_list;
    }    
}