package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by charybde on 26.04.16.
 * Contains all methods that are utils for the application but not specific of a particular class
 */
public class Utils {

    /**
     * Get the name of a programmer
     * @param p the programmer we want to get the name
     * @param context the context in which the method is called
     * @return the name of the programmer in a string
     */
    public static String getProgrammerName(Programmer p, Context context) {
        Resources res = context.getResources();
        if (p.hasId()) {
            return res.getStringArray(R.array.persons)[p.getId() % res.getStringArray(R.array.persons).length];
        }

        return p.getName();
    }

    /**
     * Get the name of a feature
     * @param f the feature we want the name
     * @param context the context in which the method is called
     * @return the name of the feature in a String
     */
    public static String getFeatureName(Features f, Context context){
        Resources res = context.getResources();
        switch(f.getNeeded()){
            case Android :
                return res.getStringArray(R.array.features_ANDROID)[f.getId() % res.getStringArray(R.array.features_ANDROID).length];
            case JAVA:
                return res.getStringArray(R.array.features_JAVA)[f.getId() % res.getStringArray(R.array.features_JAVA).length];
            case Network:
                return res.getStringArray(R.array.features_NETWORK)[f.getId() % res.getStringArray(R.array.features_NETWORK).length];
            case  Design:
                return res.getStringArray(R.array.features_DESIGN)[f.getId() % res.getStringArray(R.array.features_DESIGN).length];
            case Diagrams:
                return res.getStringArray(R.array.features_DIAGRAMS)[f.getId() % res.getStringArray(R.array.features_DIAGRAMS).length];
        }
        return "No Name";
    }

    /**
     * Get the names of skills corresponding to a particulare skill type
     * @param type the skill type
     * @param context the context in which the method is called
     * @return An array of strings. Each element is the name of a skill
     */
    public static String[] getFeatureNamesArray(SkillType type, Context context){
        Resources res = context.getResources();
        switch(type){
            case Android :
                return res.getStringArray(R.array.features_ANDROID);
            case JAVA:
                return res.getStringArray(R.array.features_JAVA);
            case Network:
                return res.getStringArray(R.array.features_NETWORK);
            case  Design:
                return res.getStringArray(R.array.features_DESIGN);
            case Diagrams:
                return res.getStringArray(R.array.features_DIAGRAMS);
        }
        return null;
    }

    /**
     * Get the sprite corresponding to a skill type
     * @param type the skill type
     * @param context the context in which the method is called
     * @return the drawable resource corresponding to the sprite
     */
    public static Drawable getFeatureImg(SkillType type, Context context){
        switch(type){
            case Android :
                return ContextCompat.getDrawable(context, R.drawable.android);
            case JAVA:
                return ContextCompat.getDrawable(context, R.drawable.java);
            case Network:
                return ContextCompat.getDrawable(context, R.drawable.network);
            case  Design:
                return ContextCompat.getDrawable(context, R.drawable.design);
            case Diagrams:
                return ContextCompat.getDrawable(context, R.drawable.diagrams);
        }
        return null;
    }

    public static Drawable getFeatureImg(Features f, Context context){
        return getFeatureImg(f.getNeeded(), context);
    }

    /**
     * From an integer representing an amount of money, get the string corresponding to it.
     * @param money the money to convert into string
     * @return the string corresponding to the money in the format 123,456
     * Warning: the comma represents thousands
     */
    public static String getMoneyReadable(int money) {
        String newMoney = "";

        while ((money / 1000) > 0) {

            String money_to_treat = "" + money % 1000;
            /* padding 0 to the money to be treaten */

            while (money_to_treat.length() < 3) {
                money_to_treat = "0" + money_to_treat;
            }

            newMoney = "," + money_to_treat + newMoney;
            money = money / 1000;
        }

        newMoney = money + newMoney;

        return newMoney;
    }
}
