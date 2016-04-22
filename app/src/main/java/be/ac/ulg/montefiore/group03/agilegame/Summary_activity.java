package be.ac.ulg.montefiore.group03.agilegame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer_Builder;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Journal_List_Adapter;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Programmer_List_Adapter;

public class Summary_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ListView stuff = (ListView) findViewById(R.id.summary_list);

        //TODO Navigate through all historic
        //TODO handle null historic by  a message
        //TODO back button
        if(GameLogic.getInstance().getSummary(GameLogic.getInstance().getTurn() - 1) != null) {
            stuff.setAdapter(new Journal_List_Adapter(this, GameLogic.getInstance().getSummary(GameLogic.getInstance().getTurn() - 1)));
            System.out.println("NOTNUUUUUUUUUUUUUUL");
        }
    }

}
