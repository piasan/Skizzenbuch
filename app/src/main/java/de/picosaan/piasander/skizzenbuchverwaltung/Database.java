package de.picosaan.piasander.skizzenbuchverwaltung;

import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final static String TAG = "Database";

    private DatabaseReference mDatabase;
    private FirebaseUser user;

    public Database(){


    }


    //Read Data

    public ArrayList<Skizzenbuch> getBuecherListe(){

        final ArrayList<Skizzenbuch> buecherListe = new ArrayList<>();
        String userID = user.getUid();

        mDatabase.child("User-"+userID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Skizzenbuch buch = snapshot.getValue(Skizzenbuch.class);

                        buecherListe.add(buch);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });

        return buecherListe;

    }


}
