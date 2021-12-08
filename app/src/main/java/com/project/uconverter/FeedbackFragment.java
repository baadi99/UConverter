package com.project.uconverter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class FeedbackFragment extends Fragment {

    // Required empty public constructor
    public FeedbackFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText emailView = view.findViewById(R.id.email);
        EditText messageView = view.findViewById(R.id.message);
        Button sendBtn = view.findViewById(R.id.button_send);

        sendBtn.setOnClickListener((View v) -> {
            // Get form data
            String email = emailView.getText().toString().trim();
            String message = messageView.getText().toString().trim();

            //if email is valid clear error
            emailView.setError(null);
            if (email.isEmpty()) {
                emailView.setError("Email is Required!");
            } else if (!isEmailValid(email)) {
                emailView.setError("Invalid Email!");
            } else {
                //if email is valid clear error
                emailView.setError(null);

                if (message.isEmpty()) {
                    messageView.setError("Message can't be empty!");
                } else {

                    Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
                    sendEmailIntent.setType("message/rfc822")
                            .setPackage("com.google.android.gm")
                            .putExtra(Intent.EXTRA_EMAIL, new String[]{"sakhrihamza9@gmail.com", "mohsin.baadi99@gmail.com"})
                            .putExtra(Intent.EXTRA_SUBJECT, "Uconverter App Feedback")
                            .putExtra(Intent.EXTRA_TEXT, message);

                    try {
                        startActivity(Intent.createChooser(sendEmailIntent, "Send mail..."));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getActivity(), "Gmail application is not installed on this device", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }

    private boolean isEmailValid(String email) {

        final String regEx = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regEx);

        return pattern.matcher(email).matches();
    }
}