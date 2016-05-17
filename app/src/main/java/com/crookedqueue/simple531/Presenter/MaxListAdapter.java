package com.crookedqueue.simple531.Presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.MaxesContainer;
import com.crookedqueue.simple531.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaxListAdapter extends RecyclerView.Adapter<MaxListAdapter.MaxViewHolder> {
    List<MaxesContainer> maxList;

    public MaxListAdapter(List<MaxesContainer> maxList) {
        this.maxList = maxList;
    }

    @Override
    public MaxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.max_list_item, parent, false);
        return new MaxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MaxViewHolder holder, int position) {
        MaxesContainer container = maxList.get(position);
        holder.txtDate.setText(container.getFormattedDate());
        holder.txtSquatMax.setText("Squat: " + String.valueOf(container.getSquatMax()));
        holder.txtBenchMax.setText("Bench: " + String.valueOf(container.getBenchMax()));
        holder.txtDeadliftMax.setText("Deadlift: " + String.valueOf(container.getDeadliftMax()));
        holder.txtPressMax.setText("Press: " + String.valueOf(container.getPressMax()));
    }

    @Override
    public int getItemCount() {
        return maxList == null ? 0: maxList.size();
    }

    class MaxViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_date_max)
        TextView txtDate;
        @Bind(R.id.txt_squat_max_list_item)
        TextView txtSquatMax;
        @Bind(R.id.txt_bench_max_list_item)
        TextView txtBenchMax;
        @Bind(R.id.txt_deadlift_max_list_item)
        TextView txtDeadliftMax;
        @Bind(R.id.txt_press_max_list_item)
        TextView txtPressMax;
        @Bind(R.id.btn_delete_max_list_item)
        ImageButton btnDeleteMax;

        public MaxViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_delete_max_list_item)
        public void deleteMax(){
            DbHelper.getInstance(itemView.getContext()).deleteMaxRecord(maxList.get(getAdapterPosition()));
            maxList.remove(getAdapterPosition());
            notifyDataSetChanged();
            Toast.makeText(itemView.getContext(), "Max deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
