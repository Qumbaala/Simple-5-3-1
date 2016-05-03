package com.crookedqueue.simple531.Presenter;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.crookedqueue.simple531.Model.RoundingRules.RoundedWeightCalc;
import com.crookedqueue.simple531.Model.Tools.PlateLoader;
import com.crookedqueue.simple531.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {

    List<ExerciseSet> setList = new ArrayList<>();

    public ExerciseListAdapter(List<ExerciseSet> setList) {
        this.setList = setList;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.exercise_list_item, parent, false);
        ExerciseViewHolder holder = new ExerciseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        ExerciseSet set = setList.get(position);
        holder.txt_label.setText(set.getLabel());
        holder.txt_weight.setText(String.valueOf(set.getRoundedWeight()));
        holder.txt_reps.setText(String.valueOf(set.getReps()));
        holder.img.setImageDrawable(holder.mapLiftLabelToImg(position));
    }

    @Override
    public int getItemCount() {
        return setList == null ? 0:setList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgview_container)
        ImageView img;
        @Bind(R.id.txt_lift_label)
        TextView txt_label;
        @Bind(R.id.txt_lift_weight)
        TextView txt_weight;
        @Bind(R.id.txt_lift_reps)
        TextView txt_reps;
        @Bind(R.id.btn_plate_loader)
        Button btnPlaterLoader;
        @Bind(R.id.btn_joker_set)
        Button btnJokerSet;
        @Bind(R.id.btn_record_set)
        Button btnRecordSet;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public Drawable mapLiftLabelToImg(int pos){
            ExerciseSet set = setList.get(pos);
            int i = 0;
            switch (set.getLiftLabel()){
                case SQUAT:
                    i = R.drawable.ic_circle_squat;
                    break;
                case BENCH_PRESS:
                    i = R.drawable.ic_circle_bench;
                    break;
                case DEADLIFT:
                    i = R.drawable.ic_circle_deadlift;
                    break;
                case OVERHEAD_PRESS:
                    i = R.drawable.ic_circle_press;
                    break;
                case ASSISTANCE:
                    i = R.drawable.ic_circle_assistance;
                    break;
                case WARM$UP:
                    i = R.drawable.ic_circle_warmup;
                    break;
                case JOKER:
                    i = R.drawable.ic_circle_joker;
                    break;
            }
            return ContextCompat.getDrawable(itemView.getContext(), i);
        }

        @OnClick(R.id.btn_plate_loader)
        public void createPlateLoaderDialog(){
            PlateLoader plateLoader = new PlateLoader(Double.parseDouble(txt_weight.getText().toString()),
                    DbHelper.getInstance(itemView.getContext()).retrieveIsUseKg());
            Toast.makeText(itemView.getContext(), plateLoader.buildLoaderMessage(), Toast.LENGTH_LONG).show();
        }

        @OnClick(R.id.btn_joker_set)
        public void addJokerSet(){
            DbHelper helper = DbHelper.getInstance(itemView.getContext());
            RoundedWeightCalc calc = new RoundedWeightCalc(helper.retrieveIsUseKg(), helper.retrieveIsUseRoundUp());
            double weight = Double.parseDouble(txt_weight.getText().toString()) * 1.05;
            int reps = Integer.parseInt(txt_reps.getText().toString());
            ExerciseSet jokerSet = new ExerciseSet(LiftLabel.JOKER, calc.performCalc(weight), reps);
            int adjAdapterPos = getAdapterPosition() + 1;
            setList.add(adjAdapterPos, jokerSet);
            notifyItemInserted(adjAdapterPos);
        }

        @OnClick(R.id.btn_record_set)
        public void recordSet(){
            DbHelper helper = DbHelper.getInstance(itemView.getContext());
            ExerciseSet set = setList.get(getAdapterPosition());
            helper.insertCompletedSet(set);
            Toast.makeText(itemView.getContext(), "Added to database!", Toast.LENGTH_SHORT).show();
        }
    }
}
