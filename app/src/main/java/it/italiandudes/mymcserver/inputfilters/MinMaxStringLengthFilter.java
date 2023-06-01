package it.italiandudes.mymcserver.inputfilters;

import android.text.InputFilter;
import android.text.Spanned;

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
            String input = dest.toString() + source.toString();

            if(input.length()<max && input.length()>min){
                return null;
            }
        }catch(NumberFormatException exception){}

        return "";
    }
}
