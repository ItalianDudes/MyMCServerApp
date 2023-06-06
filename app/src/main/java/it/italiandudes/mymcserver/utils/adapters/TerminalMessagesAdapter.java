package it.italiandudes.mymcserver.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.utils.models.PlayersDataRow;

public class TerminalMessagesAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dataSet;
    private Context mContext;

    public TerminalMessagesAdapter(@NonNull Context context, ArrayList<String> data) {
        super(context,R.layout.item_terminal,data);
        this.dataSet=data;
        this.mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String dataRow = getItem(position);

        MessageHolder holder;
        if (convertView == null) {

            holder = new MessageHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_terminal, parent, false);

            holder.messageTxt = convertView.findViewById(R.id.message);

            convertView.setTag(holder);
        } else {
            holder = (MessageHolder) convertView.getTag();
        }

        holder.messageTxt.setText(dataRow);

        return convertView;
    }

    private static class MessageHolder{
        public TextView messageTxt;
    }
}
