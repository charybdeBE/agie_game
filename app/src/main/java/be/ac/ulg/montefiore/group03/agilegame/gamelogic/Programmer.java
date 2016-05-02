package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.List;

import be.ac.ulg.montefiore.group03.agilegame.Utils;

/**
 * Created by charybde on 08.03.16.
 */
public class Programmer extends Person {
    private ArrayList<Skills> skills;
    private ArrayList<Interest> interests;

    private Boolean isFired;
    private int firedTime; //nr of month before being really fired
    private Features workOn; // Work max on a task ?
    private int salary;
    private double bonus; // Bonus to be applied on his month work

    public Programmer(String name) {
        super(name);
        this.skills = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.bonus = 1;
        this.isFired = false;
    }

    public Programmer(String name, ArrayList<Skills> _s, ArrayList<Interest> _i) {
        super(name);
        this.bonus = 1;
        this.skills = _s;
        this.interests = _i;
        this.salary = 2000;
        for(int i = 0; i < _s.size(); ++i){
            this.salary += 300 * _s.get(i).getLevel() * _s.get(i).getLevel();
        }
        this.isFired = false;
    }

    public Programmer(int id, ArrayList<Skills> _s, ArrayList<Interest> _i) {
        super(id);
        this.bonus = 1;
        this.skills = _s;
        this.interests = _i;
        this.salary = 2000;
        for(int i = 0; i < _s.size(); ++i){
            this.salary += 300 * _s.get(i).getLevel() * _s.get(i).getLevel();
        }
        this.isFired = false;
    }

    public Boolean like(Interest _i){
        return this.interests.contains(_i);
    }

    public Boolean hasSkill(SkillType type){
        if(getSkill(type) != null)
            return true;
        return false;
    }
    public Skills getSkill(SkillType type){
        for(Skills s : skills){
            if(s.getType() == type)
                return s;
        }
        return null;
    }

    public int getSalary(){
        return this.salary;
    }

    public Features getWork(){
        return workOn;
    }

    public void work(){
        if(workOn != null) {
            Double workTime = workOn.getDuration() - workOn.progress(this);
            notifyObservers(workTime);
        }
    }

    public void setBonus(double bonus){
        this.bonus = bonus;
    }
    public double getBonus(){
        return bonus;
    }

    public void setWork(Features task){
        this.workOn = task;
    }

    public void addSkill(Skills s){
        notifyObservers(s);
        skills.add(s);
    }

    public void getRaise(int i){
        this.salary += i;
    }

    /**
     * fire a programmer (due to legal issue he is only fire after 3 months)
     */
    public void fire(){
        this.isFired = true;
        this.firedTime = 3;
    }

    /**
     * Reset the bonus, and workOn + progress on the fire action
     * @param team = the team the programmer is working for
     */
    public void endMonth(ArrayList<Programmer> team){
        if(isFired){
            firedTime--;
            if(firedTime == 0)
                team.remove(this);

            this.bonus = 0.5;
        }
        else {
            this.bonus = 1.0;
        }

        if(this.workOn.getDuration() <= 0)
            this.workOn = null;
    }

    public ArrayList<Skills> getSkills() { return this.skills; }

    public ArrayList<Interest> getInterests() { return this.interests; }

    public int isFired() {
        if (this.isFired)
            return this.firedTime;
        return -1;
    }

    public void hire() {
        GameLogic.getInstance().hire(this);
    }
}
