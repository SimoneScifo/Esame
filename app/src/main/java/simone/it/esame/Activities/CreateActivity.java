package simone.it.esame.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import simone.it.esame.R;

/**
 * Created by Simone on 16/03/2017.
 */

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText titleET, dataET, textET;
    Button btnBlue, btnRed, btnGreen, btnEdit, btnDelete;

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

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBlue:
            case R.id.btnRed:
            case R.id.btnGreen:
            case R.id.btnEdit:
            case R.id.btnDelete:
        }
    }
}
