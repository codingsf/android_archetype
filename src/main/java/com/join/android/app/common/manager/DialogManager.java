package com.join.android.app.common.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import com.join.android.app.mgps.R;

public class DialogManager {
    private static DialogManager manager;

    private PopupWindow popupWindow;
    private Button btnOk, btnCancel;
    private TextView tvTitle, tvMessage;
    private View viewDialog;
    private Context context;
    private Toast result;
    private int position = DIALOG_POSITION_BOTTOM;
    public static final int DIALOG_POSITION_BOTTOM = Gravity.BOTTOM;
    public static final int DIALOG_POSITION_CENTER = Gravity.CENTER;
    //警告
    public static final int DIALOG_TYPE_WARRING = 1;
    //成功
    public static final int DIALOG_TYPE_OK = 2;
    //失败
    public static final int DIALOG_TYPE_ERROR = 3;


    private DialogManager() {
        popupWindow = null;
    }

    public static DialogManager getInstance() {
        if (manager == null) manager = new DialogManager();

        return manager;
    }

    /**
     * 创建普通菜单 有标题,内容,取消,确定
     *
     * @param context
     */
    public void createNormalDialog(Context context, String title, String message) {
        viewDialog = LayoutInflater.from(context).inflate(R.layout.alert_dialog_view, null);
        tvTitle = (TextView) viewDialog.findViewById(R.id.tv_dialog_title);
        tvMessage = (TextView) viewDialog.findViewById(R.id.tv_dialog_content);
        tvTitle.setText(title);
        tvMessage.setText(message);
        btnCancel = (Button) viewDialog.findViewById(R.id.btn_dialog_cancel);
        btnOk = (Button) viewDialog.findViewById(R.id.btn_dialog_ok);
        popupWindow = new PopupWindow(viewDialog, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setPopupWindow();
        handler.sendEmptyMessage(0);
    }

    public void setOk(String name, View.OnClickListener click) {
        btnOk.setOnClickListener(click);
        btnOk.setText(name);
    }

    public void setCancel(String name, View.OnClickListener click) {
        btnCancel.setOnClickListener(click);
        btnCancel.setText(name);
    }

    /**
     * 创建普通提示框
     * message提示内容
     * dialogType 提示图标类型
     */
    public void makeText(Context context, String message, int dialogType) {
        if (result==null){
         result = new Toast(context);
        }
        View viewDialog = LayoutInflater.from(context).inflate(R.layout.register_alert_dialog, null);
        TextView tvMessage = (TextView) viewDialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);
        ImageView ivIcon = (ImageView) viewDialog.findViewById(R.id.iv_dialog_icon);
        if (dialogType == DialogManager.DIALOG_TYPE_ERROR) {
            ivIcon.setImageResource(R.drawable.dialog_error_icon);
        } else if (dialogType == DialogManager.DIALOG_TYPE_OK) {
            ivIcon.setImageResource(R.drawable.dialog_ok_icon);
        } else if (dialogType == DialogManager.DIALOG_TYPE_WARRING) {
            ivIcon.setImageResource(R.drawable.dialog_warring_icon);
        }
        result.setView(viewDialog);
        //setGravity方法用于设置位置，此处为垂直居中
        result.setGravity(Gravity.CENTER_HORIZONTAL | position, 0, 0);
        result.setDuration(Toast.LENGTH_SHORT);
        result.show();
    }

    public void setToastPosition(int position) {
        this.position = position;
    }

    //关闭当前弹出框
    public void dismiss() {
        if (popupWindow != null) {
            try {
                popupWindow.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }


    /**
     * 创建自定义弹出框
     *
     * @param view   自定的试图
     * @param width  在屏幕中的宽度
     * @param height 在屏幕中的高度
     * @param x      偏移屏幕宽度
     * @param y      偏移屏幕高度
     * @param parent 根据父容器定位
     */
    public void createDialog(View view, int width, int height, int x, int y, View parent) {
        popupWindow = new PopupWindow(view, width, height);
        setPopupWindow();
        popupWindow.showAtLocation(parent, Gravity.LEFT | Gravity.TOP, x, y);
    }

    /**
     * 获取系统屏幕宽度
     */
    public int windowWidth(Activity activity) {
        return displayMetrics(activity).widthPixels;
    }

    public int windowHeight(Activity activity) {
        return displayMetrics(activity).heightPixels;
    }

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {

            try {
                popupWindow.showAtLocation(viewDialog.findViewById(R.id.full_main), Gravity.CENTER | Gravity.CENTER, 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    });

    public int getStateBar(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    private DisplayMetrics displayMetrics(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    private void setPopupWindow() {
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(-00000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.update();
    }


}
