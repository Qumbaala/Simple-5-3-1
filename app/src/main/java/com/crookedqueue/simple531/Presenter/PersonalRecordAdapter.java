package com.crookedqueue.simple531.Presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.PersonalRecord;
import com.crookedqueue.simple531.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalRecordAdapter extends RecyclerView.Adapter<PersonalRecordAdapter.PersonalRecordViewHolder> {
    List<PersonalRecord> recordList;

    public PersonalRecordAdapter(List<PersonalRecord> recordList) {
        this.recordList = recordList;
    }

    @Override
    public PersonalRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.personal_record_list_item, parent, false);
        return new PersonalRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonalRecordViewHolder holder, int position) {
        PersonalRecord pr = recordList.get(position);
        holder.txtLiftLabel.setText(pr.getStringLabel());
        holder.txtWeight.setText(String.valueOf(pr.getWeight()));
        holder.txtReps.setText(String.valueOf(pr.getReps()));
        holder.txtDate.setText(pr.getFormattedDate());
        holder.txtNotes.setText(pr.getNote());
    }

    public void refresh(List<PersonalRecord> newRecordList){
        recordList.clear();
        recordList.addAll(newRecordList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recordList == null ? 0: recordList.size();
    }

    public class PersonalRecordViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgview_container_pr)
        ImageView image;
        @Bind(R.id.txt_lift_label_pr)
        TextView txtLiftLabel;
        @Bind(R.id.txt_lift_weight_pr)
        TextView txtWeight;
        @Bind(R.id.txt_lift_reps_pr)
        TextView txtReps;
        @Bind(R.id.txt_date_pr)
        TextView txtDate;
        @Bind(R.id.txt_notes)
        TextView txtNotes;
        @Bind(R.id.btn_delete_pr)
        ImageButton btnDeletePr;

        public PersonalRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_delete_pr)
        public void deletePersonalRecord(){
            DbHelper.getInstance(itemView.getContext()).deletePersonalRecord(recordList.get(getAdapterPosition()));
            recordList.remove(getAdapterPosition());
            notifyDataSetChanged();
            Toast.makeText(itemView.getContext(), "Record deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
