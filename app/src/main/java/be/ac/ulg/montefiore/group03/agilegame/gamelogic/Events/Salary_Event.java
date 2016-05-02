package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by charybde on 19.04.16.
 */
public class Salary_Event extends All_Team_Event {
    int salary_bonus;

    public Salary_Event(int id, Date d, double delay, int increase) {
        super(id, d, delay);
        this.salary_bonus = increase;
    }

    public void effect(Programmer _p){
        super.effect(_p);
        _p.getRaise(salary_bonus);
        _p.notify(this);
    }
}
