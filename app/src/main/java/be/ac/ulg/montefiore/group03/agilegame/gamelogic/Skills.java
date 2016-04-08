package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Skills {
    //    level = (0.1 * sqrt(XP)) + 1

    private int level;
    private int current_xp;

    private SkillType type;

    public Skills(SkillType _t){
        this.level = 1;
        this.type = _t;
        this.current_xp = 0;
    }

    public Skills(SkillType _t, int _level){
        this.type = _t;
        this.level = _level;
        this.current_xp = (_level - 1) *  (_level - 1) * 100;
    }

    public int levelUp(){
       return  ++this.level;
    }

    public int levelDown(){
        return --this.level;
    }
    public SkillType getType(){
        return type;
    }
    public int getLevel(){
        return level;
    }
    public int getXp() { return this.current_xp; }

    public void  gainXp(int xp){
        current_xp += xp;
        while(level < (0.1 * Math.sqrt(current_xp))  + 1)
            this.levelUp();
    }
}
