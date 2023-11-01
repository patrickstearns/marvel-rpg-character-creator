package com.oblong.marvel.model;

import java.util.ArrayList;
import java.util.List;

public enum Talent {
    Guns(TalentGroup.Weapon, 1, "Guns", "Individuals without this Talent fire guns (all handguns, rifles, and submachine guns, including laser, stun. and concussion varieties) at their Agility rank. Those with this Talent fire such weapons at +1CS."),
    ThrownWeapons(TalentGroup.Weapon, 1, "Thrown Weapons", "Characters with this Talent toss weapons designed to be thrown (including spears, daggers, Shuriken, disks, and snowballs) at +1CS to their Agility"),
    Bows(TalentGroup.Weapon, 1, "Bows", "Bows are tricky items to operate, such that those who have not been trained fire them at - 1CS to their Agility. Those with this Talent gain a +1CS to hit with all bows, including crossbows, and may fire and reload in a single round. They may fire multiple arrows on a successful Agility FEAT."),
    BluntWeapons(TalentGroup.Weapon, 1, "Blunt Weapons", "Characters with this Talent gain a +1CS to hit when attacking with a weapon that resolves attacks on the Blunt Attacks column of the Battle Effects Table."),
    SharpWeapons(TalentGroup.Weapon, 1, "Sharp Weapons", "Characters with this Talent gain a +1CS to hit when attacking with a weapon that resolves attacks on the Edged Attack column of the Battle Effects Table. This includes swords, daggers (unless thrown), and spears, but excludes claws and other natural extensions that inflict this type of damage."),
    OrientalWeapons(TalentGroup.Weapon, 1, "Oriental Weapons", "This a special category that grants the character a +1CS to Fighting or Agility when using the following weapons: Shuriken, crossbows, sais (treat as swords), and oriental swords and daggers (including the katana and the kris)."),
    Marksman(TalentGroup.Weapon, 2, "Marksman", "The character with this Talent gains a + 1CS to hit with any distance weapon that requires line of sight to hit (the character could benefit when firing heavy artillery, but not when controlling a teleguided missile). Such a weapon in the hands of a marksman does not suffer penalties to hit from range."),
    WeaponsMaster(TalentGroup.Weapon, 2, "Weapons Master", "The character with this Talent gains a +1CS to hit with any weapon that requires a Fighting FEAT to hit."),
    WeaponsSpecialist(TalentGroup.Weapon, 2, "Weapons Specialist", "The character with this Talent gains a + 2CS with a single weapon of choice. This may be any type of weapon, missile or melee. The character who is a weapon specialist will also increase his initiative when using this weapon by 1."),

    MartialArtsA(TalentGroup.Fighting, 1, "Martial Arts A", "This form of martial arts concentrates on using an opponent's strength against him, and is typical of oriental- American forms such as judo and karate. The practitioner of this type of martial arts can Stun or Slam an opponent regardless of their comparative Strengths and Endurances."),
    MartialArtsB(TalentGroup.Fighting, 1, "Martial Arts B", "This form of martial arts is keyed on offense and inflicting damage in short, quick bursts, and includes such disciplines as boxing. The practitioner of this form of martial arts gains a +1CS to Fighting ability when engaged in unarmed combat."),
    MartialArtsC(TalentGroup.Fighting, 1, "Martial Arts C", "This form of martial arts concentrates on holds and escapes. The practitioner of this form gains a + 1CS to his Strength for Grappling attacks (including damage), a + 1CS to Strength for Escaping and a +1CS to Agility for purposes of Dodging."),
    MartialArtsD(TalentGroup.Fighting, 1, "Martial Arts D", "This meditative form of martial arts searches out the weak spots of the opponent's defenses and strikes against them. The practitioner of this form of attack may ignore the effects of Body Armor (though not force fields) for determining Stun and Slam results. The attack by the character with this Talent does not have to inflict damage to force a check for possible Stun and Slam. The disadvantage is that the target of this attack must be studied for two rounds before the effects may be brought into play. The character with this Talent does not have to attack the character, only watch him in battle for two rounds previous to attacking."),
    MartialArtsE(TalentGroup.Fighting, 1, "Martial Arts E", "This form of martial arts encourages quick striking to catch the opponent off-guard. Heroes with this form of Martial Arts are at a + t to initiative rolls in unarmed combat."),
    Wrestling(TalentGroup.Fighting, 1, "Wrestling", "The hero with this Talent is proficient in applying holds. It includes familiar types of wrestling as well as the sumo forms of the art. The hero with this Talent gains a + 2CS when making Grappling attacks, but gains no benefit in damage. (A hero with Martial Arts B and this Talent gains a +3CS to hit in a Grappling attack, and a + 1CS for damage.)"),
    ThrownObjects(TalentGroup.Fighting, 1, "Thrown Objects", "The hero with this Talent gains a +1CS with all Throwing attacks (both Edged and Blunt), and +1CS on Catching. This applies to both thrown weapons and normal items. If the hero has the Thrown Weapons Talent as well, the modification is +2CS when using thrown weapons."),
    Tumbling(TalentGroup.Fighting, 1, "Tumbling", "The hero with this Talent knows how to fall and land without undue injury. Individuals with this Talent may make an Agility FEAT to land feet-first after any fall that does not inflict damage."),
    Acrobatics(TalentGroup.Fighting, 1, "Acrobatics", "The hero with this Talent is very limber and as such gains advantages when under attack. The hero has a +1CS when dodging, evading, and escaping."),

