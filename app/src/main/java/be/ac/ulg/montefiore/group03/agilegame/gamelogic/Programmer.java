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

    /**
     * Does the programmer like an interest ?
     * @param _i interest
     * @return true if the programmer likes _i
     *         false otherwise
     */
    public Boolean like(Interest _i) {
        if (this.interests != null)
            return this.interests.contains(_i);
        return false;
    }

    /**
     * Does the programmer has some skills corresponding to a skill type?
     * @param type a skill type
     * @return true if the programmer has some skills corresponding to the skill type type
     *         false otherwise
     */
    public Boolean hasSkill(SkillType type){
        if(getSkill(type) != null)
            return true;
        return false;
    }

    /**
     * Get the skills that corresponds to a skill type.
     * @param type the skill type
     * @return All skills corresponding to a skill type in an ArrayList<Skills>
     */
    public Skills getSkill(SkillType type){
        for(Skills s : skills){
            if(s.getType() == type)
                return s;
        }
        return null;
    }

    /**
     * Get salary
     * @return the programmers'salary (int)
     */
    public int getSalary(){
        return this.salary;
    }

    /**
     * Get the task on wich the programmer is working on
     * @return the task on which the programmer is working on
     */
    public Features getWork(){
        return workOn;
    }

    /**
     * Do the programmer working on his task
     */
    public void work(){
        if(workOn != null) {
            Double workTime = workOn.getDuration() - workOn.progress(this);
            notifyObservers(workTime);
        }
    }

    /**
     * Set a bonus for the programmer
     * @param bonus the bonus to set
     */
    public void setBonus(double bonus){
        this.bonus = bonus;
    }

    /**
     * Get the bonus of the programmer
     * @return the programmer's bonus
     */
    public double getBonus(){
        return bonus;
    }

    /**
     * Set the programmer's task
     * @param task the task on which the programmer will work on
     */
    public void setWork(Features task){
        this.workOn = task;
    }

    /**
     * Add a skill to a programmer
     * @param s the skill to add
     */
    public void addSkill(Skills s){
        notifyObservers(s);
        skills.add(s);
    }

    /**
     * Raise the programmer'salary. salary = salary + i.
     * @param i the raise of the salary
     */
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

        if(this.workOn != null && this.workOn.getDuration() <= 0)
            this.workOn = null;
    }

    /**
     * Get all programmer'skills
     * @return programmer'skills in an ArrayList<Skills>
     */
    public ArrayList<Skills> getSkills() { return this.skills; }

    /**
     * Get all programmer's interests
     * @return programmer's interest in an ArrayList<Interest>
     */
    public ArrayList<Interest> getInterests() { return this.interests; }

    /**
     * Is the programmer fired ?
     * @return if the programmer is fired, it returns the number of month before the programmer is effectively fired
     *         -1 if the programmer is not fired
     */
    public int isFired() {
        if (this.isFired)
            return this.firedTime;
        return -1;
    }

    /**
     * Hire the programmer
     * After the function, the programmer will be a team member.
     */
    public void hire() {
        GameLogic.getInstance().hire(this);
    }
}
