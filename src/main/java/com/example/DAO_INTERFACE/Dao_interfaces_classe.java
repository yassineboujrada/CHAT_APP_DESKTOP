package com.example.DAO_INTERFACE;

import java.util.List;

public interface Dao_interfaces_classe<Name> {
    public List<Name> getAllData();
    public Name getData(String mail);
    public void addData(Name classe_plz);
}
