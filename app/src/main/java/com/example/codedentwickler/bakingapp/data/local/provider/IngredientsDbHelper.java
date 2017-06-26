package com.example.codedentwickler.bakingapp.data.local.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.codedentwickler.bakingapp.data.local.provider.IngredientContract.Entry;

/**
 * Created by codedentwickler on 6/24/17.
 */

public class IngredientsDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "ingredients.db";

    private static final String PRIMARY_KEY_AUTO_INCREMENT = " PRIMARY KEY AUTOINCREMENT, ";

    private static final String TEXT = " TEXT, ";

    private static final String TEXT_NO_COMMA = " TEXT";

    private static final String NOT_NULL = " NOT NULL, ";

    private static final String UNIQUE = " UNIQUE";

    private static final String INTEGER = " INTEGER";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + IngredientContract.Entry.TABLE_NAME + " (" +
                    Entry._ID + INTEGER + PRIMARY_KEY_AUTO_INCREMENT +
                    Entry.COLUMN_NAME_QUANTITY + INTEGER +
                    Entry.COLUMN_NAME_INGREDIENTS + TEXT +
                    Entry.COLUMN_NAME_MEASURE + TEXT_NO_COMMA +
                    " );";

    private static final String DROP_TABLE_QUERY =
            " DROP TABLE " + Entry.TABLE_NAME+ ";";

    public IngredientsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }
}
