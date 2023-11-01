package com.oblong.marvel.model;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private PhysicalForm physicalForm;
    private OriginOfPower originOfPower;
    private Integer fighting, agility, strength, endurance, reason, intuition, psyche, health, karma, resources, popularity;
    private List<Power> powers;
    private List<Contact> contacts;
    private List<Talent> talents;
    private List<Weakness> weaknesses;
    private Demographics demographics;

    public Character(){
        powers = new ArrayList<Power>();
        contacts = new ArrayList<Contact>();
        talents = new ArrayList<Talent>();
        weaknesses = new ArrayList<Weakness>();
    }

    public PhysicalForm getPhysicalForm() {
        return physicalForm;
    }

    public void setPhysicalForm(PhysicalForm physicalForm) {
        this.physicalForm = physicalForm;
    }

    public OriginOfPower getOriginOfPower() {
        return originOfPower;
    }

    public void setOriginOfPower(OriginOfPower originOfPower) {
        this.originOfPower = originOfPower;
    }

    public Integer getFighting() {
        return fighting;
    }

    public void setFighting(Integer fighting) {
        this.fighting = fighting;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getIntuition() {
        return intuition;
    }

    public void setIntuition(Integer intuition) {
        this.intuition = intuition;
    }

    public Integer getPsyche() {
        return psyche;
    }

    public void setPsyche(Integer psyche) {
        this.psyche = psyche;
    }

    public Integer getResources() {
        return resources;
    }

    public void setResources(Integer resources) {
        this.resources = resources;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    public String toHTML(CreationContext context){
        StringBuilder b = new StringBuilder("<html><body><font face=\"arial\" size=\"4\"><table><tr><td valign=\"top\" width=\"50%\">\n" +
                "<h3 style=\"background: #222288; color: #FFFFFF;\">&nbsp;Primary Abilities</h3>\n" +
                "<b>Physical Form:</b>").append(getPhysicalForm().getName()).append("<br>\n" +
                "<b>Origin of Power:</b> ").append(getOriginOfPower().getName()).append("<br>\n" +
                "<br><table>\n" +
                "<tr><td><b>Fighting</b></td><td>").append(AbilityRank.fromValue(getFighting())).append("(").append(getFighting()).append(")</td></tr>\n" +
                "<tr><td><b>Agility</b></td><td>").append(AbilityRank.fromValue(getAgility())).append("(").append(getAgility()).append(")</td></tr>\n" +
                "<tr><td><b>Strength</b></td><td>").append(AbilityRank.fromValue(getStrength())).append("(").append(getStrength()).append(")</td></tr>\n" +
                "<tr><td><b>Endurance</b></td><td>").append(AbilityRank.fromValue(getEndurance())).append("(").append(getEndurance()).append(")</td></tr>\n" +
                "<tr><td><b>Reason</b></td><td>").append(AbilityRank.fromValue(getReason())).append("(").append(getReason()).append(")</td></tr>\n" +
                "<tr><td><b>Intuition</b></td><td>").append(AbilityRank.fromValue(getIntuition())).append("(").append(getIntuition()).append(")</td></tr>\n" +
                "<tr><td><b>Psyche</b></td><td>").append(AbilityRank.fromValue(getPsyche())).append("(").append(getPsyche()).append(")</td></tr>\n" +
                "</table><h3 style=\"background: #222288; color: #FFFFFF;\">&nbsp;Secondary Abilities</h3><table>\n" +
                "<tr><td><b>Health</b></td><td><b>Karma</b></td><td><b>Resources</b></td><td><b>Popularity</b></td></tr>\n" +
                "<tr>\n")
                .append("\t\t\t\t<td>").append(getHealth()).append("</td>\n")
                .append("\t\t\t\t<td>").append(getKarma()).append("</td>\n")
                .append("\t\t\t\t<td>").append(AbilityRank.fromValue(getResources())).append("(").append(getResources()).append(")</td>\n")
                .append("\t\t\t\t<td>").append(AbilityRank.fromValue(getPopularity())).append("(").append(getPopularity()).append(")</td>\n")
                .append("</tr></table></td><td valign=\"top\"><h3 style=\"background: #222288; color: #FFFFFF;\">" +
                        "&nbsp;Weaknesses (").append(weaknesses.size()).append(")</h3>\n");
        for (Weakness weakness: getWeaknesses()) b.append(weakness.getName()).append("<br>\n");

        b.append("<br>\n<h3 style=\"background: #222288; color: #FFFFFF;\">Contacts (").append(context.getContacts()).append("/").append(context.getMaxContacts()).append(" max to start").append(")</h3>\n");
        for (Contact contact: getContacts()) b.append(contact.getName()).append("<br>\n");

        b.append("<br>\n<h3 style=\"background: #222288; color: #FFFFFF;\">Talents (").append(context.getTalents()).append("/").append(context.getMaxTalents()).append(" max to start").append(")</h3>\n");
        for (Talent talent: getTalents()) b.append(talent.getName()).append("<br>\n");

        b.append("</td></tr><tr><td colspan=\"2\">\n");

        b.append("<br>\n<h3 style=\"background: #222288; color: #FFFFFF;\">Powers (").append(context.getPowers()).append("/").append(context.getMaxPowers()).append(" max to start").append(")</h3>\n");
        for (Power power: getPowers()){
            b.append("<b>").append(power.getPowerType().getName()).append("</b> (").append(power.getRank().getSymbol()).append(")").append(power.getSubType() != null ? " "+power.getSubType() : "").append("<br>\n");
            b.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(power.getPowerType().getNotes()).append("<br>\n");
        }

        b.append("<h3 style=\"background: #222288; color: #FFFFFF;\">&nbsp;Notes</h3>\n<ul>\n");
        b.append("<li>PHYSICAL FORM:").append(getPhysicalForm().getNotes()).append("</li>\n");
        b.append("<li>ORIGIN OF POWER:").append(getOriginOfPower().getNotes()).append("</li>\n");
        for (Weakness weakness: getWeaknesses())
            b.append("<li>WEAKNESS:").append(weakness.getNotes()).append("</li>\n");
        b.append("</ul>\n" +
                "<br>\n" +
                "<h3 style=\"background: #222288; color: #FFFFFF;\">&nbsp;Notes to Player</h3>\n" +
                "<ul>\n");
        for (String note: context.getNotes())
            b.append("\t\t<li>").append(note).append("</li>\n");
        b.append("</ul>\n" +
                "<br>\n" +
                "</td></tr></table></font></body></html>");

        return b.toString();
    }
}
