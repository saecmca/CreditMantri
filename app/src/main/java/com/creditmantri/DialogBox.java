package com.creditmantri;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;



/**
 * Created by Mani on 16-06-2017.
 */

public class DialogBox {


  public static boolean isNetworkStatusAvialable(Context context) {
    ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
    if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
      // alerts(context, "Network Not Available! Please turn on Wifi or use mobile data.", "0");
      noInternet(context, "", "1");
            /*Intent main=new Intent(context, UnderMaintanance.class);
            context.startActivity(main);*/

      return false;
    }
    return true;
  }


  //strType=0 -finish
  //strType=1 -Restart the Activity
  //strType=2 -Dismiss dialog


  public static String noInternet(final Context context, String imageUrl, final String type) {
    try {

      Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return imageUrl;

  }


}
