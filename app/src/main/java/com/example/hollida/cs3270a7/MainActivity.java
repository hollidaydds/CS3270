package com.example.hollida.cs3270a7;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText Id_val, name_val, code_val, start_val, end_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragContainer, new CourseListFragment(),"LF")
                    .commit();
/*            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bot, new CourseViewFragment(),"CV")
                    .commit();*/
            }

    }

    public void fabClicked(View v)
    {
        CourseEditFragment courseFrag = new CourseEditFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContainer, courseFrag,"EF")
                .commit();

    }

    public void addEntry(View v)
    {
        CourseEditFragment EF = (CourseEditFragment)
                getSupportFragmentManager().findFragmentByTag("EF");

    }

    public void reload()
    {
       getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContainer, new CourseListFragment(),"BF")
                .commit();
    }
    public void openViewer(long id)
    {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragContainer, new CourseViewFragment(), "CV");
        transaction.commit();
        populateSong(id);

        }

    public void populateSong(long id) {
        DatabaseHelper dbHelper = new DatabaseHelper(this,"Couses",null,1);
        Cursor cursor = dbHelper.getOneCourse(id);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String code = cursor.getString(cursor.getColumnIndex("code"));
        String startAt = cursor.getString(cursor.getColumnIndex("start"));
        String endAt = cursor.getString(cursor.getColumnIndex("end"));

        CourseViewFragment EF = (CourseViewFragment)
                getSupportFragmentManager().findFragmentByTag("CV");
        EF.loadCourse(id, name, code, startAt, endAt);

    }
}
