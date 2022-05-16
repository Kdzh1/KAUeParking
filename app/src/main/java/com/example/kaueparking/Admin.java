package com.example.kaueparking;

import androidx.annotation.NonNull;

public class Admin extends User{

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }



    public boolean deleteTicket(Ticket t, DBHelper db){
         return db.deleteData("ticket", t);
    }

    public boolean approveTicket(DBHelper db, Ticket t){
        return db.approveTicket(t.getId()+"");
    }
    public boolean addNewSecurity(DBHelper db, Security s){
        return db.insertData(s);
    }
    public boolean editSecurity(DBHelper db,Security s){
        db.deleteData("security",s);
        return db.insertData(db);
    }
    public boolean removeSecurity(DBHelper db, String s){
        Security ss = (Security) db.getData("security", s);
        return db.deleteData("ticket",ss);
    }






}
