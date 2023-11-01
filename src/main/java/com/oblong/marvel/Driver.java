package com.oblong.marvel;

import com.oblong.marvel.controller.CreateCharacterController;
import com.oblong.marvel.model.*;
import com.oblong.marvel.model.Character;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args){
        Driver d = new Driver();
        CreationContext context = d.createRandomCharacter();
        System.out.println(output(context.getCharacter(), context.getNotes()));
        System.exit(0);
    }

    public CreationContext createRandomCharacter(){
        //initialize
        CreationContext context = CreateCharacterController.initialize();
        Character c = context.getCharacter();

        //basics
        CreateCharacterController.generateBasics(context);

        //bonus ability points
        if (context.getBonusAbilityRanksToSpend() > 0)
            context.getNotes().add("You have "+context.getBonusAbilityRanksToSpend()+" bonus ability ranks to spend.  Each may boost one of your primary abilities by 1 rank.");
        CreateCharacterController.spendBonusAbilityRanks(context, new int[]{0, 0, 0, 0, 0, 0, 0});

        //confirm!
        CreateCharacterController.confirmBasics(context, true);

        //num stuff
        CreateCharacterController.rollNumberOfPowersContactsTalents(context);

        //spend resources - we skip here
        context.getNotes().add("You may reduce your Resources to buy additional Contacts, Talents or Powers.  You may not reduce your Resources below Feeble in this way or gain more than you have available initial slots.  Contacts and Talents cost 1 rank of Resources each, and Powers cost 2.");
        CreateCharacterController.spendResources(context, 0, 0, 0);

        //choose form powers
        List<Power> selectedFormPowers = new ArrayList<Power>();
        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.Traveller))
            selectedFormPowers.add(
                    new Power(Power.PowerType.random(Power.PowerClass.Travel), CreateCharacterController.rollPowerRank()));
        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.AnimalSenses)){
            selectedFormPowers.add(new Power(Power.PowerType.random(Power.PowerClass.Detection), AbilityRank.Good));
            selectedFormPowers.add(new Power(Power.PowerType.random(Power.PowerClass.Detection), AbilityRank.Good));
        }
        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.EnergyBeing)){
            selectedFormPowers.add(
                    new Power(Power.PowerType.random(Power.PowerClass.EnergyEmission), CreateCharacterController.rollPowerRank()));
        }
        CreateCharacterController.chooseFormPowers(context, selectedFormPowers);

        //generate powers
        CreateCharacterController.generatePowers(context);

        //bonus powers
        for (Power power: new ArrayList<Power>(context.getCharacter().getPowers())){
            List<Power> selectedBonusPowers = new ArrayList<Power>();
            for (int i = 0; i < power.getPowerType().getNumBonusPowers(); i++){
                Power.PowerClass powerClass = Power.PowerClass.random();
                Power.PowerType type = Power.PowerType.random(powerClass);
                AbilityRank rank = CreateCharacterController.rollPowerRank();
                selectedBonusPowers.add(new Power(type, rank));
            }
            CreateCharacterController.chooseBonusPowers(context, power, selectedBonusPowers);
        }

        //optional powers
        for (Power power: new ArrayList<Power>(c.getPowers())){
            List<Power> selectedOptionalPowers = new ArrayList<Power>();
            for (Power.PowerType type: power.getPowerType().getOptionalPowers())
                selectedOptionalPowers.add(new Power(type, CreateCharacterController.rollPowerRank()));
            CreateCharacterController.chooseOptionalPowers(context, power, selectedOptionalPowers);
        }

        //weakness(es)
        CreateCharacterController.generateWeakness(context);

        //confirm!
        CreateCharacterController.confirmPowers(context, true);

        //talents/contacts
        CreateCharacterController.generateTalentsAndContacts(context);

        //confirm!
        CreateCharacterController.confirmTalentsAndContacts(context, true);

        return context;
    }

    private static String output(Character c, List<String> notes){
        StringBuilder b = new StringBuilder();
        b.append(rpad("Physical Form :", 24)).append(rpad(c.getPhysicalForm().getName(), 56)).append("\n");
        b.append(rpad("Origin of Power:", 24)).append(rpad(c.getOriginOfPower().getName(), 56)).append("\n");
        b.append("F ").append(AbilityRank.fromValue(c.getFighting()).getSymbol()).append("\n");
        b.append("A ").append(AbilityRank.fromValue(c.getAgility()).getSymbol()).append("\n");
        b.append("S ").append(AbilityRank.fromValue(c.getStrength()).getSymbol()).append("\n");
        b.append("E ").append(AbilityRank.fromValue(c.getEndurance()).getSymbol()).append("\n");
        b.append("R ").append(AbilityRank.fromValue(c.getReason()).getSymbol()).append("\n");
        b.append("I ").append(AbilityRank.fromValue(c.getIntuition()).getSymbol()).append("\n");
        b.append("P ").append(AbilityRank.fromValue(c.getPsyche()).getSymbol()).append("\n");
        b.append("Health     ").append(c.getHealth()).append("\n");
        b.append("Karma      ").append(c.getKarma()).append("\n");
        b.append("Resources  ").append(AbilityRank.fromValue(c.getResources()).getSymbol()).append("\n");
        b.append("Popularity ").append(AbilityRank.fromValue(c.getPopularity()).getSymbol()).append("\n");
        b.append("\n");
        
        StringBuilder contacts = new StringBuilder();
        for (Contact contact: c.getContacts()) contacts.append(contact.getName()).append(", ");
        b.append("Contacts: ").append(contacts.toString()).append("\n");

        StringBuilder talents = new StringBuilder();
        for (Talent talent: c.getTalents()) talents.append(talent.getName()).append(", ");
        b.append("Talents: ").append(talents.toString()).append("\n");

        StringBuilder weaknesss = new StringBuilder();
        for (Weakness weakness: c.getWeaknesses()) weaknesss.append(weakness.getName()).append(", ");
        b.append("Weaknesses: ").append(weaknesss.toString()).append("\n");

        b.append("============================== POWERS ================================").append("\n");
        for (Power power: c.getPowers()){
            b.append(power.getPowerType().getName()).append(" (").append(power.getRank().getSymbol()).append(")").append(power.getSubType() != null ? " "+power.getSubType() : "").append("\n");
            b.append("\t").append(power.getPowerType().getNotes()).append("\n");
        }

        b.append("============================== NOTES ================================").append("\n");
        b.append("PHYSICAL FORM: ").append(c.getPhysicalForm().getNotes()).append("\n");
        b.append("ORIGIN OF POWER: ").append(c.getOriginOfPower().getNotes()).append("\n");
        for (int i = 0; i < c.getWeaknesses().size(); i++)
            b.append("WEAKNESS:\n").append(c.getWeaknesses().get(i).getNotes()).append("\n");

        b.append("========================= NOTES TO PLAYER ===========================").append("\n");
        for (String note: notes)
            b.append(note).append("\n");

        return b.toString();
    }
    
    private static String rpad(String s, int numchars){
        int d = numchars-s.length();
        if (d < 0) return s;
        else return s+pad(d);
    }

    private static String lpad(String s, int numchars){
        int d = numchars-s.length();
        if (d < 0) return s;
        else return pad(d)+s;
    }
    
    private static String pad(int num){
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < num; i++) b.append(" ");
        return b.toString();
    }
}
