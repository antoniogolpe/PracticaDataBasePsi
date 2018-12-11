package psi1819.udc.es.lab04golpe;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends Activity implements View.OnClickListener {

    public final static String TAG = "_TAG";

    NotesDataBaseHelper db;

    EditText et_firstname;
    EditText et_lastname;
    EditText et_grade;

    int id;
    Button but_add;
    Button but_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_nota);

        db = new NotesDataBaseHelper(this);

        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_grade = (EditText) findViewById(R.id.et_grade);

        Bundle extra = getIntent().getExtras(); // check if not null
        if (extra != null  // and ...
                ) {
            // ...
            Notes not = (Notes) extra.getSerializable("NOTE");

            id=not.getId();
            et_firstname.setText(not.getNombre());
            et_lastname.setText(not.getApellido());
            et_grade.setText(String.valueOf(not.getNota()));

            but_update = (Button) findViewById(R.id.but_update);
            but_update.setOnClickListener(this);
            but_update.setVisibility(View.VISIBLE);
        }

        but_add = (Button) findViewById(R.id.but_add);
        but_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == but_add) {
            //
            insertarNota();
            Log.d(TAG, "click add nota");
        } else if (view == but_update) {
            //
            editarNota();
            setResult(RESULT_OK, null);
            Log.d(TAG, "click modify nota");
        }
    }

    private void mensaje_error(){
        Toast.makeText(this,"Todos los datos deben de estar cubiertos",Toast.LENGTH_LONG).show();
    }

    private void editarNota(){
        if ((et_firstname.getText().toString().trim().length()==0)
                || (et_lastname.getText().toString().trim().length()==0)
                || (et_grade.getText().toString().trim().length()==0)){
            mensaje_error();
        }else{
            Notes n = new Notes(id, et_firstname.getText().toString(),
                    et_lastname.getText().toString(),
                    Integer.valueOf(et_grade.getText().toString()));
            db.updateNota(n);
        }
    }

    private void insertarNota(){
        if ((et_firstname.getText().toString().trim().length()==0)
                || (et_lastname.getText().toString().trim().length()==0)
                || (et_grade.getText().toString().trim().length()==0)){
            mensaje_error();
        }else{
            Notes n = new Notes(et_firstname.getText().toString(),
                    et_lastname.getText().toString(),
                    Integer.valueOf(et_grade.getText().toString()));
            db.insertNota(n);
        }

    }
}
