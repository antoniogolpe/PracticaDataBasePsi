package psi1819.udc.es.lab04golpe;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class dbTestActivity extends AppCompatActivity {

    NotesDataBaseHelper dbNotas;
    ListView list;
    private String TAG="dbTestActivity ";

    private ArrayList<Notes> datos = new ArrayList<>();
    NotesAdaprter notAdapter;

    DialogFragment dialog;

    static final int REQUEST_UPDATE_COMENTARIO= 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        dbNotas = new NotesDataBaseHelper(this);

        list = (ListView) findViewById(R.id.list_notes);

        datos= dbNotas.getNotes();

        dialog = new Dialogo();

        notAdapter =new NotesAdaprter(this,datos);
        list.setAdapter(notAdapter);

        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo mInfo) {
        super.onCreateContextMenu(menu, v, mInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                Log.d(TAG,"edit");
                Intent i = new Intent(dbTestActivity.this, NewNote.class);
                Notes not = datos.get(info.position);
                i.putExtra("NOTE",not);
                startActivityForResult(i, REQUEST_UPDATE_COMENTARIO);
                return true; // When you successfully handle a menu item, return true
            case R.id.menu_delete:
                Log.d(TAG,"delete");
                dbNotas.deleteNota(datos.get(info.position).getId());
                actualizar();
                return true;
            default:
                return super.onContextItemSelected(item); // superclass
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_UPDATE_COMENTARIO:
                if (resultCode== RESULT_OK){
                    actualizar();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menu_add) {
            Log.d(TAG,"add");
            startActivity(new Intent(this, NewNote.class));
        }
        if (id==R.id.menu_update) {
            Log.d(TAG,"update");
            actualizar();
        }
        if (id==R.id.menu_filter) {
            crearDialg();
            Log.d(TAG,"filter");
        }
        if (id==R.id.menu_sort) {
            crearDialg();
            Log.d(TAG,"sort");
        }
        if (id==R.id.menu_cpTest) {
            Log.d(TAG,"cpTest");
            startActivity(new Intent(this,cpTestActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void crearDialg(){
        dialog.show(getSupportFragmentManager(), "Dialogo");
    }

    private void actualizar(){
        datos= dbNotas.getNotes();
        notAdapter.setData(datos);
        notAdapter.notifyDataSetChanged();
    }



}
