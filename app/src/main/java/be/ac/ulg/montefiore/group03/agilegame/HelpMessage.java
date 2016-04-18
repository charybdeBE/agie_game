package be.ac.ulg.montefiore.group03.agilegame;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.Random;

/**
 * Created by charybde on 18.04.16.
 */
public class HelpMessage extends DialogFragment {

    private String[] messages;
    private Random gen;
    private Boolean first_call;

    public HelpMessage(){
        super();
        gen = new Random();
        first_call = true;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        if (first_call) {
            Resources res = getResources();
            messages = res.getStringArray(R.array.help);
            first_call = false;
        }


        int msg = gen.nextInt(messages.length);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.help_button_text)
                .setMessage(messages[msg]) //Random Msg
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // nothing to do just go back
                    }
                })
        ;
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
