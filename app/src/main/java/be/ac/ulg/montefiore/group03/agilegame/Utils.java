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
 */
public class Utils {

    public static String getProgrammerName(Programmer p, Context context) {
        Resources res = context.getResources();
        if (p.hasId()) {
            return res.getStringArray(R.array.persons)[p.getId() % res.getStringArray(R.array.persons).length];
        }

        return p.getName();
    }

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

}