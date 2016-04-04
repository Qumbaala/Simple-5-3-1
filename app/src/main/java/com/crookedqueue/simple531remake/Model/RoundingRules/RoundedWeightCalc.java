package com.crookedqueue.simple531remake.Model.RoundingRules;

import java.io.Serializable;

/**
 * Created by Jason on 11/30/2015.
 */
public class RoundedWeightCalc implements Roundable {
    private Roundable mRoundable = null; //used as a delegate for rounding bx
    private final boolean mIsKg; //used in factory method to set delegate mRoundable
    private final boolean mIsRoundUp; //used in factory method to set delegate mRoundable

    public RoundedWeightCalc(boolean mIsKg, boolean mIsRoundUp) {
        this.mIsKg = mIsKg;
        this.mIsRoundUp = mIsRoundUp;
        mRoundable = setRoundingBx(mIsKg, mIsRoundUp);
    }

    @Override
    public Double performCalc(Double weight) {
        mRoundable = setRoundingBx(mIsKg, mIsRoundUp); //sets the delegate to perform the calculation
        return mRoundable.performCalc(weight); //returns weight in appropriate format based on rounding formula (either double or int)
    }

    private Roundable setRoundingBx(boolean mIsKg, boolean mIsRoundUp){
        if (mIsKg && mIsRoundUp){return new KgRoundUp();} //if using kg and rounding up
        else if (mIsKg && !mIsRoundUp){return new KgRoundDown();} //if using kg and rounding down
        else if (!mIsKg && mIsRoundUp) {return new ImperialRoundUp();} //if not using kg and rounding up
        else    {return new ImperialRoundDown();} //if not using kg and rounding down
    }
}
