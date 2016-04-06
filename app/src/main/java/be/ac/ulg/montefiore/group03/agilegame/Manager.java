package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ac.ulg.montefiore.group03.agilegame.Calendar;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

public class Manager extends AppCompatActivity {

    ListView programmers = null;
    ArrayList<Programmer> team = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Set up the global vieuw */
        setContentView(R.layout.activity_manager);




        /* Set up the programmers list */
        if (programmers == null) {
            this.programmers = (ListView) findViewById(R.id.programmers);
        }

        this.team = GameLogic.getInstance().getTeam();

        // just for testing
        String[] programmers = new String[]{"Programmeur 1", "Programmeur 2", "Programmeur 3", "Programmeur 4"};

        List<HashMap<String, String>> prog_list = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> prog;
        for (int i = 0; i < programmers.length; i++) {
            prog = new HashMap<String, String>();
            prog.put("Name", programmers[i]);
            prog_list.add(prog);
        }

        ListAdapter adpter = new SimpleAdapter(this, prog_list, android.R.layout.simple_list_item_2, new String[] {"name"}, new int[] {android.R.id.text1});

        this.programmers.setAdapter(adpter);





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

    private String getBudgetReadable(int budget) {

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
