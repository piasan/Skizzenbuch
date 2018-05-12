package de.picosaan.piasander.skizzenbuchverwaltung;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private DatabaseReference mDatabase;
    private FirebaseUser user;

    public Database(){

        // Current Firebase User
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Get an instance of the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

    }
}
