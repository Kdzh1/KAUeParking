package com.example.kaueparking;

import android.graphics.Bitmap;

public class Ticket {
    private int id;
    private String plate;
    private String price;
    private String location;
    private String time;
    private int status = 0; // Ticket is not paid
    private int approved = 1; // Ticket is approved and no need for admin to check it
    private String driverID;
    private Bitmap violationImg;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public Bitmap getViolationImg() {
        return violationImg;
    }

    public String getDescription() {
        return description;
    }
    public boolean ensureFilling(){
        if (this.id < 0){
            return false;
        }if (this.plate == null){
            return false;
        }if (this.price == null){
            return false;
        }if (this.time == null){
            return false;
        }
        return true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", price='" + price + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", approved=" + approved +
                ", driverID='" + driverID + '\'' +
                ", violationImg=" + violationImg +
                '}';
    }

    public void setViolationImg(Bitmap violationImg) {
        this.violationImg = violationImg;
    }
}
