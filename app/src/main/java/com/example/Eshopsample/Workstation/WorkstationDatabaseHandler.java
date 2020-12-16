package com.example.Eshopsample.Workstation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.Eshopsample.PcTower.PcTowerConstants;
import com.example.Eshopsample.PersonalComputer.PersonalComputer;
import com.example.Eshopsample.PersonalComputer.PersonalComputerConstants;

import java.util.ArrayList;
import java.util.List;

public class WorkstationDatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;


    public WorkstationDatabaseHandler(@Nullable Context context) {
        super(context, WorkstationConstants.DB_NAME, null, WorkstationConstants.DB_VERSION);
        this.ctx=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_WORKSTATION_TABLE = "CREATE TABLE " + WorkstationConstants.TABLE_NAME + "("
                + WorkstationConstants.KEY_ID + " INTEGER PRIMARY KEY," + WorkstationConstants.KEY_MEMORY_GB + " LONG,"
                + WorkstationConstants.KEY_FREQUENCY_HZ + " REAL,"
                + WorkstationConstants.KEY_SIZE_INCHES + " LONG,"
                + WorkstationConstants.KEY_HARD_DISK_GB + " LONG,"
                + WorkstationConstants.KEY_OPERATING_SYSTEM + " TEXT);";
        db.execSQL(CREATE_WORKSTATION_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + WorkstationConstants.TABLE_NAME);

        onCreate(db);

    }

    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */

    //Add workstation
    public void addWorkstation(WorkStation workStation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WorkstationConstants.KEY_MEMORY_GB, workStation.getMemoryGb());
        values.put(WorkstationConstants.KEY_FREQUENCY_HZ, workStation.getCpuFrequency());
        values.put(WorkstationConstants.KEY_SIZE_INCHES, workStation.getScreenSizeInches());
        values.put(WorkstationConstants.KEY_HARD_DISK_GB, workStation.getHardDiskGB());
        values.put(WorkstationConstants.KEY_OPERATING_SYSTEM, workStation.getOperatingSystem());
        //Insert the row
        db.insert(WorkstationConstants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");

    }


    //Get a workstation
    public WorkStation getWorkstation (int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PersonalComputerConstants.TABLE_NAME, new String[] {WorkstationConstants.KEY_ID,
                       WorkstationConstants.KEY_MEMORY_GB, WorkstationConstants.KEY_FREQUENCY_HZ,
                        WorkstationConstants.KEY_SIZE_INCHES,WorkstationConstants.KEY_HARD_DISK_GB,WorkstationConstants.KEY_OPERATING_SYSTEM},
                WorkstationConstants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        WorkStation workStation = new WorkStation();
        workStation.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(WorkstationConstants.KEY_ID))));
        workStation.setMemoryGb(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_MEMORY_GB)));
        workStation.setCpuFrequency(cursor.getDouble(cursor.getColumnIndex(WorkstationConstants.KEY_FREQUENCY_HZ)));
        workStation.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_SIZE_INCHES)));
        workStation.setHardDiskGB(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_HARD_DISK_GB)));
        workStation.setOperatingSystem(cursor.getString(cursor.getColumnIndex(WorkstationConstants.KEY_OPERATING_SYSTEM)));
        //convert timestamp to something readable


        return workStation;
    }


    //Get all personal computers
    public List<WorkStation> getAllWorkstations() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<WorkStation> workStationList = new ArrayList<>();

        Cursor cursor = db.query(WorkstationConstants.TABLE_NAME, new String[] {
               WorkstationConstants.KEY_ID, WorkstationConstants.KEY_MEMORY_GB,
                WorkstationConstants.KEY_FREQUENCY_HZ,WorkstationConstants.KEY_SIZE_INCHES,WorkstationConstants.KEY_HARD_DISK_GB,WorkstationConstants.KEY_OPERATING_SYSTEM},
                null, null, null, null,
                WorkstationConstants.KEY_MEMORY_GB + " DESC");

        if (cursor.moveToFirst()) {
            do {
                WorkStation workStation = new WorkStation();
                workStation.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(WorkstationConstants.KEY_ID))));
                workStation.setMemoryGb(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_MEMORY_GB)));
                workStation.setCpuFrequency(cursor.getDouble(cursor.getColumnIndex(WorkstationConstants.KEY_FREQUENCY_HZ)));
                workStation.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_SIZE_INCHES)));
                workStation.setHardDiskGB(cursor.getInt(cursor.getColumnIndex(WorkstationConstants.KEY_HARD_DISK_GB)));
                workStation.setOperatingSystem(cursor.getString(cursor.getColumnIndex(WorkstationConstants.KEY_OPERATING_SYSTEM)));



                workStationList.add(workStation);

            }while (cursor.moveToNext());
        }

        return workStationList;
    }


    public void deleteAllWorkstations(){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ WorkstationConstants.TABLE_NAME);
    }
    //Updated workstation
    public int updateWorkstation(WorkStation workStation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WorkstationConstants.KEY_MEMORY_GB, workStation.getMemoryGb());
        values.put(WorkstationConstants.KEY_FREQUENCY_HZ, workStation.getCpuFrequency());
        values.put(WorkstationConstants.KEY_SIZE_INCHES, workStation.getScreenSizeInches());
        values.put(WorkstationConstants.KEY_HARD_DISK_GB, workStation.getHardDiskGB());
        values.put(WorkstationConstants.KEY_OPERATING_SYSTEM, workStation.getOperatingSystem());

        //update row
        return db.update(PersonalComputerConstants.TABLE_NAME, values, PersonalComputerConstants.KEY_ID + "=?", new String[] { String.valueOf(workStation.getId())} );
    }


    //Delete workstation
    public void deleteWorkstaion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WorkstationConstants.TABLE_NAME, WorkstationConstants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }



    //Get count
    public int getPersonalWorkstation() {
        String countQuery = "SELECT * FROM " + WorkstationConstants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
