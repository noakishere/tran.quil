package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivitiesPage extends AppCompatActivity {

    private FirebaseFirestore fStore;
    private RecyclerView mFirestoreList;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_page);

        fStore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.activities_recyclerview);

        Query query = fStore.collection("Exercises");

        FirestoreRecyclerOptions<GuidedExerciseModel> options = new FirestoreRecyclerOptions.Builder<GuidedExerciseModel>()
                .setQuery(query, GuidedExerciseModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<GuidedExerciseModel, ExercisesViewHolder>(options) {
            @NonNull
            @Override
            public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guided_meditation_exercises_list, parent, false);
                return new ExercisesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ExercisesViewHolder exercisesViewHolder, int i, @NonNull GuidedExerciseModel guidedExerciseModel) {
                exercisesViewHolder.list_name.setText(guidedExerciseModel.getTitle());
                exercisesViewHolder.list_type.setText(guidedExerciseModel.getType());
                exercisesViewHolder.list_desc.setText(guidedExerciseModel.getDesc());
                exercisesViewHolder.list_time.setText(guidedExerciseModel.getTime() + " min");
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class ExercisesViewHolder extends RecyclerView.ViewHolder {

        private TextView list_name;
        private TextView list_type;
        private TextView list_desc;
        private TextView list_time;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.activityTitleGuidedMeditationList);
            list_type = itemView.findViewById(R.id.activityTypeGuidedMeditationList);
            list_desc = itemView.findViewById(R.id.activityDescriptionGuidedMeditationList);
            list_time = itemView.findViewById(R.id.activityLengthGuidedMeditationList);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}