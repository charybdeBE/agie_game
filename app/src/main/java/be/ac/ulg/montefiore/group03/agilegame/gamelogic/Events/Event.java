package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.text.SimpleDateFormat;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 08.03.16.
 */
public abstract  class  Event implements Comparable<Event> {
    protected Date occurs;
    protected int delay; // could be negative
    protected Skills amelioration; // May be negative ?
    protected String name;

    public abstract void effect(Programmer _p);


    public Event(String s, Date d) {
        occurs = d;
        name = s;
    }


    public String getName(){
        return name;
    }

    public Date getDate() {
        return occurs;

    }
    @Override
    public int compareTo(Event another) {
        return this.getDate().compareTo(another.getDate());
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "" + sdf.format(occurs) + " " + name;
    }
}
