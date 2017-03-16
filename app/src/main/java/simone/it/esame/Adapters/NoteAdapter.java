package simone.it.esame.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import simone.it.esame.Activities.CreateActivity;
import simone.it.esame.Activities.ViewActivity;
import simone.it.esame.Models.Note;
import simone.it.esame.R;

/**
 * Created by Simone on 16/03/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter <NoteAdapter.NoteVH> {
    ArrayList<Note> dataSet = new ArrayList<>();
    private int position;

    public void addNote(Note item) {
        dataSet.add(0, item);
        notifyItemInserted(0);

    }

    public void updateNote(Note item, int position) {
        dataSet.set(position, item);
        notifyItemChanged(position);
    }

    public ArrayList<Note> getNotes() {
        return dataSet;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Note getNote(int position) {
        return dataSet.get(position);
    }


    public void setDataSet(ArrayList<Note> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    public void deleteNote(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);

    }
    @Override
    public NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(NoteVH holder, int position) {
        Note note = dataSet.get(position);
        holder.titleTV.setText(note.getTitle());
        holder.dateTV.setText(note.getDate());
        holder.textTV.setText(note.getText());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTV, dateTV, textTV;

        public NoteVH(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            dateTV = (TextView) itemView.findViewById(R.id.dateTV);
            textTV = (TextView) itemView.findViewById(R.id.textTV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), CreateActivity.class);
            (v.getContext()).startActivity(i);
        }
    }
}
