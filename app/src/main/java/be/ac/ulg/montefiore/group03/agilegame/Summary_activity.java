package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        if(GameLogic.getInstance().getSummary(GameLogic.getInstance().getTurn() - 1) != null) {
            stuff.setAdapter(new Journal_List_Adapter(this, GameLogic.getInstance().getSummary(GameLogic.getInstance().getTurn() - 1)));
        }

        Button back_btn = (Button) findViewById(R.id.back_button);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Manager.class);
                startActivity(i);
            }
        });
    }

}
