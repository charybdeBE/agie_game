package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.Pole_emploi;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by laurent on 02.05.16.
 */
public class Programmer_Pole_Emploi_List_Adapter extends Programmer_List_Adapter {

    public Programmer_Pole_Emploi_List_Adapter(Context context, ArrayList<Programmer> programmers) {
        super(context, programmers);
    }

    @Override
    public void setOnProgrammerClick(AlertDialog.Builder builder, final Programmer p) {
        builder.setNegativeButton("Hire", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((Pole_emploi) context).refreshList();
                p.hire();
                dialog.dismiss();
            }
        });
    }
}
