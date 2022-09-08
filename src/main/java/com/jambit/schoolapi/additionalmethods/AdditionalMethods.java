package com.jambit.schoolapi.additionalmethods;

import java.util.regex.Pattern;

public class AdditionalMethods {
    public static boolean isEmailValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return email.matches(pattern.pattern());
    }

    public static boolean isNameValid(String name) {
        return name != null && name.length() > 0;
    }
}