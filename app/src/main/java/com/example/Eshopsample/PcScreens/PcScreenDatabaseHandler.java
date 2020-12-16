package com.example.Eshopsample.PcScreens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.Eshopsample.PcTower.PcTowerConstants;

import java.util.ArrayList;
import java.util.List;

public class PcScreenDatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;

    public PcScreenDatabaseHandler(@Nullable Context context) {
        super(context, PcScreensConstants.DB_NAME, null, PcScreensConstants.DB_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IP_ADDRESS_TABLE = "CREATE TABLE " + PcScreensConstants.TABLE_NAME + "("
                + PcScreensConstants.KEY_ID + " INTEGER PRIMARY KEY,"
                + PcScreensConstants.KEY_SIZE_INCHES + " LONG);";

        db.execSQL(CREATE_IP_ADDRESS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PcScreensConstants.TABLE_NAME);

        onCreate(db);

    }

    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */

    //Add pcscreen
    public void addPcScreen(PcScreen pcScreen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PcScreensConstants.KEY_SIZE_INCHES, pcScreen.getScreenSizeInches());

        //Insert the row
        db.insert(PcScreensConstants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");

    }


    //Get a pcsreen
    public PcScreen getPcScreen(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PcTowerConstants.TABLE_NAME, new String[] {PcScreensConstants.KEY_ID,
                        PcScreensConstants.KEY_SIZE_INCHES},
                PcTowerConstants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        PcScreen pcScreen = new PcScreen();
        pcScreen.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PcScreensConstants.KEY_ID))));
        pcScreen.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(PcScreensConstants.KEY_SIZE_INCHES)));


        //convert timestamp to something readable


        return pcScreen;
    }


    //Get all pcscreens
    public List<PcScreen> getAllPcScreens() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<PcScreen> pcScreenList = new ArrayList<>();

        Cursor cursor = db.query(PcScreensConstants.TABLE_NAME, new String[] {
                PcScreensConstants.KEY_ID, PcScreensConstants.KEY_SIZE_INCHES,
                }, null, null, null, null, PcScreensConstants.KEY_SIZE_INCHES + " DESC");

        if (cursor.moveToFirst()) {
            do {
                PcScreen pcScreen = new PcScreen();
                pcScreen.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PcScreensConstants.KEY_ID))));
                pcScreen.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(PcScreensConstants.KEY_SIZE_INCHES)));




                // Add to the pcScreenList
                pcScreenList.add(pcScreen);

            }while (cursor.moveToNext());
        }

        return pcScreenList;
    }


    public void deleteAllPcScreens(){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ PcScreensConstants.TABLE_NAME);
    }
    //Updated pcscreen
    public int updatePcScreen(PcScreen pcScreen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PcScreensConstants.KEY_SIZE_INCHES, pcScreen.getScreenSizeInches());

        //update row
        return db.update(PcTowerConstants.TABLE_NAME, values, PcTowerConstants.KEY_ID + "=?", new String[] { String.valueOf(pcScreen.getId())} );
    }


    //Delete pcscreen
    public void deletePcScreen(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PcScreensConstants.TABLE_NAME, PcScreensConstants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }



    //Get count
    public int getPcScreenCount() {
        String countQuery = "SELECT * FROM " + PcScreensConstants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
