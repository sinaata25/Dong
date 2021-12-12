package ataie.sina.dong.database;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

    Context context;
    /////////////////////////////////////////////////////////////DATABASE SET
    public static final String DB_NAME="Dong";
    public static final int DB_VERSION=1;
    ////////////////////////////////////////////////////////////TABLE SET
    //id//detail//users//date_ijad//result_kharj//
    public static final String TBL_NAME="spends";
    public static final String COL_ID="id";
    public static final String COL_DETAIL="detail";
    public static final String COL_USERS="users";
    public static final String COL_DATE="date";
    public static final String COL_RESULT="result";
    //////////////////////////////////////////////////////////////////WRITE QUERY
    public static final String QUERY="CREATE TABLE IF NOT EXISTS "+TBL_NAME+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_DETAIL+" TEXT,"+
            COL_USERS+" TEXT,"+
            COL_DATE+" TEXT,"+
            COL_RESULT+" INTEGER);";
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////TABLE SET
public static final String TBL_NAME_1="pays";
    public static final String COL_ID_1="id";
    public static final String COL_ID_PERSON="id_person";
    public static final String COL_TABLE_ID="table_id";
    public static final String COL_NAME="name";
    public static final String COL_PAYS="pays";
    public static final String COL_DETAIL_1="detail";
    public static final String COL_DATE_1="date";
    //////////////////////////////////////////////////////////////////WRITE QUERY
    public static final String QUERY_1="CREATE TABLE IF NOT EXISTS "+TBL_NAME_1+"("+
            COL_ID_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_TABLE_ID+" INTEGER,"+
            COL_ID_PERSON+" INTEGER,"+
            COL_NAME+" TEXT,"+
            COL_DETAIL_1+" TEXT,"+
            COL_DATE_1+" TEXT,"+
            COL_PAYS+" INTEGER);";
////////////////////////////////////////////////////////////////////


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(QUERY_1);
            db.execSQL(QUERY);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }

    ////////////////////////////////baraye vared kardan etelatat  kafist in tabe ra sakht va seda bezanim dar class delkhah,ba patrametre delkhah
    public long addinfo(String detail,String users,String date,int result)
    {

        ContentValues contentvalues=new ContentValues();
//...........................................................................setting content values
        contentvalues.put(COL_DETAIL, detail);
        contentvalues.put(COL_USERS, users);
        contentvalues.put(COL_DATE, date);
        contentvalues.put(COL_RESULT, result);
//............................................................................
        SQLiteDatabase sqlitedatabase=this.getWritableDatabase();
        long id=sqlitedatabase.insert(TBL_NAME, null,contentvalues);
        return id;
    }



    public long addinfo_1(int table_id,int id_person,String name,String detail,int pays,String date)
    {


        ContentValues contentvalues=new ContentValues();
//...........................................................................setting content values
        contentvalues.put(COL_DETAIL_1, detail);
        contentvalues.put(COL_ID_PERSON,id_person);
        contentvalues.put(COL_TABLE_ID, table_id);
        contentvalues.put(COL_NAME, name);
        contentvalues.put(COL_PAYS, pays);
        contentvalues.put(COL_DATE_1, date);
//............................................................................
        SQLiteDatabase sqlitedatabase=this.getWritableDatabase();
        long id=sqlitedatabase.insert(TBL_NAME_1, null,contentvalues);
        return id;
    }

    ///////////////////////////////baraye daryafte etelaat database yek cursur misazim va in tabe ro mirizim dakhelesh
    public Cursor getinfo(int datanum)
    {
        Cursor cursor = null;
        if(datanum==0){
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TBL_NAME, null);

        }else if(datanum==1) {
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TBL_NAME_1, null);
        }
        return cursor;
    }
//


    public Cursor getinfo_raw(int datanum,int raw)
    {
        Cursor cursor = null;
        if(datanum==1){
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TBL_NAME_1+" WHERE table_id="+raw, null);
        }
        else if(datanum==0){
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TBL_NAME+" WHERE id="+raw, null);
        }



        return cursor;
    }










    public void delet_row(int id)
    {
        SQLiteDatabase sqldatabase=this.getWritableDatabase();
        sqldatabase.delete(TBL_NAME, COL_ID+" = ?", new String[]{String.valueOf(id)});
    }






}