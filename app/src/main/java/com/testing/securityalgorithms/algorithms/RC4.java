package com.testing.securityalgorithms.algorithms;

import android.content.Context;


public class RC4 extends Algorithms {

    private static char[] key;
    private static char[] stream;
    private static char[] keyStream;

    private static int maxByteLength = 256;

    public RC4(Context context) {
        super(context);
    }

    @Override
    public String encrypt(String plaintext) {

        PRGA(plaintext);
        char[] holder = new char[plaintext.length()];
        for(int i = 0; i < plaintext.length(); i ++){
            holder[i] = (char) (keyStream[i] ^ plaintext.charAt(i));
        }

        return new String(holder);
    }

    @Override
    public String decrypt(String ciphertext) {

//        char[] holder = new char[ciphertext.length()];
//        for(int i = 0; i < holder.length; i++){
//            holder[i] = (char) (ciphertext.charAt(i) ^ keyStream[i]);
//        }
        return encrypt(ciphertext);
    }

    @Override
    public void initKey(String inputKeys){
        initKeyStream(inputKeys);
        KSA();
    }

    private void initKeyStream(String inputKeys){
        char[] keyChars = inputKeys.toCharArray();

        stream = new char[maxByteLength];
        key = new char[maxByteLength];

        for(char i = 0; i < maxByteLength ; i ++){
            stream[i] = i ;
            key[i] = keyChars[i % keyChars.length];
        }
    }

    private void KSA(){
        for(char i = 0,j = 0; i < maxByteLength ; i++){
            j = (char) ((j + stream[i] + key[i]) % maxByteLength);
            char temp = stream[i];
            stream[i] = stream[j];
            stream[j] = temp;
        }
    }

    private void PRGA(String plainText){
        keyStream = new char[plainText.length()];

        for(int i = 0, j, k = 0; i < plainText.length(); i ++){
            j = (i + 1 ) % maxByteLength;
            k = (k + stream[j]) % maxByteLength;
            char temp = stream[j];
            stream[j] = stream[k];
            stream[k] = temp;
            int index = (stream[j] + stream[k] )% maxByteLength;
            keyStream[i] = stream[index];
        }
    }

    @Override
    public String toString() {
        return "RC 4";
    }
}
