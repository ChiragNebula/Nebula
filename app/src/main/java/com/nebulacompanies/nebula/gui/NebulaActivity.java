package com.nebulacompanies.nebula.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyTextView;

/**
 * Created by Palak Mehta on 9/6/2016.
 */
public class NebulaActivity extends Base2Activity {

    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11;
    Animation animation;
    ImageView imageView;
    Button btn;
    Handler handler, handler1, handler2, handler3, handler4, handler5, handler6, handler7, handler8, handler9, handler10, handler11, handler12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nebula_details);
        init();
    }
    void init() {
        imageView = (ImageView) findViewById(R.id.nebula_id);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

        text1 = (MyTextView) findViewById(R.id.txtfornebulaRealEstate);
        text2 = (MyTextView) findViewById(R.id.txtfornebulasin1);
        text3 = (MyTextView) findViewById(R.id.txtfornbulaVacations);
        text4 = (MyTextView) findViewById(R.id.txtfornbualsin2);
        text5 = (MyTextView) findViewById(R.id.txtfornbualTimeshare);
        text6 = (MyTextView) findViewById(R.id.txtfornbualsin3);
        text7 = (MyTextView) findViewById(R.id.txtfornbualNutraceuticals);
        text8 = (MyTextView) findViewById(R.id.txtfornebula1text);
        text9 = (MyTextView) findViewById(R.id.txtfornebula12);
        text10 = (MyTextView) findViewById(R.id.txtfornebula121);
        text11 = (MyTextView)findViewById(R.id.txtfornebulaappVersion);
        btn = (Button) findViewById(R.id.btnok);

        setFonts();
        // Handler img
        handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                imageView.startAnimation(animation);
                imageView.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(r, 100);

        // Handler text1
        handler1 = new Handler();
        Runnable r1 = new Runnable() {
            public void run() {
                text1.startAnimation(animation);
                text1.setVisibility(View.VISIBLE);
            }
        };
        handler1.postDelayed(r1, 700);

        // Handler text2
        handler2 = new Handler();
        Runnable r2 = new Runnable() {
            public void run() {
                text2.startAnimation(animation);
                text2.setVisibility(View.VISIBLE);
            }
        };
        handler2.postDelayed(r2, 1000);

        // Handler text3
        handler3 = new Handler();
        Runnable r3 = new Runnable() {
            public void run() {
                text3.startAnimation(animation);
                text3.setVisibility(View.VISIBLE);
            }
        };
        handler3.postDelayed(r3, 1400);

        // Handler text4
        handler4 = new Handler();
        Runnable r4 = new Runnable() {
            public void run() {
                text4.startAnimation(animation);
                text4.setVisibility(View.VISIBLE);
            }
        };
        handler4.postDelayed(r4, 1800);

        // Handler text5
        handler5 = new Handler();
        Runnable r5 = new Runnable() {
            public void run() {
                text5.startAnimation(animation);
                text5.setVisibility(View.VISIBLE);
            }
        };
        handler5.postDelayed(r5, 2200);

        // Handler text6
        handler6 = new Handler();
        Runnable r6 = new Runnable() {
            public void run() {
                text6.startAnimation(animation);
                text6.setVisibility(View.VISIBLE);
            }
        };
        handler6.postDelayed(r6, 2600);

        // Handler text7
        handler7 = new Handler();
        Runnable r7 = new Runnable() {
            public void run() {
                text7.startAnimation(animation);
                text7.setVisibility(View.VISIBLE);
            }
        };
        handler7.postDelayed(r7, 3000);

        // Handler text8

        handler8 = new Handler();
        Runnable r8 = new Runnable() {
            public void run() {
                text8.startAnimation(animation);
                text8.setVisibility(View.VISIBLE);
            }
        };
        handler8.postDelayed(r8, 3400);

        // Handler text9
        handler9 = new Handler();
        Runnable r9 = new Runnable() {
            public void run() {
                text9.startAnimation(animation);
                text9.setVisibility(View.VISIBLE);
            }
        };
        handler9.postDelayed(r9, 3800);

        // Handler text10
        handler10 = new Handler();
        Runnable r10 = new Runnable() {
            public void run() {
                btn.startAnimation(animation);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ContactUs.class);
                        startActivity(i);
                    }
                });
            }
        };
        handler10.postDelayed(r10, 4200);

        handler11=new Handler();
        Runnable r11 = new Runnable() {
            public void run() {
                text10.startAnimation(animation);
                text10.setVisibility(View.VISIBLE);
                SpannableString content = new SpannableString(getResources().getString(R.string.privacy_policy));
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                text10.setText(content);
                text10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openPrivacyPolicy();
                    }
                });

            }
        };
        handler11.postDelayed(r11, 100);

        handler12=new Handler();
        Runnable r12 = new Runnable() {
            public void run() {
                text11.startAnimation(animation);
                text11.setVisibility(View.VISIBLE);
                text11.append(getVersion());

            }
        };
        handler12.postDelayed(r12, 100);

    }

    private void openPrivacyPolicy() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater factory = LayoutInflater.from(NebulaActivity.this);
        final View view = factory.inflate(R.layout.privacy_policy, null);
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int arg1) {
                d.dismiss();
            }
        });
        alertDialogBuilder.setView(view);
        alertDialogBuilder.show();
    }

    private String getVersion(){
        PackageManager manager = getApplicationContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setFonts(){
        Typeface tf1 = Typeface.createFromAsset(getAssets(), Config.FONT_STYLE);
        btn.setTypeface(tf1);
    }
}
