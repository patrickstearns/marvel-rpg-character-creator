package com.oblong.marvel.model;

import java.util.ArrayList;
import java.util.List;

public class CreationContext {

    public enum Step {
        GenerateBasics,
        SpendBonusAbilityPoints,
        ConfirmBasics,
        RollNumbersOfPowers,
        SpendResources,
        ChooseFormPowers,
        GeneratePowers,
        ChooseBonusPowers,
        ChooseOptionalPowers,
        GenerateWeakness,
        ConfirmPowers,
        GenerateTalentsAndContacts,
        ConfirmTalentsAndContacts,
        Review
    }

    private Character character;
    private Step step;
    private int bonusAbilityRanksToSpend;
    private int powers, maxPowers, contacts, maxContacts, talents, maxTalents;
    private List<Talent.TalentGroup> talentGroups;
    private List<String> notes;

    public CreationContext(){
        notes = new ArrayList<String>();
    }

    public Character getCharacter(){ return character; }
    public void setCharacter(Character character){ this.character = character; }

    public Step getStep(){ return step; }
    public void setStep(Step step){ this.step = step; }

    public int getBonusAbilityRanksToSpend() {
        return bonusAbilityRanksToSpend;
    }

    public void setBonusAbilityRanksToSpend(int bonusAbilityPointsToSpend) {
        this.bonusAbilityRanksToSpend = bonusAbilityPointsToSpend;
    }

    public int getPowers() {
        return powers;
    }

    public void setPowers(int powers) {
        this.powers = powers;
    }

    public int getMaxPowers() {
        return maxPowers;
    }

    public void setMaxPowers(int maxPowers) {
        this.maxPowers = maxPowers;
    }

    public int getContacts() {
        return contacts;
    }

    public void setContacts(int contacts) {
        this.contacts = contacts;
    }

    public int getMaxContacts() {
        return maxContacts;
    }

    public void setMaxContacts(int maxContacts) {
        this.maxContacts = maxContacts;
    }

    public int getTalents() {
        return talents;
    }

    public void setTalents(int talents) {
        this.talents = talents;
    }

    public int getMaxTalents() {
        return maxTalents;
    }

    public void setMaxTalents(int maxTalents) {
        this.maxTalents = maxTalents;
    }

    public List<Talent.TalentGroup> getTalentGroups() {
        return talentGroups;
    }

    public void setTalentGroups(List<Talent.TalentGroup> talentGroups) {
        this.talentGroups = talentGroups;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}
