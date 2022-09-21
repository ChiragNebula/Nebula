package com.nebulacompanies.nebula.gui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.nebulacompanies.nebula.BuildConfig;
import com.nebulacompanies.nebula.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.security.auth.callback.Callback;

/**
 * Created by Palak Mehta on 16-Dec-17.
 */

public class ShowNotificationImage extends Activity{

    String image_path, image_text, created_date;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_item);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getInt("id");
            image_text = b.getString("image_text");
            image_path = b.getString("image_path");
        }

        Log.i("INFO","Notifications id : "+ id);
        Log.i("INFO","Notifications : "+ image_text);
        Log.i("INFO","Notifications : "+ image_path);

        final ImageView imageView = (ImageView) findViewById(R.id.pager_image);
        ImageView sharing=(ImageView) findViewById(R.id.sharing);
        final ProgressBar progressBar  = (ProgressBar) findViewById(R.id.progressBar1);
        TextView textView = (TextView) findViewById(R.id.pager_text);
        //TextView dateTextView = (TextView) findViewById(R.id.pager_date);

        textView.setText(image_text);
        if (!image_path.isEmpty()) {


            Glide.with(this).load(image_path.replaceAll(" ", "%20"))
                    .into(imageView);
        }
        sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri bmpUri = getLocalBitmapUri(imageView);
                Log.i("INFO","call shar ;-"+bmpUri);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    Toast.makeText(getApplicationContext(), "sharing failed", Toast.LENGTH_SHORT).show();
                    // ...sharing failed, handle error
                }
            }
        });

        /*ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_swipe);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(id);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());*/
    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s

            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            if (Build.VERSION.SDK_INT > 24) {
                bmpUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
            }else
            {
                bmpUri = Uri.fromFile(file);
            }
            Log.i("INFO","Call for bmpUri:-"+bmpUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) {
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0);
            }
        }
    }

}
