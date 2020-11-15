package com.example.notesapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.ViewHolder> {
    //    private List<Note> notes = new ArrayList<>();
    private onitemclicklistener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }


        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentnote = getItem(position);
        holder.textViewtitle.setText(currentnote.getTitle());
        holder.textViewdescription.setText(currentnote.getDescription());
        holder.textViewpriority.setText(String.valueOf(currentnote.getPriority()));
    }


    public Note getnoteat(int position) {
        return getItem(position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewtitle;
        private TextView textViewdescription;
        private TextView textViewpriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewtitle = itemView.findViewById(R.id.textviewtitle);
            textViewdescription = itemView.findViewById(R.id.textviewdescription);
            textViewpriority = itemView.findViewById(R.id.textviewpriority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onitemclick(getItem(position));
                    }
                }
            });

        }
    }

    public interface onitemclicklistener {
        void onitemclick(Note note);
    }

    public void setonitemclicklistener(onitemclicklistener listener) {
        this.listener = listener;

    }

}
