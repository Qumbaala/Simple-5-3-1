package com.crookedqueue.simple531.Model.Tools;


public class PlateLoader {

    private int numFourtyFives;
    private int numTwentyFives;
    private int numTens;
    private int numFives;
    private int numTwos;
    private int numTwentyKg;
    private int numTenKg;
    private int numFifteenKg;
    private int numFiveKg;
    private int numTwoHalfKg;
    private int numOneQuarterKg;
    private double weightToLoad = 0;


    private final int BARWEIGHT = 45;
    private final int FOURTYFIVE_PLATE = 90;
    private final int TWENTYFIVE_PLATE = 50;
    private final int TEN_PLATE = 20;
    private final int FIVE_PLATE = 10;
    private final int TWO_PLATE = 5;

    private final boolean isUseKg;
    private final int BAR_WEIGHT_KG = 20;
    private final int TWENTY_KG = 40;
    private final int FIFTEEN_KG = 30;
    private final int TEN_KG = 20;
    private final int FIVE_KG = 10;
    private final int TWO_HALF_KG = 5;
    private final double ONE_QUARTER = 2.5;

    public PlateLoader(double weight, boolean isUseKg) {
        this.weightToLoad = weight;
        this.isUseKg = isUseKg;
    }

    private void determineNumOfPlates() {
        if (!isUseKg) {
            int weightToLoad = (((int) this.weightToLoad) - BARWEIGHT);
            if (weightToLoad >= FOURTYFIVE_PLATE)
                numFourtyFives = weightToLoad / FOURTYFIVE_PLATE;
            weightToLoad = (weightToLoad % FOURTYFIVE_PLATE);
            if (weightToLoad >= TWENTYFIVE_PLATE)
                numTwentyFives = weightToLoad / TWENTYFIVE_PLATE;
            weightToLoad = (weightToLoad % TWENTYFIVE_PLATE);
            if (weightToLoad >= TEN_PLATE)
                numTens =  weightToLoad / TEN_PLATE;
            weightToLoad = (weightToLoad % TEN_PLATE);
            if (weightToLoad >= FIVE_PLATE)
                numFives =  weightToLoad / FIVE_PLATE;
            weightToLoad = (weightToLoad % FIVE_PLATE);
            if (weightToLoad >= TWO_PLATE)
                numTwos =  weightToLoad / TWO_PLATE;
        }
        else if (isUseKg){
            double weightToLoad = this.weightToLoad - BAR_WEIGHT_KG;
            if (weightToLoad >= TWENTY_KG)
                numTwentyKg = (int) weightToLoad / TWENTY_KG;
            weightToLoad = (weightToLoad % TWENTY_KG);
            if (weightToLoad >= FIFTEEN_KG)
                numFifteenKg = (int) weightToLoad / FIFTEEN_KG;
            weightToLoad = (weightToLoad % FIFTEEN_KG);
            if (weightToLoad >= TEN_KG)
                numTenKg = (int) weightToLoad / TEN_KG;
            weightToLoad = (weightToLoad % TEN_KG);
            if (weightToLoad >= FIVE_KG)
                numFiveKg = (int) weightToLoad / FIVE_KG;
            weightToLoad = (weightToLoad % FIVE_KG);
            if (weightToLoad >= TWO_HALF_KG)
                numTwoHalfKg = (int) weightToLoad / TWO_HALF_KG;
            weightToLoad = (weightToLoad % TWO_HALF_KG);
            if (weightToLoad >= ONE_QUARTER)
                numOneQuarterKg = (int) (weightToLoad / ONE_QUARTER);
        }
    }

    public String buildLoaderMessage() {
        determineNumOfPlates();
        StringBuilder message = new StringBuilder("Load each side of the bar with:\n");

        if (!isUseKg) {
            if (numFourtyFives != 0) {
                message.append(numFourtyFives);
                message.append(" 45lb plates\n");
            }
            if (numTwentyFives != 0) {
                message.append(numTwentyFives);
                message.append(" 25lb plates\n");
            }
            if (numTens != 0) {
                message.append(numTens);
                message.append(" 10lb plates\n");
            }
            if (numFives != 0) {
                message.append(numFives);
                message.append(" 5lb plates\n");
            }
            if (numTwos != 0) {
                message.append(numTwos);
                message.append(" 2.5lb plates");
            }

        } else if (isUseKg) {
            if (numTwentyKg != 0) {
                message.append(numTwentyKg);
                message.append(" 20kg plates\n");
            }
            if (numFifteenKg != 0) {
                message.append(numFifteenKg);
                message.append(" 15kg plates\n");
            }
            if (numTenKg != 0) {
                message.append(numTenKg);
                message.append(" 10kg plates\n");
            }
            if (numFiveKg != 0) {
                message.append(numFiveKg);
                message.append(" 5kg plates\n");
            }
            if (numTwoHalfKg != 0) {
                message.append(numTwoHalfKg);
                message.append(" 2.5kg plates\n");
            }
            if (numOneQuarterKg != 0) {
                message.append(numOneQuarterKg);
                message.append(" 1.25kg plates");
            }
        }
        return String.valueOf(message);
    }

    public Number getWeightToLoad() {
        return weightToLoad;
    }

    public int getNumFourtyFives() {
        return numFourtyFives;
    }

    public int getNumTwentyFives() {
        return numTwentyFives;
    }

    public int getNumTens() {
        return numTens;
    }

    public int getNumFives() {
        return numFives;
    }

    public int getNumTwos() {
        return numTwos;
    }
}