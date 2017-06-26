package com.example.codedentwickler.bakingapp.data.local.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.codedentwickler.bakingapp.BuildConfig;

/**
 * Created by codedentwickler on 6/24/17.
 */

public final class IngredientContract {

    static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;

    private IngredientContract(){}

    public static class Entry implements BaseColumns {

        private static Uri.Builder builder = new Uri.Builder()
                .scheme("content").authority(CONTENT_AUTHORITY);

        private static final Uri BASE_CONTENT_URI = Uri.parse(builder.build().toString());

        private static final String PATH_INGREDIENTS = "ingredients";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INGREDIENTS);

        static final String TABLE_NAME = "ingredients";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_NAME_QUANTITY = "quantity";

        public static final String COLUMN_NAME_MEASURE = "measure";

        public static final String COLUMN_NAME_INGREDIENTS = "ingredient";



        static String[] INGREDIENTS_COLUMNS = new String[]{
                _ID,
                COLUMN_NAME_QUANTITY,
                COLUMN_NAME_MEASURE,
                COLUMN_NAME_INGREDIENTS
        };
    }

}
