package com.example.classificame.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static FirebaseAuth auth;
    private static DatabaseReference firebaseRef;

    public static DatabaseReference getDatabase() {
        if (firebaseRef == null) {
            firebaseRef = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseRef;
    }

    public static FirebaseAuth getAuth(){
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
