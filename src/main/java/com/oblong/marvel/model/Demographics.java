package com.oblong.marvel.model;

public class Demographics {

    private String gender, ageGroup, sexualOrientation, ethnicity, hair, eyes;

    public Demographics(String gender, String ageGroup, String sexualOrientation, String ethnicity,
                        String hair, String eyes){
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.sexualOrientation = sexualOrientation;
        this.ethnicity = ethnicity;
        this.hair = hair;
        this.eyes = eyes;
    }

    public String toHTML(){
        StringBuilder b = new StringBuilder();
        b.append("<table><tr>")
                .append("<td><b>Gender</b></td><td>").append(gender).append("(").append(sexualOrientation).append(")</td>")
                .append("<td><b>Ethnicity</b></td><td colspan=3>").append(ethnicity).append("</td>")
                .append("</tr><tr>")
                .append("<td><b>Age Group</b></td><td>").append(ageGroup).append("</td>")
                .append("<td><b>Hair</b></td><td>").append(hair).append("</td>")
                .append("<td><b>Eyes</b></td><td>").append(eyes).append("</td>")
                .append("</tr></table>");
        return b.toString();
    }

}
