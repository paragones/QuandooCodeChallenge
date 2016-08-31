package com.paularagones.quandoocodechallenge.Services;

import android.content.Context;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.R;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Mack and Aragones on 31/08/2016.
 */
public class CustomerService {

    public static void loadData(Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();

        try {
            JsonParser jsonParserCustomer = jsonFactory.createParser(context.getResources().openRawResource(R.raw.customer_list));
            List<Customer> customers = objectMapper.readValue(jsonParserCustomer, new TypeReference<List<Customer>>() {});

            Realm realm = RealmService.getInstance(context);
            RealmService.transactCustomers(customers, realm);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
