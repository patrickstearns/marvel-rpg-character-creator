package com.oblong.marvel.model;

public class Weakness {

    private static enum Stimulus {
        ElementalAllergy("Elemental Allergy", 0.13d, "<b>Elemental Allergy</b>: The hero suffers adverse effects if he is exposed to a specific one of the hundred-odd elements. This Allergy only occurs if the element is present in a pure state and in an accumulation of at least one ounce. If the element is combined in a molecular state or is in insufficient quantities, no Effect occurs. The element must be within 20 feet of the hero for it to have any Effect. This allergy can be overcome by physically insulating the hero from the element or by increasing the distance between them."),
        MolecularAllergy("Molecular Allergy", 0.04d, "<b>Molecular Allergy</b>: This is similar to Elemental Allergy. A specific compound causes the Effect. Common examples include water, wood, and Adamantium."),
        EnergyAllergy("Energy Allergy", 0.24d, "<b>Energy Allergy</b>: The hero suffers when he is exposed to a specific form of energy. This can be any wavelength or Intensity of energy found in the Energy Emission and Energy Control sections of this book. Sunlight is a common example of Energy Allergy, particularly when dealing with supernatural beings."),
        EnergyDepletion("Energy Depletion", 0.24d, "<b>Energy Depletion</b>: The hero has a finite energy supply that permits him to manifest Power. This energy must be periodically renewed by means of rest, food, Energy Absorption, Energy Vampirism, or simply making contact with a source of that energy. If the hero's energy level drops too low, dire Effects result."),
        EnergyDampening("Energy Dampening", 0.12d, "<b>Energy Dampening</b>: The hero has the problem of having to release the energy pent up within himself; this is done by using his Powers. If the hero is somehow prevented from using his Powers, the energy begins to do internal damage."),
        FiniteLimit("Finite Limit", 0.12d, "<b>Finite Limit</b>: All of the hero's Powers have a finite number of times they can be used. This is determined by a second, independent Power rank roll; this rank number is the number of uses remaining for that Power. When a Power is depleted, the hero suffers the Effect. The Judge can determine how such Powers can be recharged."),
        Psychological("Psychological", 0.05d, "<b>Psychological Weakness</b>: A specific event, condition, or mental state has an adverse effect on the hero. This Stimulus directly affects the hero's mind; any resulting physical damage is psychosomatic in nature. That is, any physical damage results are due to the hero's instinctive belief in those results. Examples include being physically bound, even if the hero could normally snap the binding material; facing a foe covered in a specific color; or feeling extremely confused. Psychological Weaknesses can be temporarily overcome by an Unearthly Intensity Psyche FEAT. They can be permanently overcome by a Shift Z Intensity Psyche FEAT."),
        ;
        public static Stimulus random(){
            double val = Math.random();
            for (Stimulus stimulus: values()){
                val -= stimulus.probability;
                if (val <= 0) return stimulus;
            }
            return values()[0];
        }

        private String name, notes;
        private double probability;
        private Stimulus(String name, double probability, String notes){
            this.name = name;
            this.probability = probability;
            this.notes = notes;
        }
        public String getName(){ return name; }
        public String getNotes(){ return notes; }
    }

    private static enum Effect {
        PowerNegation("Power Negation", 0.50d, "<b>Power Negation</b>: The hero's Powers cease to function when the hero is within 20 feet of the stimulus. The hero's Primary Abilities are also affected; these drop -1CS per 10 turns until a minimum rank of Typical is reached for all of them. If the. hero is beyond the 20 foot limit but tries to use his Powers to affect the Stimulus, certain uses of Power automatically fail. Any Power that directly acts on a target cannot affect a Stimulus. Only Powers that indirectly affect the Stimulus succeed, such as using brute force to lob missiles at the Stimulus."),
        Incapacitation("Incapacitation", 0.40d, "<b>Incapacitation</b>: The hero becomes physically ill after exposure to the Stimulus. Beginning with the initial contact, the hero loses one point of Health per turn. This loss continues for the Duration of the Effect. However, the loss stops when Health reaches zero; this Effect does not directly kill the hero. The hero retains his Powers but finds it harder to use them as his condition worsens."),
        Fatal("Fatal", 0.10d, "<b>Fatal</b>: As in the above, the hero gets ill after the initial exposure to the Stimulus. However, the Health loss does not stop at zero. Death will eventually occur if Health and (subsequently) Endurance drop to zero during the Weakness Duration. The hero can be revived if proper medical attention is given, but only after his Health and Endurance have both reached zero."),
        ;
        public static Effect random(boolean noAbilitiesAboveRemarkable){
            double val = Math.random();
            Effect selected = null;
            for (Effect effect: values()){
                val -= effect.probability;
                if (val <= 0){
                    selected = effect;
                    val = Double.MAX_VALUE;
                }
            }

            if (selected == Fatal && noAbilitiesAboveRemarkable) selected = Incapacitation;

            return selected;
        }

