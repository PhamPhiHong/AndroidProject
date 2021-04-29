package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TestVoice extends AppCompatActivity {

    private TextView txtInput;
    private Button btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference rt = database.getReference("message");
    public DatabaseReference rq = database.getReference("lichsu");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        txtInput = findViewById(R.id.txtInput);
        btnSpeak = findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    //    /**
//     * Gọi dialog của google speech thông qua Intent
//     * Một số action quan trọng trong Intent như
//     * ACTION_RECOGNIZE_SPEECH, LANGUAGE_MODEL_FREE_FORM, EXTRA_PROMPT
//     * */

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Trả lại dữ liệu sau khi nhập giọng nói vào
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String a = result.get(0);
                    String b = getIntent().getStringExtra("Role1");
                    String c = getIntent().getStringExtra("User");
//                    if (b.equals("0"))
//                    {
//                        switch (a)
//                        {
//                            case "Tắt Đèn":
//                                rt.child("module1").child("den").setValue("off");
//                                txtInput.setText(result.get(0));
//                                rq.push().setValue(new dataHis("tatden",a,GetDate()));
//                                break;
//                            case "Mở đèn":
//                                rt.child("module1").child("den").setValue("on");
//                                txtInput.setText(result.get(0));
//                                rq.push().setValue(new dataHis("moden",a,GetDate()));
//                                break;
//                            default:
//                                txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
//                        }
//                    }
//                    else
//                        {
                    switch (a) {
                        case "mở cửa":
                            if (b.equals("0")) {
                                txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
                                break;
                            } else {
                                rt.child("module1").child("cua").setValue("on");
                                txtInput.setText(result.get(0));
                                rq.push().setValue(new dataHis("mocua", c, GetDate()));
                                break;
                            }
                        case "đóng cửa":
                            if (b.equals("0")) {
                                txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
                                break;
                            } else {
                                rt.child("module1").child("cua").setValue("off");
                                txtInput.setText(result.get(0));
                                rq.push().setValue(new dataHis("dongcua", c, GetDate()));
                                break;
                            }
                        case "mở máy bơm":
                            if (b.equals("0")) {
                                txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
                                break;
                            } else {
                                rt.child("module1").child("maybom").setValue("on");
                                txtInput.setText(result.get(0));
                                rq.push().setValue(new dataHis("momaybom", c, GetDate()));
                                break;
                            }

                        case "tắt máy bơm":
                            if (b.equals("0")) {

                                txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
                                break;
                            } else {
                                rt.child("module1").child("maybom").setValue("off");
                                txtInput.setText(result.get(0));
                                rq.push().setValue(new dataHis("tatmaybom", c, GetDate()));
                                break;
                            }
                        case "Tắt Đèn":
                            rt.child("module1").child("den").setValue("off");
                            txtInput.setText(result.get(0));
                            rq.push().setValue(new dataHis("tatden", c, GetDate()));
                            break;
                        case "Mở đèn":
                            rt.child("module1").child("den").setValue("on");
                            txtInput.setText(result.get(0));
                            rq.push().setValue(new dataHis("moden", c, GetDate()));
                            break;
                        default:
                            txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
                    }
                    //    }

//                    if (result.get(0).equals("mở cửa")  )
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("cua").setValue("on");
//                    }
//
//                    if (result.get(0).equals("đóng cửa") )
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("cua").setValue("off");
//                    }
//
//                    if (result.get(0).equals("mở máy bơm") )
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("maybom").setValue("on");
//                    }
//
//                    if (result.get(0).equals("tắt máy bơm") )
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("maybom").setValue("off");
//                    }
//
//
//                    if ( result.get(0).equals("Tắt Đèn"))
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("den").setValue("off");
//                    }
//
//                    if ( result.get(0).equals("Mở đèn") )
//                    {
//                        txtInput.setText(result.get(0));
//                        rt.child("module1").child("den").setValue("on");
//                    }
//
//                    else {
//                        txtInput.setText("Vui Lòng Đọc Lại Câu Lệnh");
//                    }
                }
                break;
            }
        }
    }

    private String GetDate() {

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String dateString1 = df.format(date);
        return dateString1;
    }
}