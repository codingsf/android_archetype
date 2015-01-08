package com.join.android.app.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.join.android.app.mgps.R;
import com.join.android.app.common.data.ImageInfo;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.utils.BitMapUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zengmei on 13-11-4.
 */
public class PhotoAdapter extends BaseAdapter {
    private List<ImageInfo> mResults;
    private FrameLayout.LayoutParams layoutParams;
    private Context context;

    public PhotoAdapter(Context context, List<ImageInfo> results) {
        this.context = context;
        if (results != null)
            mResults = results;
        else
            mResults = new LinkedList<ImageInfo>();
        int mScreenWidth = DialogManager.getInstance().windowWidth((Activity) context);
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, context
                .getResources().getDisplayMetrics());
        int width = (mScreenWidth - padding) / 3;
        layoutParams = new FrameLayout.LayoutParams(width, width);
    }

    public int getCount() {
        return mResults == null ? 0 : mResults.size();
    }

    public Object getItem(int position) {
        return mResults == null ? null : mResults.get(position);
    }

    public long getItemId(int position) {
        return mResults == null ? 0 : position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.photo_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.photo_item_img);
            holder.image.setLayoutParams(layoutParams);
            holder.description = (TextView) convertView
                    .findViewById(R.id.photo_item_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageInfo info = mResults.get(position);
        holder.image.setImageBitmap(BitMapUtils.getBitmap(context, info.getFirstPath(), layoutParams.width, layoutParams.width));
        holder.description.setText(info.getDisplayName() + "("
                + info.getTag().size() + ")");
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView description;
    }
}
