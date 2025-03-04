/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.littlepasscrack;

/**
 *
 * @author Amina Ferra
 */
import java.io.*;

public class Attacks {
    private static final String hPass = "aallo";
    private static final String name = "Amina";
    private static final String dFile = "C:\\Users\\Amina Ferra\\Downloads\\default-routers.txt"; //add your path here 

    public static boolean validateUser(String u, String p) {
        return u.equals(name) && p.equals(hPass);
    }

     public static String dAttack() {
        try (BufferedReader br = new BufferedReader(new FileReader(dFile))) {
            String l;
            while ((l = br.readLine()) != null) {
                if (l.equals(hPass)) {
                    return "Password found using dictionary attack: " + hPass;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dictionary file: " + e.getMessage());
        }
        return "";
    }

    public static String bfAttack() {
        char[] ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        return genComb("", ch, 5);
    }

    private static String genComb(String pre, char[] ch, int maxL) {
        if (pre.length() == maxL) {
            System.out.println("Trying: " + pre);
            if (pre.equals(hPass)) {
                return "Password found using brute force: " + pre;
            }
            return "";
        }
        for (char c : ch) {
            String result = genComb(pre + c, ch, maxL);
            if (!result.isEmpty()) return result;
        }
        return "";
    }
}