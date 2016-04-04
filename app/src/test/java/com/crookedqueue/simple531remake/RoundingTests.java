package com.crookedqueue.simple531remake;

import com.crookedqueue.simple531remake.Model.RoundingRules.ImperialRoundDown;
import com.crookedqueue.simple531remake.Model.RoundingRules.ImperialRoundUp;
import com.crookedqueue.simple531remake.Model.RoundingRules.KgRoundDown;
import com.crookedqueue.simple531remake.Model.RoundingRules.KgRoundUp;
import com.crookedqueue.simple531remake.Model.RoundingRules.NumberStringFormatter;
import com.crookedqueue.simple531remake.Model.RoundingRules.Roundable;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by qumbaala on 4/4/2016.
 */
public class RoundingTests {
    @Test
    public void testImperialRoundUpFormatting(){
        String expected = "320";
        Roundable imperialRoundUp = new ImperialRoundUp();
        NumberStringFormatter formatter = new NumberStringFormatter(false);
        String actual = formatter.fixDoubleIfImperial(imperialRoundUp.performCalc(317.00));
        assertEquals(expected, actual);
    }

    @Test
    public void testImperialRoundDownFormatting(){
        String expected = "315";
        Roundable imperialRoundUp = new ImperialRoundDown();
        NumberStringFormatter formatter = new NumberStringFormatter(false);
        String actual = formatter.fixDoubleIfImperial(imperialRoundUp.performCalc(317.00));
        assertEquals(expected, actual);
    }
    @Test
    public void testKgRoundUpFormatting(){
        String expected = "317.5";
        Roundable imperialRoundUp = new KgRoundUp();
        NumberStringFormatter formatter = new NumberStringFormatter(true);
        String actual = formatter.fixDoubleIfImperial(imperialRoundUp.performCalc(317.00));
        assertEquals(expected, actual);
    }
    @Test
    public void testKgRoundDownFormatting(){
        String expected = "315.0";
        Roundable imperialRoundUp = new KgRoundDown();
        NumberStringFormatter formatter = new NumberStringFormatter(true);
        String actual = formatter.fixDoubleIfImperial(imperialRoundUp.performCalc(317.00));
        assertEquals(expected, actual);
    }


}
