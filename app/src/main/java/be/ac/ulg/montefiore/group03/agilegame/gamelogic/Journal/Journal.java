package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.All_Team_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Feature_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Programmer_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Salary_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;


/**
 * Created by charybde on 20.04.16.
 */
public class Journal implements Observer {
    private HashMap<Programmer, Journal_entry_programmer> entries_prog;
    private HashMap<Features,Journal_entry_feature> entries_feat;
    private Journal_entry_budget entry_budget;

    public Journal(){
        entries_prog = new HashMap<>();
        entries_feat = new HashMap<>();
        entry_budget = new Journal_entry_budget();
    }

    @Override
    public void update(Observable observable, Object data) {
        if(observable instanceof Programmer){
            if(data instanceof Skills)
                update_programmer_skills((Programmer) observable, (Skills) data);
            if(data instanceof Double)
                update_programmer_work((Programmer) observable, (Double) data);
            if(data instanceof Programmer_Event)
                update_programmer_event((Programmer) observable, (Programmer_Event) data);
        }
        if(observable instanceof Features){
            if(data instanceof Double && (Double) data == 0)
                update_feature_completion((Features) observable);
            if(data instanceof Double && (Double) data == -1) //Make constance or stuff like that
                update_feature_cancel((Features) observable);
            if(data instanceof Feature_Event)
                update_feature_event((Features) observable, (Feature_Event) data);

        }
    }

    private void update_feature_event(Features feat, Feature_Event event) {
        Journal_entry_feature entry = getOrNew(feat);
        entry.addEvents(event);
        entries_feat.put(feat, entry);
    }


    private void update_feature_completion(Features f) {
        Journal_entry_feature entry = getOrNew(f);
        entry.setIsFinish(true);
        entries_feat.put(f, entry);
    }

    private void update_feature_cancel(Features f) {
        Journal_entry_feature entry = getOrNew(f);
        entry.setIsCancel(true);
        entries_feat.put(f, entry);
    }

    private Journal_entry_programmer getOrNew(Programmer prog){
        Journal_entry_programmer entry;
        if(entries_prog.containsKey(prog)) {
            entry = entries_prog.get(prog);
        }
        else{
            entry = new Journal_entry_programmer();
        }
        return entry;
    }
    private Journal_entry_feature getOrNew(Features f){
        Journal_entry_feature entry;
        if(entries_feat.containsKey(f)){
            entry = entries_feat.get(f);
        }
        else{
            entry = new Journal_entry_feature();
        }
        return entry;
    }

    private void update_programmer_work(Programmer prog, Double data) {
        Journal_entry_programmer entry = getOrNew(prog);
        Journal_entry_feature entry_feat = getOrNew(prog.getWork());
        entry.setWorkDone(data);
        entry.setFeat(prog.getWork());
        entry_feat.addWorkDone(data);
        entries_feat.put(prog.getWork(), entry_feat);
        entries_prog.put(prog, entry);
    }

    private void update_programmer_skills(Programmer prog, Skills data) {
        Journal_entry_programmer entry = getOrNew(prog);
        entry.addUpSkills(data);
        entries_prog.put(prog, entry);
    }

    private void update_programmer_event(Programmer prog, Programmer_Event event) {
        System.out.println("okok");
        Journal_entry_programmer entry = getOrNew(prog);
        entry.addEvent(event);
        entries_prog.put(prog, entry);
    }

    public void setStartBudget(int budget){
        entry_budget.setStartBudget(budget);
    }

    public void addSalary(int salary){
        entry_budget.addSalaryDepense(salary);
    }

    public ArrayList<Journal_entry> getArray(){
        ArrayList<Journal_entry> toRet = new ArrayList<>();
        for(Map.Entry<Features, Journal_entry_feature> entry : entries_feat.entrySet()){
            entry.getValue().setId(entry.getKey().getId());
            entry.getValue().setType(entry.getKey().getNeeded());
            toRet.add(entry.getValue());
        }
        for(Map.Entry<Programmer, Journal_entry_programmer> entry : entries_prog.entrySet()){
            entry.getValue().setId(entry.getKey().getId());
            toRet.add(entry.getValue());
        }
        toRet.add(entry_budget);
        return toRet;
    }
}

