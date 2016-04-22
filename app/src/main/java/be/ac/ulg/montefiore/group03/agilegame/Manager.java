package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Programmer_List_Adapter;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Task_List_Adapter;

public class Manager extends AppCompatActivity {

    ListView programmers_view = null;
    ArrayList<Programmer> team = null;
    HelpMessage help_msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Set up the global vieuw */
        setContentView(R.layout.activity_manager);

        /* Set up the programmers list */

        this.team = GameLogic.getInstance().getTeam();

        this.programmers_view = (ListView) findViewById(R.id.programmers);

        this.programmers_view.setAdapter(new Programmer_List_Adapter(this, this.team));

        /* Set up the budget */
        int newBudget = 0;
        newBudget = GameLogic.getInstance().getBudget();
        String budget = getBudgetReadable(newBudget);
        budget = "Budget:\n" + budget + " $";

        TextView budgetTextView = (TextView) findViewById(R.id.budget_textView);

        if (budgetTextView != null)
            budgetTextView.setText(budget);

        /* Set up the calendar button listener */
        Button toCal = (Button) findViewById(R.id.calendar_button);
        toCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Calendar.class);

                startActivity(i);
            }
        });


        Button toPole = (Button) findViewById(R.id.recruit_btn);
        toPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Pole_emploi.class);
                startActivity(i);
            }
        });

        help_msg = new HelpMessage();
        Button help = (Button) findViewById(R.id.help_button);
        help.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                help_msg.show(getSupportFragmentManager(), "help");
            }
        });

        Button simulate_btn = (Button) findViewById(R.id.simulate_button);
        simulate_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameLogic.getInstance().simulate();
                Intent i = new Intent(getApplicationContext(), Summary_activity.class);
                startActivity(i);
            }
        });

        Button month_button = (Button) findViewById(R.id.month_now);

        month_button.setText(DateUtil.dateToString(GameLogic.getInstance().getNow(), "MMM yyyy"));
        ListView tasks= (ListView) findViewById(R.id.tasks);
        tasks.setAdapter(new Task_List_Adapter(this, GameLogic.getInstance().getFeatureList()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO move at an appropriate place
    public static String getBudgetReadable(int budget) {

        String newBudget = "";

        while((budget / 1000) > 0) {

            String reste = "" + budget % 1000;

            /* padding 0 to the reste */
            while (reste.length() < 3) {

                reste = "0" + reste;
            }

            newBudget = "," + reste + newBudget;
            budget = budget / 1000;
        }

        newBudget = budget + newBudget;

        return newBudget;
    }
}