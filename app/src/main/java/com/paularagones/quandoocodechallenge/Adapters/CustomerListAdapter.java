package com.paularagones.quandoocodechallenge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.paularagones.quandoocodechallenge.Activities.TableReservationActivity;
import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.Views.CustomerRowView;

import org.greenrobot.eventbus.EventBus;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Mack and Aragones on 30/08/2016.
 */
public class CustomerListAdapter extends RealmSearchAdapter<Customer, CustomerListAdapter.ViewHolder> {

    private static final String LOG_TAG = CustomerListAdapter.class.getSimpleName();
    private Realm realm;
    private Context context;
    private RealmResults<Customer> customers;

    public CustomerListAdapter(
            Context context,
            Realm realm,
            String filterKey) {
        super(context, realm, filterKey);
        this.realm = realm;
        this.context = context;
        customers = realm.where(Customer.class).findAll();
    }

    public class ViewHolder extends RealmSearchViewHolder{

        private CustomerRowView customerRowView;

        public ViewHolder(CustomerRowView customerRowView) {
            super(customerRowView);
            this.customerRowView = customerRowView;
        }
    }

    @Override
    public CustomerListAdapter.ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder vh = new ViewHolder(new CustomerRowView(viewGroup.getContext()));
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(CustomerListAdapter.ViewHolder viewHolder, int i) {

        final Customer customer = customers.get(i);


        viewHolder.customerRowView.setValues(customer);

        viewHolder.customerRowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = new Intent(context, TableReservationActivity.class);
                context.startActivity(mainIntent);

                EventBus eventBus = EventBus.getDefault();
                eventBus.postSticky(customer.toString());

            }
        });
    }


}
