package com.quintus.labs.datingapp.Utils;

/**
 * Created by Quintus Labs on 23-Dec-2018.
 * www.quintuslabs.com
 */

public class StringManipulation {

    public static String expandUsername(String username) {
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username) {
        return username.replace(" ", ".");
    }
}
