package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Skills {
    private int level;
    private SkillType type;

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

}
