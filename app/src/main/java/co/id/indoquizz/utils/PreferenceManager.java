package co.id.indoquizz.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }
    //TODO SET


    //TODO GET


    //TODO CLEAR
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}