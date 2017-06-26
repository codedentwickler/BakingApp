package com.example.codedentwickler.bakingapp.data.local.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.codedentwickler.bakingapp.data.local.provider.IngredientContract.CONTENT_AUTHORITY;
import static com.example.codedentwickler.bakingapp.data.local.provider.IngredientContract.Entry.TABLE_NAME;
import static com.example.codedentwickler.bakingapp.data.local.provider.IngredientContract.Entry;

/**
 * Created by codedentwickler on 6/24/17.
 */

public class IngredientsProvider extends ContentProvider {

    private static final int INGREDIENTS = 10;
    private static final int INGREDIENTS_ID = 11;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(CONTENT_AUTHORITY, TABLE_NAME, INGREDIENTS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, TABLE_NAME + "/#", INGREDIENTS_ID);
    }

    private IngredientsDbHelper mDbHelper;


    @Override
    public boolean onCreate() {
        mDbHelper = new IngredientsDbHelper(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(IngredientContract.Entry.TABLE_NAME);

        int match = sUriMatcher.match(uri);

        switch (match) {
            case INGREDIENTS:
                break;

            case INGREDIENTS_ID:
                queryBuilder.appendWhere(Entry._ID + "=" + uri.getLastPathSegment());

            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);

        }

        Cursor retCursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, null);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case INGREDIENTS:
                int noOfRows = database.delete(TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return noOfRows;

            case INGREDIENTS_ID:

                int noOfRows2 = database.delete(TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return noOfRows2;

            default:
                throw new IllegalArgumentException("Deletion cannot be done on unknown uri" + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        switch (match) {
            case INGREDIENTS:
                int count = database.update(TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;


            case INGREDIENTS_ID:
                String whereCondition = Entry._ID + " = " + uri.getLastPathSegment();
                if (!selection.isEmpty()) {
                    whereCondition += " AND " + selection;
                }
                int id = database.update(TABLE_NAME, values, whereCondition, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return id;

            default:
                throw new IllegalArgumentException("Cannot update row with unknown uri" + uri);
        }
    }
}
