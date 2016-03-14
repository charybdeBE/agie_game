package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import be.ac.ulg.montefiore.group03.agilegame.DateUtil;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by charybde on 14.03.16.
 */
public class Event_Builder {
    public static final int MAX_EVENT = 10;
    public static final double RANDOMNESS = 0.7;

    private static Event_Builder instance = null;
    private ArrayList<Integer> nrOfEvents;
    private Random gen;

    private Event_Builder(){
        this.gen = new Random();
        this.nrOfEvents = new ArrayList<Integer>();
        this.nrOfEvents.add(0);
        Event_Builder.instance = this;
    }

    public static Event_Builder getInstance(){
        if(Event_Builder.instance == null)
            return new Event_Builder();
        return Event_Builder.instance;
    }

    public Programmer_Event buildProgramingEvent(Date month, int turn){
        if(nrOfEvents.size() <= turn){
            for(int i=nrOfEvents.size(); i <= turn; ++i)
                nrOfEvents.add(0);
        }
       if (nrOfEvents.get(turn) > MAX_EVENT )
            return null;

        int day = getADay() - DateUtil.getDay(month) + 1; //Select a day of the month
        Date ev = DateUtil.dateFromString(day + "."+DateUtil.getMonth(month)+"."+DateUtil.getYear(month), "d.M.y");

        nrOfEvents.set(turn, nrOfEvents.get(turn)  + 1);
        System.out.println(nrOfEvents.get(turn));
        Programmer_Event p =  new Programmer_Event("Bull" + nrOfEvents.get(turn), ev);
        return p;

    }

    private int getADay(){
        return (gen.nextInt() % 27) + 1; //Don't care of the month lol
    }
}
