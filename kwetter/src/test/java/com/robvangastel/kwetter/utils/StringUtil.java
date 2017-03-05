package com.robvangastel.kwetter.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robvangastel
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        if ((s != null) && (s.trim().length() > 0))
            return false;
        else
            return true;
    }
}