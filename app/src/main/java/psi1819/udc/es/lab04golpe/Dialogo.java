package psi1819.udc.es.lab04golpe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Dialogo extends DialogFragment {

    /*String filter;
    String sort;
    String colum;
    EditText et;
    Spinner sfilt;
    Spinner ssort;*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        /* et = (EditText) getActivity().findViewById(R.id.et_dialog);
         sfilt= (Spinner) getActivity().findViewById(R.id.sp_dialog_filter);
         ssort= (Spinner) getActivity().findViewById(R.id.sp_dialog_sort);

        String[] datos1 = new String[] {"ID", "nombre", "apellido", "nota"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, datos1);

        sfilt.setAdapter(adapter1);

        String[] datos2 = new String[] {"ASC","DESC"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, datos2);

        ssort.setAdapter(adapter2);*/

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog, null));
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
               /* if(et.getText().toString().trim().length()!=0){
                    filter = et.getText().toString();
                }*/
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }


}
