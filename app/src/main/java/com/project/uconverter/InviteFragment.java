package com.project.uconverter;

<<<<<<< HEAD
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
=======
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
<<<<<<< HEAD
import androidx.core.content.res.ResourcesCompat;
=======
>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.Toast;


public class InviteFragment extends Fragment {

    // UI Elements
    private EditText linkView;
    private ImageButton copyBtn;
    private Button shareBtn;

=======


public class InviteFragment extends Fragment {

    // UI Elements
    private EditText linkView;
    private Button copyBtn;

>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
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
<<<<<<< HEAD
        shareBtn = view.findViewById(R.id.button_share_link);
        //Copying to clipboard
        copyBtn.setOnClickListener((View v) -> {
=======
        // For the animation
        Handler handler = new Handler(Looper.myLooper());
        //Copying to clipboard
        copyBtn.setOnClickListener((View v) -> {

>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData  = ClipData.newPlainText("EditText", linkView.getText().toString());
            clipboard.setPrimaryClip(clipData);
            clipData.getDescription();
<<<<<<< HEAD
            Toast.makeText(getContext(), "Copied to clipboard!", Toast.LENGTH_SHORT).show();
        });

        //Share action
        shareBtn.setOnClickListener((View v) -> {
            Intent sendIntent = new Intent()
                    .setType("message/rfc822")
                    .setAction(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_TEXT, "Download this app:\n" + linkView.getText().toString())
                    .setType("text/plain");
            try {
                startActivity(Intent.createChooser(sendIntent, "Send to..."));
            } catch(ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "No messaging application is not installed on this device", Toast.LENGTH_SHORT).show();
            }
=======
            copyBtn.setText(R.string.copied);
            copyBtn.setEnabled(false); // Disable the button
            handler.postDelayed(() -> copyBtn.setText(R.string.copy_link), 2000);
>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
        });
    }
}