package com.join.android.app.common.pop;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.join.android.app.mgps.R;
import com.join.android.app.common.manager.PhotoManager;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-26
 * Time: 下午3:31
 */
public class PhotoPopupWindow extends BasePopupWindow implements View.OnClickListener {
    private View view;
    public PhotoPopupWindow(Context context) {
        super(context);
        initView();
    }
    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.mine_photo_popupwindow, null);
        view.findViewById(R.id.btn_nativ_pic).setOnClickListener(this);
        view.findViewById(R.id.btn_protograph).setOnClickListener(this);
        view.findViewById(R.id.full_main).setOnClickListener(this);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setContent(view);
    }
    public void show(){
        showAtLocation(view.findViewById(R.id.full_main), Gravity.CENTER | Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_nativ_pic:
                PhotoManager.getInstance(context).doPhotoSingle((Activity) context);
                break;
            case R.id.btn_protograph:
                PhotoManager.getInstance(context).doTakePhoto((Activity) context);
                break;
            case R.id.full_main:
                dismiss();
                break;
        }
        dismiss();
    }

}
