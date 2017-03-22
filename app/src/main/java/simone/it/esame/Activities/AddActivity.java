package simone.it.esame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import simone.it.esame.Adapters.NoteAdapter;
import simone.it.esame.Database.DatabaseHandler;
import simone.it.esame.R;

import static simone.it.esame.Activities.ViewActivity.NOTE_DATE_KEY;
import static simone.it.esame.Activities.ViewActivity.NOTE_TEXT_KEY;
import static simone.it.esame.Activities.ViewActivity.NOTE_TITLE_KEY;
import static simone.it.esame.Activities.ViewActivity.REQUEST_ADD;
import static simone.it.esame.Activities.ViewActivity.REQUEST_EDIT;

/**
 * Created by Simone on 17/03/2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

        EditText titleET, dataET, textET;
        Button btnBlue, btnRed, btnGreen, btnAdd;
        DatabaseHandler database;
        NoteAdapter adapter;
        Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);
            titleET = (EditText) findViewById(R.id.titleET);
            dataET = (EditText) findViewById(R.id.dateET);
            textET = (EditText) findViewById(R.id.textET);
            btnBlue = (Button) findViewById(R.id.btnBlue);
            btnRed = (Button) findViewById(R.id.btnRed);
            btnGreen = (Button) findViewById(R.id.btnGreen);
            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnBlue.setOnClickListener(this);
            btnRed.setOnClickListener(this);
            btnGreen.setOnClickListener(this);
            btnAdd.setOnClickListener(this);

            adapter = adapter.getInstance();

            intent = getIntent();
            if (intent != null){
                titleET.setText(intent.getStringExtra(NOTE_TITLE_KEY));
                textET.setText(intent.getStringExtra(NOTE_TEXT_KEY));
                dataET.setText(intent.getStringExtra(NOTE_DATE_KEY));
            }
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btnBlue:

                case R.id.btnRed:

                case R.id.btnGreen:

                case R.id.btnAdd:
                    Intent intent = new Intent(this, ViewActivity.class);
                    intent.putExtra(NOTE_TITLE_KEY, titleET.getText().toString());
                    intent.putExtra(NOTE_DATE_KEY, dataET.getText().toString());
                    intent.putExtra(NOTE_TEXT_KEY, textET.getText().toString());
                    setResult(REQUEST_ADD);
                    startActivity(intent);
                    break;

            }
        }
    }
