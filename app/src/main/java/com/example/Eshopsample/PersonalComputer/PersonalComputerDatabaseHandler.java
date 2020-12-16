package com.example.Eshopsample.PersonalComputer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.Eshopsample.PcTower.PcTower;
import com.example.Eshopsample.PcTower.PcTowerConstants;

import java.util.ArrayList;
import java.util.List;

public class PersonalComputerDatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;


    public PersonalComputerDatabaseHandler(@Nullable Context context) {
        super(context, PersonalComputerConstants.DB_NAME, null, PersonalComputerConstants.DB_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IP_ADDRESS_TABLE = "CREATE TABLE " + PersonalComputerConstants.TABLE_NAME + "("
                + PersonalComputerConstants.KEY_ID + " INTEGER PRIMARY KEY," + PersonalComputerConstants.KEY_MEMORY_GB + " LONG,"
                +PersonalComputerConstants.KEY_FREQUENCY_HZ + " REAL,"+PersonalComputerConstants.KEY_SIZE_INCHES + " LONG,"+PersonalComputerConstants.KEY_HARD_DISK_GB + " LONG);";

        db.execSQL(CREATE_IP_ADDRESS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PersonalComputerConstants.TABLE_NAME);

        onCreate(db);

    }

    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */

    //Add pcTower
    public void addPersonalComputer(PersonalComputer personalComputer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalComputerConstants.KEY_MEMORY_GB, personalComputer.getMemoryGb());
        values.put(PersonalComputerConstants.KEY_FREQUENCY_HZ, personalComputer.getCpuFrequency());
        values.put(PersonalComputerConstants.KEY_SIZE_INCHES, personalComputer.getScreenSizeInches());
        values.put(PersonalComputerConstants.KEY_HARD_DISK_GB, personalComputer.getHardDiskGB());

        //Insert the row
        db.insert(PersonalComputerConstants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");

    }


    //Get a personalComputer
    public PersonalComputer getPersonalComputer (int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PersonalComputerConstants.TABLE_NAME, new String[] {PersonalComputerConstants.KEY_ID,
                        PersonalComputerConstants.KEY_MEMORY_GB, PersonalComputerConstants.KEY_FREQUENCY_HZ,
                        PersonalComputerConstants.KEY_SIZE_INCHES,PersonalComputerConstants.KEY_HARD_DISK_GB},
                PersonalComputerConstants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        PersonalComputer personalComputer = new PersonalComputer();
        personalComputer.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PersonalComputerConstants.KEY_ID))));
        personalComputer.setMemoryGb(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_MEMORY_GB)));
        personalComputer.setCpuFrequency(cursor.getDouble(cursor.getColumnIndex(PersonalComputerConstants.KEY_FREQUENCY_HZ)));
        personalComputer.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_SIZE_INCHES)));
        personalComputer.setHardDiskGB(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_HARD_DISK_GB)));
        //convert timestamp to something readable


        return personalComputer;
    }


    //Get all personal computers
    public List<PersonalComputer> getAllPersonalComputers() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<PersonalComputer> personalComputerList = new ArrayList<>();

        Cursor cursor = db.query(PersonalComputerConstants.TABLE_NAME, new String[] {
                PersonalComputerConstants.KEY_ID, PersonalComputerConstants.KEY_MEMORY_GB,
                PcTowerConstants.KEY_FREQUENCY_HZ,PersonalComputerConstants.KEY_SIZE_INCHES,PersonalComputerConstants.KEY_HARD_DISK_GB},
                null, null, null, null, PersonalComputerConstants.KEY_MEMORY_GB + " DESC");

        if (cursor.moveToFirst()) {
            do {
                PersonalComputer personalComputer = new PersonalComputer();
                personalComputer.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PersonalComputerConstants.KEY_ID))));
                personalComputer.setMemoryGb(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_MEMORY_GB)));
                personalComputer.setCpuFrequency(cursor.getDouble(cursor.getColumnIndex(PersonalComputerConstants.KEY_FREQUENCY_HZ)));
                personalComputer.setScreenSizeInches(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_SIZE_INCHES)));
                personalComputer.setHardDiskGB(cursor.getInt(cursor.getColumnIndex(PersonalComputerConstants.KEY_HARD_DISK_GB)));



                personalComputerList.add(personalComputer);

            }while (cursor.moveToNext());
        }

        return personalComputerList;
    }


    public void deleteAllPersonalComputers(){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ PersonalComputerConstants.TABLE_NAME);
    }
    //Updated personal computer
    public int updatePersonalComputer(PersonalComputer personalComputer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalComputerConstants.KEY_MEMORY_GB, personalComputer.getMemoryGb());
        values.put(PersonalComputerConstants.KEY_FREQUENCY_HZ, personalComputer.getCpuFrequency());
        values.put(PersonalComputerConstants.KEY_SIZE_INCHES, personalComputer.getScreenSizeInches());
        values.put(PersonalComputerConstants.KEY_HARD_DISK_GB, personalComputer.getHardDiskGB());


        //update row
        return db.update(PersonalComputerConstants.TABLE_NAME, values, PersonalComputerConstants.KEY_ID + "=?", new String[] { String.valueOf(personalComputer.getId())} );
    }


    //Delete personalcomputer
    public void deletePeronalComputer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PersonalComputerConstants.TABLE_NAME, PersonalComputerConstants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }



    //Get count
    public int getPersonalComputerCount() {
        String countQuery = "SELECT * FROM " + PersonalComputerConstants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
