package be.ac.ulg.montefiore.group03.agilegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;

public class Calendar extends AppCompatActivity {


    //NOTE MonthInit is calculated compare to current month, if one day we use save game change it
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> eventsString;

    private int month; //nr of month since beg of the game

    public GameLogic logic; //TODO CHANGE !

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView cal = (CalendarView)findViewById(R.id.calendarView);
        month = calculateMonth(cal);
        cal.setOnDateChangeListener(new calListener(this));


        ListView list = (ListView)findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 ,eventsString);
        list.setAdapter(listAdapter);

        updateEvent();

    }

    protected int calculateMonth(CalendarView cal){
        SimpleDateFormat monthSDF = new SimpleDateFormat("M");
        SimpleDateFormat yearSDF = new SimpleDateFormat("y");

        int monthInit = Integer.getInteger(monthSDF.format(new Date(System.currentTimeMillis())));
        int yearInit = Integer.getInteger(yearSDF.format(new Date(System.currentTimeMillis())));
        int monthCal = Integer.getInteger(monthSDF.format(new Date(cal.getDate())));
        int yearCal = Integer.getInteger(yearSDF.format(new Date(cal.getDate())));
        return monthCal - monthInit + 12 * (yearCal - yearInit);
    }

    protected void updateEvent(){
        eventsString = logic.getStringEventsOfMonth(month);
        listAdapter.notifyDataSetChanged();
    }

    protected int getMonth(){
        return month;
    }

    protected void setMonth(int _m){
        month = _m;
    }


}

class calListener implements CalendarView.OnDateChangeListener {

    Calendar context;

    public calListener(Calendar _c){
        this.context = _c;
    }
    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        context.setMonth(context.calculateMonth(view));
        context.updateEvent();

    }

}