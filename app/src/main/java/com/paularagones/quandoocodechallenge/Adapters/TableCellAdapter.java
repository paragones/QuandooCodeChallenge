package com.paularagones.quandoocodechallenge.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paularagones.quandoocodechallenge.Models.TableCell;
import com.paularagones.quandoocodechallenge.Services.RealmService;
import com.paularagones.quandoocodechallenge.Views.TableCellView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Mack and Aragones on 31/08/2016.
 */
public class TableCellAdapter extends RecyclerView.Adapter<TableCellAdapter.ViewHolder> {

    private static final String LOG_TAG = TableCellAdapter.class.getSimpleName();
    private List<Boolean> tables;
    private String customer;
    private TableCellAdapter thisAdapter = this;
    private int changedPosition = -1;
    private int oldPosition;
    private Context context;

    private HashMap<Integer, String> tableCellMap;

    public TableCellAdapter(Context context, List<Boolean> tables, String customer){
        this.customer = customer;
        this.tables = tables;
        this.context = context;

        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("test", context.MODE_PRIVATE);
        String storedHashMapString = prefs.getString("tableCellMapString", "oopsDintWork");
        java.lang.reflect.Type type = new TypeToken<HashMap<Integer, String>>(){}.getType();

        if (storedHashMapString.equals("oopsDintWork")) {
            Log.e(LOG_TAG, "new table cell");
            tableCellMap = new HashMap<Integer, String>();
        } else {
            tableCellMap = gson.fromJson(storedHashMapString, type);

            if (tableCellMap.containsValue(customer)) {
                for (Map.Entry<Integer, String> entry : tableCellMap.entrySet()) {
                    if (customer.equals(entry.getValue())) {
                        tableCellMap.remove(entry.getKey());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TableCellView tableCellView;

        public ViewHolder(TableCellView tableCellView) {
            super(tableCellView);
            this.tableCellView = tableCellView;
        }

    }

    @Override
    public TableCellAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        ViewHolder vh = new ViewHolder(new TableCellView(parent.getContext()));
        return vh;
    }

    @Override
    public void onBindViewHolder(final TableCellAdapter.ViewHolder holder, final int position) {
        holder.tableCellView.setTableNumber(position + 1);

        if (tableCellMap.get(position) != null) {
            holder.tableCellView.setReservation(tableCellMap.get(position));
        } else {
            holder.tableCellView.removeReservation();
        }

        holder.tableCellView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tableCellMap.get(position) == null) {
                    Log.e(LOG_TAG, "tableCellMap.get(position) == null");

                    holder.tableCellView.setReservation(customer);
                    oldPosition = changedPosition;
                    changedPosition = position;

                    tableCellMap.put(position, customer);

                    if (oldPosition != -1) {
                        thisAdapter.notifyItemChanged(oldPosition);
                        tableCellMap.remove(oldPosition);
                    }


                    Gson gson = new Gson();
                    String tableCellMapString = gson.toJson(tableCellMap);
                    SharedPreferences prefs = context.getSharedPreferences("test", context.MODE_PRIVATE);
                    prefs.edit().putString("tableCellMapString", tableCellMapString).apply();

                    Log.e(LOG_TAG, "tableCellMap : " + tableCellMap);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }
}
