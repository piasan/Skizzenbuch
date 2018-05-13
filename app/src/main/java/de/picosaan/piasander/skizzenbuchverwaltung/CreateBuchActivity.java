package de.picosaan.piasander.skizzenbuchverwaltung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBuchActivity extends AppCompatActivity implements View.OnClickListener {

    //Text fields
    private EditText editBookName;
    private EditText editSeitenAktuell;
    private EditText editSeitenGesamt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_book);

        findViewById(R.id.ok_button).setOnClickListener(this);
        findViewById(R.id.cancel_button).setOnClickListener(this);

        editBookName = (EditText) findViewById(R.id.editBookName);
        editSeitenAktuell = (EditText) findViewById(R.id.editAktuelleSeiten);
        editSeitenGesamt = (EditText) findViewById(R.id.editSeitenGesamt);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ok_button:

                String bookName = editBookName.getText().toString();
                long seitenAktuell = Long.parseLong(editSeitenAktuell.getText().toString());
                long seitenGesamt = Long.parseLong(editSeitenGesamt.getText().toString());

                if (bookName.length() < 1) {
                    Toast.makeText(CreateBuchActivity.this, R.string.no_name, Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent();
                    intent.putExtra("BOOK_NAME", bookName);
                    intent.putExtra("SEITEN_AKTUELL", seitenAktuell);
                    intent.putExtra("SEITEN_GESAMT", seitenGesamt);

                    setResult(RESULT_OK, intent);
                    finish();
                }

                break;


            case R.id.cancel_button:

                setResult(RESULT_CANCELED);
                finish();

                break;

        }

    }

}
