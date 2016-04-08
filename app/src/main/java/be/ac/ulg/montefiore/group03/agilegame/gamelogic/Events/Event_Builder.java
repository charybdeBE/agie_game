package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import be.ac.ulg.montefiore.group03.agilegame.DateUtil;
import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 14.03.16.
 */
public class Event_Builder {
    public static final int MAX_EVENT = 10;
    public static final double RANDOMNESS = 0.7;
    public static final int NR_OF_PROG_EVENT = 5;

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

/*
    public Feature_Event buildFeatureEvent(Date d, int turn){
        //TODO Implement like buildProgramming (or even mix the 2)
    }
*/

    public Programmer_Event getSpecialized(int id, Date d){
        id %= NR_OF_PROG_EVENT;
        switch(id){
            case 0:case 3:
                return new Programmer_Event(id, d, Interest.Video_Game, null, 3);
            case 1:
                Skills s = new Skills(SkillType.JAVA);
                s.gainXp(50);
                return new Programmer_Event(id, d, Interest.New_Tech, s, 5);
            case 2:
                SkillType[] cache = SkillType.values();
                Skills ss = new Skills(cache[gen.nextInt(cache.length)]); //random skill for the theme of the conference
                ss.gainXp(50);
                return new Programmer_Event(id, d, Interest.OpenSource, ss, 5);
            case 4:
                return new Programmer_Event(id, d, Interest.Cars, null, 2);

        }
        return null;
    }


    /**
     * @author Laurent
     * @param month the 1st of the month where the event could happen
     * @param turn the turn it is creates
     * @return the event created warning it could be null thanks of random
     */
    public Programmer_Event buildProgramingEvent(Date month, int turn){
        if(nrOfEvents.size() <= turn){
            for(int i=nrOfEvents.size(); i <= turn; ++i)
                nrOfEvents.add(0);
        }
       if (nrOfEvents.get(turn) > MAX_EVENT )
            return null;

        nrOfEvents.set(turn, nrOfEvents.get(turn)  + 1);

        double coin = gen.nextDouble();
        if(coin > RANDOMNESS)
            return null;

        int day = getADay(); //Select a day of the month
        System.out.println("" + day + "."+DateUtil.getMonth(month)+"."+DateUtil.getYear(month));
        Date ev = DateUtil.dateFromString(""+ day + "."+DateUtil.getMonth(month)+"."+DateUtil.getYear(month), "d.M.y");

        int id = gen.nextInt(NR_OF_PROG_EVENT);
        System.out.println("aaaaaaaaaa "  + id );
        return getSpecialized(id , ev);

    }

    private int getADay(){
        return gen.nextInt(28) + 1; //Don't care of the month lol
    }
}
