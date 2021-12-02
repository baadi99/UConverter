package com.project.uconverter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;


public class FeedbackFragment extends Fragment {

    // UI elements
    private EditText emailView;
    private EditText messageView;
    private Button sendBtn;

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

        emailView = view.findViewById(R.id.email);
        messageView = view.findViewById(R.id.message);
        sendBtn = view.findViewById(R.id.button_send);

        sendBtn.setOnClickListener((View v) -> {
            // Get form data
            String email = emailView.getText().toString().trim();
            String message = messageView.getText().toString().trim();

            if(email.isEmpty()) {
                emailView.setError("Email is Required!");
            } else if(!isEmailValid(email)) {
                emailView.setError("Invalid Email!");
            } else {
                //if email is valid clear error
                emailView.setError(null);
            }

            if(message.isEmpty()) {
                messageView.setError("Message can't be empty!");
            }

            //Send email
        });

    }

    private boolean isEmailValid(String email) {

        final String regEx = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regEx);

        return pattern.matcher(email).matches();
    }
}