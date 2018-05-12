package de.picosaan.piasander.skizzenbuchverwaltung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowBuecherActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final static String TAG = "ShowBuecherActivity";
    private final static int NEW_BOOK_REQUEST_CODE = 1;

    private Database db;
    private ArrayList<Skizzenbuch> buecher;

    private BuecherAdapter buecherAdapter;

    private LinearLayout loadingLayout;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_buecher);

        // Buttons
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.new_book_button).setOnClickListener(this);

        //LoadingLayout
        loadingLayout = (LinearLayout) findViewById(R.id.loadingLayout);

        db = new Database();

        buecher = new ArrayList<>();

    }


    @Override
    protected void onStart() {
        super.onStart();

        // Set up ListView and Adapter
        buecherAdapter = new BuecherAdapter(buecher, this);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(buecherAdapter);
        listView.setOnItemClickListener(this);

        if (buecher.size() > 0) {
            buecherAdapter.notifyDataSetChanged();
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.new_book_button:

                Intent newBookIntent = new Intent(this, CreateBuchActivity.class);

                startActivityForResult(newBookIntent, NEW_BOOK_REQUEST_CODE);
                break;

            case R.id.sign_out_button:

                Intent signOutIntent = new Intent(this, LoginActivity.class);
                String message = "signout";

                signOutIntent.putExtra("EXTRA_MESSAGE", message);
                startActivity(signOutIntent);
                finish();
                break;

            //case xy:
            // bla;
            //break;
        }
    }

    //OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Skizzenbuch buch = buecherAdapter.getItem(position);
        String buchID = buch.getBuch_id();
        Log.d(TAG, buchID);

       /* Intent TourDetailIntent = new Intent(this, TourDetailActivity.class);
        TourDetailIntent.putExtra("TourID", tourID);
        startActivity(TourDetailIntent);*/
    }


    // Get message from other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case NEW_BOOK_REQUEST_CODE:

                if (resultCode == RESULT_OK) {

                    String bookName = data.getStringExtra("BOOK_NAME");

                }


        }


    }
}
