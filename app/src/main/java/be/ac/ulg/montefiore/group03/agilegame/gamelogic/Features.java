package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Features {
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
            int level = p.getSkill(this.needed).getLevel();
            this.monthNeeded -= ((double) level) * bonus * delay_bonus;
            p.getSkill(this.needed).gainXp((int) (50 * delay_bonus * bonus));
        }
        else{
            p.addSkill(new Skills(this.needed));
        }
        return this.monthNeeded < 0 ? 0 : this.monthNeeded;

    }
}
