package com.nebulacompanies.nebula.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.Toast;

import com.nebulacompanies.nebula.R;

public class Uttils {
  public  static  ProgressDialog mProgressDialog;

    Context context;
    public Uttils(Context context) {
        this.context = context;
        // TODO Auto-generated constructor stub

    }

    public static void  showProgressDialoug(Context context){
         mProgressDialog = new ProgressDialog(context , R.style.MyTheme);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setContentView(R.layout.progressdialog);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressDialog.show();
    }

    public static void hideProgressDialoug(){
        mProgressDialog.dismiss();
    }


    public static String  checkString(String str_message){
      String str = "";
      if (str_message == null || str_message == ""){
          str = "";
      }
      else {
          str = str_message;
      }
      return str;
    }

    public static void showToast(Context context,String str_Message){
        Toast toast = Toast.makeText(context,str_Message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

}
