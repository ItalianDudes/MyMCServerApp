package it.italiandudes.mymcserver.inputfilters;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import it.italiandudes.mymcserver.Constants;
import it.italiandudes.mymcserver.utils.exceptions.NameTooLongException;

public class MinMaxStringLengthFilter implements InputFilter {

    private int min;
    private int max;

    public MinMaxStringLengthFilter(int min, int max){
        this.min=min;
        this.max=max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try{
            String input = source.toString();

            Log.d(Constants.Log.TAG,"MinMaxStringLength Filter Input: "+input);
            if(input.length()<max && input.length()>min){
                return null;
            }
        }catch(NameTooLongException exception){}

        return "";
    }
}
