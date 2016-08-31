package com.paularagones.quandoocodechallenge.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paularagones.quandoocodechallenge.Adapters.CustomerListAdapter;
import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.R;
import com.paularagones.quandoocodechallenge.Services.CalendarUtil;
import com.paularagones.quandoocodechallenge.Services.CustomerService;
import com.paularagones.quandoocodechallenge.Services.RealmService;

import java.io.IOException;
import java.util.List;

import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CustomerListActivity extends AppCompatActivity {

    private static final String LOG_TAG = CustomerListActivity.class.getSimpleName();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        RealmService.resetRealm(this);
        CustomerService.loadData(this);

        RealmSearchView realmSearchView = (RealmSearchView) findViewById(R.id.search_view);

        realm = RealmService.getInstance(this);
        CustomerListAdapter adapter = new CustomerListAdapter(this, realm, "customerLastName");
        realmSearchView.setAdapter(adapter);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeRealm();
    }

    private void closeRealm() {
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }






}