    Medicine(TalentGroup.Professional, 2, "Medicine", "The hero with this Talent has extensive knowledge of medicine, and as such limited Talents in healing. In general. a character losing Endurance Ranks as the result of a lethal situation can have those losses stopped by any character checking on him. The individual with Medicine Talent may bring back characters that have reached the Shift 0 level up to 20 turns after they have reached that level. A character with this Talent may restore one rank of Endurance to a wounded character per week, in addition to natural healing. Finally, the character with Medicine as a Talent is +1CS on Reason FEATs that involve medical problems, medications, poisons, and surgery."),
    Law(TalentGroup.Professional, 1, "Law", "The character with this Talent has an extensive background in law (the assumption being US Law, but this may vary according to the Judge's campaign). The hero may be a lawyer or capable of applying to pass the bar (Reason FEAT of Good Intensity). A character with Law as a Talent gains a +1CS to all FEAT rolls involving the law, including correct legal procedure. A character without Law gains no benefit to Reason FEATs, and in addition, will have to make Reason FEATs more often than a character with Law Talent."),
    LawEnforcement(TalentGroup.Professional, 1, "Law Enforcement", "The character with this Talent has a background with lawenforcement authorities. This Talent includes both Gun and Law Talents, and the character, if still a member of a lawenforcement agency, may legally carry a gun and make arrests."),
    Pilot(TalentGroup.Professional, 1, "Pilot", "The character with this Talent has a working knowledge of most aircraft, and receives a +1CS for all FEAT rolls involving an aircraft that character is controlling (including Control FEATs, Agility FEATs, and Reason FEATs involving aircraft handling and design). A character with a background that would permit it (a hero who is an alien) may extend this Talent to spacecraft as well."),
    Military(TalentGroup.Professional, 1, "Military", "The hero has had some dealings with one of the armed services. In military matters, the hero gets a +1CS to all FEAT rolls, and in addition may take a member of the armed services as a Contact."),
    BusinessFinance(TalentGroup.Professional, 1, "Business and Finance", "The hero is familiar with the world of business, corporate finance, and how money works. Initial resources are a minimum of Good, and the hero gains a +1CS for FEAT rolls dealing with money. The hero gains a Contact in the Professional category."),
    Journalism(TalentGroup.Professional, 1, "Journalism", "The hero with this Talent gains an additional 2 Contacts to those already generated. The Contacts should be connected with the media in some fashion, such as at local newspapers, radio or TV stations, or has sources in law enforcement, political circles, or snitches of the criminal underworld."),
    Engineering(TalentGroup.Professional, 1, "Engineering", "Engineering includes all the varied types of that profession dedicated to the design of functional items: civil, chemical, mechanical, etc. A character with Engineering Talent gains a +1CS to all FEATs involving building things, including the Resource FEAT to determine if an object can be built."),
    Crime(TalentGroup.Professional, 1, "Crime", "The hero with this Talent has an understanding of the criminal mind and behavior, either from studies or first-hand observation. The character with this Talent gains a +1CS on all Reason and Intuition FEATs involving criminal practices (\"If I were a crook, where would I hide?\"). The hero also gains a Contact in either the police or crime areas."),
    Psychiatry(TalentGroup.Professional, 1, "Psychiatry", "The hero with this Talent has a background in studies of the mind, and as such gains a + 1CS on all FEATs involving the mind. This is a popular Talent with those heroes and villains with Mental Powers, and the character with these Talents gains a +1CS on FEATs involving Mental Control, Domination, Hypnosis, Emotion Control, and Mental Probe Powers."),
    DetectiveEspionage(TalentGroup.Professional, 1, "Detective/Espionage", "The hero with this Talent has been trained to notice small clues in solving crimes. The character with this Talent gains a + 1CS to discover clues to a crime, and in addition gains a Contact in either crime, law enforcement, law, or espionage."),

