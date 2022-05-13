package com.example.kaueparking;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {

    @Test
    public void ensureFilling() {
        Ticket t = new Ticket();
        t.setId(1);
        t.setPrice("150");
        t.setLocation("jeddah");
        t.setTime("15.30");
        t.setPlate("8485EGD");
        boolean result = t.ensureFilling();
        assertEquals(true,result);
    }
}