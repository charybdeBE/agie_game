package be.ac.ulg.montefiore.group03.agilegame.gamelogic.events;

import java.text.SimpleDateFormat;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**

 */
public abstract  class  Event  implements Comparable<Event> {
    /**
     * delay :
     * 0 = no work done this month
     * -1 = all rest work done but no XP
     * positive integer i = work and xp divide by i and
     */
    protected Date occurs;
    protected double delay; //Multiplicator
    protected Skills amelioration;
    protected String name;
    protected int id;

    /**
     *
     * @param _p the programmer that the effect has to be applied to
     */
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

    public Event(int i, Date d, Skills amelioration, double delay){
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
