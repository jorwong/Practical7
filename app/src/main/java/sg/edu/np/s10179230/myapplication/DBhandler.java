package sg.edu.np.s10179230.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper {
    private static final String TAG="MyDBhandler";
    public static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="accountDB.db";
    public static final String ACCOUNTS="Accounts";
    public static final String COLUMN_USERNAME="UserName";
    public static final String COLUMN_PASSWORD="Password";

    public DBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE="CREATE TABLE "+ACCOUNTS+" ("+COLUMN_USERNAME+" TEXT,"+COLUMN_PASSWORD+" TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNTS);
        onCreate(db);
    }

    public void addAccount(Account a){ //DO CRUD

        ContentValues values=new ContentValues();
        values.put(COLUMN_PASSWORD,a.getPassword());
        values.put(COLUMN_USERNAME,a.getUsername());
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(ACCOUNTS,null,values);
        db.close();
    }
}
