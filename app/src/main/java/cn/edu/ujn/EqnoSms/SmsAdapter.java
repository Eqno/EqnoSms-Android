package cn.edu.ujn.EqnoSms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SmsAdapter extends ArrayAdapter<ChatItem> {

    private final int resourceID;

    public SmsAdapter(Context context, int resource, List<ChatItem> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatItem msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID,  null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.layout_left);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.layout_right);
            viewHolder.leftPho = (TextView) view.findViewById(R.id.name_left);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.content_left);
            viewHolder.rightPho = (TextView) view.findViewById(R.id.name_right);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.content_right);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (msg.getType() == ChatItem.RECEIVE) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftPho.setText((msg.getPhone()+" -> me"));
            viewHolder.leftMsg.setText(msg.getMessage());
        }
        else if (msg.getType() == ChatItem.SEND) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightPho.setText(("me -> "+msg.getPhone()));
            viewHolder.rightMsg.setText(msg.getMessage());
        }
        return view;
    }
    static class ViewHolder {
        LinearLayout leftLayout, rightLayout;
        TextView leftPho, leftMsg, rightMsg, rightPho;
    }
}