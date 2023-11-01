package com.oblong.marvel.model;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    public static enum Type {
        MedicalProfessional("Medical Professional"),
        Lawyer("Lawyer"),
        LawEnforcement("Law Enforcement"),
        Military("Military"),
        BusinessWorld("Business Professional"),
        Journalism("Journalist"),
        Crime("Criminal"),
        Engineering("Engineer"),
        Psychiatry("Mental Health Professional"),
        DetectiveEspionage("Detective/Espionage"),
        HeroGroup("Hero Group"),
        ArtistPerformer("Artist/Performer"),

        Chemistry("Chemist"),
        Biology("Biologist"),
        Geology("Geologist"),
        Genetics("Genetecist"),
        Archeology("Archaeologist"),
        Physics("Physicist"),
        Computers("Programmer/Hacker"),
        Electronics("Techie"),

        Local("Local Politician"),
        State("State Politician"),
        National("National Politician"),
        OtherNational("National Beaurocrat"),
        International("International Diplomat"),
        Planetary("Planetary Leader"),

        Religion("Religious Figure"),
        OccultLore("Occult Researcher/Practitioner"),
        Mythology("Mythologist"),
        ;
        private String name;
        private Type(String name){
            this.name = name;
        }
        public String getName(){ return name; }
    }

    public static Contact random(){
        Type type = Type.values()[(int)(Math.random()*Type.values().length)];
        return new Contact(type.getName());
    }
    
    private String name;

    public Contact(String name){
        this.name = name;
    }

    public String getName(){ return name; }
    
}
