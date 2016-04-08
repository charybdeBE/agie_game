package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 08.03.16.
 */
public class Programmer_Event extends Event {

    private Interest depend;

    public Programmer_Event(String s, Date d) {
        super(s,d);
    }

    public Programmer_Event(String s, Date d, Interest i){
        super(s,d);
        this.depend = i;
    }

    public Programmer_Event(int  id, Date d, Interest i){
        super(id,d);
        this.depend = i;
    }

    public Programmer_Event(int i, Date d, Interest interest, Skills up, int delay){
        super(i,d,up, delay);
        this.depend = interest;
    }



    //TODO Complete rework to include xp and all parameters
    public void effect(Programmer _p) { //Could be inherited by special programmer_event
        if(_p.like(depend)){
            if(_p.hasSkill(this.amelioration.getType())){
                if(this.amelioration.getLevel() > 0)
                    _p.getSkill(this.amelioration.getType()).levelUp();
                else
                    _p.getSkill(this.amelioration.getType()).levelDown();

            }
        }
    }


}
