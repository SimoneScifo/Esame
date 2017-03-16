package simone.it.esame.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import simone.it.esame.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCreate, btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnView = (Button) findViewById(R.id.btnView);
        btnCreate.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCreate){
            Intent i = new Intent(this, CreateActivity.class);
            startActivity (i);
        }
        else if (v.getId() == R.id.btnView){
            Intent i = new Intent(this, ViewActivity.class);
            startActivity(i);
        }
    }
}
