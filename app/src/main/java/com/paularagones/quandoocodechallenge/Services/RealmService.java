package com.paularagones.quandoocodechallenge.Services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.paularagones.quandoocodechallenge.Activities.CustomerListActivity;
import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.Models.TableCell;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mack and Aragones on 31/08/2016.
 */
public class RealmService {

    private Context context;

    public static RealmConfiguration getRealmConfig(Context context) {
        return new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static void resetRealm(Context context) {
        Realm.deleteRealm(RealmService.getRealmConfig(context));
    }

    public static Realm getInstance(Context context) {
        return Realm.getInstance(RealmService.getRealmConfig(context));
    }

    public static void transactCustomers(List<Customer> customers, Realm realm) {
        realm.beginTransaction();
        realm.copyToRealm(customers);
        realm.commitTransaction();
        realm.close();
    }

    public static void transactTables(TableCell tableCell, Realm realm) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(tableCell);
        realm.commitTransaction();
        realm.close();
    }

}
