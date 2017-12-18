package com.example.user.mailsender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.mailsender.databinding.ActivityMainBinding;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        
        init();
    }

    private void init() {
        final String To = binding.etFrom.getText().toString();
        final String SUBJECT = binding.etSubject.getText().toString();
        final String MESSAGE = binding.etMessage.getText().toString();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{To});
                intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                intent.putExtra(Intent.EXTRA_TEXT, MESSAGE);

                intent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(intent, "Select Email Sending App :"));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), LENGTH_LONG).show();
                }
            }
        });
    }
}
