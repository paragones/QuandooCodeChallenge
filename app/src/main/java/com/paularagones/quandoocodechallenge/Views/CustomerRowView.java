package com.paularagones.quandoocodechallenge.Views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mack and Aragones on 30/08/2016.
 */
public class CustomerRowView extends LinearLayout {

    @Bind(R.id.first_name)
    TextView firstName;

    @Bind(R.id.last_name)
    TextView lastName;

    public CustomerRowView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.customer_row, this);
        ButterKnife.bind(this);
    }

    public void setValues(Customer customer) {
        firstName.setText(customer.getCustomerFirstName());
        lastName.setText(customer.getCustomerLastName());
    }
}
