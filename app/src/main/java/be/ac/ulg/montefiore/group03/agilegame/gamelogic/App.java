package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by charybde on 18.03.16.
 */
public class App {
    private static final int MAX_TASK_SIZE = 21;
    private ArrayList<Features> tasks;

    private int initialBudget;

    public App(int features){
        this.tasks = new ArrayList<Features>();
        initialBudget = 10200003;
        Random e = new Random();
        SkillType[] vals = SkillType.values(); //Cache the table for performencies
        for(int i = 0; i < features; ++i){
            int skill_nr =  e.nextInt(vals.length);
            SkillType s = vals[skill_nr];
            int size = e.nextInt(MAX_TASK_SIZE);
            tasks.add(new Features(s, size));
        }

    }

    public int getInitialBudget() {
        return this.initialBudget;
    }
    public ArrayList<Features> getFeatures() { return this.tasks;  }
}
