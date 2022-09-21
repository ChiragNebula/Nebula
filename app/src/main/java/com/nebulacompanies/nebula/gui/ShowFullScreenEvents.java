package com.nebulacompanies.nebula.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nebulacompanies.nebula.BuildConfig;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.util.Session;
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Palak Mehta on 17-Jan-18.
 */

public class ShowFullScreenEvents extends Activity {

    int id;

    // ArrayList<SubEventList> eventLists;
    int main = 41;
    boolean isImageAdded = true;
    int total = 0;
    // MyTextView likesTextView;
    //Modify Sagar Virvani
    //Call Api
    private APIInterface mAPIInterface;
    ConnectionDetector cd;
    public static final String TAG = "Events";
    Session session;
    UserAuthorization mUserAuthorization;
    boolean onBack;
    String EventName;
    String PREFS_NAME = "MyPrefsFile";

    ArrayList<String> imagepic = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();
    ImageView imageViewBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_view);

        Intent i = getIntent();
        if (i != null) {
            id = i.getExtras().getInt("id");
            // eventLists = (ArrayList<SubEventList>) i.getSerializableExtra("image_list");
            onBack = i.getExtras().getBoolean("first_time_event_sup");
            EventName = i.getExtras().getString("eventName");
            imagepic = i.getExtras().getStringArrayList("imagepic");
            date = i.getExtras().getStringArrayList("date");
            count = i.getExtras().getIntegerArrayList("count");
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_swipe);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(id);
        viewPager.setPageTransformer(true, new ShowFullScreenSiteProgress.ZoomOutPageTransformer());
    }

    private class ImagePagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public ImagePagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagepic.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            final ImageView imageView = (ImageView) itemView.findViewById(R.id.pager_image);
            ImageView sharing = (ImageView) itemView.findViewById(R.id.sharing);
            final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            MyTextView textView = (MyTextView) itemView.findViewById(R.id.pager_text);
            MyTextView dateTextView = (MyTextView) itemView.findViewById(R.id.pager_date);

            if (position < imagepic.size()) {
                dateTextView.setText(date.get(position));

                Glide.with(container.getContext()).load(imagepic.get(position).replaceAll(" ", "%20"))
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.nebula_placeholder)

                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(imageView );



                /*Picasso.with(container.getContext()).load(imagepic.get(position).replaceAll(" ", "%20"))
                        //.skipMemoryCache()
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onError() {
                                progressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                            }
                        });*/

                sharing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri bmpUri = getLocalBitmapUri(imageView);
                        Log.d("INFO", "call shar ;-" + bmpUri);
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

               /* dateTextView.setText(SetDateFormat(eventLists.get(position).getCreatedate()));*/

                container.addView(itemView);
            }
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
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

            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            if (Build.VERSION.SDK_INT > 24) {
                bmpUri = FileProvider.getUriForFile(ShowFullScreenEvents.this, BuildConfig.APPLICATION_ID + ".provider", file);
            } else {
                bmpUri = Uri.fromFile(file);
            }
            Log.i("INFO", "Call for bmpUri:-" + bmpUri);
            //bmpUri = Uri.fromFile(file);
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