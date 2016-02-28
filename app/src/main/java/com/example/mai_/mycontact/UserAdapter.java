package com.example.mai_.mycontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mai_ on 08-Oct-15.
 */
public class UserAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public UserAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    static class LayoutHandler{
        TextView Name,Mob,Email;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler=new LayoutHandler();
        if (row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler.Name= (TextView) row.findViewById(R.id.contactName);
            layoutHandler.Email= (TextView) row.findViewById(R.id.contactEmail);
            layoutHandler.Mob= (TextView) row.findViewById(R.id.contactMobile);
            row.setTag(layoutHandler);
        }
        else
            layoutHandler= (LayoutHandler) row.getTag();
        User user= (User) this.getItem(position);
        layoutHandler.Name.setText(user.getName());
        layoutHandler.Mob.setText(user.getMobile());
        layoutHandler.Email.setText(user.getEmail());
        return row;
    }
}
