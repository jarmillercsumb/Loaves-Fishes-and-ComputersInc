
package com.example.loavesfishesandcomputers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class LFCDbAdapter {

    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "LFCDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    // Arrays of Data
	public String[] class_name = null;
	public String[] dayofweek = null;
	public String[] starttime = null;
	public String[] ids = null;
	public String[] instructor = null;
	public String[] location = null;
	public String[] description = null;
	
	public String[][] how2info = null;
	public String[][] how2s = null;

    /**
     * Database creation sql statement
     */

    private static final String DATABASE_NAME = "lfc_app";
    private static final String DATABASE_HOW2_TABLE = "lfc_how2s";
    private static final String DATABASE_HOW2_STEPS_TABLE = "lfc_how2_steps";
    private static final String DATABASE_CATEGORY_TABLE = "lfc_categories";
    private static final String DATABASE_HOW2_CAT_TABLE = "lfc_how2_categories";
    private static final int DATABASE_VERSION = 2;
    
    private static final String DATABASE_CREATE1 =
			"create table "+DATABASE_HOW2_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " how2_id text,"
				+ " title text,"
				+ " tagline text,"
				+ " body text,"
				+ " last_updated text,"
				+ " language text,"
				+ " image text);";
    private static final String DATABASE_CREATE2 =
			"create table "+DATABASE_HOW2_STEPS_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " how2_id text,"
				+ " how2_step_id text,"
				+ " title text,"
				+ " body text,"
				+ " position text);";
    private static final String DATABASE_CREATE3 =
			"create table "+DATABASE_CATEGORY_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " category text,"
				+ " last_modified text"
				+ ");";
    private static final String DATABASE_CREATE4 =
			"create table "+DATABASE_HOW2_CAT_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " how2_id text,"
				+ " category_id text"
				+ ");";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE2);
            db.execSQL(DATABASE_CREATE3);
            db.execSQL(DATABASE_CREATE4);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_HOW2_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_HOW2_STEPS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_CATEGORY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_HOW2_CAT_TABLE);
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public LFCDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public LFCDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    
    public long createHow2(String how2_id, String title, String tagline, String body, String image, String last_updated, String language) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("how2_id", how2_id);
        initialValues.put("title", title);
        initialValues.put("tagline", tagline);
        initialValues.put("body", body);
        initialValues.put("image", image);
        initialValues.put("last_updated", last_updated);
        initialValues.put("language", language);
        
        return mDb.insert(DATABASE_HOW2_TABLE, null, initialValues);
    }

    public long createHow2Step(String how2_step_id, String how2_id, String title, String body, String position) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("how2_step_id", how2_step_id);
        initialValues.put("how2_id", how2_id);
        initialValues.put("title", title);
        initialValues.put("body", body);
        initialValues.put("position", position);

        return mDb.insert(DATABASE_HOW2_STEPS_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     * 
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean delete(String tableName, long rowId) {

        return mDb.delete(tableName, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    // was used before to clean up database for resetting. Now marked private to avoid accidentally emptying the database.
    public boolean cleanup() {
    	mDb.delete(DATABASE_HOW2_TABLE,null,null);
    	mDb.delete(DATABASE_HOW2_STEPS_TABLE,null,null);
    	return true;
    }
    
    protected Cursor fetchHow2(Integer how2) {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_TABLE+" where _id='"+Integer.toString(how2)+"'",null);
    }
    
    protected Cursor fetchHow2s() {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_TABLE+"",null);
    }
    
    protected Cursor fetchHow2Steps(Integer how2) {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_STEPS_TABLE+" where how2_id='" + Integer.toString(how2) + "' order by position asc",null);
    }
    
    protected boolean buildHow2s() {
    	Cursor c = fetchHow2s();
    	
    	Integer i=null;
    	how2s = new String[c.getCount()][4];
    	for (i=0; i<c.getCount(); i++) {
    		c.moveToPosition(i);
    		how2s[i][0] = c.getString(c.getColumnIndexOrThrow("how2_id"));
    		how2s[i][1] = c.getString(c.getColumnIndexOrThrow("title"));
    		how2s[i][2] = c.getString(c.getColumnIndexOrThrow("tagline"));
    		how2s[i][3] = c.getString(c.getColumnIndexOrThrow("image"));
    	}
    	return true;
    }
    
    protected boolean buildHow2info(Integer how2) {
    	Cursor c = fetchHow2Steps(how2);
    	
    	Integer i=null;
    	how2info = new String[c.getCount()][5];
    	for (i=0; i<c.getCount(); i++) {
    		c.moveToPosition(i);
    		how2info[i][0] = c.getString(c.getColumnIndexOrThrow("how2_id"));
    		how2info[i][1] = c.getString(c.getColumnIndexOrThrow("how2_step_id"));
    		how2info[i][2] = c.getString(c.getColumnIndexOrThrow("title"));
    		how2info[i][3] = c.getString(c.getColumnIndexOrThrow("body"));
    		how2info[i][4] = c.getString(c.getColumnIndexOrThrow("position"));
    	}
    	return true;
    }
    
    public int countHow2Steps(Integer how2) {
    	Cursor c = mDb.rawQuery("select * from "+DATABASE_HOW2_STEPS_TABLE+" where how2_id='" + Integer.toString(how2) + "'",null);
    	
    	int return_val = 0;
    	return_val = c.getCount();
    	
    	return return_val;
    }
    
    public String getLastUpdated() {
    	Cursor c = mDb.rawQuery("select last_updated from "+DATABASE_HOW2_TABLE+" ORDER BY last_updated desc LIMIT 1",null);
    	
    	String return_val = null;
    	
    	if ((c.moveToFirst()) || c.getCount() !=0){
    		return_val =  c.getString(c.getColumnIndexOrThrow("last_updated"));
    	}
    	return return_val;
    }
    
    public String[][] getHow2s() {
    	buildHow2s();
    	
    	return how2s;
    }
    
    public String[][] getHow2info(Integer how2_id) {
    	buildHow2info(how2_id);
    	
    	return how2info;
    }
    
    public Cursor fetchRow(String rowId, String table_name) throws SQLException {

        Cursor mCursor = 
        		mDb.rawQuery("SELECT * FROM "+table_name+" where _id='"+rowId+"'", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public long checkHow2ID(String id) {
    	Cursor c = mDb.rawQuery("select _id from "+DATABASE_HOW2_TABLE+" WHERE how2_id='"+id+"' LIMIT 1",null);
    	
    	long return_val = 0;
    	
    	if ((c.moveToFirst()) || c.getCount() !=0){
            return_val =  c.getLong(c.getColumnIndex("_id"));
        }
    	return return_val; 
    }

    public boolean updateHow2(String how2_id, String title, String tagline, String body, String image, String last_updated, String language) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("how2_id", how2_id);
        initialValues.put("title", title);
        initialValues.put("tagline", tagline);
        initialValues.put("body", body);
        initialValues.put("image", image);
        initialValues.put("last_updated", last_updated);
        initialValues.put("language", language);

        return mDb.update(DATABASE_HOW2_TABLE, initialValues, "how2_id=" + how2_id, null) > 0;
    }

    public boolean updateHow2Step(String how2_step_id, String how2_id, String title, String body, String position) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("how2_step_id", how2_step_id);
        initialValues.put("how2_id", how2_id);
        initialValues.put("title", title);
        initialValues.put("body", body);
        initialValues.put("position", position);

        return mDb.update(DATABASE_HOW2_STEPS_TABLE, initialValues, "how2_step_id=" + how2_step_id, null) > 0;
    }
    
    public String urlCall() {
    	URL url = null;
        InputStream in = null;

    	try {
			url = new URL("http://174.121.85.157/~lfs/index.php?option=com_lfc_app&task=how_tos.getjson&last_updated="+getLastUpdated());
		} catch (MalformedURLException e) {
			return "1";
		}
		
		try {
			in = url.openStream();
		} catch (IOException e) {
			return e.toString();
		}
		InputStreamReader reader = new InputStreamReader(in);
		String encoding = reader.getEncoding();
		// read the JSON data
		StringWriter writer = new StringWriter();
		
		try {
			IOUtils.copy(in, writer, encoding);
		} catch (IOException e1) {
			return "4";
		}
		
		Long itemid = null;
		cleanup();
		try {
			JSONArray mainArray = new JSONArray(writer.toString());
			for (int i = 0 ; i < mainArray.length() ; i ++)
			{
				JSONObject currentItem = mainArray.getJSONObject(i);
				//if ((itemid = checkHow2ID(currentItem.getString("id"))) > 0) {
				//	updateHow2(currentItem.getString("id"), currentItem.getString("title"), currentItem.getString("tagline"), currentItem.getString("body"), currentItem.getString("image"), currentItem.getString("last_updated"), currentItem.getString("language"));
				//} else {
					itemid = createHow2(currentItem.getString("id"), currentItem.getString("title"), currentItem.getString("tagline"), currentItem.getString("body"), currentItem.getString("image"), currentItem.getString("last_updated"), currentItem.getString("language"));
				//}
			    
			    JSONArray currentItemStepsArray = currentItem.getJSONArray("steps"); 
			    for (int j = 0 ; j < currentItemStepsArray.length() ; j ++)
			    {
			    	JSONObject currentStep = currentItemStepsArray.getJSONObject(j);
			    	//if ((itemid = checkHow2ID(currentStep.getString("id"))) > 0) {
			    	//	updateHow2Step(currentStep.getString("id"), currentStep.getString("how_to_id"), currentStep.getString("title"), currentStep.getString("body"), currentStep.getString("position"));
					//} else {
						createHow2Step(currentStep.getString("id"), currentStep.getString("how_to_id"), currentStep.getString("title"), currentStep.getString("body"), currentStep.getString("position"));
					//}
			    	
			    }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "5";
		}
		
		return writer.toString();

    }
    
}
