package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import be.ac.ulg.montefiore.group03.agilegame.DateUtil;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event_Builder;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Feature_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Programmer_Event;

/**
 * Created by charybde on 08.03.16.
 */
public class GameLogic {

    private HashMap<Date, ArrayList<Programmer_Event>> events; // programming events note that the key is supposed to be the first day of the month
    private ArrayList<Programmer> team;
    private App application;

    private Date start;
    private Date now;
    private int turn;
    private int budget;


    private static GameLogic single = null; //Singleton rox

    public static GameLogic getInstance(){
        if(GameLogic.single == null){
            GameLogic.single = new GameLogic();
        }
        return GameLogic.single;
    }

    private GameLogic(){
        Date ajd = DateUtil.dateFromString("1.3.2016","d.M.y");
        events = new HashMap<Date, ArrayList<Programmer_Event>>();
        team = new ArrayList<Programmer>();

        team.add(new Programmer("Sylvain Dazy"));
        team.add(new Programmer("Laurent Vanosmael"));


        start = ajd;
        now = ajd;
        turn = 0;

        application = new App(4);
        budget = application.getInitialBudget();
    }

    public ArrayList<Programmer_Event> getEventsOfDay (Date _d) {
        ArrayList<Programmer_Event> toRet = new ArrayList<>();
        ArrayList<Programmer_Event> e = events.get(DateUtil.getFirstDayOfMonth(_d));
        if(e == null){
            //Generate events for the whole month
            generateMonthEvent(_d);
            return getEventsOfDay(_d);
        }
        int size = e.size();
        int day = DateUtil.getDay(_d);

        for(int i = 0; i < size; ++i) {
            if (DateUtil.getDay(e.get(i).getDate()) == day) {
                toRet.add(e.get(i));
            }
        }
        return toRet;
    }

    public Date getStart(){
        return start;
    }

    public Date getNow(){
        return now;
    }


    //TODO include feature events
    private void generateMonthEvent(Date _d){
        int actualTurn =  12 * (DateUtil.getYear(_d) - DateUtil.getYear(start)) + DateUtil.getMonth(_d) - DateUtil.getMonth(start);
        if(actualTurn < 0) {
            return;
        }
        ArrayList<Programmer_Event> e = new ArrayList<Programmer_Event>();
        for(int i=0; i <= Event_Builder.MAX_EVENT; ++i) {
            Programmer_Event t = Event_Builder.getInstance().buildProgramingEvent(_d, actualTurn);
            if(t != null)
                e.add(t);
        }
        Collections.sort(e);
        events.put(DateUtil.getFirstDayOfMonth(_d), e);

    }

    public ArrayList<Programmer_Event> getEventsOfMonth(Date _d) {
        ArrayList<Programmer_Event> e = events.get(DateUtil.getFirstDayOfMonth(_d));
        if(e == null){
            generateMonthEvent(_d);
            return getEventsOfMonth(_d);
        }

        return e;
    }

    //TODO Manage programmers

    /**
     * Give the current team of programmers
     *
     * @return the list of programmers of the team
     */
    public ArrayList<Programmer> getTeam() {
        return this.team;
    }

    /**
     * Give the current budget
     * Created by Sylvain
     * @return budget
     */
    public int getBudget() {

        return this.budget;
    }
    
    
    public ArrayList<Features> getFeatureList(){
        return this.application.getFeatures();
    }

    public void simulate(){
        Feature_Event feat_event = Event_Builder.getInstance().buildFeatureEvent(now, turn, application);
        if(feat_event != null){
            feat_event.effect(application);
        }
        ArrayList<Programmer_Event> prog_event = getEventsOfMonth(now);
        for(Programmer programmer : team) {
            for (Programmer_Event event : prog_event) {
                event.effect(programmer);
            }
            programmer.work();
            budget -= programmer.getSalary();
        }

        //Update now and turn
        int month = DateUtil.getMonth(now) + 1;
        int year = DateUtil.getYear(now);
        if(month == 13) {
            month = 1;
            year++;
        }
        now = DateUtil.dateFromString("1."+month+"."+year, "d.M.y");
        turn++;

    }
}

