package it.italiandudes.mymcserver.inputfilters;

import android.text.InputFilter;
import android.text.Spanned;

public class MinMaxFilter implements InputFilter {

    private int min;
    private int max;

    public MinMaxFilter(int min, int max){
        this.min=min;
        this.max=max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try{
            int input = Integer.parseInt(dest.toString() + source.toString());

            if(input<max && input>min){
                return null;
            }
        }catch(NumberFormatException exception){}

        return "";
    }
}
