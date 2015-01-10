package com.join.android.app.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.join.android.app.common.utils.APKUtils_;
import com.kingnet.android.app.mugo.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.php25.PDownload.DownloadFile;

import java.io.File;
import java.util.List;

/**
 * Created by lala on 14/12/22.
 * 显示所有已经下载的文件列表
 */
public class DownloadedListAdapter extends BaseAdapter{

    List<DownloadFile> downloadFiles;
    Context mContext;

    public DownloadedListAdapter(Context context,List<DownloadFile> downloadFiles){
        this.mContext = context;
        this.downloadFiles = downloadFiles;
    }

    @Override
    public int getCount() {
        if(downloadFiles==null)return 0;
        return downloadFiles.size();
    }

    @Override
    public DownloadFile getItem(int i) {
        return downloadFiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        final DownloadFile downloadFile = downloadFiles.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_download,null);
            holder = new ViewHolder();
            holder.portrait = (ImageView) convertView.findViewById(R.id.portrait);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.createTime = (TextView) convertView.findViewById(R.id.createTime);
            holder.install = (Button) convertView.findViewById(R.id.install);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(downloadFile.getPortraitURL(),holder.portrait);
        holder.name.setText(downloadFile.getShowName());
        holder.createTime.setText(downloadFile.getFinishTime());

        final boolean installed = APKUtils_.getInstance_(mContext).checkInstall(mContext,downloadFile.getPackageName());
        if(installed){//打开
            holder.install.setText("打开");
        }else{//安装
            holder.install.setText("安装");
        }

        holder.install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(installed){//打开
                    APKUtils_.getInstance_(mContext).open(mContext,downloadFile.getPackageName());
                }else{//安装
                    APKUtils_.getInstance_(mContext).installAPK((Activity)mContext,new File(downloadFile.getAbsolutePath()));
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        //图片
        public ImageView portrait;
        //名称
        public TextView name;
        //下载日期
        public TextView createTime;
        //安装
        public Button install;
    }

}
