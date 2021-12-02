package com.project.uconverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class InviteFragment extends Fragment {

    // UI Elements
    private EditText linkView;
    private Button copyBtn;

    // Required empty public constructor
    public InviteFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_invite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linkView = view.findViewById(R.id.link);
        copyBtn = view.findViewById(R.id.button_copy_link);
        // For the animation
        Handler handler = new Handler(Looper.myLooper());
        //Copying to clipboard
        copyBtn.setOnClickListener((View v) -> {

            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData  = ClipData.newPlainText("EditText", linkView.getText().toString());
            clipboard.setPrimaryClip(clipData);
            clipData.getDescription();
            copyBtn.setText(R.string.copied);
            copyBtn.setEnabled(false); // Disable the button
            handler.postDelayed(() -> copyBtn.setText(R.string.copy_link), 2000);
        });
    }
}