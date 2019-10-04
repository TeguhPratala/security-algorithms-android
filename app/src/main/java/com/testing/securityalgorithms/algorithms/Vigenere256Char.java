package com.testing.securityalgorithms.algorithms;

import android.content.Context;

public class Vigenere256Char extends Algorithms {

    private static char[] keySet;
    private static final char MAX_CHAR_SET = 256;

    public Vigenere256Char(Context context) {
        super(context);
    }

    @Override
    public String encrypt(String input) {
        char[] plainChars = input.toCharArray();
        char[] ciperChars = new char[plainChars.length];
        for(int i = 0; i < plainChars.length; i ++){
            ciperChars[i] = (char) (((plainChars[i] + keySet[ i % keySet.length]) + MAX_CHAR_SET) % MAX_CHAR_SET);
        }

        return new String(ciperChars);
    }

    @Override
    public String decrypt(String input) {
        char[] ciperChars = input.toCharArray();
        char[] plainChars = new char[ciperChars.length];
        for(int i = 0; i < ciperChars.length; i ++){
            plainChars[i] = (char) (((ciperChars[i] - keySet[ i % keySet.length]) + MAX_CHAR_SET) % MAX_CHAR_SET);
        }

        return new String(plainChars);
    }

    @Override
    public void initKey(String key) {
        keySet = new char[key.length()];

        for(int i = 0; i < key.length(); i ++){
            keySet[i] = key.charAt(i);
        }
    }

    @Override
    public String toString() {
        return "Vigenere Char 256";
    }
}
