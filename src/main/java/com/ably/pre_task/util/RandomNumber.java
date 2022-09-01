package com.ably.pre_task.util;

import java.util.Random;

public class RandomNumber {

    private static final int certNumLength = 6;

    public static String generate() {
        Random random = new Random(System.currentTimeMillis());

        int range = (int) Math.pow(10, certNumLength);
        int trim = (int) Math.pow(10, certNumLength - 1);
        int result = random.nextInt(range) + trim;

        if (result > range) {
            result = result - trim;
        }

        return String.valueOf(result);
    }

}
