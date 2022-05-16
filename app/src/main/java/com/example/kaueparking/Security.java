package com.example.kaueparking;

public class Security extends User{

    public boolean issueTicket(DBHelper db,Ticket t){
        return db.insertData(t);
    }

    }
