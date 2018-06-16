package com.gifary.commontemplate.adapter.notification;

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
import com.gifary.commontemplate.models.Notif;

import java.util.List;

/**
 * Created by gifary on 6/16/18.
 */

public class NotificationAdapter extends ArrayAdapter<Notif> {
    private Context context;
    private List<Notif> notifs;

    public NotificationAdapter(@NonNull Context context, int resource, @NonNull List<Notif> objects) {
        super(context, resource, objects);
        this.context =context;
        notifs=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notif notif = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_two_line,parent,false);
        }
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.ll_two_line);
        ll.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, 0);
        ll.setLayoutParams(layoutParams);

        MyTextView tvCustomerName = (MyTextView) convertView.findViewById(R.id.tv_heading);
        MyTextView tvCustomerAddres  =(MyTextView) convertView.findViewById(R.id.tv_second_line);

        tvCustomerName.setText(notif.getTitle());
        tvCustomerAddres.setText(notif.getMessage());

        return convertView;
    }
}
