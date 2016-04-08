package be.ac.ulg.montefiore.group03.agilegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer_Builder;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Programmer_List_Adapter;

public class Pole_emploi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole_emploi);
        ListView prog= (ListView) findViewById(R.id.avaiable_prog);
        prog.setAdapter(new Programmer_List_Adapter(this, Programmer_Builder.getInstance().getAvaiableCoders()));

        //TODO Back button
    }
}
