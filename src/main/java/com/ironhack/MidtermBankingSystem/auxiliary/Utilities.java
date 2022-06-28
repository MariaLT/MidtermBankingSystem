package com.ironhack.MidtermBankingSystem.auxiliary;

import java.util.Random;

public class Utilities {

    public static Long randomId(){
        Random random = new Random();
        Long randomId = random.nextLong(18);
        return randomId;
    }

}
