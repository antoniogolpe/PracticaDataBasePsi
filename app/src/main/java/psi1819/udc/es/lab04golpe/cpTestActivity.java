package psi1819.udc.es.lab04golpe;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class cpTestActivity extends Activity {

    private final static String TAG = "_TAG";

    EditText et_word;
    Button but_search;
    ListView lv_cp;
    Button but_cp_insertar;
    Button but_cp_borrar;
    Button but_cp_modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_test);

        et_word = (EditText) findViewById(R.id.et_word);
        but_search = (Button) findViewById(R.id.but_search);
        lv_cp = (ListView) findViewById(R.id.lv_cp);
        but_cp_insertar = (Button) findViewById(R.id.but_cp_insertar);
        but_cp_borrar = (Button) findViewById(R.id.but_cp_borrar);
        but_cp_modificar = (Button) findViewById(R.id.but_cp_modificar);

        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et_word.getText().toString();
                if (!word.isEmpty()) {
                    Log.d(TAG, "the word is " + word);
                    //
                } else{
                    Toast.makeText(getApplicationContext(), "Rellene el campo de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
        but_cp_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et_word.getText().toString();
                if (!word.isEmpty()) {
                    Log.d(TAG, "the word is " + word);
                    //
                } else{
                    Toast.makeText(getApplicationContext(), "Rellene el campo de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
        but_cp_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et_word.getText().toString();
                if (!word.isEmpty()) {
                    Log.d(TAG, "the word is " + word);
                    //
                } else{
                    Toast.makeText(getApplicationContext(), "Rellene el campo de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
        but_cp_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et_word.getText().toString();
                if (!word.isEmpty()) {
                    Log.d(TAG, "the word is " + word);
                    //
                } else{
                    Toast.makeText(getApplicationContext(), "Rellene el campo de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerForContextMenu(lv_cp);
    }




}