    Chemistry(TalentGroup.Scientific, 1, "Chemistry", "A +1CS on matters of chemistry, including developing new formulas, finding cures for inorganic poisons, and identifying chemicals by smell, touch, or taste."),
    Biology(TalentGroup.Scientific, 1, "Biology", "A + 1CS on matters of biology, including animal and plant identification, finding cures for organic poisons, and researching diseases and their cures."),
    Geology(TalentGroup.Scientific, 1, "Geology", "A +1CS on matters involving the Earth, including volcanic activity, the geology of the surrounding land, types of rocks and their powers, and mineral identification."),
    Genetics(TalentGroup.Scientific, 1, "Genetics", "A +1CS on matters involving the genes, including creating new life forms, understanding mutants, and researching diseases."),
    Archaeology(TalentGroup.Scientific, 1, "Archaeology", "A +1CS on matters involving the past, including paleontology, historical records, and ancient myths and legends."),
    Physics(TalentGroup.Scientific, 1, "Physics", "A +1CS on matters involving physics and astrophysics, including motion, flight, and the planets and stars."),
    Computers(TalentGroup.Scientific, 1, "Computers", "A +1CS on matters involving computers, computer-controlled equipment, and artificial intelligences."),
    Electronics(TalentGroup.Scientific, 1, "Electronics", "A +1CS on matters involving electronic devices, including their creation and repair."),

    Trance(TalentGroup.MysticalMental, 1, "Trance", "The character may place himself into a trance. While in a trance the character slows his body functions to such a level that he may be assumed to be deceased (Intuition FEAT for the character checking). A character in a trance reduces needs for food and water to a minimal level, and may regain Endurance ranks at one rank per day."),
    MesmerismAndHypnosis(TalentGroup.MysticalMental, 1, "Mesmerism and Hypnosis", "This Talent is a primitive form of Mind Control at the Power rank number equal to the Reason of the character with this Talent. Information can be gained as per a Mental Probe, and posthypnotic suggestions may be implanted within the victim's mind. Any attempt to force an individual to do something that he would not normally do, or divulge information that he would not normally reveal, will cause the hypnotism to break. A hypnotic command fades in 1-10 hours after it is given."),
    SleightOfHand(TalentGroup.MysticalMental, 1, "Sleight of Hand", "This is a Talent developed by stage magicians which causes items to appear and disappear by a combination of misdirection and swift, fluid gestures. The character with this Talent may palm small items, making them appear or disappear with Agility +1CS ability."),
    ResistDomination(TalentGroup.MysticalMental, 1, "Resist Domination", "This is a Psi-Screen that may be developed by the individuals without that Power. This permits the character to resist mental attacks as if the character had a mental power of Psyche +1CS. The Talent is passive in nature, and does not grant any other particular benefit. A character with Mental Probe may be able to discern where the character gained this Talent, but nothing else."),
    MysticOrigin(TalentGroup.MysticalMental, 2, "Mystic Origin", "In the Marvel Universe, all humankind has the potential for developing magical Powers. This \"Talent\" shows that the character has some background with magical forces. Heroes may have derived their powers from these forces if they choose this Talent. A character with this Talent may have Magical Powers, with the approval of the Judge. If the Judge allows magical player characters, then any of the initial Powers created may be spells, and should be noted as deriving from Personal, Universal, or Dimensional energies."),
    OccultLore(TalentGroup.MysticalMental, 1, "Occult Lore", "The character with this Talent has a knowledge of magical societies, antiquities, runes, and a general understanding of forgotten lore. The character gains a +1CS to Reason FEATs involving items of a magical nature."),

