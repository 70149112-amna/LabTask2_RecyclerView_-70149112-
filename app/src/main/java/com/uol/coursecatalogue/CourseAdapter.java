package com.uol.coursecatalogue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Scenario 1, step 3: Adapter + ViewHolder
// Scenario 2, step 1: OnItemClickListener interface lives inside this adapter
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    // Tells MainActivity the position of the card that was tapped
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Course> courses;
    private final OnItemClickListener listener;

    public CourseAdapter(List<Course> courses, OnItemClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item_course.xml -> one card
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        // Write this course's data into the card
        Course course = courses.get(position);
        holder.tvCode.setText(course.getCode());
        holder.tvTitle.setText(course.getTitle());
        holder.tvCredits.setText("Credit Hours: " + course.getCreditHours());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    // Static inner ViewHolder: finds the three TextViews only ONCE
    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCode;
        final TextView tvTitle;
        final TextView tvCredits;

        public CourseViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCredits = itemView.findViewById(R.id.tvCredits);

            // Scenario 2, step 1: click listener on the whole card
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
