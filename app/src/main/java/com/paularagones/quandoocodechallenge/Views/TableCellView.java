package com.paularagones.quandoocodechallenge.Views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paularagones.quandoocodechallenge.Models.TableCell;
import com.paularagones.quandoocodechallenge.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mack and Aragones on 30/08/2016.
 */
public class TableCellView extends LinearLayout {

    @Bind(R.id.card_view)
    CardView cardView;

    @Bind(R.id.table_number)
    TextView tableNumber;

    @Bind(R.id.reservation)
    TextView reservation;

    @Bind(R.id.ll_table_cell)
    LinearLayout llTableCell;

    public TableCellView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.table_cell, this);
        ButterKnife.bind(this);
    }

    public void setReservation(String name) {
        reservation.setText(name);
        llTableCell.setBackgroundColor(Color.CYAN);
    }

    public void removeReservation(){
        reservation.setText("FREE");
        llTableCell.setBackgroundColor(Color.WHITE);
    }

    public void setTableNumber(int i) {
        tableNumber.setText("Table " + i);
    }


}
