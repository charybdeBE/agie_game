package be.ac.ulg.montefiore.group03.agilegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;

public class Calendar extends AppCompatActivity {


    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> eventsString;
    private CalendarView cal    ;
    private ListView list;

    public GameLogic logic; //TODO CHANGE !

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        logic = new GameLogic();

        eventsString = logic.getStringEventsOfDay(logic.getNow());
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1 ,eventsString));

        cal = (CalendarView)findViewById(R.id.calendarView1);
        cal.setOnDateChangeListener(new calListener(this));
        cal.setDate(logic.getNow().getTime());

    }

    protected void updateEvent(Date select){
        eventsString = logic.getStringEventsOfDay(select);

        list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, eventsString));

    }




}

class calListener implements CalendarView.OnDateChangeListener {

    Calendar context;

    public calListener(Calendar _c){
        this.context = _c;
    }
    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) { //it seems that month = [0;11]
        Date selected = DateUtil.dateFromString(""+dayOfMonth+"."+(month+1)+"."+year+"", "d.M.y");
        context.updateEvent(selected);
    }

}