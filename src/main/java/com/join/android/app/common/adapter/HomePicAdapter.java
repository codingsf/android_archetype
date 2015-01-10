package com.join.android.app.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.kingnet.android.app.mugo.R;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.manager.PhotoManager;
import com.join.android.app.common.manager.TaskManager;
import com.join.android.app.common.utils.BitMapUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePicAdapter extends BaseAdapter {
    private List<String> path;
    private RelativeLayout.LayoutParams params;
    private Context context;
    private LruCache<String, Bitmap> mMemoryCache;
    private Bitmap bitmap;
    private String url;
    private ArrayList<String> mSelect;//选中的图片

    public HomePicAdapter(Context context, ArrayList<String> path) {
        this.path = path;
        this.context = context;
        mSelect = new ArrayList<String>();
        int mScreenWidth = DialogManager.getInstance().windowWidth((Activity) context);
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, context
                .getResources().getDisplayMetrics());
        int width = (mScreenWidth - padding) / 4;
        params = new RelativeLayout.LayoutParams(width, width);
        mMemoryCache = PhotoManager.getInstance(context).initLruCache();
    }

    public int getCount() {
        return path == null ? 0 : path.size();
    }

    public void addSelect(String position) {
        mSelect.add(position);
    }

    public void removeSelect(String position) {
        mSelect.remove(position);
    }

    public boolean isSelect(String position) {
        return mSelect.contains(position);
    }

    public Object getItem(int position) {
        return path == null ? null : position;
    }

    public long getItemId(int position) {
        return position;
    }

    public void changData(ArrayList<String> path) {
        this.path = path;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.album_activity_item, null);
//            holder = new ViewHolder();
//            holder.photo = (ImageView) convertView.findViewById(R.id.album_item_photo);
//            holder.select = (ImageView) convertView.findViewById(R.id.album_item_select);
//            holder.photo.setLayoutParams(params);
//            holder.select.setLayoutParams(params);
//            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        url = path.get(position);
//        holder.photo.setTag(url);
        bitmap = mMemoryCache.get(url);
//        if (bitmap != null)
//            holder.photo.setImageBitmap(bitmap);
//        else
//            TaskManager.getInstance().submit(new BitmapWorkerTask(convertView), url);
//        holder.select.setVisibility(mSelect.contains(String.valueOf(position)) ? View.VISIBLE : View.GONE);
        return convertView;
    }

    class ViewHolder {
        ImageView photo;
        ImageView select;
    }

    private class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private View v;
        private String imageUrl;

        public BitmapWorkerTask(View v) {
            this.v = v;
        }

        @Override
        protected Bitmap doInBackground(String... str) {
            imageUrl = str[0];
            Bitmap bitmap = BitMapUtils.getBitmap(context, imageUrl, params.width, params.height);
            if (bitmap != null) {
                mMemoryCache.put(imageUrl, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) v.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
