package scorecard.project.com.githabittracker.data;

import android.provider.BaseColumns;


/**
 * Created by Abhishek on 23/03/2017.
 */

public final class HabitContract {

    private HabitContract() {

    }

    public static final class HabitEntry implements BaseColumns {
        public final static String _ID = BaseColumns._ID;
        public final static String TABLE_NAME = "habits";

        public final static String MORNING_EXERCISE = "Morning_Walk";
        public final static String CUPS_OF_COFFEE = "Coffee_cups";
        public final static String GLASS_OF_WATER = "Water";
        public final static String MEDITATION = "Meditation";




    }
}