        private String name, notes;
        private double probability;
        private Effect(String name, double probability, String notes){
            this.name = name;
            this.probability = probability;
            this.notes = notes;
        }
        public String getName(){ return name; }
        public String getNotes(){ return notes; }
    }

    private static enum Duration {
        ContinousWithContact("Continous With Contact", 0.40d, "<b>Continuous with Contact</b>: As long as the hero remains within the effective range of the Stimulus, the Effect continues. When the hero is insulated or moved away from the Stimulus, the Effect immediately ceases. Lost Health points are returned at a rate of 2 per turn. If death has occurred, the hero now has the possibility of being revived."),
        LimitedWithContact("Limited With Contact", 0.20d, "<b>Limited Duration with Contact</b>: The Effect begins immediately upon contact with the Stimulus. However, the Effect only functions for a limited amount of time. After that time elapses, the hero is assumed to have either built up a temporary immunity to the Stimulus or the Stimulus has discharged its Effect. In either case, the Stimulus cannot further affect the hero. Duration is 1 -100 turns after initial Contact. The Judge makes a random die roll to determine this."),
        LimitedAfterContact("Limited After Contact", 0.30d, "<b>Limited Duration After Contact</b>: The Effect is continuous with Contact and also lasts for a limited time after the hero is no longer exposed to the Stimulus. Duration is 1 -100 turns after the hero's contact, with the Stimulus is broken. The Judge may either randomly determine this or develop his own criteria."),
        Permanent("Permanent", 0.10d, "<b>Permanent</b>: The Stimulus does its full damage to the hero, even when exposure to the Stimulus has been interrupted. In this case, the Stimulus sets off a bodily self-destruction sequence that must continue to its full extent. In the case of Power Negation, the hero's Powers are permanently lost. Incapacitation results in the hero reaching zero Health points and lapsing into a coma lasting 1 -10 days. After that time, he may regain his Health at the normal rate. Fatal results in the death of the hero as both Health and Endurance drop to zero. The hero can be revived, however, and will eventually recover if revival occurs."),
        ;
        public static Duration random(){
            double val = Math.random();
            for (Duration duration: values()){
                val -= duration.probability;
                if (val <= 0) return duration;
            }
            return values()[0];
        }

        private String name, notes;
        private double probability;
        private Duration(String name, double probability, String notes){
            this.name = name;
            this.probability = probability;
            this.notes = notes;
        }
        public String getName(){ return name; }
        public String getNotes(){ return notes; }
    }

    public static Weakness generate(boolean noAbilitiesAboveRemarkable){
        Stimulus stimulus = Stimulus.random();
        Effect effect = Effect.random(noAbilitiesAboveRemarkable);
        Duration duration = Duration.random();
        return new Weakness("<b>"+stimulus.getName()+", "+duration.getName()+"</b> causing <b>"+effect.getName()+"</b><br>", stimulus.getNotes()+"<br>"+duration.getNotes()+"<br>"+effect.getNotes());
    }

    public static Weakness generatePsychological(boolean noAbilitiesAboveRemarkable){
        Stimulus stimulus = Stimulus.Psychological;
        Effect effect = Effect.random(noAbilitiesAboveRemarkable);
        Duration duration = Duration.random();
        return new Weakness("<b>"+stimulus.getName()+", "+duration.getName()+"</b> causing <b>"+effect.getName()+"</b><br>", stimulus.getNotes()+"<br>"+duration.getNotes()+"<br>"+effect.getNotes());
    }

    private String name, notes;

    public Weakness(String name, String description){
        this.name = name;
        this.notes = description;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }
}
