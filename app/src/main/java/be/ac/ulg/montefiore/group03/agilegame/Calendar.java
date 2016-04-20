package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Programmer_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Event_List_Adapter;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;



public class Calendar extends AppCompatActivity {


    private ArrayList<Programmer_Event> event;
    private CalendarView cal    ;
    private ListView list;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        event = GameLogic.getInstance().getEventsOfMonth(GameLogic.getInstance().getNow());
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new Event_List_Adapter(getApplicationContext(), event));

        cal = (CalendarView)findViewById(R.id.calendarView1);
        cal.setOnDateChangeListener(new calListener(this));
        cal.setDate(GameLogic.getInstance().getNow().getTime());

        Button monthView = (Button)findViewById(R.id.monthV);
        monthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = GameLogic.getInstance().getEventsOfMonth(new Date(cal.getDate()));
                list.setAdapter(new Event_List_Adapter(getApplicationContext(), event));
            }
        });

        Button toCal = (Button) findViewById(R.id.back);
        toCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Manager.class);
                startActivity(i);
            }
        });
    }

    protected void updateEvent(Date select){
        event = GameLogic.getInstance().getEventsOfDay(select);
        list.setAdapter(new Event_List_Adapter(getApplicationContext(),event));
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