package com.uol.coursecatalogue;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Scenario 2, steps 3 and 4: details screen + "Email Instructor"
public class CourseDetailActivity extends AppCompatActivity {

    // public static final keys used by MainActivity (putExtra) and here (getXxxExtra)
    public static final String EXTRA_CODE = "extra_code";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_INSTRUCTOR = "extra_instructor";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_CREDITS = "extra_credits";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // Read the values sent by MainActivity
        Intent intent = getIntent();
        String code = intent.getStringExtra(EXTRA_CODE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String instructor = intent.getStringExtra(EXTRA_INSTRUCTOR);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        int creditHours = intent.getIntExtra(EXTRA_CREDITS, 0);

        // Screen title = course code
        setTitle(code);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back arrow
        }

        TextView tvCode = findViewById(R.id.tvDetailCode);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvInstructor = findViewById(R.id.tvDetailInstructor);
        TextView tvCredits = findViewById(R.id.tvDetailCredits);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        Button btnEmail = findViewById(R.id.btnEmailInstructor);

        tvCode.setText(code);
        tvTitle.setText(title);
        tvInstructor.setText("Instructor: " + instructor);
        tvCredits.setText("Credit Hours: " + creditHours);
        tvDescription.setText(description);

        // Implicit Intent: open an email app addressed to THIS course's instructor only
        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + email));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query about " + code + " - " + title);
            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(CourseDetailActivity.this,
                        R.string.no_email_app, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
