package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charybde on 08.03.16.
 */
public class Programmer extends Person {
    private ArrayList<Skills> skills;
    private ArrayList<Interest> interests;

    private Features workOn; // Work max on a task ?
    private int salary;
    private double bonus; // Bonus to be applied on his month work

    public Programmer(String name) {
        super(name);
        this.skills = new ArrayList<>();
        this.interests = new ArrayList<>();
    }

    public Programmer(String name, ArrayList<Skills> _s, ArrayList<Interest> _i) {
        super(name);
        this.bonus = 1;
        this.skills = _s;
        this.interests = _i;
        this.salary = 800;
        for(int i = 0; i < _s.size(); ++i){
            this.salary += 300 * _s.get(i).getLevel() * _s.get(i).getLevel();
        }
    }

    public Programmer(int id, ArrayList<Skills> _s, ArrayList<Interest> _i) {
        super(id);
        this.bonus = 1;
        this.skills = _s;
        this.interests = _i;
        this.salary = 800;
        for(int i = 0; i < _s.size(); ++i){
            this.salary += 300 * _s.get(i).getLevel() * _s.get(i).getLevel();
        }
    }

    public Boolean like(Interest _i){
        return this.interests.contains(_i);
    }

    public Boolean hasSkill(SkillType type){
        return this.skills.contains(type);
    }
    public Skills getSkill(SkillType type){
        return skills.get(skills.indexOf(type));
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
        System.out.println("Let's work");
        this.workOn = task;
    }

    public void addSkill(Skills s){
        notifyObservers(s);
        skills.add(s);
    }

    public void getRaise(int i){
        this.salary += i;
    }

}
