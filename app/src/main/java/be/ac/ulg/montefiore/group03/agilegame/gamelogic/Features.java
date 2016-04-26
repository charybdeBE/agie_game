package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.Observable;

/**
 * Created by charybde on 08.03.16.
 */
public class Features extends Observable {
    private SkillType needed; // The skill needed to work on tat feature
    private double monthNeeded; // The numbe rof month / Level of work
    private int id;
    private double delay_bonus;

    public Features(int id, SkillType what, int time){
        this.id = id;
        this.needed = what;
        this.monthNeeded = time;
        this.delay_bonus = 1;
    }


    public SkillType getNeeded() { return this.needed; }
    public double getDuration(){ return  this.monthNeeded; }
    public int getId() {return this.id; }
    public double getBonus() {return  this.delay_bonus; }
    public void setBonus(double _b) {this.delay_bonus = _b;}

    /**
     * Working on a task (gain 50 xp or a skill)
     * @param  p : the programmer who has work on the task
     * @return the number of month needed to work after
     */
    public double progress(Programmer p){
        double bonus = p.getBonus();
        if(p.hasSkill(this.needed)){
            System.out.println("DEBUG " + p.getSkill(this.needed).getLevel() + "B" + bonus + "C" + delay_bonus);
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

        double still_to_do =  this.monthNeeded < 0 ? 0 : this.monthNeeded;
        if(still_to_do == 0) {
            notifyObservers(new Double(0));
        }


        return still_to_do;

    }
}
