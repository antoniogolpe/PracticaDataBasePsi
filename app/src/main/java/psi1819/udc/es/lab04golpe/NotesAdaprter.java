package psi1819.udc.es.lab04golpe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class NotesAdaprter extends BaseAdapter{

    private Context context;
    private ArrayList<Notes> notes;

    public NotesAdaprter(Context context, ArrayList<Notes> notes) {
        this.context=context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Notes getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Notes not = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.row_id);
        TextView name = (TextView) convertView.findViewById(R.id.row_name);
        TextView lastname = (TextView) convertView.findViewById(R.id.row_lastname);
        TextView nota = (TextView) convertView.findViewById(R.id.row_nota);
        // Populate the data into the template view using the data object
        id.setText(String.valueOf(not.getId()));
        name.setText(not.getNombre());
        lastname.setText(not.getApellido());
        nota.setText(String.valueOf(not.getNota()));
        // Return the completed view to render on screen
        return convertView;
    }

    public void setData(ArrayList<Notes> clases) {
        this.notes = clases;
        notifyDataSetChanged();
    }


}