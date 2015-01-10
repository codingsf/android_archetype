package com.join.android.app.mugo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.join.android.app.common.utils.ImageOptionFactory;
import com.join.android.app.mugo.dto.AD;
import com.kingnet.android.app.mugo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by lala on 15/1/9.
 */
public class ADDialog extends AlertDialog{

    private AD ad;

    public ADDialog(Context context) {
        super(context);
    }

    public ADDialog(Context context, int theme,AD _ad) {
        super(context, theme);
        this.ad = _ad;
    }

    public ADDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ad);

        ImageView adImg = (ImageView) findViewById(R.id.adImg);
        ImageView imgOpen = (ImageView) findViewById(R.id.imgOpen);

        ImageLoader.getInstance().displayImage(ad.getImgUrl(), adImg,ImageOptionFactory.getADDialogOptions());
        imgOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(ad.getJumpUrl());
                intent.setData(content_url);
                getContext().startActivity(intent);
            }
        });



    }


}
