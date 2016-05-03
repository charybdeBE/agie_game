package be.ac.ulg.montefiore.group03.agilegame.gamelogic.journal;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.events.Feature_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;

/**
 * Created by charybde on 20.04.16.
 */
public class Journal_entry_feature implements Journal_entry{
    private double workDone;
    private boolean isFinish;
    private boolean isCancel;
    private int id;


    private SkillType type;

    public ArrayList<Feature_Event> getEvents() {
        return events;
    }

    public double getWorkDone() {
        return workDone;
    }

    public void addWorkDone(double workDone) {
        this.workDone += workDone;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void addEvents(Feature_Event event) {
        this.events.add(event);
    }

    public void setId(int i){id = i;}

    public int getId( ){ return id;}


    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    private ArrayList<Feature_Event> events;

    Journal_entry_feature(){
        events = new ArrayList<>();
        workDone = 0;
        isCancel = false;
        isFinish = false;
    }
}
