package com.project.gutenberg.util;

import java.lang.ref.WeakReference;

import com.project.gutenberg.Home;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
    private Context context;

    public BitmapWorkerTask(ImageView imageView, Context context) {
    	this.context = context;
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    protected Bitmap doInBackground(Integer... params) {
        final Bitmap bitmap = decodeSampledBitmapFromResource(context.getResources(), params[0], 100, 100);
        //Home.addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
		   final BitmapFactory.Options options = new BitmapFactory.Options();
		   options.inPreferredConfig = Bitmap.Config.RGB_565;
		   options.inJustDecodeBounds = false;
		   if (Home.screen_width < 1600) {
			   options.inScaled = true;
			   if (Home.screen_width < 800) {
				   options.inSampleSize=2;
			   } else {
				   options.inSampleSize=2;				   
			   }
			   Log.d("cache sampling", "2");
		   }
		   return BitmapFactory.decodeResource(res, resId, options);
	}
    
}