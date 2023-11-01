package com.oblong.marvel.model;

public enum OriginOfPower {
    Natal("Natal", "<b>Natal</b>: The hero-was born in the Body Type he has, and possessed all his Powers from birth onward (although learning to control them took time). Note: If a character is a Cyborg, he was severely deformed at birth and required immediate emergency medical and scientific aid.", 0.10d),
    Maturity("Maturity", "<b>Maturity</b>: The hero gained his Powers sometime after reaching adulthood. Powers may have begun manifesting themselves sporadically during adolescence, or even childhood, but full control wasn't achieved until maturity. Most Random Mutants are in this class.", 0.10d),
    SelfAchievement("Self-Achievement", "<b>Self-Achievement</b>: The hero actively sought out a means of giving himself Power. He developed the methods, procedures, equipment, or whatever permits him to possess Power. This could be through scientific endeavors (Henry Pym/ Ant Man), arcane study (Dr. Strange), or physical training (iron Fist). Anyone can gain Powers similar to the hero's by following this special training.", 0.10d),
    Endowment("Endowment", "<b>Endowment</b>: The hero was given his Power by another being. This includes such diverse situations as being transformed (Tigra), being charged with Power (the Power Pack kids), and acquiring an item whose possession gives the hero Power (Black Knight, Vindicator).", 0.05d),
    TechnicalMishap("Technical Mishap", "<b>Technical Mishap</b>: The hero was caught in an experiment or procedure gone awry, with the result that the hero gained Powers that were totally unexpected. Such freak conditions cannot be completely duplicated, although they can be simulated. Simulations produce slightly different results, though. Cloak and Dagger are examples of Mishap Origins. The late Sasquatch was an example of a Mishap Simulation, from trying to recreate the Gamma Blast that created the Hulk.", 0.15d),
    TechnicalProcedure("Technical Procedure", "<b>Technical Experiment</b>: The hero was the subject of a controlled scientific or magical experiment. Assuming that all the factors are reproduced, such a Technical Experiment should be “able to produce a steady supply of superpowered heroes. Unfortunately, the geniuses behind such experiments often leave inadequate notes; if something happens to the genius, the experiment is irreproducible. Dr. Reinstein, for example, never really wrote down the Super-Soldier Formula that transformed Steve Rogers into Captain America. Attempts to recreate it produced the Infinity Formula that has the simpler effect of increasing the subject's Health, Endurance, and lifespan.", 0.10d),
    Creation("Creation", "<b>Creation</b>: The hero was born in the form he now has, that of an adult who possesses Power and/or whatever the Body Type is. Most of the Artificial Body Types belong in this class. Examples include the android Human Torch, all robots, angels, demons, and deities.", 0.05d),
    BiologicalExposure("Biological Exposure", "<b>Biological Exposure</b>: The hero gained Power after exposure to a special lifeform or a substance secreted by that lifeform. Werewolves and Bio-Vampires are common examples of this. Normal Humans are transformed into those kinds of beings after being bitten by another Werewolf or Bio-Vampire. The hero can also gain Power from an experiment using bio- chemicals. For example, the late Whizzer gained his Power after consecutive injections of cobra venom and mongoose blood.", 0.11d),
    ChemicalExposure("Chemical Exposure", "<b>Chemical Exposure</b>: The hero was transformed by exposure to an exotic element, compound, or mixture. This substance can be inhaled, ingested, injected, or just placed next to the hero for it to affect him. The chemicals involved in these incidents react in random ways with the hero's genetic structure and produce unique results. If another person is exposed to the same chemical, the results may be different or fatal. Madcap is an example of this.", 0.11d),
    EnergyExposure("Energy Exposure", "<b>Energy Exposure</b>: The hero was exposed to a special form and Intensity of energy— anything out of the ordinary will do—and turned into his present self. Most of the Marvel Super Heroes from the early 1960s are examples of this. The Fantastic Four, the Hulk, and Daredevil, for examples, all received Powers after being subjected to hard radiation.", 0.11d),
    Rebirth("Rebirth", "<b>Rebirth</b>: The hero was once a perfectly ordinary person. Then he died. Something happened to him that destroyed his old body and gave him a new one, complete with Power. Examples of this class include Marc Spector/Moon Knight and Arthur Douglas/Drax the Destroyer.", 0.02d),
    ;
    public static OriginOfPower random(){
        double val = Math.random();
        for (OriginOfPower value: values()){
            val -= value.probability;
            if (val <= 0) return value;
        }
        return values()[0];
    }

    private double probability;
    private String name;
    private String notes;

    private OriginOfPower(String name, String description, double probability){
        this.name = name;
        this.notes = description;
        this.probability = probability;
    }

    public double getProbability(){ return probability; }
    public String getName(){ return name; }
    public String getNotes(){ return notes; }

}
