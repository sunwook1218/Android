package com.hs.mobile.gw.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomIconDBHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "customIconManager";
	private static final String USER_IMAGE_TABLE = "customicon";
	
	private static final String KEY_NAME = "name";
    private static final String KEY_VERSION = "version";
    private static final String KEY_BITMAP = "bitmap";    
	
    private static CustomIconDBHelper dbHelper; 
    public static CustomIconDBHelper getDBHelper(Context context){
    	if(dbHelper == null){
    		dbHelper = new CustomIconDBHelper(context);
    		//dbHelper.getWritableDatabase().delete(USER_IMAGE_TABLE, null, null);
    	}    	    	
    	return dbHelper;
    }
    
	public CustomIconDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {	
		db.execSQL("DROP TABLE IF EXISTS '" + USER_IMAGE_TABLE + "'");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USER_IMAGE_TABLE + "("
                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_VERSION + " NUMBER,"
                + KEY_BITMAP + " BLOB)";		
        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_IMAGE_TABLE);
        onCreate(db);
	}
	
	public void addImage(CustomIcon icon){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, icon.getName());
		values.put(KEY_VERSION, icon.getVersion());
		values.put(KEY_BITMAP, icon.getByteArray());
		db.insert(USER_IMAGE_TABLE, null, values);
	}
	
	public CustomIcon getIcon(String name){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(USER_IMAGE_TABLE, new String[] { KEY_NAME,
                KEY_VERSION, KEY_BITMAP}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null){  
            if(!cursor.moveToFirst()){
            	return null;
            }
        }else{
        	return null;
        }
        
        CustomIcon icon = new CustomIcon(cursor.getString(0), cursor.getInt(1), cursor.getBlob(2));
        return icon;
	}
	
	public void deleteIcon(String name){
		 SQLiteDatabase db = this.getWritableDatabase();
		    db.delete(USER_IMAGE_TABLE, KEY_NAME + " = ?",
		            new String[] { name });
		    db.close();
	}
	
	public void deleteImage(CustomIcon icon){
		deleteIcon(icon.getName());
	}
	
	public void updateIcon(CustomIcon icon){
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, icon.getName());
		values.put(KEY_VERSION, icon.getVersion());
		values.put(KEY_BITMAP, icon.getByteArray());
	    db.update(USER_IMAGE_TABLE, values, KEY_NAME + " = ?",
	            new String[] { icon.getName()});
	}

}
