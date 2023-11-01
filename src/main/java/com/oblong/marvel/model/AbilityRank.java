package com.oblong.marvel.model;

public enum AbilityRank {

    ShiftZero("0", 0, 0, 0),
    Feeble("Fe", 2, 1, 2),
    Poor("Pr", 4, 3, 4),
    Typical("Ty", 6, 5, 7),
    Good("Gd", 10, 8, 15),
    Excellent("Ex", 20, 16, 25),
    Remarkable("Rm", 30, 26, 35),
    Incredible("In", 40, 36, 45),
    Amazing("Am", 50, 46, 62),
    Monstrous("Mn", 75, 63, 87),
    Unearthly("Un", 100, 88, 125),
    ShiftX("X", 150, 126, 175),
    ShiftY("Y", 250, 176, 350),
    ShiftZ("Z", 500, 351, 999),
    Class1000("1000", 1000, 1000, 1000),
    Class3000("3000", 3000, 3000, 3000),
    Class5000("5000", 5000, 5000, 5000),
    Beyond("B", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
    None("--", -1, -1, -1),
    ;
    public static AbilityRank fromValue(int value){
        for (AbilityRank rank: values())
            if (rank.getMinValue() <= value && rank.getMaxValue() >= value)
                return rank;
        throw new IllegalArgumentException("fromValue with value of "+value);
        //return ShiftZero;
    }

    private String symbol;
    private int value;
    private int minValue, maxValue;

    private AbilityRank(String symbol, int value, int minValue, int maxValue){
        this.symbol = symbol;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getSymbol(){ return symbol; }
    public int getValue(){ return value; }
    public int getMinValue(){ return minValue; }
    public int getMaxValue(){ return maxValue; }

    public boolean lessThan(AbilityRank other){ return ordinal()-other.ordinal() < 0; }
    public boolean greaterThan(AbilityRank other){ return ordinal()-other.ordinal() > 0; }
    public AbilityRank add(int ranks){
        int newOrdinal = ordinal()+ranks;
        newOrdinal = Math.max(0, newOrdinal);
        newOrdinal = Math.min(values().length-1, newOrdinal);
        return values()[newOrdinal];
    }

    public int difference(AbilityRank other){
        return ordinal()-other.ordinal();
    }
}
