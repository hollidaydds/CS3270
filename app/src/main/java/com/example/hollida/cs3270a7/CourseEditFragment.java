package com.example.hollida.cs3270a7;


import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseEditFragment extends Fragment {

    private View rootView;
    private EditText edit_Id_val, edit_name_val, edit_code_val, edit_start_val, edit_end_val;

    public CourseEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_course_edit, container, false);
        edit_Id_val = (EditText) rootView.findViewById(R.id.edit_id_val);
        edit_name_val = (EditText) rootView.findViewById(R.id.edit_name_val);
        edit_code_val = (EditText) rootView.findViewById(R.id.edit_code_val);
        edit_start_val = (EditText)rootView.findViewById(R.id.edit_start_val);
        edit_end_val = (EditText) rootView.findViewById(R.id.edit_end_val);

        FloatingActionButton InsertSong = (FloatingActionButton) rootView.findViewById(R.id.addCourseFAB);
        InsertSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(),"Couses",null,1);
                long rowID = dbHelper.insertCourse(
                        edit_name_val.getText().toString(),
                        edit_code_val.getText().toString(),
                        edit_start_val.getText().toString(),
                        edit_end_val.getText().toString());
                MainActivity ma = (MainActivity) getActivity();
                ma.reload();


            }});

        // Inflate the layout for this fragment
        return rootView;
    }



    public void populateCourse(long id)
    {
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "Couses" ,null,1);
        Cursor cursor = dbHelper.getOneCourse(id);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String code = cursor.getString(cursor.getColumnIndex("code"));
        String startAt = cursor.getString(cursor.getColumnIndex("start"));
        String endAt = cursor.getString(cursor.getColumnIndex("end"));

        edit_Id_val.setText(Long.toString(id));
        edit_name_val.setText(name);
        edit_code_val.setText(code);
        edit_start_val.setText(startAt);
        edit_end_val.setText(endAt);
    }


}
