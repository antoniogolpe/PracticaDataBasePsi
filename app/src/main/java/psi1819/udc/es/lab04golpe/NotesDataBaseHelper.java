package psi1819.udc.es.lab04golpe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NotesDataBaseHelper  extends SQLiteOpenHelper {

    /**
     * Nombre de la base de datos
     */
    private static final String DB_NAME = "notes1.db";

    /**
     * Version de la base de datos
     */
    private static final int DB_VERSION = 1;

    /**
     * Nombre de la tabla de la base de datos
     */
    public static final String TABLE_NOTES = "notes";

    /**
     * Nombres de los campos de la tabla notes
     */
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_NOTA = "nota";
    public static final String[] NOTAS_ALL_COL = {COL_ID, COL_NAME, COL_LASTNAME, COL_NOTA};

    private final static String DATABASE_CREATE = "CREATE TABLE " + TABLE_NOTES + "( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL, "
            + COL_LASTNAME + " TEXT NOT NULL, "
            + COL_NOTA + " INTEGER NOT NULL );";

    public final static String consulta = "SELECT " + COL_ID +"," + COL_NAME +"," + COL_LASTNAME +","
            + COL_NOTA + " FROM " + TABLE_NOTES;

    public NotesDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Base de datos ","upgrading");
        Log.w(NotesDataBaseHelper.class.getName(), "Upgrading db from version "
                + oldVersion + " to " + newVersion + ", which will destroy old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public long insertNota(Notes note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, note.getNombre());
        values.put(COL_LASTNAME, note.getApellido());
        values.put(COL_NOTA, note.getNota());
        return db.insert(TABLE_NOTES, null, values);
    }

    public int deleteNota(int id) {
        return getWritableDatabase().delete(TABLE_NOTES, COL_ID + " =? ",
            new String[ ] { String.valueOf(id) });
    }

    public int updateNota(Notes note) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, note.getNombre());
        values.put(COL_LASTNAME, note.getApellido());
        values.put(COL_NOTA, note.getNota());
        return getWritableDatabase().update(TABLE_NOTES, values,
                COL_ID + " = " + note.getId(), null);
    }

   /* public void getNote(int id){
        getWritableDatabase().query(TABLE_NOTES,null,COL_ID + " =? ",
            new String[] {String.valueOf(id)},null,null,null);
    }*/

   /* public Cursor getNotes(){
        Cursor datos= getWritableDatabase().rawQuery(consulta, null);

         return datos;
    }*/

    public ArrayList<Notes> getNotes() throws SQLException {
        ArrayList<Notes> notes=new ArrayList<Notes>();
        Notes note=null;

        Cursor datos= getWritableDatabase().rawQuery(consulta, null);
        if(datos.moveToFirst()){
            while(!datos.isAfterLast()){
                Log.d("Dato", String.valueOf(datos.getInt(0)));
                Log.d("Dato1", String.valueOf(datos.getString(1)));
                Log.d("Dato2", String.valueOf(datos.getString(2)));
                Log.d("Dato3", String.valueOf(datos.getInt(3)));
                note = new Notes(datos.getInt(0),datos.getString(1),datos.getString(2),(datos.getInt(3)));
                notes.add(note);
                datos.moveToNext();
            }
            datos.close();
        }
        return notes;
    }
}
