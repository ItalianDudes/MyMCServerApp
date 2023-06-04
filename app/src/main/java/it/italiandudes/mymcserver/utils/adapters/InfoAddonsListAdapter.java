package it.italiandudes.mymcserver.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.utils.models.AddonsDataRow;
import it.italiandudes.mymcserver.utils.models.PlayersDataRow;

public class InfoAddonsListAdapter extends ArrayAdapter<AddonsDataRow> {

    private ArrayList<AddonsDataRow> dataSet;
    private Context mContext;

    public InfoAddonsListAdapter(@NonNull Context context, ArrayList<AddonsDataRow> data) {
        super(context, R.layout.item_info_lists, data);
        this.dataSet=data;
        this.mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        AddonsDataRow dataRow = getItem(position);

        if(dataRow.getLength()==1) {

            OneCellRowHolder holder;

            if (convertView == null) {

                holder = new OneCellRowHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_info_lists, parent, false);

                holder.cell1Txt = convertView.findViewById(R.id.first_cell_txt);
                holder.cell1ImgView = convertView.findViewById(R.id.first_cell_imgview);

                convertView.setTag(holder);

            } else {
                holder = (OneCellRowHolder) convertView.getTag();
            }

            holder.cell1Txt.setText(dataRow.getAddon1().getName());
            if (dataRow.getAddon1().isActive()) {
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
        }else if(dataRow.getLength()==2) {


            TwoCellsRowHolder holder;

            if (convertView == null) {

                holder = new TwoCellsRowHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_info_lists, parent, false);

                holder.cell1Txt = convertView.findViewById(R.id.first_cell_txt);
                holder.cell1ImgView = convertView.findViewById(R.id.first_cell_imgview);
                holder.cell2Txt = convertView.findViewById(R.id.second_cell_txt);
                holder.cell2ImgView =convertView.findViewById(R.id.second_cell_imgview);

                convertView.setTag(holder);

            } else {
                holder = (TwoCellsRowHolder) convertView.getTag();
            }

            holder.cell1Txt.setText(dataRow.getAddon1().getName());
            if (dataRow.getAddon1().isActive()) {
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
            holder.cell2Txt.setText(dataRow.getAddon2().getName());
            if (dataRow.getAddon2().isActive()) {
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
        }else if(dataRow.getLength()==3) {
            ThreeCellsRowHolder holder;

            if (convertView == null) {

                holder = new ThreeCellsRowHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_info_lists, parent, false);

                holder.cell1Txt = convertView.findViewById(R.id.first_cell_txt);
                holder.cell1ImgView = convertView.findViewById(R.id.first_cell_imgview);
                holder.cell2Txt = convertView.findViewById(R.id.second_cell_txt);
                holder.cell2ImgView =convertView.findViewById(R.id.second_cell_imgview);
                holder.cell3Txt = convertView.findViewById(R.id.third_cell_txt);
                holder.cell3ImgView = convertView.findViewById(R.id.third_cell_imgview);

                convertView.setTag(holder);

            } else {
                holder = (ThreeCellsRowHolder) convertView.getTag();
            }

            holder.cell1Txt.setText(dataRow.getAddon1().getName());
            if (dataRow.getAddon1().isActive()) {
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
            holder.cell2Txt.setText(dataRow.getAddon2().getName());
            if (dataRow.getAddon2().isActive()) {
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }
            holder.cell3Txt.setText(dataRow.getAddon3().getName());
            if (dataRow.getAddon3().isActive()) {
                holder.cell3ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }else{
                holder.cell3ImgView.setBackgroundResource(R.drawable.baseline_circle_active_24);
            }
        }else{
            FourCellsRowHolder holder;

            if (convertView == null) {

                holder = new FourCellsRowHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_info_lists, parent, false);

                holder.cell1Txt = convertView.findViewById(R.id.first_cell_txt);
                holder.cell1ImgView = convertView.findViewById(R.id.first_cell_imgview);
                holder.cell2Txt = convertView.findViewById(R.id.second_cell_txt);
                holder.cell2ImgView =convertView.findViewById(R.id.second_cell_imgview);
                holder.cell3Txt = convertView.findViewById(R.id.third_cell_txt);
                holder.cell3ImgView = convertView.findViewById(R.id.third_cell_imgview);
                holder.cell4Txt = convertView.findViewById(R.id.fourth_cell_txt);
                holder.cell4ImgView = convertView.findViewById(R.id.fourth_cell_imgview);

                convertView.setTag(holder);

            } else {
                holder = (FourCellsRowHolder) convertView.getTag();
            }

            holder.cell1Txt.setText(dataRow.getAddon1().getName());
            if (dataRow.getAddon1().isActive()) {
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_check_circle_24);
            }else{
                holder.cell1ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
            holder.cell2Txt.setText(dataRow.getAddon2().getName());
            if (dataRow.getAddon2().isActive()) {
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_check_circle_24);
            }else{
                holder.cell2ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
            holder.cell3Txt.setText(dataRow.getAddon3().getName());
            if (dataRow.getAddon3().isActive()) {
                holder.cell3ImgView.setBackgroundResource(R.drawable.baseline_check_circle_24);
            }else{
                holder.cell3ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
            holder.cell4Txt.setText(dataRow.getAddon4().getName());
            if (dataRow.getAddon4().isActive()) {
                holder.cell4ImgView.setBackgroundResource(R.drawable.baseline_check_circle_24);
            }else{
                holder.cell4ImgView.setBackgroundResource(R.drawable.baseline_circle_notactive_24);
            }
        }

        return convertView;
    }

    private static class OneCellRowHolder{
        public TextView cell1Txt;
        public ImageView cell1ImgView;
    }

    private static class TwoCellsRowHolder{
        public TextView cell1Txt;
        public ImageView cell1ImgView;
        public TextView cell2Txt;
        public ImageView cell2ImgView;
    }

    private static class ThreeCellsRowHolder{
        public TextView cell1Txt;
        public ImageView cell1ImgView;
        public TextView cell2Txt;
        public ImageView cell2ImgView;
        public TextView cell3Txt;
        public ImageView cell3ImgView;
    }

    private static class FourCellsRowHolder{
        public TextView cell1Txt;
        public ImageView cell1ImgView;
        public TextView cell2Txt;
        public ImageView cell2ImgView;
        public TextView cell3Txt;
        public ImageView cell3ImgView;
        public TextView cell4Txt;
        public ImageView cell4ImgView;
    }

}
