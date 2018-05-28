package de.picosaan.piasander.skizzenbuchverwaltung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowBuecherActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final static String TAG = "ShowBuecherActivity";
    private final static int NEW_BOOK_REQUEST_CODE = 1;

    private ArrayList<Skizzenbuch> buecher;

    private BuecherAdapter buecherAdapter;

    private LinearLayout loadingLayout;
    private ListView listView;

    private DatabaseReference mDatabase;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_buecher);

        // Buttons
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.new_book_button).setOnClickListener(this);

        //LoadingLayout
        loadingLayout = (LinearLayout) findViewById(R.id.loadingLayout);

        // Current Firebase User
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Get an instance of the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

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
        listView.setVisibility(View.VISIBLE);

        loadingLayout.setVisibility(View.VISIBLE);

        getBuecherListe();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    public void getBuecherListe(){

        buecherAdapter.clear();
        buecher.clear();

        String userID = user.getUid();

        mDatabase.child("User-"+userID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Skizzenbuch buch = snapshot.getValue(Skizzenbuch.class);

                    buecher.add(buch);
                }

                loadingLayout.setVisibility(View.GONE);
                buecherAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });

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
                    long seitenAktuell = data.getLongExtra("SEITEN_AKTUELL", 0);
                    long seitenGesamt = data.getLongExtra("SEITEN_GESAMT", 0);
                    long date = System.currentTimeMillis();

                    Skizzenbuch buch = new Skizzenbuch(bookName, seitenGesamt, date, seitenAktuell, 0);

                    String userID = user.getUid();

                    mDatabase.child("User-" + userID).push().setValue(buch);

                }

        }

    }
}
