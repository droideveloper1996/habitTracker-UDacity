package scorecard.project.com.githabittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import scorecard.project.com.githabittracker.data.HabitContract.HabitEntry;
import scorecard.project.com.githabittracker.data.HabitHelper;

public class MainActivity extends AppCompatActivity {


    private HabitHelper mHabitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHabitHelper = new HabitHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    public void insertHabit() {
        SQLiteDatabase db = mHabitHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.MEDITATION, "DONE");
        values.put(HabitEntry.MORNING_EXERCISE, "DONE");
        values.put(HabitEntry.GLASS_OF_WATER, 7);
        values.put(HabitEntry.CUPS_OF_COFFEE, 3);


        long row = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (row == -1) {
            Log.i("Error Occured", "in");
        }

    }

    public void displayDatabaseInfo() {

        Cursor cursor = readHabit();
        TextView displayView = (TextView) findViewById(R.id.textView);
        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append('\n' + HabitEntry._ID + "  -  " +
                    HabitEntry.MORNING_EXERCISE + "  -  " +
                    HabitEntry.MEDITATION + "  -  " +
                    HabitEntry.GLASS_OF_WATER + "  -  " +
                    HabitEntry.CUPS_OF_COFFEE

            );
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int meditationColumnIndex = cursor.getColumnIndex(HabitEntry.MEDITATION);
            int moringColumnIndex = cursor.getColumnIndex(HabitEntry.MORNING_EXERCISE);
            int coffeeColumnINdex = cursor.getColumnIndex(HabitEntry.CUPS_OF_COFFEE);
            int glassofwater = cursor.getColumnIndex(HabitEntry.GLASS_OF_WATER);
            Log.i("idcolumn", String.valueOf(idColumnIndex));
            Log.i("MEditationcolumn", String.valueOf(meditationColumnIndex));
            Log.i("morning", String.valueOf(moringColumnIndex));
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(meditationColumnIndex);
                String morningWalk = cursor.getString(moringColumnIndex);
                int coffee = cursor.getInt(coffeeColumnINdex);
                int glassofWater = cursor.getInt(glassofwater);

                //  Log.i("current ID", String.valueOf(currentID));


                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + "  " + currentID + "       " +
                        currentName + "                         " +
                        morningWalk + "             " +
                        coffee + "                " +
                        glassofWater + "  "


                ));
            }


        } finally {
            cursor.close();
        }

    }

    public Cursor readHabit() {
        SQLiteDatabase db = mHabitHelper.getReadableDatabase();
        String[] projection = {HabitEntry._ID, HabitEntry.MORNING_EXERCISE,
                HabitEntry.MEDITATION,
                HabitEntry.GLASS_OF_WATER,
                HabitEntry.CUPS_OF_COFFEE};
        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertHabit();
                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_open_editor:
                openEditor();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openEditor() {
        Intent i = new Intent(getApplicationContext(), EditorActivity.class);
        startActivity(i);
    }

}
