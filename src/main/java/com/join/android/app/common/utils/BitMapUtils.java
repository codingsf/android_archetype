package com.join.android.app.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.*;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;


public class BitMapUtils {
    //切割图片边角
    public static Bitmap toRoundCorner(Bitmap bitmap, int scalaPixel) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = scalaPixel;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    //等比例压缩 数据文件路径
    public static Bitmap getBitmap(Context context, String file, int width, int height) {
        try {
            //设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度
            Bitmap bitmap;
            if (width != 0 && height != 0) {
                BitmapFactory.Options opt = options(context);
                BitmapFactory.decodeFile(file, opt);
                opt = options(opt, width, height);
                bitmap = BitmapFactory.decodeFile(file, opt);
            } else
                bitmap = BitmapFactory.decodeFile(file);
            Matrix matrix = new Matrix();
            matrix.postRotate(readPictureDegree(file));
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //等比例压缩 数据bytes
    public static Bitmap getBitmap(Context context, byte[] bytes, int width, int height) {
        try {
            //设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度
            if (width != 0 && height != 0) {
                BitmapFactory.Options opt = options(context);
                BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opt);
                opt = options(opt, width, height);
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opt);
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //跳转图片方向
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return degree;
    }

    //bitmap 2 byte
    private static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //这个函数能够设定图片的宽度与高度
        //Bitmap map = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
        return baos.toByteArray();
    }

    //创建options
    private static BitmapFactory.Options options(Context context) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        opt.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        opt.inScaled = true;
        return opt;
    }

    //计算比例
    private static BitmapFactory.Options options(BitmapFactory.Options opt, int width, int height) {
        int outWidth = opt.outWidth; //获得图片的实际高和宽
        int outHeight = opt.outHeight;
        opt.inDither = false;
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0) {
            int yRatio = (int) Math.ceil(outHeight
                    / height);
            int xRatio = (int) Math
                    .ceil(outWidth / width);
            if (yRatio > 1 || xRatio > 1) {
                if (yRatio > xRatio) {
                    opt.inSampleSize = yRatio;
                } else {
                    opt.inSampleSize = xRatio;
                }
            }
        }
        opt.inJustDecodeBounds = false;//最后把标志复原
        return opt;
    }

    //图片在项目的res文件夹下面
    public static Bitmap getFromRes(Context context ,String name) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resID = context.getResources().getIdentifier(name, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(context.getResources(), resID);
     }

}
