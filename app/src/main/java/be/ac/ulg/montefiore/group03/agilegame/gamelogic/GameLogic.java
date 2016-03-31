package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import be.ac.ulg.montefiore.group03.agilegame.DateUtil;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event_Builder;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Programmer_Event;

/**
 * Created by charybde on 08.03.16.
 */
public class GameLogic {

    private HashMap<Date, ArrayList<Event>> events; //note that the key is supposed to be the first day of the month
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
        events = new HashMap<Date, ArrayList<Event>>();
        team = new ArrayList<Programmer>();
        Programmer_Event test = new Programmer_Event("First of March", ajd);
        Programmer_Event t2 = new Programmer_Event("Presentation to the client", DateUtil.dateFromString("15.3.2016","d.M.y"));
        Programmer_Event t3 = new Programmer_Event("E3", DateUtil.dateFromString("15.3.2016","d.M.y"));

        ArrayList<Event> ll = new ArrayList<Event>();
        ll.add(test);
        ll.add(t2);
        ll.add(t3);
        events.put(ajd, ll);
        start = ajd;
        now = ajd;
        turn = 0;

        application = new App();
        budget = application.getInitialBudget();
    }

    public ArrayList<String> getStringEventsOfDay (Date _d) {
        ArrayList<String> toRet = new ArrayList<>();
        ArrayList<Event> e = events.get(DateUtil.getFirstDayOfMonth(_d));
        if(e == null){
            //Generate events for the whole month
            generateMonthEvent(_d);
            return getStringEventsOfDay(_d);
        }
        int size = e.size();
        int day = DateUtil.getDay(_d);

        for(int i = 0; i < size; ++i) {
            if (DateUtil.getDay(e.get(i).getDate()) == day) {
                toRet.add(e.get(i).getName());
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
        ArrayList<Event> e = new ArrayList<Event>();
        for(int i=0; i <= Event_Builder.MAX_EVENT; ++i) {
            Programmer_Event t = Event_Builder.getInstance().buildProgramingEvent(_d, actualTurn);
            if(t != null)
                e.add(t);
        }
        Collections.sort(e);
        events.put(DateUtil.getFirstDayOfMonth(_d), e);

    }

    public ArrayList<String> getStringEventsOfMonth(Date _d) {
        ArrayList<String> toRet = new ArrayList<>();
        ArrayList<Event> e = events.get(DateUtil.getFirstDayOfMonth(_d));
        if(e == null){
            generateMonthEvent(_d);
            return getStringEventsOfMonth(_d);
        }
        int size = e.size();
        int day = DateUtil.getDay(_d);

        for(int i = 0; i < size; ++i) {
            toRet.add(e.get(i).toString());
        }
        return toRet;
    }

    //TODO Manage programmers

    /**
     * Give the current budget
     * Created by Sylvain
     * @return budget
     */
    public int getBudget() {

        return this.budget;
    }
}

