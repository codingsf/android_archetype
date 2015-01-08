package com.php25.PDownload;

/**
 * Created with mawanjin
 * User: mawanjin
 * Date: 14-9-9
 * Time: 上午9:13
 * To change this template use File | Settings | File Templates.
 */
public interface DownloadHandler {
    void updateProcess(float process);

    void finished();
}
