package fr.hamchez.roundnettracker.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class ObjectSharedPreferences{

    private static String FILE_NAME = "ROUNDNET";

    public static void saveObjectToSharedPreference(Context context, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass>GenericClass getSavedObjectFromPreference(Context context, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

}
