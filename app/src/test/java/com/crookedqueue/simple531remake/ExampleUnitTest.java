package com.crookedqueue.simple531remake;

import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.SetListBuilder;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.Utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void repFactoryTest(){
        List<Integer> returnedList = Utils.getRepScheme(1);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(5);
        expectedList.add(5);
        assertArrayEquals(expectedList.toArray(), returnedList.toArray());
    }
    @Test
    public void repFactoryTestWeek2(){
        List<Integer> returnedList = Utils.getRepScheme(2);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3);
        expectedList.add(3);
        expectedList.add(3);
        assertArrayEquals(expectedList.toArray(), returnedList.toArray());
    }
    @Test
    public void repFactoryTestWeek3(){
        List<Integer> returnedList = Utils.getRepScheme(3);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(3);
        expectedList.add(1);
        assertArrayEquals(expectedList.toArray(), returnedList.toArray());
    }
    @Test
    public void repFactoryTestWeek4(){
        List<Integer> returnedList = Utils.getRepScheme(4);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(5);
        expectedList.add(5);
        assertArrayEquals(expectedList.toArray(), returnedList.toArray());
    }
    @Test
    public void repFactoryTestExpNotEqual(){
        List<Integer> returnedList = Utils.getRepScheme(1);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(5);
        expectedList.add(3);
        assertNotEquals(expectedList.toArray(), returnedList.toArray());
    }

    @Test
    public void percFactoryTestWeek1(){
        List<Double> actual = Utils.getPercentages(1);
        List<Double> expected = new ArrayList<>();
        expected.add(.65);
        expected.add(.75);
        expected.add(.85);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test
    public void percFactoryTestWeek2(){
        List<Double> actual = Utils.getPercentages(2);
        List<Double> expected = new ArrayList<>();
        expected.add(.7);
        expected.add(.8);
        expected.add(.9);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test
    public void percFactoryTestWeek3(){
        List<Double> actual = Utils.getPercentages(3);
        List<Double> expected = new ArrayList<>();
        expected.add(.75);
        expected.add(.85);
        expected.add(.95);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test
    public void percFactoryTestWeek4(){
        List<Double> actual = Utils.getPercentages(4);
        List<Double> expected = new ArrayList<>();
        expected.add(.4);
        expected.add(.5);
        expected.add(.6);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test
    public void percFactoryTestNotEqual(){
        List<Double> actual = Utils.getPercentages(1);
        List<Double> expected = new ArrayList<>();
        expected.add(.4);
        expected.add(.5);
        expected.add(.6);
        assertNotEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void setBuilderTest(){
        ExerciseSet expected = new ExerciseSet("Bench Press", 204.75, 5);
        SetListBuilder builder = new SetListBuilder();
        ExerciseSet actual = builder.buildSetList(LiftType.BENCH_PRESS, 315.0, 1).get(0);
        assertEquals(expected.getLabel(), actual.getLabel());
        assertEquals(expected.getRawWeight(), actual.getRawWeight(), 2);
        assertEquals(expected.getReps(), actual.getReps());
    }
}