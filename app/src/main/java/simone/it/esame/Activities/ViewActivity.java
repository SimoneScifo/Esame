package simone.it.esame.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import simone.it.esame.Adapters.NoteAdapter;
import simone.it.esame.Database.DatabaseHandler;
import simone.it.esame.Models.Note;
import simone.it.esame.R;

/**
 * Created by Simone on 16/03/2017.
 */

public class ViewActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    public static final int REQUEST_ADD = 1001;
    public static final int REQUEST_DELETE = 1002;
    public static final int REQUEST_EDIT = 1003;
    public static final int REQUEST_NOTE = 1004;

    public static final String NOTE_TITLE_KEY = "NOTE_TITLE_KEY";
    public static final String NOTE_TEXT_KEY = "NOTE_TEXT_KEY";
    public static final String NOTE_COLOR_KEY = "NOTE_COLOR_KEY";
    public static final String NOTE_DATE_KEY = "NOTE_DATE_KEY";

    //Costanti per modificare layout
    private static final String LAYOUT_MANAGER_KEY = "LAYOUT_MANAGER_KEY";
    private int STAGGERED_LAYOUT = 1;
    private int LINEAR_LAYOUT = 2;
    private int layoutManagerType = LINEAR_LAYOUT;
    private int orderManager = 0;
    private int DESC_ORDER = 4;
    private int ASC_ORDER = 5;

    Button btnOrder, btnGrid;
    EditText searchET;
    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;
    RecyclerView noteRV;
    DatabaseHandler database;
    Intent intent;
    Note note;
    Note editNote;
    int color = Color.WHITE;

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

        adapter = adapter.getInstance();
        layoutManager = getSavedLayoutManager();
        noteRV = (RecyclerView) findViewById(R.id.noteRV);
        noteRV.setAdapter(adapter);
        noteRV.setLayoutManager(layoutManager);
        database = new DatabaseHandler(this);
        adapter.setDataSet(database.getAllNotes());

        intent = getIntent();
        if (intent != null) {
            if (intent.getFlags() == REQUEST_ADD) {
                note = new Note();
                note.setTitle(intent.getStringExtra(NOTE_TITLE_KEY));
                note.setText(intent.getStringExtra(NOTE_TEXT_KEY));
                note.setDate(intent.getStringExtra(NOTE_DATE_KEY));
                note.setColor(intent.getIntExtra(NOTE_COLOR_KEY, color));
                noteRV.scrollToPosition(0);
                database.addNote(note);
                adapter.addNote(note);
                Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGrid:
                if (getLayoutManagerType() == STAGGERED_LAYOUT) {
                    setLayoutManagerType(LINEAR_LAYOUT);
                    noteRV.setLayoutManager(new LinearLayoutManager(this));
                    btnGrid.setText("GRIGLIA");


                } else {
                    setLayoutManagerType(STAGGERED_LAYOUT);
                    noteRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    btnGrid.setText("LISTA");

                }
                break;
            case R.id.btnOrder:
                if (orderManager == ASC_ORDER || orderManager == 0) {
                    adapter.setDataSet(database.getOrderDescNotes());
                    btnOrder.setText("DISCENDENTE");
                    this.orderManager = DESC_ORDER;
                } else if (orderManager == DESC_ORDER) {
                    adapter.setDataSet(database.getOrderAscNotes());
                    btnOrder.setText("ASCENDENTE");
                    this.orderManager = ASC_ORDER;
                }
                break;
        }

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.setDataSet(database.getSearchNotes(s));
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (searchET.getText().toString().equalsIgnoreCase("")) {
            searchET.setHint("Cerca");
            adapter.setDataSet(database.getAllNotes());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (data.getFlags() == REQUEST_DELETE) {
                database.deleteNote(adapter.getNote(adapter.getPosition()));
                adapter.deleteNote(adapter.getPosition());
                Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();

            } else if (data.getFlags() == REQUEST_EDIT) {
                editNote = new Note();
                editNote.setTitle(data.getStringExtra(NOTE_TITLE_KEY));
                editNote.setText(data.getStringExtra(NOTE_TEXT_KEY));
                editNote.setDate(data.getStringExtra(NOTE_DATE_KEY));
                editNote.setColor(data.getIntExtra(NOTE_COLOR_KEY, color));
                noteRV.scrollToPosition(0);

                adapter.updateNote(editNote, adapter.getPosition());
                database.updateNote(editNote);

                Toast.makeText(getApplicationContext(), "Edit success", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private RecyclerView.LayoutManager getSavedLayoutManager() {
        SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int layoutManager = sharedPrefs.getInt(LAYOUT_MANAGER_KEY, -1);
        if (layoutManager == STAGGERED_LAYOUT) {
            setLayoutManagerType(layoutManager);
            return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        }
        if (layoutManager == LINEAR_LAYOUT) {
            setLayoutManagerType(layoutManager);
            return new LinearLayoutManager(this);
        }
        return new LinearLayoutManager(this);

    }
    public int getLayoutManagerType() {

        return layoutManagerType;
    }

    public void setLayoutManagerType(int layoutManagerType) {
        this.layoutManagerType = layoutManagerType;
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences layoutPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = layoutPreferences.edit();
        editor.putInt(LAYOUT_MANAGER_KEY, getLayoutManagerType());
        editor.apply();
    }
}
