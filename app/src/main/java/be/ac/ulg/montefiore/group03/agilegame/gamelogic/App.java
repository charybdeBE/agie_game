package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;

/**
 * Created by charybde on 18.03.16.
 */
public class App {
    private ArrayList<Features> tasks;

    private int initialBudget;

    //TODO implements (generation of task in the constructor)
    public App(){

        initialBudget = 10200003;
    }

    public int getInitialBudget() {
        return this.initialBudget;
    }
}
