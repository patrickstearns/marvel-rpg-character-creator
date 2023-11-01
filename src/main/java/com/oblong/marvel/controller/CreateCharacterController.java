package com.oblong.marvel.controller;

import com.oblong.marvel.model.*;
import com.oblong.marvel.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * expected order of calls:
 * initialize()
 * generateBasics()
 *-spendBonusAbilityRanks() (* times)
 *-confirmBasics() [may goto generateBasics()]
 * rollNumberOfPowersContactsTalents()
 *-spendResources()
 * generatePowers()
 * generateWeakness()
 *-confirmPowers()
 * generateTalentsAndContacts()
 *-confirmTalentsAndContacts()
 */

//TODO: demographics module

@SuppressWarnings("unused")
public class CreateCharacterController {

    public static CreationContext initialize(){
        CreationContext context = new CreationContext();
        context.setCharacter(new Character());
        context.setStep(CreationContext.Step.GenerateBasics);
        return context;
    }

    public static void generateBasics(CreationContext context){
        Character c = context.getCharacter();

        c.setDemographics(new CreateDemographicsController().randomDemographics());

        PhysicalForm form = PhysicalForm.random();
        c.setPhysicalForm(form);

        c.setOriginOfPower(OriginOfPower.random());

        c.setFighting(rollAbility(form.getRandomRanksColumn()).add(form.getFightingBonus()).getValue());
        c.setAgility(rollAbility(form.getRandomRanksColumn()).add(form.getAgilityBonus()).getValue());
        c.setStrength(rollAbility(form.getRandomRanksColumn()).add(form.getStrengthBonus()).getValue());
        c.setEndurance(rollAbility(form.getRandomRanksColumn()).add(form.getEnduranceBonus()).getValue());
        c.setReason(rollAbility(form.getRandomRanksColumn()).add(form.getReasonBonus()).getValue());
        c.setIntuition(rollAbility(form.getRandomRanksColumn()).add(form.getIntuitionBonus()).getValue());
        c.setPsyche(rollAbility(form.getRandomRanksColumn()).add(form.getPsycheBonus()).getValue());

        updateHealthAndKarma(context);

        AbilityRank popularity = AbilityRank.Typical.add(form.getPopularityBonus());
        if (c.getPhysicalForm().getPerks().contains(Perk.FeeblePopularity)) popularity = AbilityRank.Feeble;
        if (c.getPhysicalForm().getPerks().contains(Perk.Unpopular)) popularity = AbilityRank.ShiftZero;
        c.setPopularity(popularity.getValue());

        AbilityRank resources = AbilityRank.Typical;
        resources.add(rollAbilityModifier());
        resources.add(form.getResourcesBonus());
        if (resources.lessThan(AbilityRank.Feeble)) resources = AbilityRank.Feeble;
        if (resources.greaterThan(AbilityRank.Monstrous)) resources = AbilityRank.Monstrous;
        if (c.getPhysicalForm().getPerks().contains(Perk.PoorResources)) resources = AbilityRank.Poor;
        if (c.getPhysicalForm().getPerks().contains(Perk.ZeroResources)) resources = AbilityRank.ShiftZero;
        c.setResources(resources.getValue());

        context.setBonusAbilityRanksToSpend(form.getBonusAbilityRanks());
        context.setStep(CreationContext.Step.SpendBonusAbilityPoints);
    }

    public static void spendBonusAbilityRanks(CreationContext context, int[] pointsToSpend) throws IllegalArgumentException {
        if (pointsToSpend.length != 7) throw new IllegalArgumentException("pointsToSpend must be exactly 7 long");

        Character c = context.getCharacter();

        int sum = 0;
        for (int p : pointsToSpend) sum += p;
        if (sum > context.getBonusAbilityRanksToSpend())
            throw new IllegalArgumentException("can't spend more points than you have left to spend");

        c.setFighting(AbilityRank.fromValue(c.getFighting()).add(pointsToSpend[0]).getValue());
        c.setAgility(AbilityRank.fromValue(c.getAgility()).add(pointsToSpend[1]).getValue());
        c.setStrength(AbilityRank.fromValue(c.getStrength()).add(pointsToSpend[2]).getValue());
        c.setEndurance(AbilityRank.fromValue(c.getEndurance()).add(pointsToSpend[3]).getValue());
        c.setReason(AbilityRank.fromValue(c.getReason()).add(pointsToSpend[4]).getValue());
        c.setIntuition(AbilityRank.fromValue(c.getIntuition()).add(pointsToSpend[5]).getValue());
        c.setPsyche(AbilityRank.fromValue(c.getPsyche()).add(pointsToSpend[6]).getValue());

        updateHealthAndKarma(context);

        context.setBonusAbilityRanksToSpend(context.getBonusAbilityRanksToSpend()-sum);
        if (context.getBonusAbilityRanksToSpend() == 0) context.setStep(CreationContext.Step.ConfirmBasics);
    }

