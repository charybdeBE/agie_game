package be.ac.ulg.montefiore.group03.agilegame.gamelogic.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import be.ac.ulg.montefiore.group03.agilegame.DateUtil;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.App;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic_Const;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 14.03.16.
 */
public class Event_Builder implements GameLogic_Const {

    private static Event_Builder instance = null;
    private ArrayList<Integer> nrOfEvents;
    private ArrayList<Boolean> haveFeatEvent;
    private Random gen;


    private Event_Builder(){
        this.gen = new Random();
        this.nrOfEvents = new ArrayList<Integer>();
        this.haveFeatEvent = new ArrayList<>();
        this.haveFeatEvent.add(false);
        this.nrOfEvents.add(0);


        Event_Builder.instance = this;
    }

    public static Event_Builder getInstance(){
        if(Event_Builder.instance == null)
            return new Event_Builder();
        return Event_Builder.instance;
    }


    /**
     *
     * @param month The 1st of the month the event could happen
     * @param turn  the xe turn of the game
     * @return An event if one is generated null if not
     */
    public Feature_Event buildFeatureEvent(Date month, int turn, App a){
        if(haveFeatEvent.size() <= turn){
            for(int i=haveFeatEvent.size(); i <= turn; ++i)
                haveFeatEvent.add(false);
        }
        if (haveFeatEvent.get(turn) == true )
            return null;

        haveFeatEvent.set(turn,true);

        double coin = gen.nextDouble();
        if(coin > RANDOMNESS_EVENT)
            return null;

        int day = getADay(); //Select a day of the month
        Date ev = DateUtil.dateFromString("" + day + "." + DateUtil.getMonth(month) + "." + DateUtil.getYear(month), "d.M.y");

        int id = gen.nextInt(NR_OF_FEAT_EVENT);
        return getSpecializedFeatureEvent(id, ev, a);
    }

    public Feature_Event getSpecializedFeatureEvent(int id, Date d, App a){
        id %= NR_OF_FEAT_EVENT;
        Skills s;
        Features f;
        ArrayList<Features> feat;
        switch(id){
            case 0:
                feat = a.getFeatures(SkillType.Network);
                if(feat.size() == 0)
                    return null;
                f = feat.get(gen.nextInt(feat.size()));
                s = new Skills(SkillType.Network);
                s.gainXp(25); //Bonus xp
                return new Feature_Event(id, d, f, s, 0.2);
            case 1:
                feat = a.getFeatures();
                f = feat.get(gen.nextInt(feat.size()));
                return new Feature_Event(id, d, f, null, 0.5);
            case 2:
                feat = a.getFeatures(SkillType.Diagrams);
                if(feat.size() == 0)
                    return null;
                f = feat.get(gen.nextInt(feat.size()));
                return new Feature_Event(id, d, f, null, 0); // 0 means no work this month
            case 3:
                feat = a.getFeatures();
                f = feat.get(gen.nextInt(feat.size()));
                return new Completion_Event(id, d, f); //The task get complete
        }
        return null;
    }


    public Programmer_Event getSpecializedProgrammerEvent(int id, Date d){
        id %= NR_OF_PROG_EVENT;
        switch(id){
            case 0:case 3:
                return new Programmer_Event(id, d, Interest.Games, null, 0.7);
            case 1:
                Skills s = new Skills(SkillType.JAVA);
                s.gainXp(50);
                return new Programmer_Event(id, d, Interest.Technologies, s, 0.2);
            case 2:
                SkillType[] cache = SkillType.values();
                Skills ss = new Skills(cache[gen.nextInt(cache.length)]); //random skill for the theme of the conference
                ss.gainXp(50);
                return new Programmer_Event(id, d, Interest.OpenSource, ss, 0.3);
            case 4:
                return new Programmer_Event(id, d, Interest.Cars, null, 0.8);
            case 5:
                return new All_Team_Event(id, d, 1.1); // Confer 10% de bonus for all programmer
            case 6:
                return new All_Team_Event(id, d, 0.7);
            case 7:
                return new Programmer_Event(id, d, Interest.Technologies, null, 0.8);
            case 8:
                return new Salary_Event(id, d, 0.9, 100);


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
        if(coin > RANDOMNESS_EVENT)
            return null;

        int day = getADay(); //Select a day of the month
        Date ev = DateUtil.dateFromString(""+ day + "."+DateUtil.getMonth(month)+"."+DateUtil.getYear(month), "d.M.y");

        int id = gen.nextInt(NR_OF_PROG_EVENT);
        return getSpecializedProgrammerEvent(id , ev);

    }

    private int getADay(){
        return gen.nextInt(28) + 1; //Don't care of the month lol
    }
}
