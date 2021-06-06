package sg.edu.np.week2practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOLLOWED = "followed";

    /*public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, DATABASE_NAME,factory,DATABASE_VERSION);
    }*/
    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS +
                "("+ COLUMN_NAME + " TEXT,"
        + COLUMN_DESC + " TEXT,"
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        //CREATE TABLE TABLE_USERS
        for(int i=0; i<20; i++)
        {
            ContentValues c = new ContentValues();
            c.put("name", "Name" + new Random().nextInt());
            c.put("description","Description " + new Random().nextInt());
            c.put("followed", new Random().nextInt()%2 == 0);
            db.insert("user", null, c);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESC,user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        while(cursor.moveToNext()){
            User user1 = new User();
            user1.name = cursor.getString(0);
            user1.description = cursor.getString(1);
            user1.id = cursor.getInt(2);
            user1.followed = cursor.getInt(3)==0?false:true;

            userList.add(user1);
        }
        cursor.close();
        db.close();

        return userList;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followed", u.followed);
        int count = db.update("user", values, "id = ?", new String[]{ "" + u.id });

        db.close();
    }


}
