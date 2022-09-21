package com.nebulacompanies.nebula.location;

import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.JsonObject;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 2/21/2017.
 */

public class LocationSender extends AsyncTask<String, Void, Void> {

    Context mContext = null;
    GoogleApiClient mGoogleApiClient;
    String mID;
    Location mLastLocation;
    //String LOCATION_API = Config.NEB_API + "/API/Location/LocationTrack";
    private static APIInterface mAPIInterface;

    public LocationSender(Context context, GoogleApiClient googleApiClient, String MemberID) {
        this.mContext = context;
        this.mGoogleApiClient = googleApiClient;
        this.mID = MemberID;

        mAPIInterface = APIClient.getClient(mContext).create(APIInterface.class);
    }

    @Override
    protected Void doInBackground(String... strings) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.i("Latitude : ", String.valueOf(mLastLocation.getLatitude()));
            Log.i("Longitude : ", String.valueOf(mLastLocation.getLongitude()));
            //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            getLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
        return null;
    }

    public void getLocation(Double latitude, Double longitude) {
        Geocoder geocoder;
        //List<Address> addresses = null;
        geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                String address1 = addresses.get(0).getAddressLine(1);
                String area = addresses.get(0).getSubLocality();
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String fullAddress = address + ", " + address1 + ", " + area + ", " + city + ", " + state + ", " + country + ", " + postalCode;
                Log.i("Location : ", fullAddress);
                //sendLocationToServer(Double.toString(latitude), Double.toString(longitude), mID, fullAddress);
            //    sendLocationToServer_(latitude, longitude);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void sendLocationToServer(String latitude, String longitude, String ID, String location) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(LOCATION_API);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("Longitude", longitude));
            nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
            nameValuePairs.add(new BasicNameValuePair("Memberid", ID));
            nameValuePairs.add(new BasicNameValuePair("Address", location));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            Log.i("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
