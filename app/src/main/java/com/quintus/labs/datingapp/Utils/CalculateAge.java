package com.quintus.labs.datingapp.Utils;

import java.util.Calendar;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */

public class CalculateAge {
    private int age;

    public CalculateAge(String dob) {
        String[] splitDOB = dob.split("-");
        setAge(Integer.parseInt(splitDOB[2]), Integer.parseInt(splitDOB[0]), Integer.parseInt(splitDOB[1]));
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int year, int month, int day) {
        Calendar dateOfBirth = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dateOfBirth.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        this.age = age;
    }
}