    Artist(TalentGroup.Other, 1, "Artist", "The character with an artist background creates works of art, either for himself or for sale to others. This includes painting, sculpting, and writing. A single work takes 1-10 weeks, and upon completion grants the artist Karma points equal to 10 times the number of weeks. The character must allocate some time daily at his work."),
    Languages(TalentGroup.Other, 1, "Languages", "The character with this Talent has a natural understanding of languages. The character gains 1 additional language at start, and made add other languages at half the cost of a Talent (500 paints regardless of who teaches it). Characters without languages Talent must gain this Talent first to learn other languages. The gaining of additional languages assumes someone is available to teach these languages. A Player character with this Talent does not have to assign a language at start, but may fill one in later as need be."),
    FirstAid(TalentGroup.Other, 1, "First Aid", "The medicine Talent notes that the loss of Endurance ranks may be halted by someone checking on the dying character and administering some form of aid. The First Aid Talent grants the character this immediate halt to Endurance rank loss, the recovery of one rank immediately (one use only per situation). and in addition, the hero with this Talent can stabilize a dying character at Shift 0 Health up to 5 rounds after that character reaches that level."),
    RepairAndTinkering(TalentGroup.Other, 1, "Repair and Tinkering", "The character with this Talent gains a + 1CS to any Reason FEATs involving the repair and modification of existing items, but not the building of new items. This +1CS may be added to any other bonuses gained from other Talents, so that an Engineer with Tinkering Talent would gain a +2CS on repair."),
    Trivia(TalentGroup.Other, 1, "Trivia", "This is a general category that covers any one subject desired by the character. On that subject, the character gains + 1CS to all Reason FEATS. (Say, the character is into collecting Spores and Fungus. A Trivia Talent would be: Trivia/ Spores and Fungus). Trivia categories should be specific (old movies, military history, sports, rock music, comic books) as opposed to general (all knowledge) or covered by other Talents."),
    Performer(TalentGroup.Other, 1, "Performer", "The character is someone who acts, sings, dances, mimes, or otherwise uses his Talents to entertain (this is related to the Artist, the key difference being that the Artist may leave the scene of creation; the Performer is identified with that creation directly). A Performer receives 10 karma points for a week's worth of performance, whether in a play, doing a nightclub routine, or working for a movie."),
    AnimalTraining(TalentGroup.Other, 2, "Animal Training", "The character with this Talent has the ability to train animals to perform certain stunts. The individual does not have Animal Empathy or Communications and Control, but may teach an animal a trick based on the Reason FEAT roll. If the hero with this Talent does have Animal Empathy or Animal Communications and Control as Powers, these Powers are raised by +1CS."),
    HeirToFortune(TalentGroup.Other, 2, "Heir to Fortune", "This is not a Talent, but a situation which brings the character into a lot of money. The minimum Resources of a character with this Talent is Remarkable (If your character is making Excellent Resources or less, do not take this Talent). This \"Talent\" may not be gained by a character after the generation process is finished, and may only be chosen by characters being generated."),
    Student(TalentGroup.Other, 2, "Student", "Similar to Heir to Fortune, this Talent may only be chosen at the start of play, and may not be gained through experience. The Student character has no other initial Talents, but may gain other Talents at a discounted price. New Talents cost 1000 Karma points if learned from another player character, 800 if learned from outside. Students may maintain Advancement Totals for a Talent along with other forms of Advancement funds."),
    Leadership(TalentGroup.Other, 1, "Leadership", "The hero with this Talent has the brains and understanding of a cohesive group, such that he is a benefit to the team. Any Karma Pool to which the character belongs receives a 50-point bonus, provided the character with this Talent is recognized as the \"team leader.\" A Karma Pool may only have one recognized leader. though more than one character with Leadership may belong to one group. When the \"Leader\" of a group leaves, the 50 points are deducted from the Karma Pool, but the leader does not receive them for personal use (the bonus points only exist as a part of the pool)."),
    ;
    public static enum TalentGroup {
        Weapon(0.20d),
        Fighting(0.25d),
        Professional(0.20d),
        Scientific(0.20d),
        MysticalMental(0.10d),
        Other(0.05d),
        ;
        public static TalentGroup random(){
            double val = Math.random();
            for (TalentGroup value: values()){
                val -= value.probability;
                if (val <= 0) return value;
            }
            return values()[0];
        }

        private double probability;
        private TalentGroup(double probability){
            this.probability = probability;
        }
    }

    public static List<Talent> forGroup(TalentGroup group){
        List<Talent> talents = new ArrayList<Talent>();
        for (Talent talent: values())
            if (talent.group == group)
                talents.add(talent);
        return talents;
    }
    
    public static Talent random(){
        List<Talent> talents = forGroup(TalentGroup.random());
        return talents.get((int)(Math.random()*talents.size()));
    }

    private TalentGroup group;
    private String name, description;
    private int slots;
    
    private Talent(TalentGroup group, int slots, String name, String description){
        this.group = group;
        this.name = name;
        this.description = description;
        this.slots = slots;
    }

    public TalentGroup getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSlots() {
        return slots;
    }
}
