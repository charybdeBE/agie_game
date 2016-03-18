package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;

public class Calendar extends AppCompatActivity {


    private ArrayList<String> eventsString;
    private CalendarView cal    ;
    private ListView list;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        eventsString = GameLogic.getInstance().getStringEventsOfMonth(GameLogic.getInstance().getNow());
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list,eventsString));


        cal = (CalendarView)findViewById(R.id.calendarView1);
        cal.setOnDateChangeListener(new calListener(this));
        cal.setDate(GameLogic.getInstance().getNow().getTime());

        Button monthView = (Button)findViewById(R.id.monthV);
        monthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsString = GameLogic.getInstance().getStringEventsOfMonth(new Date(cal.getDate()));
                list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list, eventsString));
            }
        });

        Button toCal = (Button) findViewById(R.id.back);
        toCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    protected void updateEvent(Date select){
        eventsString = GameLogic.getInstance().getStringEventsOfDay(select);
        list.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list,eventsString));
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