package com.senai.devinhouse.m1s09_spring_boot.utils;

public class UtilGeradorId {

    private static Integer idCustomer = 0;
    private static Integer idAccount = 0;

    public static int generateSequenceIdCustomer(){
        return idCustomer += 1;
    }

    public static int generateSequenceIdAccount(){
        return idAccount += 1;
    }

}
