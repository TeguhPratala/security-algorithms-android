package com.testing.securityalgorithms.algorithms;

import android.content.Context;

@SuppressWarnings("SpellCheckingInspection")
public class Vigenere26Char extends Algorithms {

    private static short[] charset;
    private short[] keySet;
    private static int MAX_CHARSET_LENGTH = 26;
    private boolean isValid;

    static{
        charset = new short[MAX_CHARSET_LENGTH];
        for(short i = 0 ; i < MAX_CHARSET_LENGTH; i++){
            charset[i] = i;
            i++;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    public Vigenere26Char(Context context) {
        super(context);
    }

    @Override
    public String encrypt(String input) {
        String upperCaseString = input.toUpperCase();
        char[] textChar = new char[upperCaseString.length()];

        if(keySet == null || keySet.length == 0 || !isAllAlphabet(input) || !isValid){
            return "";
        }

        for(int i = 0, j = 0 ; i < textChar.length; i ++){

            if(upperCaseString.charAt(i) != ' '){
                short temp =(short) (((upperCaseString.charAt(i) - 'A') + keySet[j % keySet.length] + 26 ) % 26);
                textChar[i] = (char) (temp + 'A');
                j++;
            }else{
                textChar[i] = ' ';
            }
        }

        return new String(textChar);
    }

    @Override
    public String decrypt(String input) {
        String upperCaseString = input.toUpperCase();
        char[] textChar = new char[upperCaseString.length()];

        if(keySet == null || keySet.length == 0 || !isAllAlphabet(input) || !isValid){
            return "";
        }

        for(int i = 0, j =0 ; i < textChar.length; i ++){

            if(upperCaseString.charAt(i) != ' '){
                short temp =(short) (((upperCaseString.charAt(i) - 'A') - keySet[j % keySet.length] + 26 ) % 26);
                j++;
                textChar[i] = (char) (temp + 'A');
            }else{
                textChar[i] = ' ';
            }
        }

        return new String(textChar);
    }

    @Override
    public void initKey(String key) {
        isValid = isAllAlphabet(key);

        if(isValid){
            String ignorCase = key.toUpperCase().trim();
            keySet = new short[key.length()];

            for(int i = 0 ; i < ignorCase.length(); i ++){
                keySet[i] = (short) (ignorCase.charAt(i) - 'A');
            }
        }
    }

    private boolean isAllAlphabet(String text){
        for(int i = 0 ; i < text.length(); i ++){
            if(!Character.isAlphabetic(text.charAt(i))){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Vigenere 26 Char";
    }
}
