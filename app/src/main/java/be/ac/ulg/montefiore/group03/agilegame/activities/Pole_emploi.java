package be.ac.ulg.montefiore.group03.agilegame.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer_Builder;
import be.ac.ulg.montefiore.group03.agilegame.listAdapter.Programmer_Pole_Emploi_List_Adapter;

public class Pole_emploi extends AppCompatActivity {

    private ListView prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole_emploi);
        prog= (ListView) findViewById(R.id.avaiable_prog);
        prog.setAdapter(new Programmer_Pole_Emploi_List_Adapter(this, Programmer_Builder.getInstance().getAvaiableCoders()));

        Button back_btn = (Button) findViewById(R.id.back_button);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Manager.class);
                startActivity(i);
            }
        });
    }

    /**
     * Refresh the list
     */
    public void refreshList(){
        prog.setAdapter(new Programmer_Pole_Emploi_List_Adapter(this, Programmer_Builder.getInstance().getAvaiableCoders()));
    }

}
