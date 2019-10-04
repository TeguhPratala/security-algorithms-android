package com.testing.securityalgorithms.algorithms;

        import android.content.Context;

public abstract class Algorithms {

    protected Context context;
    public Algorithms(Context context){
        this.context = context;
    }

    public abstract String encrypt(String input)throws Exception;
    public abstract String decrypt(String input) throws Exception;
    public abstract void initKey(String key);
}
