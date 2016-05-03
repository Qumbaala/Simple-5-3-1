package com.crookedqueue.simple531.Presenter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.PersonalRecord;
import com.crookedqueue.simple531.R;
import com.crookedqueue.simple531.View.PersonalRecordDialog;
import com.crookedqueue.simple531.View.PersonalRecordFragment;

import java.util.List;

public class PersonalRecordPresenter {
    private final PersonalRecordFragment frag;

    public PersonalRecordPresenter(PersonalRecordFragment frag) {
        this.frag = frag;
    }

    public List<PersonalRecord> getPersonalRecordList(){
        return DbHelper.getInstance(frag.getContext()).retrieveAllPersonalRecords();
    }

    public void buildPersonalRecordDialog(){
        PersonalRecordDialog prd = new PersonalRecordDialog();
        prd.setListener(this);
        prd.show(frag.getFragmentManager(), "Personal Record");
    }

    public void notifyAdapter(){
        frag.getAdapter().refresh(DbHelper.getInstance(frag.getContext()).retrieveAllPersonalRecords());
    }

    public void setAnimator(){
        frag.getFab().setHideAnimation(AnimationUtils.loadAnimation(frag.getContext(), R.anim.fab_hide));
        frag.getFab().setShowAnimation(AnimationUtils.loadAnimation(frag.getContext(), R.anim.fab_show));
        frag.getRecycler().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && frag.getFab().getVisibility() == View.VISIBLE) {
                    frag.getFab().hide(true);
                } else if (dy < 0 && frag.getFab().getVisibility() != View.VISIBLE) {
                    frag.getFab().show(true);
                }
            }
        });
    }

}
