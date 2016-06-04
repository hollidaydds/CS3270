package com.example.hollida.cs3270a7;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseViewFragment extends Fragment {

    View rootView;
    TextView Id_val, name_val, code_val, start_val, end_val;
    public CourseViewFragment() {
        // Required empty public constructor
    }
   
    public void onCreateOptionsMenu(
           Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         setHasOptionsMenu(true);

        //return inflater.inflate(R.layout.fragment_course_view, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_course_view, container, false);
        Id_val = (TextView) rootView.findViewById(R.id.id_val);
        name_val = (TextView) rootView.findViewById(R.id.name_val);
        code_val = (TextView) rootView.findViewById(R.id.course_val);
        start_val = (TextView)rootView.findViewById(R.id.start_val);
        end_val = (TextView) rootView.findViewById(R.id.end_val);
        return rootView;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.save)
        {

        }
        else if (id == R.id.delete)
        {

        }
        return true;
    }

    public void populateCourse(long id)
    {
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(),"Couses",null,1);
        Cursor cursor = dbHelper.getOneCourse(id);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String code = cursor.getString(cursor.getColumnIndex("code"));
        String startAt = cursor.getString(cursor.getColumnIndex("start"));
        String endAt = cursor.getString(cursor.getColumnIndex("end"));

        Id_val.setText(Long.toString(id));
        name_val.setText(name);
        code_val.setText(code);
        start_val.setText(startAt);
        end_val.setText(endAt);
    }
    public void loadCourse(long id, String n, String c, String s, String e )
    {

        Id_val.setText(Long.toString(id));
        name_val.setText(n);
        code_val.setText(c);
        start_val.setText(s);
        end_val.setText(e);
    }
}
