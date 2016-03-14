package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 08.03.16.
 */
public abstract  class  Event {
    protected Date occurs;
    protected int delay; // could be negative
    protected Skills amelioration; // May be negative ?
    protected String name;

    public abstract void effect(Programmer _p);

    public String getName(){
        return name;
    }

    public Date getDate() {
        return occurs;
    }
}
