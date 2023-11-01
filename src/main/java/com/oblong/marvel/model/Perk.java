package com.oblong.marvel.model;

public enum Perk {

    PoorResources("Poor Resources", "Resources start out at Poor."),
    ZeroResources("Zero Resources", "Resources start out at Zero."),
    FeeblePopularity("Feeble Popularity", "Initial popularity is Feeble."),
    Unpopular("Unpopular", "Initial popularity is zero."),
    JustOneFriend("Just One Friend", "No initial contacts except for forced ones."),
    AllAlone("All Alone", "No initial contacts."),
    Traveller("Traveller", "Gain an additional Travel power."),
    AnimalSenses("Animal Senses", "Gain two additional Detection powers at Good rank."),
    EnergyBeing("Energy Being", "Gain an additional Energy Emission power, and optionally an Energy Control power."),
    ExtraPsychologicalWeakness("Extra Psychological Weakness", "Start with an additional Psychological weakness."),
    DoubleHP("Double HP", "Initial health is doubled."),
    ;
    private String name, description;

    private Perk(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){ return name; }
    public String getDescription(){ return description; }

}