    public static void confirmBasics(CreationContext context, boolean confirmed){
        if (confirmed) context.setStep(CreationContext.Step.RollNumbersOfPowers);
        else context.setStep(CreationContext.Step.GenerateBasics);
    }

    public static void rollNumberOfPowersContactsTalents(CreationContext context){
        double proll = Math.random();
        int powers, maxPowers;
        if (proll < 0.12){ powers = 1; maxPowers = 3; }
        else if (proll < 0.26){ powers = 2; maxPowers = 4; }
        else if (proll < 0.41){ powers = 3; maxPowers = 5; }
        else if (proll < 0.55){ powers = 4; maxPowers = 6; }
        else if (proll < 0.66){ powers = 5; maxPowers = 7; }
        else if (proll < 0.75){ powers = 2; maxPowers = 8; }
        else if (proll < 0.83){ powers = 7; maxPowers = 9; }
        else if (proll < 0.89){ powers = 8; maxPowers = 10; }
        else if (proll < 0.94){ powers = 9; maxPowers = 12; }
        else if (proll < 0.97){ powers = 10; maxPowers = 12; }
        else if (proll < 0.90){ powers = 12; maxPowers = 14; }
        else { powers = 14; maxPowers = 18; }
        powers += context.getCharacter().getPhysicalForm().getNumberOfPowersBonus();
        if (powers > maxPowers) powers = maxPowers;
        context.setPowers(powers);
        context.setMaxPowers(maxPowers);

        double troll = Math.random();
        int talents, maxTalents;
        if (troll < 0.12){ talents = 0; maxTalents = 3; }
        else if (troll < 0.26){ talents = 1; maxTalents = 4; }
        else if (troll < 0.41){ talents = 1; maxTalents = 6; }
        else if (troll < 0.55){ talents = 2; maxTalents = 4; }
        else if (troll < 0.66){ talents = 2; maxTalents = 6; }
        else if (troll < 0.75){ talents = 2; maxTalents = 8; }
        else if (troll < 0.83){ talents = 3; maxTalents = 4; }
        else if (troll < 0.89){ talents = 3; maxTalents = 6; }
        else if (troll < 0.94){ talents = 4; maxTalents = 8; }
        else if (troll < 0.97){ talents = 4; maxTalents = 4; }
        else if (troll < 0.90){ talents = 5; maxTalents = 6; }
        else { talents = 6; maxTalents = 8; }
        context.setTalents(talents);
        context.setMaxTalents(maxTalents);

        double croll = Math.random();
        int contacts, maxContacts;
        if (croll < 0.12){ contacts = 1; maxContacts = 3; }
        else if (croll < 0.26){ contacts = 2; maxContacts = 4; }
        else if (croll < 0.41){ contacts = 3; maxContacts = 5; }
        else if (croll < 0.55){ contacts = 4; maxContacts = 6; }
        else if (croll < 0.66){ contacts = 5; maxContacts = 7; }
        else if (croll < 0.75){ contacts = 2; maxContacts = 8; }
        else if (croll < 0.83){ contacts = 7; maxContacts = 9; }
        else if (croll < 0.89){ contacts = 8; maxContacts = 10; }
        else if (croll < 0.94){ contacts = 9; maxContacts = 12; }
        else if (croll < 0.97){ contacts = 10; maxContacts = 12; }
        else if (croll < 0.90){ contacts = 12; maxContacts = 14; }
        else { contacts = 14; maxContacts = 18; }
        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.JustOneFriend)){
            contacts = 1;
            maxContacts = 1;
        }
        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.AllAlone)){
            contacts = 0;
            maxContacts = 0;
        }

        context.setContacts(contacts);
        context.setMaxContacts(maxContacts);

        context.setStep(CreationContext.Step.SpendResources);
    }

    public static void spendResources(CreationContext context, int numPowers, int numContacts, int numTalents) throws IllegalArgumentException {
        Character c = context.getCharacter();
        int pointsAvailable = AbilityRank.fromValue(c.getResources()).difference(AbilityRank.Feeble);
        if (pointsAvailable < 0) pointsAvailable = 0;
        int sum = numContacts + numTalents + 2 * numPowers;

        if (sum > pointsAvailable) throw new IllegalArgumentException("can't spend more points than would put resources to feeble (s="+sum+", pa="+pointsAvailable+")");

        c.setResources(AbilityRank.fromValue(c.getResources()).add(-sum).getValue());

        if (sum == 0 || pointsAvailable == 0) context.setStep(CreationContext.Step.ChooseFormPowers);
    }

    public static void chooseFormPowers(CreationContext context, List<Power> selectedPowers) throws IllegalArgumentException {
        Character c = context.getCharacter();
        int slotsAvailable = context.getMaxPowers()-c.getPowers().size();
        if (selectedPowers.size() > slotsAvailable) throw new IllegalArgumentException("can't select more form powers than available power slots");
        for (Power power: selectedPowers){
            String subtype = power.getSubType();
            if (subtype == null) subtype = "";
            else subtype += " ";
            subtype += "(form power from "+c.getPhysicalForm().getName()+")";
            power.setSubType(subtype);
        }
        c.getPowers().addAll(selectedPowers);

        context.setStep(CreationContext.Step.GeneratePowers);
    }

    public static void generatePowers(CreationContext context){
        int slotsTaken = 0;

        List<Power> powers = new ArrayList<Power>();
        powers.addAll(context.getCharacter().getPhysicalForm().getForcedPowers());
        for (int i = powers.size(); i < context.getPowers(); ){
            Power.PowerClass powerClass = Power.PowerClass.random();
            Power.PowerType powerType = Power.PowerType.random(powerClass);
            AbilityRank powerRank = rollPowerRank();
            Power power = new Power(powerType, powerRank);
            powers.add(power);
            i += power.getPowerType().getSlots();
            slotsTaken += power.getPowerType().getSlots();
        }

        List<Power.PowerType> bonusPowers = new ArrayList<Power.PowerType>();
        for (Power power: powers) bonusPowers.addAll(power.getPowerType().getForcedBonusPowers());
        for (int i = powers.size(); i < bonusPowers.size(); ){
            Power.PowerType powerType = bonusPowers.get(i);
            AbilityRank powerRank = rollPowerRank();
            Power power = new Power(powerType, powerRank);
            powers.add(power);
            i += power.getPowerType().getSlots();
            slotsTaken += power.getPowerType().getSlots();
        }

        if (slotsTaken > context.getMaxPowers()){
            Power powerToRemove = null;
            for (Power power: powers)
                if (power.getPowerType().getSlots() == 1)
                    powerToRemove = power;

            if (powerToRemove == null) powerToRemove = powers.get(0);

            powers.remove(powerToRemove);
        }

        for (Power power: powers){
            context.setMaxPowers(context.getMaxPowers()+power.getPowerType().getMaxPowersBonus());
        }

        context.getCharacter().setPowers(powers);

        context.setStep(CreationContext.Step.ChooseBonusPowers);
    }

    public static void chooseBonusPowers(CreationContext context, Power grantingPower, List<Power> selectedPowers) throws IllegalArgumentException {
        Character c = context.getCharacter();

        for (Power power: selectedPowers){
            String subtype = power.getSubType();
            if (subtype == null) subtype = "";
            else subtype += " ";
            subtype += "(bonus power from "+grantingPower.getPowerType().getName()+")";
            power.setSubType(subtype);
        }
        c.getPowers().addAll(selectedPowers);

        context.setStep(CreationContext.Step.ChooseOptionalPowers);
    }

    public static void chooseOptionalPowers(CreationContext context, Power grantingPower, List<Power> selectedPowers) throws IllegalArgumentException {
        Character c = context.getCharacter();

        for (Power power: selectedPowers){
            String subtype = power.getSubType();
            if (subtype == null) subtype = "";
            else subtype += " ";
            subtype += "(optional power from "+grantingPower.getPowerType().getName()+")";
            power.setSubType(subtype);
        }
        c.getPowers().addAll(selectedPowers);

        context.setStep(CreationContext.Step.GenerateWeakness);
    }

    public static void generateWeakness(CreationContext context){
        Character c = context.getCharacter();
        boolean noAbilitiesAboveRemarkable = false;
        if (AbilityRank.fromValue(c.getFighting()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getAgility()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getStrength()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getEndurance()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getReason()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getIntuition()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;
        if (AbilityRank.fromValue(c.getPsyche()).greaterThan(AbilityRank.Remarkable)) noAbilitiesAboveRemarkable = true;

        List<Weakness> weaknesses = new ArrayList<Weakness>();
        weaknesses.add(Weakness.generate(noAbilitiesAboveRemarkable));
        if (c.getPhysicalForm().getPerks().contains(Perk.ExtraPsychologicalWeakness))
            weaknesses.add(Weakness.generatePsychological(noAbilitiesAboveRemarkable));
        c.setWeaknesses(weaknesses);

        context.setStep(CreationContext.Step.ConfirmPowers);
    }

    public static void confirmPowers(CreationContext context, boolean confirmed){
        //apply any hyper-ability powers, generate any subtypes
        Character c = context.getCharacter();
        for (Power power: c.getPowers()){
            switch(power.getPowerType()){
                case HyperFighting:
                    c.setFighting(AbilityRank.fromValue(c.getFighting()).add(power.getRank().ordinal()).getValue());
                    if (AbilityRank.fromValue(c.getFighting()).greaterThan(AbilityRank.Monstrous))
                        c.setFighting(AbilityRank.Monstrous.getValue());
                    context.getNotes().add("Fighting value shown reflects this ability.");
                    break;
                case HyperAgility:
                    c.setAgility(AbilityRank.fromValue(c.getAgility()).add(power.getRank().ordinal()).getValue());
                    if (AbilityRank.fromValue(c.getAgility()).greaterThan(AbilityRank.Monstrous))
                        c.setAgility(AbilityRank.Monstrous.getValue());
                    context.getNotes().add("Agility value shown reflects this ability.");
                    break;
                case HyperStrength:
                    c.setStrength(AbilityRank.fromValue(c.getStrength()).add(power.getRank().ordinal()).getValue());
                    if (AbilityRank.fromValue(c.getStrength()).greaterThan(AbilityRank.Monstrous))
                        c.setStrength(AbilityRank.Monstrous.getValue());
                    context.getNotes().add("Strength value shown reflects this ability.");
                    break;
                case HyperEndurance:
                    c.setEndurance(AbilityRank.fromValue(c.getEndurance()).add(power.getRank().ordinal()).getValue());
                    if (AbilityRank.fromValue(c.getEndurance()).greaterThan(AbilityRank.Monstrous))
                        c.setEndurance(AbilityRank.Monstrous.getValue());
                    context.getNotes().add("Endurance value shown reflects this ability.");
                    break;
            }

            String subtype = power.getSubType();
            String moreSubtype = power.getPowerType().generateSubtype();
            String newSt = subtype == null ? "" : subtype;
            if (moreSubtype != null) newSt += moreSubtype;
            power.setSubType(newSt);
        }

        //consolidate any duplicated powers - keep the better one
        List<Power> powers = context.getCharacter().getPowers();
        List<Power> powersToRemove = new ArrayList<Power>();
        for (int i = 0; i < powers.size(); i++){
            for (int j = i+1; j < powers.size(); j++){
                if (powers.get(i).getPowerType() == powers.get(j).getPowerType()){
                    if (powers.get(i).getRank().lessThan(powers.get(j).getRank())) powersToRemove.add(powers.get(i));
                    else powersToRemove.add(powers.get(j));
                    context.getNotes().add("Rolled "+powers.get(j).getPowerType().getName()+" twice; the one with the higher power rank was kept.");
                }
            }
        }
        for (Power power: powersToRemove) powers.remove(power);

        //add a note if they have too many powers
        if (powers.size() > context.getMaxPowers()){
            int overage = powers.size() - context.getMaxPowers();
            context.getNotes().add("There are more powers listed than available initial slots.  You need to drop "+overage+" slots' worth of powers.  Keep in mind that if you drop a power with optional powers, you can't keep the optional powers.");
        }

        //add any notes from their power classes
        Set<Power.PowerClass> powerClasses = new TreeSet<Power.PowerClass>();
        for (Power power: powers) powerClasses.add(power.getPowerClass());
        for (Power.PowerClass powerClass: powerClasses)
            if (powerClass.getNotes() != null)
                context.getNotes().add(powerClass.getNotes());

        //next step
        if (confirmed) context.setStep(CreationContext.Step.GenerateTalentsAndContacts);
        else context.setStep(CreationContext.Step.RollNumbersOfPowers);
    }

    public static void generateTalentsAndContacts(CreationContext context){
        Character c = context.getCharacter();

        List<Contact> contacts = new ArrayList<Contact>();
        contacts.addAll(c.getPhysicalForm().getForcedContacts());
        while (contacts.size() < context.getContacts())
            contacts.add(Contact.random());
        c.setContacts(contacts);

        List<Talent> talents = new ArrayList<Talent>();
        int slotsUsed = 0;
        while (talents.size() < context.getTalents()){
            Talent t = Talent.random();
            talents.add(t);
            slotsUsed += t.getSlots();
        }

        if (slotsUsed > context.getMaxTalents()){
            Talent toRemove = null;
            for (Talent talent: talents)
                if (talent.getSlots() == 1){
                    toRemove = talent;
                    break;
                }
            if (toRemove != null) talents.remove(toRemove);
        }

        c.setTalents(talents);

        context.setStep(CreationContext.Step.ConfirmTalentsAndContacts);
    }

    public static void confirmTalentsAndContacts(CreationContext context, boolean confirmed){
        context.getNotes().add(0, context.getCharacter().getDemographics().toHTML());

        if (confirmed) context.setStep(CreationContext.Step.Review);
        else context.setStep(CreationContext.Step.GenerateTalentsAndContacts);
    }

    private static void updateHealthAndKarma(CreationContext context){
        Character c = context.getCharacter();
        c.setHealth(c.getFighting()+c.getAgility()+c.getStrength()+c.getEndurance());
        c.setKarma(c.getReason()+c.getIntuition()+c.getPsyche());

        if (context.getCharacter().getPhysicalForm().getPerks().contains(Perk.DoubleHP)) c.setHealth(c.getHealth()*2);
    }

    private static AbilityRank rollAbility(int column){
        double r = Math.random();
        switch(column){
            case 1:
                if (r < 0.05) return AbilityRank.Feeble;
                else if (r < 0.10) return AbilityRank.Poor;
                else if (r < 0.20) return AbilityRank.Typical;
                else if (r < 0.40) return AbilityRank.Good;
                else if (r < 0.60) return AbilityRank.Excellent;
                else if (r < 0.80) return AbilityRank.Remarkable;
                else if (r < 0.96) return AbilityRank.Incredible;
                else return AbilityRank.Amazing;
            case 2:
                if (r < 0.05) return AbilityRank.Feeble;
                else if (r < 0.25) return AbilityRank.Poor;
                else if (r < 0.75) return AbilityRank.Typical;
                else if (r < 0.95) return AbilityRank.Good;
                else return AbilityRank.Excellent;
            case 3:
                if (r < 0.05) return AbilityRank.Feeble;
                else if (r < 0.10) return AbilityRank.Poor;
                else if (r < 0.40) return AbilityRank.Typical;
                else if (r < 0.80) return AbilityRank.Good;
                else if (r < 0.95) return AbilityRank.Excellent;
                else return AbilityRank.Remarkable;
            case 4:
                if (r < 0.05) return AbilityRank.Feeble;
                else if (r < 0.10) return AbilityRank.Poor;
                else if (r < 0.15) return AbilityRank.Typical;
                else if (r < 0.40) return AbilityRank.Good;
                else if (r < 0.50) return AbilityRank.Excellent;
                else if (r < 0.70) return AbilityRank.Remarkable;
                else if (r < 0.90) return AbilityRank.Incredible;
                else if (r < 0.98) return AbilityRank.Amazing;
                else return AbilityRank.Monstrous;
            case 5:
                if (r < 0.10) return AbilityRank.Feeble;
                else if (r < 0.20) return AbilityRank.Poor;
                else if (r < 0.30) return AbilityRank.Typical;
                else if (r < 0.40) return AbilityRank.Good;
                else if (r < 0.60) return AbilityRank.Excellent;
                else if (r < 0.70) return AbilityRank.Remarkable;
                else if (r < 0.80) return AbilityRank.Incredible;
                else if (r < 0.95) return AbilityRank.Amazing;
                else return AbilityRank.Monstrous;
            default:
                throw new IllegalArgumentException("column must be in range [1-5]");
        }
    }

    public static AbilityRank rollPowerRank(){ return rollAbility(4); }

    private static int rollAbilityModifier(){
        double value = Math.random();
        if (value < 0.15) return -1;
        else if (value < 0.50) return 0;
        else if (value < 0.70) return 1;
        else if (value < 0.85) return 2;
        else if (value < 0.95) return 3;
        else return 4;
    }
}
