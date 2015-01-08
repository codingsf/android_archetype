package com.join.android.app.common.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by zengmei on 13-12-13.
 */
public class BasePopupWindow extends PopupWindow {
    protected Context context;

    public BasePopupWindow(Context context) {
        super(context);
        this.context = context;
    }

    protected void setView(View view) {
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContent(view);

    }
    protected void setContent(View view){
        setContentView(view);
        setFocusable(true);
        setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(-00000);
        setBackgroundDrawable(dw);
        update();
    }
}
