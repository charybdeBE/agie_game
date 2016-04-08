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
    protected int delay; // could be negative ? affect progression and XP
    protected Skills amelioration;
    protected String name;
    protected int id;

    public abstract void effect(Programmer _p);


    public Event(String s, Date d) {
        id = -1;
        occurs = d;
        name = s;
    }

    public Event(int i, Date d){
        id = i;
        occurs = d;
    }

    public Event(int i, Date d, Skills amelioration, int delay){
        this(i,d);
        this.amelioration = amelioration;
        this.delay = delay;
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
    public Boolean hasId(){ return this.id == -1 ? false :  true;}
    public int getId() { return this.id; }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "" + sdf.format(occurs) + " " + name;
    }
}
