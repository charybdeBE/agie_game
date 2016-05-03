package be.ac.ulg.montefiore.group03.agilegame.gamelogic.journal;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.events.Programmer_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 20.04.16.
 */
public class Journal_entry_programmer implements Journal_entry {
    private ArrayList<Programmer_Event> events;
    private ArrayList<Skills> upSkills;
    private double workDone;
    private Features feat;
    private int id;

    public Features getFeat() {
        return feat;
    }
    public void setId(int i){id = i;}

    public int getId( ){ return id;}


    public void setFeat(Features feat) {
        this.feat = feat;
    }

    public ArrayList<Programmer_Event> getEvents() {
        return events;
    }

    public void addEvent(Programmer_Event event) {
        this.events.add(event);
    }

    public ArrayList<Skills> getUpSkills() {
        return upSkills;
    }

    public void addUpSkills(Skills s) {
        this.upSkills.add(s);
    }

    public double getWorkDone() {
        return workDone;
    }

    public void setWorkDone(double workDone) {
        this.workDone = workDone;
    }

    Journal_entry_programmer(){
        events = new ArrayList<>();
        upSkills = new ArrayList<>();
    }
}
