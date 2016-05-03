package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by charybde on 18.03.16.
 */
public class App  implements GameLogic_Const{

    private ArrayList<Features> tasks;
    private int init_task;

    private int initialBudget;

    public App(int features){
        this.tasks = new ArrayList<Features>();
        initialBudget = INITIAL_BUDGET;
        Random e = new Random();
        SkillType[] vals = SkillType.values(); //Cache the table for performencies
        for(int i = 0; i < features; ++i){
            int skill_nr =  e.nextInt(vals.length);
            SkillType s = vals[skill_nr];
            int size = e.nextInt(MAX_TASK_SIZE) + 1;
            tasks.add(new Features(i, s, size));
        }
        this.init_task = features;
    }

    public int getInitialBudget() {
        return this.initialBudget;
    }
    public ArrayList<Features> getFeatures() { return this.tasks;  }

    /**
     *
     * @param st a skill type
     * @return the list of features that depend of a particular skillType
     */
    public ArrayList<Features> getFeatures(SkillType st){
        ArrayList<Features> toRet = new ArrayList<>();
        for(int i= 0; i < tasks.size(); ++i){
            if(tasks.get(i).getNeeded() == st){
                toRet.add(tasks.get(i));
            }
        }
        return toRet;
    }

    public int getInitTask(){ return this.init_task;}
}
