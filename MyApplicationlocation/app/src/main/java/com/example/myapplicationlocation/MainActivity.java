package com.example.myapplicationlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView textView;
    private TextView textView1;
    private LocationManager locationManager;
    private double longitude;
    private double latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.id_textview);
        textView1 = (TextView) findViewById(R.id.textView1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
        loc_func(location);
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        textView.setText("longitude = "+longitude+"\n"+"Latitiude = "+latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void loc_func(Location location){
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            String address = addresses.get(0).getAddressLine(0);
            String area = addresses.get(0).getLocality();
            String city = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalcode = addresses.get(0).getPostalCode();

            String fullAddress = "Address :"+address+", "+area+", "+city+", "+country+", "+postalcode;

            textView1.setText(fullAddress);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error:"+e, Toast.LENGTH_SHORT).show();
        }

    }
}
