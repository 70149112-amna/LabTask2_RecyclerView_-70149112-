package com.uol.coursecatalogue;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private RecyclerView rvCourses;
    private final ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCourses = findViewById(R.id.rvCourses);

        loadCourses();

        // Scenario 1, step 4: LayoutManager + adapter
        // (Step 5 test: comment out the next line, run, observe, then restore it)
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(new CourseAdapter(courses, this));
    }

    // Scenario 1, step 4: at least eight Course objects
    private void loadCourses() {
        courses.add(new Course("CS-1101", "Programming Fundamentals",
                "Dr. Ayesha Khan", "ayesha.khan@uol.edu.pk",
                "Introduces problem solving, algorithms, control structures, functions and arrays using a high-level language.", 4));
        courses.add(new Course("CS-1201", "Object Oriented Programming",
                "Mr. Bilal Ahmed", "bilal.ahmed@uol.edu.pk",
                "Covers classes, objects, inheritance, polymorphism, abstraction and exception handling in Java.", 4));
        courses.add(new Course("CS-2101", "Data Structures and Algorithms",
                "Dr. Usman Tariq", "usman.tariq@uol.edu.pk",
                "Studies lists, stacks, queues, trees, graphs, sorting and searching, with complexity analysis.", 4));
        courses.add(new Course("CS-2201", "Database Systems",
                "Ms. Sana Malik", "sana.malik@uol.edu.pk",
                "ER modelling, relational algebra, normalization and SQL for designing and querying databases.", 3));
        courses.add(new Course("SE-3101", "Software Engineering",
                "Amna", "70149112Amna@uol.edu.pk",
                "Software process models, requirements engineering, design, testing and project management.", 3));
        courses.add(new Course("CS-3201", "Operating Systems",
                "Afifa", "Afifa@uol.edu.pk",
                "Processes, threads, scheduling, synchronization, memory management and file systems.", 3));
        courses.add(new Course("CS-3301", "Computer Networks",
                "Ms. Hira Saeed", "hira.saeed@uol.edu.pk",
                "Layered network models, TCP/IP, routing, switching and basics of network security.", 3));
        courses.add(new Course("CS-02310", "Mobile Application Development",
                "Mr. Arslan", "Arslan@uol.edu.pk",
                "Build Android apps with Java and Android Studio: layouts, RecyclerView, Intents and more.", 3));
    }

    // Scenario 2, step 2: position -> that course only -> explicit Intent
    @Override
    public void onItemClick(int position) {
        Course course = courses.get(position);

        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra(CourseDetailActivity.EXTRA_CODE, course.getCode());
        intent.putExtra(CourseDetailActivity.EXTRA_TITLE, course.getTitle());
        intent.putExtra(CourseDetailActivity.EXTRA_INSTRUCTOR, course.getInstructor());
        intent.putExtra(CourseDetailActivity.EXTRA_EMAIL, course.getEmail());
        intent.putExtra(CourseDetailActivity.EXTRA_DESCRIPTION, course.getDescription());
        intent.putExtra(CourseDetailActivity.EXTRA_CREDITS, course.getCreditHours());
        startActivity(intent);
    }
}
