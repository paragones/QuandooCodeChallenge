package com.paularagones.quandoocodechallenge;

import com.paularagones.quandoocodechallenge.Models.Customer;
import com.paularagones.quandoocodechallenge.Services.RealmService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.Context;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class ExampleUnitTest {


    @Test
    public void testLoadingData() throws Exception {
        android.content.Context context = Mockito.mock(android.content.Context.class);

        Realm realm = RealmService.getInstance(context);

        RealmResults<Customer> customers = realm.where(Customer.class).findAll();
        System.out.println("customers " + customers.get(0).getCustomerLastName());
        assertEquals(4, 2 + 2);
    }
}