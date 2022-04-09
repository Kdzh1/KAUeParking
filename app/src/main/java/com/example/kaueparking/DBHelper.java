package com.example.kaueparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DB2022", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE admin (id TEXT UNIQUE, password TEXT,name TEXT,phone TEXT, PRIMARY KEY(id))");
        DB.execSQL("CREATE TABLE driver (id TEXT UNIQUE, password TEXT, name TEXT, phone TEXT , PRIMARY KEY(id));");
        DB.execSQL("CREATE TABLE security (id TEXT UNIQUE, password TEXT, name TEXT, phone TEXT, PRIMARY KEY(id));");
        DB.execSQL("CREATE TABLE ticket (id INTEGER UNIQUE,plate TEXT,price TEXT,\n" +
                "location TEXT, time TEXT, status INTEGER DEFAULT 0, approved INTEGER DEFAULT 0, driverID TEXT, PRIMARY KEY(id), FOREIGN KEY(driverID)REFERENCES driver (id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }


    public boolean insertData(String tableName, String data []){

        if (tableName.equalsIgnoreCase("driver")){
            return driverTable("insert",data);
        }else if (tableName.equalsIgnoreCase("security")){
            return securityTable("insert",data);
        }else if (tableName.equalsIgnoreCase("ticket")){
            return ticketTable("insert",data);
        }else
        return false;

    }
    public boolean deleteData(String tableName, String data []){

        if (tableName.equalsIgnoreCase("driver")){
            return driverTable("delete",data);
        }else if (tableName.equalsIgnoreCase("security")){
            return securityTable("delete",data);
        }else if (tableName.equalsIgnoreCase("ticket")){
            return ticketTable("delete",data);
        }else
            return false;

    }
    private boolean driverTable(String operation, String data[]){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (operation.equalsIgnoreCase("insert")){                                     // INSERT

            contentValues.put("id",data[0]);
            contentValues.put("password", data[1]);
            contentValues.put("name",data[2]);                                              // <---- CONTENT OF THE ARRAY
            contentValues.put("phone",data[3]);
            contentValues.put("ticketID",Integer.parseInt(data[4]));


            long result = db.insert("driver", null, contentValues);
            if (result==-1){ return false; }
            else { return true; }

        }else if(operation.equalsIgnoreCase("updateTicket")){                          // UPDATE

            contentValues.put("ticketID", data[0]);                                               // [0] = TICKET ID, [1] = USER ID
            Cursor cursor = db.rawQuery("select * from driver where id = ?", new String[] {data[1]} ); /* to check if the given id is exist or not   */
            if (cursor.getCount()>0) {
                long result = db.update("driver", contentValues, "id=?", new String[]{data[1]}); /* update will always return true even if it is null value */
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }
        }else if(operation.equalsIgnoreCase("delete")){                                // DELETE

                                                                                                  // [0] = USER ID
            Cursor cursor = db.rawQuery("select * from driver where id = ?", new String[] {data[0]} ); /* to check if the given id is exist or not   */
            if (cursor.getCount()>0) {
                long result = db.delete("driver", "id=?", new String[]{data[0]}); /* will always return true even if it is null value */
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }
        }
            return false;

    }


    private boolean securityTable(String operation, String data[]){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (operation.equalsIgnoreCase("insert")){                                     // INSERT

            contentValues.put("id",data[0]);
            contentValues.put("password", data[1]);
            contentValues.put("name",data[2]);                                                    // CONTENT OF THE ARRAY
            contentValues.put("phone",data[3]);


            long result = db.insert("security", null, contentValues);
            if (result==-1){ return false; }
            else { return true; }

        }else if(operation.equalsIgnoreCase("delete")){                                // DELETE

                                                                                                  // [0] = SECURITY ID
            Cursor cursor = db.rawQuery("select * from security where id = ?", new String[] {data[0]} ); /* to check if the given id is exist or not   */
            if (cursor.getCount()>0) {
                long result = db.delete("security", "id=?", new String[]{data[0]}); /* delete will always return true even if it is null value */
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean ticketTable(String operation, String data[]){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (operation.equalsIgnoreCase("insert")){                                      // INSERT
            contentValues.put("id",Integer.parseInt(data[0]));
            contentValues.put("plate", data[1]);
            contentValues.put("price",data[2]);                                                    // CONTENT OF THE ARRAY
            contentValues.put("location",data[3]);
            contentValues.put("status",Integer.parseInt(data[4]));
            contentValues.put("status",Integer.parseInt(data[5]));
            contentValues.put("driverID",data[6]);
            long result = db.insert("ticket", null, contentValues);
            if (result==-1){ return false; }
            else { return true; }
        }

        return false;
    }

    public Object getData(String tableName, String id){
            if (tableName.equalsIgnoreCase("driver")){
                return getDriver(id);
            }else if (tableName.equalsIgnoreCase("security")){
                return  getSecurity(id);
            }else if (tableName.equalsIgnoreCase("ticket")){
                return getTicket(id);
            }else if (tableName.equalsIgnoreCase("admin")){
                return getAdmin(id);
            }
                return null;

    }
    private Driver getDriver(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM driver where id=? ", new String[] {id});
        if (c.moveToFirst()){

                Driver d = new Driver();
                d.setId(c.getString(0));
                d.setPassword(c.getString(1));
                d.setName(c.getString(2));
                d.setPhone(c.getString(3));

                return d;

        }
        return null;
    }

    private Security getSecurity(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM security where id=? ", new String[] {id});
        if (c.moveToFirst()){

            Security s = new Security();
            s.setId(c.getString(0));
            s.setPassword(c.getString(1));
            s.setName(c.getString(2));
            s.setPhone(c.getString(3));

            return s;

            }
        return null;
        }
    private Admin getAdmin(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Admin where id=? ", new String[] {id});
        if (c.moveToFirst()){

            Admin a = new Admin();
            a.setId(c.getString(0));
            a.setPassword(c.getString(1));
            a.setName(c.getString(2));
            a.setPhone(c.getString(3));

            return a;

        }
        return null;
    }

    private ArrayList getTicket(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Ticket where driverID=? ", new String[] {id});
        ArrayList arr = new ArrayList();
        if (c.moveToFirst()){ // iprfjw9hauiergrfpihwp9ghhwth8
            do {
                Ticket ticket = new Ticket();
                ticket.setId(Integer.parseInt(c.getString(0)));
                ticket.setPlate(c.getString(1));
                ticket.setPrice(c.getString(2));
                ticket.setLocation(c.getString(3));
                ticket.setTime(c.getString(4));
                ticket.setStatus(Integer.parseInt(c.getString(5)));
                ticket.setApproved(Integer.parseInt(c.getString(6)));
                ticket.setDriverID(c.getString(7));
                arr.add(ticket);
            }while (c.moveToNext());
           return arr;

        }
        return null;
    }


}
