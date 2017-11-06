package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Usuario;

import static database.DBConfig.APELLIDO_USUARIO;
import static database.DBConfig.DATABASE_NAME; //Importo constante
import static database.DBConfig.DB_VERSION;
import static database.DBConfig.EMAIL_USUARIO;
import static database.DBConfig.NOMBRE_USUARIO;
import static database.DBConfig.TABLA_USUARIOS;

/**
 * Created by educacionit on 23/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }



    /***
     * Se ejecuta cada vez que se instancia el DBHelper.
     * Sólo si la DB no estaba creada anteriormente.!!!
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + TABLA_USUARIOS+ " (id integer primary key, "
                + NOMBRE_USUARIO + " text, " + APELLIDO_USUARIO + " text, "
                + EMAIL_USUARIO + " text )");
    }

    /***
     * Se ejecuta cuando se hace una modificación en la estructura de la base
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    public boolean guardarUsuario(Usuario usuario){
        if (usuario.getId()>0){
            return guardarUsuario(usuario);
        }
        else {
            return insertarUsuario(usuario);
        }
    }

    private boolean insertarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(NOMBRE_USUARIO, usuario.getNombre());
            contentValues.put(APELLIDO_USUARIO, usuario.getApellido());
            contentValues.put(EMAIL_USUARIO, usuario.getEmail());
            db.insert(TABLA_USUARIOS, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean actualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(APELLIDO_USUARIO, usuario.getApellido());
            db.update(TABLA_USUARIOS, contentValues, "id = ?", new String[]{String.valueOf(usuario.getId())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLA_USUARIOS, "id = ?", new String[]{String.valueOf(usuario.getId())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Cursor cur = db.rawQuery("select * from " + TABLA_USUARIOS, null);
        cur.moveToFirst();
        //Recorro con un cursor toda la DB
        try {
            while (!cur.isAfterLast()) {
                int id = cur.getInt(cur.getColumnIndex("id")); //Get an INT from column id
                String nombre = cur.getString(cur.getColumnIndex(NOMBRE_USUARIO));
                String apellido =  cur.getString(cur.getColumnIndex(APELLIDO_USUARIO));
                String email = cur.getString(cur.getColumnIndex(EMAIL_USUARIO));

                Usuario usuario = new Usuario(id, nombre, apellido, email);

//                usuario.setId(cur.getInt(cur.getColumnIndex("id")));
//                usuario.setNombre(cur.getString(cur.getColumnIndex(NOMBRE_USUARIO)));
//                usuario.setApellido(cur.getString(cur.getColumnIndex(APELLIDO_USUARIO)));
//                usuario.setEmail(cur.getString(cur.getColumnIndex(EMAIL_USUARIO)));

                usuarios.add(usuario);
                cur.moveToNext();
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario obtenerUsuario(int id) {
        Usuario usuario=null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Usuario> usuarios = new ArrayList<>();
            Cursor cur = db.rawQuery("select * from " + TABLA_USUARIOS + " WHERE ID=?"
                    + new String[]{String.valueOf(id)}, null);
            //Cursor cur = db.query(TABLA_USUARIOS,null,"id = ?", new String[]{String.valueOf(id)},null,null,null);

            if (cur.moveToFirst()){
                String nombre = cur.getString(cur.getColumnIndex(NOMBRE_USUARIO));
                String apellido =  cur.getString(cur.getColumnIndex(APELLIDO_USUARIO));
                String email = cur.getString(cur.getColumnIndex(EMAIL_USUARIO));

                usuario = new Usuario(id, nombre, apellido, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
