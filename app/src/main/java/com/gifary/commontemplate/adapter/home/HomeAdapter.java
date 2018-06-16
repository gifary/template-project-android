package com.gifary.commontemplate.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.gifary.commontemplate.R;
import com.gifary.commontemplate.customlayout.MyTextView;
import com.gifary.commontemplate.models.Customer;

import java.util.List;

/**
 * Created by gifary on 6/16/18.
 */

public class HomeAdapter extends ArrayAdapter<Customer> {

    private List<Customer> list;
    private Context context;
    public HomeAdapter(@NonNull Context context, int resource, @NonNull List<Customer> objects) {
        super(context, resource, objects);
        this.context = context;
        list =objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Customer customer = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_two_line,parent,false);
        }
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.ll_two_line);
        /*ll.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(10, 10, 10, 10);

        ll.setPadding(20,20,20,10);
        ll.setLayoutParams(layoutParams);*/
        ll.setBackgroundResource(R.drawable.bg_white_small_radius);

        MyTextView tvCustomerName = (MyTextView) convertView.findViewById(R.id.tv_heading);
        MyTextView tvCustomerAddres  =(MyTextView) convertView.findViewById(R.id.tv_second_line);

        tvCustomerName.setText(customer.getName());
        tvCustomerAddres.setText(customer.getAddress());

        return convertView;
    }
}
