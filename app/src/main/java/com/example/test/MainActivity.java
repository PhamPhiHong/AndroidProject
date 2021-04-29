package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtShow;
    EditText edtCustom;
    EditText edtPass;
    Button btOK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShow = findViewById(R.id.txtShow);
        btOK = findViewById(R.id.btOK);
        edtCustom = findViewById(R.id.edtCustom);
        edtPass = findViewById(R.id.edtPass);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        GetData(db);

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DocumentReference docRef = db.collection("users").document(edtCustom.getText().toString());
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                txtShow.setText("DocumentSnapshot data: " + document.getData());
//                            } else {
//                                txtShow.setText( "No such document");
//                            }
//                        } else {
//                            txtShow.setText("get failed with ");
//                        }
//                    }
//                });
//
                db.collection("users")
                        .whereEqualTo("email",edtCustom.getText().toString())
                        .whereEqualTo("pass",edtPass.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                   txtShow.setText((CharSequence) document.get("displayName"));

                                        String result = String.valueOf(document.get("displayName"));
                                        String role = String.valueOf(document.get("role"));
                                        Intent intent = new Intent(MainActivity.this,Login.class);
                                        intent.putExtra("Result",result);
                                        intent.putExtra("Role",role);
                                        startActivity(intent);

//                                        edtCustom.setVisibility(edtCustom.GONE);
//                                        edtPass.setVisibility(edtCustom.GONE);
//                                        btOK.setVisibility(btOK.GONE);

                                    }
                                } else {
                                    txtShow.setText("");
                                }
                            }
                        });
//                db.collection("users")
//                        .whereEqualTo("email",edtCustom.getText().toString())
//                        .whereEqualTo("role",edtPass.getText().toString())
//                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable QuerySnapshot value,
//                                                @Nullable FirebaseFirestoreException e) {
//                                if (e != null) {
//                                    txtShow.setText( "Listen failed.");
//                                    return;
//                                }
//
//                                String role = new String();
//                                for (QueryDocumentSnapshot doc : value) {
//
//                                        role= (doc.getString("role"));
//                                        if (role == "1") {
//                                            Intent intent = new Intent(MainActivity.this, Login.class);
//                                            intent.putExtra(Result, role);
//                                            startActivity(intent);
//                                        }
//                                        else {
//                                            txtShow.setText( "Error getting documents: ");
//                                        }
//
//                                }
//                                txtShow.setText("Current user : " + role);
//
//                            }
//                        });

            }
        });

    }


    private void GetData(FirebaseFirestore db) {
        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    txtShow.setText("");
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED)
                    {
                        txtShow.setText("");
                    }
                }

            }
        });
    }
}