package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;

/**
 * Created by charybde on 18.03.16.
 * Builder and "controller" of programmers
 */
public class Programmer_Builder {
    private final int MAX_PROG = 8;

    private static Programmer_Builder instance = null;


    private ArrayList<Programmer> freeCoders;

    private Programmer_Builder(){
        this.freeCoders = new ArrayList<Programmer>();
    }

    public static Programmer_Builder getInstance() {
        if(Programmer_Builder.instance == null){
            Programmer_Builder.instance = new Programmer_Builder();
        }
        return Programmer_Builder.instance;
    }

    //TODO to implements
    public ArrayList<Programmer> getFreeCoders(){
        if(this.freeCoders.size() < MAX_PROG) {
            //Regeneerates codes
        }
        return freeCoders;
    }

    public void engage(Programmer p){
        //TODO If we engage a coder remove it from the list
    }
}