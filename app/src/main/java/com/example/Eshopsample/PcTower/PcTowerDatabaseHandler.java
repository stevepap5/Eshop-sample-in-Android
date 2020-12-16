package com.example.Eshopsample.PcTower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PcTowerDatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;


    public PcTowerDatabaseHandler(@Nullable Context context) {
        super(context, PcTowerConstants.DB_NAME, null, PcTowerConstants.DB_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PC_TOWER_TABLE = "CREATE TABLE " + PcTowerConstants.TABLE_NAME + "("
                + PcTowerConstants.KEY_ID + " INTEGER PRIMARY KEY," + PcTowerConstants.KEY_MEMORY_GB + " LONG,"
                + PcTowerConstants.KEY_FREQUENCY_HZ + " REAL);";

        db.execSQL(CREATE_PC_TOWER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PcTowerConstants.TABLE_NAME);

        onCreate(db);

    }

    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */

    //Add pcTower
    public void addPcTower(PcTower pcTower) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PcTowerConstants.KEY_MEMORY_GB, pcTower.getMemoryGb());
        values.put(PcTowerConstants.KEY_FREQUENCY_HZ, pcTower.getCpuFrequencyGhz());


        //Insert the row
        db.insert(PcTowerConstants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");

    }


    //Get a pctower
    public PcTower getPcTower(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PcTowerConstants.TABLE_NAME, new String[] {PcTowerConstants.KEY_ID,
                        PcTowerConstants.KEY_MEMORY_GB, PcTowerConstants.KEY_FREQUENCY_HZ},
                PcTowerConstants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        PcTower pcTower = new PcTower();
        pcTower.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PcTowerConstants.KEY_ID))));
        pcTower.setMemoryGb(cursor.getInt(cursor.getColumnIndex(PcTowerConstants.KEY_MEMORY_GB)));
        pcTower.setCpuFrequencyGhz(cursor.getDouble(cursor.getColumnIndex(PcTowerConstants.KEY_FREQUENCY_HZ)));

        //convert timestamp to something readable


        return pcTower;
    }


    //Get all pctowers
    public List<PcTower> getAllPcTowers() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<PcTower> pcTowerList = new ArrayList<>();

        Cursor cursor = db.query(PcTowerConstants.TABLE_NAME, new String[] {
                PcTowerConstants.KEY_ID, PcTowerConstants.KEY_MEMORY_GB,
                PcTowerConstants.KEY_FREQUENCY_HZ}, null, null, null, null, PcTowerConstants.KEY_MEMORY_GB + " DESC");

        if (cursor.moveToFirst()) {
            do {
                PcTower pcTower = new PcTower();
                pcTower.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PcTowerConstants.KEY_ID))));
                pcTower.setMemoryGb(cursor.getInt(cursor.getColumnIndex(PcTowerConstants.KEY_MEMORY_GB)));
                pcTower.setCpuFrequencyGhz(cursor.getDouble(cursor.getColumnIndex(PcTowerConstants.KEY_FREQUENCY_HZ)));




                pcTowerList.add(pcTower);

            }while (cursor.moveToNext());
        }

        return pcTowerList;
    }


    public void deleteAllPcTowers(){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ PcTowerConstants.TABLE_NAME);
    }
    //Updated Grocery
    public int updatePcTower(PcTower pcTower) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PcTowerConstants.KEY_MEMORY_GB, pcTower.getMemoryGb());
        values.put(PcTowerConstants.KEY_FREQUENCY_HZ, pcTower.getCpuFrequencyGhz());



        //update row
        return db.update(PcTowerConstants.TABLE_NAME, values, PcTowerConstants.KEY_ID + "=?", new String[] { String.valueOf(pcTower.getId())} );
    }


    //Delete pctower
    public void deletePcTower(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PcTowerConstants.TABLE_NAME, PcTowerConstants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }



    //Get count
    public int getPcTowerCount() {
        String countQuery = "SELECT * FROM " + PcTowerConstants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
