package simone.it.esame.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import simone.it.esame.Adapters.NoteAdapter;
import simone.it.esame.Database.DatabaseHandler;
import simone.it.esame.R;

/**
 * Created by Simone on 16/03/2017.
 */

public class ViewActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button btnOrder, btnGrid;
    EditText searchET;
    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;
    RecyclerView noteRV;
    DatabaseHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnGrid = (Button) findViewById(R.id.btnGrid);
        searchET = (EditText) findViewById(R.id.searchET);
        btnOrder.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        searchET.addTextChangedListener(this);

        adapter = new NoteAdapter();
        layoutManager = new LinearLayoutManager(this);
        noteRV = (RecyclerView) findViewById(R.id.noteRV);
        noteRV.setAdapter(adapter);
        database = new DatabaseHandler(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOrder){

        }
        else if (v.getId() == R.id.btnGrid){

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
