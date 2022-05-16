package com.example.kaueparking;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class Driver extends User {
    private String plate;
    public Driver() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean makeObjection(DBHelper db,Ticket t){
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (t.getApproved() != 2) { // if the approved attribute value is 2 this means that the ticket is already checked and approved
            contentValues.put("approved", 0);
            long result = DB.update("ticket", contentValues, "id=?", new String[]{t.getId() + ""});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    public boolean payTicket(DBHelper db,String id){
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", 1); // 1 means that the ticket is paid
        long result = DB.update("ticket", contentValues, "id=?", new String[]{id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addDescription(DBHelper db, String ticketID, String description){
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", description);
        long result = DB.update("ticket", contentValues, "id=?", new String[]{ticketID});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



}
