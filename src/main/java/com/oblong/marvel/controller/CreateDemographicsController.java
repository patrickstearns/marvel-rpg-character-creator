package com.oblong.marvel.controller;

import com.oblong.marvel.model.Demographics;

public class CreateDemographicsController {

    public Demographics randomDemographics(){
        String ethnicity = randomEthnicity();
        return new Demographics(randomGender(), randomAgeGroup(), randomSexualOrientation(), ethnicity,
                randomHair(ethnicity), randomEyes(ethnicity));
    }

    private String randomGender(){
        String gender = "Male";
        if (Math.random() < 0.508) gender = "Female";
        if (Math.random() < 0.018) gender = "Trans "+gender;
        return gender;
    }

    private String randomAgeGroup(){
        String age;
        double ager = Math.random();
        if (ager < 0.063) age = "Under 5";
        else if (ager < 0.359) age = "5 - 18"; //359
        else if (ager < 0.733) age = "19 - 64";
        else age = "Over 65";
        return age;
    }

    private String randomSexualOrientation(){
        String sexualOrientation = "Hetero";
        double sor = Math.random();
        if (sor < 0.02) sexualOrientation = "Homo";
        return sexualOrientation;
    }

    private String randomEthnicity(){
        String ethnicity;
        double er = Math.random();
        if (er < 0.132) ethnicity = "African American/Black";
        else if (er < 0.144) ethnicity = "Native American/Alaskan";
        else if (er < 0.197) ethnicity = "Asian";
        else if (er < 0.199) ethnicity = "Hawaiian or Pacific Islander";
        else if (er < 0.223) ethnicity = "Two or More";
        else if (er < 0.394) ethnicity = "Hispanic or Latino";
        else if (er < 0.545) ethnicity = "White Hispanic";
        else ethnicity = "White";
        return ethnicity;
    }

    private String randomHair(String ethnicity){
        String hair = "Black";
        if (ethnicity.equals("White") || ethnicity.equals("White Hispanic") || ethnicity.equals("Two or More")){
            double hr = Math.random();
            if (hr < 0.28) hair = "Blond";
            else if (hr < 0.335) hair = "Red";
            else if (hr < 0.59) hair = "Light Brown";
            else if (hr < 0.79) hair = "Dark Brown";
        }
        return hair;
    }

    private String randomEyes(String ethnicity){
        String eyes = "Black";
        if (ethnicity.equals("White") || ethnicity.equals("White Hispanic") || ethnicity.equals("Two or More")){
            double eyr = Math.random();
            if (eyr < 0.5) eyes = "Blue";
            else if (eyr < 0.57) eyes = "Gray";
            else if (eyr < 0.63) eyes = "Green";
            else if (eyr < 0.70) eyes = "Hazel";
            else if (eyr < 0.95) eyes = "Brown";
        }
        return eyes;
    }


}
