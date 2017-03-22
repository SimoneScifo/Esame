package simone.it.esame.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import simone.it.esame.Adapters.NoteAdapter;
import simone.it.esame.R;

import static simone.it.esame.Activities.ViewActivity.NOTE_COLOR_KEY;
import static simone.it.esame.Activities.ViewActivity.NOTE_DATE_KEY;
import static simone.it.esame.Activities.ViewActivity.NOTE_TEXT_KEY;
import static simone.it.esame.Activities.ViewActivity.NOTE_TITLE_KEY;
import static simone.it.esame.Activities.ViewActivity.REQUEST_ADD;
import static simone.it.esame.Activities.ViewActivity.REQUEST_DELETE;
import static simone.it.esame.Activities.ViewActivity.REQUEST_EDIT;

/**
 * Created by Simone on 16/03/2017.
 */

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText titleET, dataET, textET;
    Button btnBlue, btnRed, btnGreen, btnEdit, btnDelete;
    NoteAdapter adapter;
    Intent intent;
    RelativeLayout layout;
    int color = Color.WHITE;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        titleET = (EditText) findViewById(R.id.titleET);
        dataET = (EditText) findViewById(R.id.dateET);
        textET = (EditText) findViewById(R.id.textET);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnBlue.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        layout = (RelativeLayout) findViewById(R.id.note_layout);
        adapter = adapter.getInstance();
        intent = getIntent();
        if (intent != null) {
            titleET.setText(intent.getStringExtra(NOTE_TITLE_KEY));
            textET.setText(intent.getStringExtra(NOTE_TEXT_KEY));
            dataET.setText(intent.getStringExtra(NOTE_DATE_KEY));
            color = (intent.getIntExtra(NOTE_COLOR_KEY, color));
        }
        layout.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnBlue:
                color = Color.BLUE;
                layout.setBackgroundColor(color);
                break;

            case R.id.btnRed:
                color = Color.RED;
                layout.setBackgroundColor(color);

                break;

            case R.id.btnGreen:
                color = Color.GREEN;
                layout.setBackgroundColor(color);
                break;

            case R.id.btnEdit:
                Intent editintent = new Intent();
                editintent.putExtra(NOTE_TITLE_KEY, titleET.getText().toString());
                editintent.putExtra(NOTE_DATE_KEY, dataET.getText().toString());
                editintent.putExtra(NOTE_TEXT_KEY, textET.getText().toString());
                editintent.putExtra(NOTE_COLOR_KEY, color);
                editintent.setFlags(intent.getFlags() == REQUEST_EDIT ? REQUEST_EDIT : REQUEST_ADD);

                if (intent.getFlags() == REQUEST_EDIT) {
                    setResult(Activity.RESULT_OK, editintent);
                    finish();
                } else{
                    editintent.setClass(this, ViewActivity.class);
                    startActivity(editintent);
                    finish();
                }

                break;


            case R.id.btnDelete:
                Intent i = new Intent();
                i.setFlags(REQUEST_DELETE);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;

            case R.id.dateET:
                    new DatePickerDialog(CreateActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALIAN);

        dataET.setText(sdf.format(myCalendar.getTime()));
    }
}

