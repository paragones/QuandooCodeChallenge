package com.paularagones.quandoocodechallenge.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paularagones.quandoocodechallenge.Adapters.TableCellAdapter;
import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.R;
import com.paularagones.quandoocodechallenge.Services.CalendarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Mack and Aragones on 31/08/2016.
 */
public class TableReservationActivity extends AppCompatActivity {

    private static final String LOG_TAG = TableReservationActivity.class.getSimpleName();
    private StaggeredGridLayoutManager gridLayoutManager;
    private EventBus eventBus;
    private String customer;
    private List<Boolean> tables;
    private RecyclerView recyclerView;
    private TableCellAdapter tableCellAdapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        context = getApplicationContext();
        CalendarUtil.setNotificationReminder(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(String customer) {
        setContentView(R.layout.activity_table_reservation);
        this.customer = customer;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser  jsonParserTables = jsonFactory.createParser(getResources().openRawResource(R.raw.table_map));
            tables = objectMapper.readValue(jsonParserTables, new TypeReference<List<Boolean>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.e(LOG_TAG, "Customer acquired : " + customer);
//        this.customer = customer;

        tableCellAdapter = new TableCellAdapter(this, tables, customer);
        recyclerView.setAdapter(tableCellAdapter);

    }

    @Subscribe(sticky = true)
    public void onEvent(HashMap<Integer, String> tableMapCell) {
        Log.e(LOG_TAG, "on Notification " );
        tableCellAdapter = new TableCellAdapter(context, tables, customer);

        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(tableCellAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.removeAllStickyEvents();
        eventBus.unregister(this);
    }
}
