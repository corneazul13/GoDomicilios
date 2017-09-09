package godomicilios.mdcc.pulpapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import godomicilios.mdcc.pulpapp.settings.session;

/**
 * Created by PROGRAMACION5 on 29/06/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG= "DataBaseHelper";
    private static final String TABLE_NAME= "session";
    private static final int VERSION_BASEDATOS = 1;

    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "goDataBase.db";

    private static final String TABLA_CONTACTOS = "CREATE TABLE IF NOT EXISTS session " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT, " +
            "status TINYINT)";


    public DataBaseHelper(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }

    public boolean addData(String item, String user, String pass, Integer status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user );
        contentValues.put("pass", pass );
        contentValues.put("status", status );

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT status FROM session WHERE id ='1';";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void insertarCONTACTO(int id, String user, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put("id", id);
            valores.put("user", user);
            valores.put("pass", pass);
            valores.put("status", 1);
            db.close();
        }
    }
    public void modificarCONTACTO(String user, String pass, Integer status){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", 1);
        valores.put("user", user);
        valores.put("pass", pass);
        valores.put("status", status);
        db.update("session", valores, "id=" + 1, null);
        db.close();
    }
    public void borrarCONTACTO(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("session", "_id="+id, null);
        db.close();
    }
    public session recuperarCONTACTO(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"id", "user", "pass", "status"};
        Cursor c = db.query("session", valores_recuperar, "id=" + id,
                null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        session contactos = new session(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getInt(3));
        db.close();
        c.close();
        return contactos;
    }

}
