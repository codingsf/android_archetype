package com.join.android.app.common.activity;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.BaseActivity;
import com.join.android.app.common.adapter.DownloadedListAdapter;
import com.join.android.app.mgps.R;
import com.php25.PDownload.DownloadFile;
import com.php25.PDownload.DownloadHandler;
import com.php25.PDownload.DownloadTool;
import org.androidannotations.annotations.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by lala on 14/12/22.
 */

@EActivity(R.layout.download_activity_layout)
public class DownloadActivity extends BaseActivity {

    @ViewById
    Button btnDownload;
    @ViewById
    Button btnAllDownloaded;
    @ViewById
    TextView txtIndicator;
    @ViewById
    TextView txtStatus;
    @ViewById
    TextView txtDownloadedList;
    @ViewById
    ListView listView;

    DownloadFile downloadFile;

    @AfterViews
    void afterViews() {

//        //获取所有已经下载完的文件
//        DownloadedListAdapter adapter = new DownloadedListAdapter(this,DownloadTool.getAllDownloaded((DownloadApplication)getApplicationContext()));
//        listView.setAdapter(adapter);
//
//        List<DownloadFile> downloadFiles = DownloadTool.getAllDownloadingTask((DownloadApplication) getApplicationContext());
//        if(downloadFiles!=null&&downloadFiles.size()>0){
//            downloadFile = downloadFiles.get(0);
//            getDownloadingStatus(downloadFile);
//        }
    }

    /**
     * 显示所有已经下载的
     */
    @Click
    void btnAllDownloadedClicked(){
//        DownloadTool.getAllDownloaded((DownloadApplication)getApplicationContext());
    }

    /**
     * 开始下载
     */
    @Click
    void btnDownloadClicked(){

        String url = "http://gdown.baidu.com/data/wisegame/2c6a60c5cb96c593/QQ_182.apk";
        String portraitURL = "http://img3sw.baidu.com/soft/3a/12350/2f1844d25944d442dc2695a260376513.png?version=1147706935";
//        //删除之前的
//        DownloadTool.deleteDownloadTask((DownloadApplication) (getApplicationContext()), url);
//
//        DownloadTool.startDownload((DownloadApplication) (getApplicationContext()), url, portraitURL, "qq", Dtype.APK, "apk", new DownloadTool.DownloadToolCallback() {
//            @Override
//            public void afterStartDownload(DownloadFile downloadFile) {
//                updateDownloadProgress(downloadFile);
//            }
//        });
    }

    /**
     * 跟踪下载进度
     * @param file
     */

    public void updateDownloadProgress(final DownloadFile file) {

//        Map<String,Future> futureMap = ((DownloadApplication) getApplicationContext()).getFutureMap();
//        Object t = futureMap.get(file.getTag());
//        if(t == null){
//            Future future = DownloadTool.updateDownloadProgress((DownloadApplication) getApplicationContext(), new DownloadHandler() {
//
//                @Override
//                public void updateProcess(float process) {
//                    if(Float.isNaN(process)){
//                        file.setPercent("0%");
//                    }else{
//                        String p = (((process)*100)+"");
//                        if(p.length()>4)
//                            p = p.substring(0,4);
//                        file.setPercent(p+"%");
//
//                    }
//                    updateDownloadProgressView(file);
//                }
//
//                @Override
//                public void finished() {
//                    file.setPercent("100%");
//                    updateDownloadProgressView(file);
//                }
//            }, file);
//
//            futureMap.put(file.getTag(),future);
//        }
    }

    @UiThread
    public void updateDownloadProgressView(DownloadFile file){
        txtIndicator.setText(file.getPercent());
    }


    /**
     * 得到当前状态
     * @param downloadFile
     * @return
     */
    private String getDownloadingStatus(DownloadFile downloadFile) {
//        if (DownloadTool.isDownloadingNow((DownloadApplication) getApplicationContext(), downloadFile.getUrl())) {
//            downloadFile.setDownloadingNow(true);
//            updateDownloadProgress(downloadFile);
//            return "正在下载";
//        }else{
//            downloadFile.setDownloadingNow(false);
//            if(downloadFile.getPercent()==null){
//                final File file = new File(downloadFile.getAbsolutePath());
//                float filelength = file.length();
//                float totalSize = new Float(downloadFile.getTotalSize());
//
//                float f = 0;
//                if(filelength>0){
//                    f = (filelength/totalSize)*100;
//                    if(f>100)f=100;
//                    String p = (f+"");
//
//                    if(p.length()>4)
//                        p = p.substring(0,4);
//                    downloadFile.setPercent(p+"%");
//                }else
//                    downloadFile.setPercent("0%");
//
//            }
//                txtStatus.setText(downloadFile.getPercent());
//
//        }

        return "暂停";

    }
}
