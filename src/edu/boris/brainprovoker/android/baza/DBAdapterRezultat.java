package edu.boris.brainprovoker.android.baza;

import edu.boris.brainprovoker.android.igralec;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapterRezultat implements BaseColumns {
	public static final String TAG = "DBAdapterRezultat";

	public static final String VALUE = "Score";
	public static final String NAME = "Name";
	public static final int POS__ID = 0;
	public static final int POS_NAME = 1;
	public static final int POS_VALUE = 2;

	public static final String TABLE = "score";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapterRezultat(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	// ---opens the database---
	public DBAdapterRezultat open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert igralec
	public long insertIgralec(igralec rezultat) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, rezultat.ime);
		initialValues.put(VALUE, rezultat.score);
		return db.insert(TABLE, null, initialValues);
	}

	// ---deletes a particular title---
	public boolean deleteIgralec(long rowId) {
		return db.delete(TABLE, _ID + "=" + rowId, null) > 0;
	}

	// ---retrieves all the titles---
	public Cursor getAll() {
		return db.query(TABLE, new String[] { _ID, // POS__ID=0;
				NAME, // POS_NAME=1
				VALUE }, null, null, null, null, null);
	}

	// ---retrieves a particular title---
	public Cursor getIgralec(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, TABLE,
				new String[] { _ID, NAME, VALUE }, _ID + "=" + rowId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---update---
	public boolean updateIgralec(igralec tmp) {
		ContentValues args = new ContentValues();
		args.put(NAME, tmp.ime);
		args.put(VALUE, tmp.score);
		return db.update(TABLE, args, _ID + "=" + tmp.getDbID(), null) > 0;
	}

}
