package com.quintus.labs.datingapp.Matched;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.CalculateAge;
import com.quintus.labs.datingapp.Utils.User;

/**
 * DatingApp
 * https://github.com/quintuslabs/DatingApp
 * Created on 25-sept-2018.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */

public class ProfileCheckinMatched extends AppCompatActivity {
    private static final String TAG = "ProfileCheckinMatched";

    private User user;
    private Context mContext = ProfileCheckinMatched.this;
    private Button sendSMSButton, sendEmailButton;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_checkin_matched);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("classUser");
        distance = intent.getIntExtra("distance", 1);

        Log.d(TAG, "onCreate: user name is" + user.getUsername());

        TextView toolbar = findViewById(R.id.toolbartag);
        toolbar.setText("Matched");

        sendSMSButton = findViewById(R.id.send_sms);
        sendEmailButton = findViewById(R.id.send_email);

        TextView profile_name = findViewById(R.id.profile_name);
        TextView profile_distance = findViewById(R.id.profile_distance);
        TextView profile_numbers = findViewById(R.id.profile_number);
        TextView profile_email = findViewById(R.id.profile_email);
        ImageView imageView = findViewById(R.id.image_matched);
        TextView profile_bio = findViewById(R.id.bio_match);
        TextView profile_interest = findViewById(R.id.interests_match);

        CalculateAge cal = new CalculateAge(user.getDateOfBirth());
        int age = cal.getAge();

        profile_name.setText(user.getUsername() + ", " + age);
        profile_email.setText(user.getEmail());

        String append = (distance == 1) ? "mile away" : "miles away";
        profile_distance.setText(distance + " " + append);


        if (user.getDescription().length() != 0) {
            profile_bio.setText(user.getDescription());
        }

        if (user.getPhone_number().length() != 0) {
            profile_numbers.setText(user.getPhone_number());
        } else {
            sendSMSButton.setEnabled(false);
        }

        //append interests
        StringBuilder interest = new StringBuilder();
        if (user.isSports()) {
            interest.append("Sports   ");
        }
        if (user.isFishing()) {
            interest.append("Fishing   ");
        }
        if (user.isMusic()) {
            interest.append("Music   ");
        }
        if (user.isTravel()) {
            interest.append("Travel   ");
        }

        profile_interest.setText(interest.toString());

        String profileImageUrl = user.getProfileImageUrl();
        switch (profileImageUrl) {
            case "defaultFemale":
                Glide.with(mContext).load(R.drawable.default_woman).into(imageView);
                break;
            case "defaultMale":
                Glide.with(mContext).load(R.drawable.default_man).into(imageView);
                break;
            default:
                Glide.with(mContext).load(profileImageUrl).into(imageView);
                break;
        }

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(user.getPhone_number(), user.getUsername());
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(user.getEmail(), user.getUsername());
            }
        });

    }

    // This method will be called when send sms button in matched profile will be clicked. This open the default sms app.
    private void sendSMS(String phoneNumber, String userName) {
        Intent smsAppOpener = new Intent(Intent.ACTION_VIEW);
        smsAppOpener.setData(Uri.parse("sms:" + phoneNumber));
        smsAppOpener.putExtra("sms_body", "Hi " + userName + ", \n" + "Love to have a coffee with you!!!!");
        startActivity(smsAppOpener);
    }

    // This method will be called when send email button in matched profile will be clicked. This open the default email app.
    private void sendEmail(String email, String userName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding our Pink Moon Match!!!");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi " + userName + ", \n" + "Love to have a coffee with you!!!!");
        startActivity(Intent.createChooser(intent, ""));
    }

}
