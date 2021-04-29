
package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;

import java.util.Date;
import java.util.HashMap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView txtTest;
    TextView txtTest1;
    TextView txtTest2;
    TextView txt1;
    TextView txt99;

    Button btnT1;
    Button btnLogOut;

    SwitchCompat switchid;
    SwitchCompat switchid1;
    SwitchCompat switchid2;

    public dataHis dataaaa;

    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference rt = database.getReference("message");
    public DatabaseReference ra = database.getReference("TinhTrangModule");
    public DatabaseReference rq = database.getReference("lichsu");
    static Boolean isTouched = false;
    static Boolean isTouched1 = false;
    static Boolean isTouched2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        switchid = findViewById(R.id.switchid);
        switchid1 = findViewById(R.id.switchid1);
        switchid2 = findViewById(R.id.switchid2);

        txtTest = findViewById(R.id.txtTest);
        txtTest1 = findViewById(R.id.txtTest1);
        txtTest2 = findViewById(R.id.txtTest2);
        txt99 = findViewById(R.id.txt99);

        btnT1 = findViewById(R.id.btnT1);
        btnLogOut = findViewById(R.id.btnLogOut);

        txt1 = findViewById(R.id.txt1);

        String a = getIntent().getStringExtra("Result");

        txt99.setText("Xin Chào" + " " + a);

        String b = getIntent().getStringExtra("Role");
        if (b.equals("0")) {
            switchid2.setVisibility(switchid2.GONE);
            switchid1.setVisibility(switchid1.GONE);
        }

        btnT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TestVoice.class);
                intent.putExtra("User", a);
                intent.putExtra("Role1", b);
                startActivity(intent);
            }
        });

//        rq.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
//
//
//                        Toast.makeText(Login.this, childDataSnapshot.getValue().toString(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        switchid.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        txtTest.setText("Đèn is" + " " + (switchid.isChecked() ? "on" : "off"));

                        if (switchid.isChecked()) {
                            rt.child("module1").child("den").setValue("on");
//                            rq.child("d1").child("thoigiandenmo").setValue(dateString1);
//                            rq.child("d1").child("tacvu").setValue("moden");
//                            rq.child("d1").child("user").setValue(a);
//                            rq.push().setValue(new dataHis("moden",a,GetDate()));

                        } else {
                            rt.child("module1").child("den").setValue("off");
//                            rq.child("d2").child("thoigiandentat").setValue(dateString1);
//                            rq.child("d2").child("tatden").setValue("tatden");
//                            rq.child("d2").child("user").setValue(a);
//                           rq.push().setValue(new dataHis("tatden",a,GetDate()));
                        }
//                        txtTest1.setText("Quat is"+ (switchid1.isChecked()? "on" : "off"));
//                        if(switchid1.isChecked()){
//                            rt.child("module1").child("quat").setValue("on");
//                        }
//                        else {
//                            rt.child("module1").child("quat").setValue("off");
//                        }
                    }
                }
        );

        switchid1.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        txtTest1.setText("Cửa is" + " " + (switchid1.isChecked() ? "on" : "off"));
                        if (switchid1.isChecked()) {
                            rt.child("module1").child("cua").setValue("on");
//                            rq.child("c1").child("thoigiancuamo").setValue(dateString1);
//                            rq.child("c1").child("tacvu").setValue("mocua");
//                            rq.child("c1").child("user").setValue(a);
//                            rq.push().setValue(new dataHis("mocua",a,GetDate()));

                        } else {
                            rt.child("module1").child("cua").setValue("off");
//                            rq.child("c2").child("thoigiancuadong").setValue(dateString1);
//                            rq.child("c2").child("tacvu").setValue("tatden");
//                            rq.child("c1").child("user").setValue(a);
//                            rq.push().setValue(new dataHis("dongcua",a,GetDate()));
                        }
                    }
                }
        );

        switchid2.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        txtTest2.setText("Máy Bơm is" + " " + (switchid2.isChecked() ? "on" : "off"));
                        if (switchid2.isChecked()) {
                            rt.child("module1").child("maybom").setValue("on");
//                             rq.child("mb1").child("thoigianmaybommo").setValue(dateString1);
//                             rq.child("mb1").child("tacvu").setValue("momaybom");
//                             rq.child("mb1").child("user").setValue(a);
//                             rq.push().setValue(new dataHis("momaybom",a,GetDate()));
                        } else {
                            rt.child("module1").child("maybom").setValue("off");
//                             rq.child("mb2").child("thoigianmaybomtat").setValue(dateString1);
//                             rq.child("mb2").child("tacvu").setValue("tatmaybom");
//                             rq.child("mb2").child("user").setValue(a);
//                             rq.push().setValue(new dataHis("tatmaybom",a,GetDate()));
                        }
                    }
                }
        );

//        switchid.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean checked = ((SwitchCompat) v).isChecked();
//                        if (checked) {
//                            rt.child("module2").child("dongcua").setValue("on");
//                        }
//                        else {
//                            rt.child("module2").child("dongcua").setValue("off");
//                        }
//
//                        txtTest.setText("Door is"+ (switchid.isChecked()? "on" : "off"));
//                    }
//                }
//        );

        switchid2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });

        switchid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched1 = true;
                return false;
            }
        });

        switchid1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched2 = true;
                return false;
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ra.child("module1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                txt1.setText("Trạng thái module: " + " " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                txt1.setText("Failed to read value.");
            }
        });

        rt.child("module1").child("den").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                if (value.equals("on")) {
                    txtTest.setText("Đèn is" + " " + value);
                    switchid.setChecked(true);
                    if (isTouched1 == true) {
                        rq.push().setValue(new dataHis("moden", a, GetDate()));
                    }
                    //           rq.push().setValue(new dataHis("moden",a,dateString1));
                } else {
                    switchid.setChecked(false);
                    if (isTouched1 == true) {
                        rq.push().setValue(new dataHis("tatden", a, GetDate()));
                    }
//                  rq.push().setValue(new dataHis("tatden",a,dateString1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txt1.setText("Failed to read value.");
            }
        });
        rt.child("module1").child("cua").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                if (value.equals("on")) {
                    txtTest1.setText("Cửa is" + " " + value);
                    switchid1.setChecked(true);
                    if (isTouched2 == true) {
                        rq.push().setValue(new dataHis("mocua", a, GetDate()));
                    }
//                    rq.push().setValue(new dataHis("mocua",a,dateString1));
                } else {
                    switchid1.setChecked(false);
                    if (isTouched2 == true) {
                        rq.push().setValue(new dataHis("dongcua", a, GetDate()));
                    }
//                    rq.push().setValue(new dataHis("dongcua",a,dateString1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txt1.setText("Failed to read value.");
            }
        });
        rt.child("module1").child("maybom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                if (value.equals("on")) {
                    txtTest2.setText("Máy Bơm is" + " " + value);
                    switchid2.setChecked(true);
                    if (isTouched == true) {
                        rq.push().setValue(new dataHis("momaybom", a, GetDate()));
                    }

//                    rq.push().setValue(new dataHis("momaybom",a,dateString1));
                } else {
                    switchid2.setChecked(false);
                    if (isTouched == true) {
                        rq.push().setValue(new dataHis("tatmaybom", a, GetDate()));
                    }

//                    rq.push().setValue(new dataHis("tatmaybom",a,dateString1));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txt1.setText("Failed to read value.");
            }
        });

    }


    private String GetDate() {

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String dateString1 = df.format(date);
        return dateString1;
    }


}