package com.example.kaueparking;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainActivityTest {

    @Test
    public void verifyUser() {
        Security s = new Security();
        s.setId("1222");
        s.setPassword("123");
        String inputID="1222";
        String inputPass="123";

        boolean result= s.verifyPass(inputPass);
        assertEquals(true, result);
    }
}