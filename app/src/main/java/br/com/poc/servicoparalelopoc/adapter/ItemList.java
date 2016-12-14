package br.com.poc.servicoparalelopoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.poc.servicoparalelopoc.R;

/**
 * Created by rudsonkiyoshi on 13/12/16.
 */

public class ItemList extends ArrayAdapter<String> {


    private final Context context;
    private final ArrayList<String> itemsArrayList;

    public ItemList(Context context, ArrayList<String> itemsArrayList) {

        super(context, R.layout.simple_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.simple_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView itemView = (TextView) rowView.findViewById(R.id.list_item);


        // 4. Set the text for textView
        itemView.setText(String.valueOf(itemsArrayList.get(position)));


        // 5. return rowView
        return rowView;
    }
}
