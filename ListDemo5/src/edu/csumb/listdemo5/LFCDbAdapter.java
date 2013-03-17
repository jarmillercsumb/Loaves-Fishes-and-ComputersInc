
package edu.csumb.listdemo5;

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
				+ " title text not null,"
				+ " tagline text not null,"
				+ " body text not null,"
				+ " last_updated text not null,"
				+ " image text not null);";
    private static final String DATABASE_CREATE4 =
			"create table "+DATABASE_HOW2_STEPS_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " how2_id text not null,"
				+ " title text not null,"
				+ " body text not null,"
				+ " position text not null);";
    private static final String DATABASE_CREATE2 =
			"create table "+DATABASE_CATEGORY_TABLE+" ("
				+ " _id integer primary key autoincrement,"
				+ " category text not null,"
				+ " last_modified text not null"
				+ ");";
    private static final String DATABASE_CREATE3 =
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
    
    public long createHow2(String title, String tagline, String body, String image, String last_updated) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("title", title);
        initialValues.put("tagline", tagline);
        initialValues.put("body", body);
        initialValues.put("image", image);
        initialValues.put("last_updated", last_updated);
        
        return mDb.insert(DATABASE_HOW2_TABLE, null, initialValues);
    }

    public long createHow2Step(Integer how2_id, String title, String body, String position) {
        ContentValues initialValues = new ContentValues();
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
    
    public boolean cleanup() {
    	mDb.delete(DATABASE_HOW2_TABLE,null,null);
    	mDb.delete(DATABASE_HOW2_STEPS_TABLE,null,null);
    	return true;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     * 
     * @return Cursor over all notes
     */
    
    public Cursor fetchHow2(Integer how2) {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_TABLE+" where _id='"+Integer.toString(how2)+"'",null);
    }
    
    public Cursor fetchHow2s() {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_TABLE+"",null);
    }
    
    public Cursor fetchHow2Steps(Integer how2) {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_STEPS_TABLE+" where how2_id='" + Integer.toString(how2) + "' order by position asc",null);
    }
    
    public int countHow2Steps(Integer how2) {
    	Cursor c = mDb.rawQuery("select * from "+DATABASE_HOW2_STEPS_TABLE+" where how2_id='" + Integer.toString(how2) + "'",null);
    	
    	int return_val = 0;

    	return_val = c.getCount();
    	
    	return return_val;
    }
    
    public boolean buildHow2s() {
    	Cursor c = fetchHow2s();
    	
    	Integer i=null;
    	how2s = new String[c.getCount()][3];
    	for (i=0; i<c.getCount(); i++) {
    		c.moveToPosition(i);
    		//Toast.makeText(mCtx, c.getString(c.getColumnIndexOrThrow("class_name")), Toast.LENGTH_SHORT).show();
    		how2s[i][0] = c.getString(c.getColumnIndexOrThrow("title"));
    		how2s[i][1] = c.getString(c.getColumnIndexOrThrow("tagline"));
    		how2s[i][2] = c.getString(c.getColumnIndexOrThrow("image"));
    	}
    	return true;
    }
    
    public boolean buildHow2info(Integer how2) {
    	Cursor c = fetchHow2(how2);
    	
    	Integer i=null;
    	how2info = new String[c.getCount()][4];
    	for (i=0; i<c.getCount(); i++) {
    		c.moveToPosition(i);
    		//Toast.makeText(mCtx, c.getString(c.getColumnIndexOrThrow("class_name")), Toast.LENGTH_SHORT).show();
    		how2info[i][0] = c.getString(c.getColumnIndexOrThrow("how2_id"));
    		how2info[i][1] = c.getString(c.getColumnIndexOrThrow("title"));
    		how2info[i][2] = c.getString(c.getColumnIndexOrThrow("body"));
    		how2info[i][3] = c.getString(c.getColumnIndexOrThrow("position"));
    	}
    	return true;
    }
    
    public String[][] getHow2s() {
    	buildHow2s();
    	
    	return how2s;
    }
    
    public String[][] getHow2info(Integer how2_id) {
    	buildHow2info(how2_id);
    	
    	return how2info;
    }
    
    /*public Cursor fetchLatestAlert() {
    	return mDb.rawQuery("select * from "+DATABASE_CATEGORY_TABLE+" order by posted desc",null);
    }
    
    public Cursor latestAlert() {
    	Cursor c = fetchLatestAlert();
    	
    	//Integer i=null;
    	//String[] return_array = new String[1];
    	
    	//c.moveToFirst();
    		//Toast.makeText(mCtx, c.getString(c.getColumnIndexOrThrow("class_name")), Toast.LENGTH_SHORT).show();
    	//return_array[1] = c.getString(c.getColumnIndexOrThrow("class_name"));
    	
    	return c;
    }
    
    public boolean buildClassTimesArrays(String class_name_str) {
    	Cursor c = fetchClassTimes(class_name_str);
    	Integer i=null;
    	ids = new String[c.getCount()];
    	instructor = new String[c.getCount()];
    	location = new String[c.getCount()];
    	description = new String[c.getCount()];
    	starttime = new String[c.getCount()];
    	class_name = new String[c.getCount()];
    	dayofweek = new String[c.getCount()];
    	
    	for (i=0 ; i<c.getCount() ; i++) {
    		c.moveToPosition(i);
    		ids[i] = c.getString(
                    c.getColumnIndexOrThrow("_id"));
    		instructor[i] = c.getString(
                    c.getColumnIndexOrThrow("instructor"));
    		location[i] = c.getString(
                    c.getColumnIndexOrThrow("location"));
    		description[i] = c.getString(
                    c.getColumnIndexOrThrow("description"));
    		starttime[i] = c.getString(
                    c.getColumnIndexOrThrow("starttime"));
    		class_name[i] = c.getString(
                    c.getColumnIndexOrThrow("class_name"));
    		dayofweek[i] = c.getString(
                    c.getColumnIndexOrThrow("dayofweek"));
    	}
    	return true;
    }
    
    public Cursor fetchAllClassTimes(String class_name) {
    	return mDb.rawQuery("select * from "+DATABASE_HOW2_TABLE+" order by dayofweek asc, starttime asc",null);
    }*/

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
   /* public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_TITLE, KEY_BODY}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }*/
    public Cursor fetchRow(String rowId, String table_name) throws SQLException {

        Cursor mCursor = 
        		mDb.rawQuery("SELECT * FROM "+table_name+" where _id='"+rowId+"'", null);

            //mDb.query(true, DATABASE_HOW2_TABLE, new String[] {KEY_ROWID,
            //        KEY_TITLE, KEY_BODY}, KEY_ROWID + "=" + rowId, null,
            //        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     * 
     * @param rowId id of note to update
     * @param title value to set note title to
     * @param body value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    /*public boolean updateNote(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_BODY, body);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/

    public boolean updateHow2(Integer rowId, String title, String tagline, String body, String image, String last_updated) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("title", title);
        initialValues.put("tagline", tagline);
        initialValues.put("body", body);
        initialValues.put("image", image);
        initialValues.put("last_updated", last_updated);

        return mDb.update(DATABASE_HOW2_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean updateHow2Step(Integer rowId, Integer how2_id, String title, String body, String position) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("how2_id", how2_id);
        initialValues.put("title", title);
        initialValues.put("body", body);
        initialValues.put("position", position);

        return mDb.update(DATABASE_HOW2_STEPS_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean updateClass(Integer rowId, String instructor, String location, String description, String class_name, 
    		String dayofweek, String starttime, String posted, String modified) {
        ContentValues args = new ContentValues();
        args.put("instructor", instructor);
        args.put("location", location);
        args.put("description", description);
        args.put("class_name", class_name);
        args.put("dayofweek", dayofweek);
        args.put("starttime", starttime);
        args.put("posted", posted);
        args.put("modified", modified);

        return mDb.update(DATABASE_HOW2_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean updateAlert(Integer rowId, String notification, String posted, String expires) {
        ContentValues args = new ContentValues();
        args.put("notification", notification);
        args.put("posted", posted);
        args.put("expires", expires);

        return mDb.update(DATABASE_CATEGORY_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public String urlCall(Integer task) {
    	URL url = null;
        InputStream in = null;
        switch (task){
	        case 1: // ALERTS
	        	try {
	    			url = new URL("http://174.121.85.157/~pwc/lfc_test.php");
	    		} catch (MalformedURLException e) {
	    			return "1";
	    		}
	        	break;
	        /*case 2: // CLASSES
	        	try {
	    			url = new URL("http://174.121.85.157/~pwc/json.php?task=classes");
	    		} catch (MalformedURLException e) {
	    			return "2";
	    		}
	        	break;*/
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
		
		// removes all old items
		// TODO: Make this only add new items.
		
		cleanup();
		
		Long itemid = null;
		//Toast.makeText(mCtx, writer.toString(), Toast.LENGTH_SHORT).show();
		switch (task){
	        case 1: // ALERTS
        		//AlertList list = null;
        		//list = getAlertList(writer.toString());
        		//List <ItemsContainer> post_list = list.getAlertsContainerList();
				try {
					JSONArray mainArray = new JSONArray(writer.toString());
					System.out.print(mainArray);
					for (int i = 0 ; i < mainArray.length() ; i ++)
					{
						JSONObject currentItem = mainArray.getJSONObject(i);
					    itemid = createHow2(currentItem.getString("title"), currentItem.getString("tagline"), currentItem.getString("body"), currentItem.getString("image"), currentItem.getString("last_updated"));
					    
					    JSONArray currentItemStepsArray = currentItem.getJSONArray("steps"); 
					    for (int j = 0 ; j < currentItemStepsArray.length() ; j ++)
					    {
					    	JSONObject currentStep = currentItemStepsArray.getJSONObject(j);
					    	createHow2Step(itemid.intValue(), currentStep.getString("title"), currentStep.getString("body"), currentStep.getString("position"));
					    }
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "5";
				}
        		/*ItemsContainer pc;
        		for (int i = 0; i < post_list.size(); i++) {
        			pc = post_list.get(i);
        			Cursor idCheck = fetchRow(pc.getAlert().id, DATABASE_CATEGORY_TABLE);
        			//Toast.makeText(mCtx, "Bad url", Toast.LENGTH_SHORT).show();
        			if (idCheck.getCount()==0) { // Insert
        				//public boolean updateClass(long rowId, String instructor, String location, String description, String class_name, 
        			    //		Integer dayofweek, Integer starttime, Integer posted, Integer modified) {
        				createAlert(pc.getAlert().notification, pc.getAlert().posted, pc.getAlert().expires);
        			    
        			    //public boolean updateAlert(long rowId, String notification, Integer posted, Integer expires) {
        				//myDb.execSQL("UPDATE pwc_alerts SET _id="+pc.getAlert().id+", notification="+pc.getAlert().notification+", posted="+pc.getAlert().posted+", expires="+pc.getAlert().expires+"");
        			} else if (idCheck.getCount()==1) { // UPDATE
        				idCheck.moveToFirst();
        				updateAlert(idCheck.getColumnIndexOrThrow("_id"), pc.getAlert().notification, pc.getAlert().posted, pc.getAlert().expires);
        			}
        			idCheck.close();
        		}*/
	        	break;
	        /*case 2: // CLASSES
        		ClassesList clist = null;
        		clist = getClassList(writer.toString());
        		List <ClassContainer> cpost_list = clist.getClassesContainerList();
        		ClassContainer cpc;
        		for (int i = 0; i < cpost_list.size(); i++) {
        			cpc = cpost_list.get(i);
        			Cursor idCheck = fetchRow(cpc.getClassList().id, DATABASE_HOW2_TABLE);
        			
        			if (idCheck.getCount()==0) { // Insert
        				//public boolean updateClass(long rowId, String instructor, String location, String description, String class_name, 
        			    //		Integer dayofweek, Integer starttime, Integer posted, Integer modified) {
        				createClass(cpc.getClassList().instructor, cpc.getClassList().location, cpc.getClassList().description,
        						cpc.getClassList().class_name, cpc.getClassList().dayofweek, cpc.getClassList().starttime,
        						cpc.getClassList().posted, cpc.getClassList().modified);
        			    
        			} else if (idCheck.getCount()==1) { // UPDATE
        				idCheck.moveToFirst();
        				updateClass(idCheck.getColumnIndexOrThrow("_id"), cpc.getClassList().instructor, cpc.getClassList().location, cpc.getClassList().description,
        						cpc.getClassList().class_name, cpc.getClassList().dayofweek, cpc.getClassList().starttime,
        						cpc.getClassList().posted, cpc.getClassList().modified);
        			}
        			idCheck.close();
	    		}
	        	break;*/
	    }
		//if (itemid.intValue() > 0) {
		//	return String.valueOf(itemid.intValue());
		//} else {
			return writer.toString();
		//}

    }
    
}
