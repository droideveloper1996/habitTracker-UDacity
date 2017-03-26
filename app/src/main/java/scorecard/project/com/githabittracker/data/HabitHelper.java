package scorecard.project.com.githabittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import scorecard.project.com.githabittracker.data.HabitContract.HabitEntry;

/**
 * Created by Abhishek on 23/03/2017.
 */

public class HabitHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "habit.db";

    public HabitHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HabitEntry.MEDITATION + " TEXT NOT NULL,"
                + HabitEntry.MORNING_EXERCISE + " TEXT NOT NULL,"
                + HabitEntry.CUPS_OF_COFFEE + " INTEGER NOT NULL,"
                + HabitEntry.GLASS_OF_WATER + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
