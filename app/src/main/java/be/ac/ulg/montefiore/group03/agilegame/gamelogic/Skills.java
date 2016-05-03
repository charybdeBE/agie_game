package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Skills {
    private static final int MAX_LEVEL = 5;
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
        if(_level > MAX_LEVEL)
            _level = 5;

        this.level = _level;
        this.current_xp = (_level - 1) *  (_level - 1) * 100;
    }

    public int levelUp(){
       if(level < MAX_LEVEL)
           ++level;
        return level;
    }

    public int levelDown(){
        if(level > 1) //No negative or 0 skill
            --level;
        return level;
    }
    public SkillType getType(){
        return type;
    }
    public int getLevel(){
        return level;
    }
    public int getXp() { return this.current_xp; }

    /**
     *
     * @param xp the number of xp points to add
     * @return true if there is a level up
     */
    public boolean gainXp(int xp){
        current_xp += xp;
        boolean lvlUp = false;
        while(level < (0.1 * Math.sqrt(current_xp))  + 1) {
            this.levelUp();
            lvlUp = true;
        }
        return  lvlUp;
    }

    /**
     *
     * @param o an object  to test
     * @return True if the object is of the same skill than o
     */
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof Skills ))
            return false;
        if(type == ((Skills) o).getType())
            return true;
        return false;
    }

}
