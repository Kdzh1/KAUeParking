package com.example.kaueparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DB2022", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE admin (id TEXT UNIQUE, password TEXT,name TEXT,phone TEXT, PRIMARY KEY(id))");
        DB.execSQL("CREATE TABLE driver (id TEXT UNIQUE, password TEXT, name TEXT, phone TEXT, plate TEXT , PRIMARY KEY(id));");
        DB.execSQL("CREATE TABLE security (id TEXT UNIQUE, password TEXT, name TEXT, phone TEXT, PRIMARY KEY(id));");
        DB.execSQL("CREATE TABLE ticket (id INTEGER UNIQUE,plate TEXT,price TEXT,location TEXT, time TEXT, status INTEGER DEFAULT 0, approved INTEGER DEFAULT 1, driverID TEXT, violation BLOB, PRIMARY KEY(id), FOREIGN KEY(driverID)REFERENCES driver (id));");
        DB.execSQL("insert into security values('1222','123','khalid','0546545654')");
        DB.execSQL("insert into admin values('0011','123','mohammed','995554466')");
        DB.execSQL("insert into driver values ('9888','123','ahmed','65564654','2724EJD')");
        DB.execSQL("insert into ticket values (0,'0','0','0','0',0,1,'0',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }


    public boolean insertData(Object obj) {

        if (obj instanceof Driver) {
            return driverTable("insert", obj);
        } else if (obj instanceof Security) {
            return securityTable("insert", obj);
        } else if (obj instanceof Ticket) {
            return ticketTable("insert", obj);
        } else
            return false;


    }

    public boolean deleteData(String tableName, String data[]) {

        if (tableName.equalsIgnoreCase("driver")) {
            return driverTable("delete", data);
        } else if (tableName.equalsIgnoreCase("security")) {
            return securityTable("delete", data);
        } else if (tableName.equalsIgnoreCase("ticket")) {
            return ticketTable("delete", data);
        } else
            return false;

    }

    private boolean driverTable(String operation, Object obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        Driver d = (Driver) obj;
        ContentValues contentValues = new ContentValues();
        if (operation.equalsIgnoreCase("insert")) {                                     // INSERT

            contentValues.put("id", d.getId());
            contentValues.put("password", d.getPassword());
            contentValues.put("name", d.getName());                                              // <---- CONTENT OF THE ARRAY
            contentValues.put("phone", d.getPhone());
            contentValues.put("plate", d.getPlate());

            long result = db.insert("driver", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else if (operation.equalsIgnoreCase("delete")) {                                // DELETE

            // [0] = USER ID
            Cursor cursor = db.rawQuery("select * from driver where id = ?", new String[]{d.getId()}); /* to check if the given id is exist or not   */
            if (cursor.getCount() > 0) {
                long result = db.delete("driver", "id=?", new String[]{d.getId()}); /* will always return true even if it is null value */
                if (result == -1) {
                    cursor.close();
                    return false;
                } else {
                    cursor.close();
                    return true;
                }
            }
        }
        return false;

    }


    private boolean securityTable(String operation, Object obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        Security s = (Security) obj;
        ContentValues contentValues = new ContentValues();
        if (operation.equalsIgnoreCase("insert")) {                                     // INSERT

            contentValues.put("id", s.getId());
            contentValues.put("password", s.getPassword());
            contentValues.put("name", s.getName());                                                    // CONTENT OF THE ARRAY
            contentValues.put("phone", s.getPhone());


            long result = db.insert("security", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else if (operation.equalsIgnoreCase("delete")) {                                // DELETE

            // [0] = SECURITY ID
            Cursor cursor = db.rawQuery("select * from security where id = ?", new String[]{s.getId()}); /* to check if the given id is exist or not   */
            if (cursor.getCount() > 0) {
                long result = db.delete("security", "id=?", new String[]{s.getId()}); /* delete will always return true even if it is null value */
                if (result == -1) {
                    cursor.close();
                    return false;

                } else {
                    cursor.close();
                    return true;
                }
            }
        }
        return false;

    }

    private boolean ticketTable(String operation, Object obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        Ticket t = (Ticket) obj;

        ContentValues contentValues = new ContentValues();

        if (operation.equalsIgnoreCase("insert")) {                                      // INSERT
            contentValues.put("id", t.getId());
            contentValues.put("plate", t.getPlate());
            contentValues.put("price", t.getPrice());
            contentValues.put("location", t.getLocation());
            contentValues.put("time",t.getTime());
            contentValues.put("status", t.getStatus());
            contentValues.put("approved", t.getApproved());
            contentValues.put("driverID", t.getDriverID());
            Bitmap bitmap = t.getViolationImg();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);    // Converting Bitmap to array of bytes
            byte[] img = byteArray.toByteArray();
            contentValues.put("violation", img);
            long result = db.insert("ticket", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else  if (operation.equalsIgnoreCase("delete")){
            Cursor cursor = db.rawQuery("select * from ticket where id = ?", new String[]{t.getId()+""});
            if (cursor.getCount() > 0){
                long result = db.delete("ticket", "id=?", new String[]{t.getId()+""});
                if (result == -1) {
                    cursor.close();
                    return false;

                } else {
                    cursor.close();
                    return true;
                }
            }
        }

        return false;
    }

    public Object getData(String tableName, String id) {
        if (tableName.equalsIgnoreCase("driver")) {
            return getDriver(id);
        } else if (tableName.equalsIgnoreCase("security")) {
            return getSecurity(id);
        } else if (tableName.equalsIgnoreCase("ticket")) {
            return getTicket(id);
        } else if (tableName.equalsIgnoreCase("admin")) {
            return getAdmin(id);
        }
        return null;

    }

    private Driver getDriver(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM driver where id=? ", new String[]{id});
        if (c.moveToFirst()) {

            Driver d = new Driver();
            d.setId(c.getString(0));
            d.setPassword(c.getString(1));
            d.setName(c.getString(2));
            d.setPhone(c.getString(3));
            d.setPlate(c.getString(4));
            c.close();
            return d;

        }
        return null;
    }

    private Security getSecurity(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM security where id=? ", new String[]{id});
        if (c.moveToFirst()) {

            Security s = new Security();
            s.setId(c.getString(0));
            s.setPassword(c.getString(1));
            s.setName(c.getString(2));
            s.setPhone(c.getString(3));
            c.close();
            return s;

        }
        return null;
    }

    private Admin getAdmin(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Admin where id=? ", new String[]{id});
        if (c.moveToFirst()) {

            Admin a = new Admin();
            a.setId(c.getString(0));
            a.setPassword(c.getString(1));
            a.setName(c.getString(2));
            a.setPhone(c.getString(3));
            c.close();
            return a;

        }
        return null;
    }

    private ArrayList getTicket(String id) { // get all tickets that the driver have
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Ticket where driverID=? ", new String[]{id});
        ArrayList arr = new ArrayList();
        if (c.moveToFirst()) {
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
            } while (c.moveToNext());
            c.close();
            return arr;

        }
        return null;
    }

    public String getDriverID(String plate) { // to fill ticket driverID attribute by passing the plate number
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM driver where plate=? ", new String[]{plate});
        c.moveToFirst();
        int i = c.getCount();

        if (i>0){
            String s = c.getString(0);
            c.close();
            return s;
        }
        c.close();
        return "0";
    }

    public String getTicketID() { // to assign ID to new ticket by returning the max id in the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select max(id) from Ticket", null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maxID = cursor.getString(0);
                return maxID;

            }
            cursor.close();
        }
            return "0";
    }

    public boolean makeObjection(String ticketID){ // this method is for driver to make objection
        SQLiteDatabase db = this.getWritableDatabase();
        Ticket t = (Ticket) this.getData("ticket",ticketID);
        ContentValues contentValues = new ContentValues();
        if (t.getApproved()!=2) { // if the approved attribute value is 2 this means that the ticket is already checked and approved
            contentValues.put("approved",0);
            long result = db.update("ticket", contentValues,"id=?", new String[]{t.getId() + ""});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean approveTicket(String ticketID){ // this method is for admin to approve that the ticket is deserved
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("approved",2); // 2 means that the ticket can not be objected any more
        long result = db.update("ticket", contentValues,"id=?", new String[]{ticketID});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


}





