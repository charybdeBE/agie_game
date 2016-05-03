package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.Observable;

/**
 * Created by charybde on 08.03.16.
 */
public class Features extends Observable {
    private SkillType needed; // The skill needed to work on tat feature
    private double monthNeeded; // The number of month / Level of work
    private int id;
    private double delay_bonus;

    public Features(int id, SkillType what, int time){
        this.id = id;
        this.needed = what;
        this.monthNeeded = time;
        this.delay_bonus = 1;
    }

    /**
     * get the skill type needed to do the feature
     * @return the skill type
     */
    public SkillType getNeeded() { return this.needed; }

    /**
     * get the Duration
     * @return TODO
     */
    public double getDuration(){ return  this.monthNeeded; }

    /**
     * Get the id of the feature
     * @return the id of the feature
     */
    public int getId() {return this.id; }

    /**
     * Get the bonus associated to the feature
     * @return the bonus
     */
    public double getBonus() {return  this.delay_bonus; }

    /**
     * set the bonus of the feature
     * @param _b the bonus
     */
    public void setBonus(double _b) {this.delay_bonus = _b;}

    /**
     * set the duration of the feature
     * @param _d the duration
     */
    public void setDuration(double _d){ this.monthNeeded = _d; }

    /**
     * Working on a task (gain 50 xp or a skill)
     * @param  p : the programmer who has work on the task
     * @return the number of month needed to work after
     */
    public double progress(Programmer p){
        double bonus = p.getBonus();
        if(monthNeeded <= 0)
            return 0;


        if(p.hasSkill(this.needed)){
            int level = p.getSkill(this.needed).getLevel();
            double work = ((double) level) * bonus * delay_bonus;
            this.monthNeeded -= work;
            Skills skill =  p.getSkill(this.needed);
            boolean lvlUp = skill.gainXp((int) (50 * delay_bonus * bonus));
            if(lvlUp){
                p.notify(skill);
            }
            p.notify(work);
            setChanged();
            notifyObservers(work);
        }
        else{
            Skills s = new Skills(this.needed);
            p.addSkill(s);
            p.notify(s);

        }

        this.monthNeeded =  this.monthNeeded <= 0 ? 0 : this.monthNeeded;
        if(monthNeeded == 0) {
            setChanged();
            notifyObservers(new Double(0));
        }


        return monthNeeded;

    }

    /**
     * TODO
     * @param o
     */
    public void notify(Object o){
        setChanged();
        notifyObservers(o);
    }
}

