package com.client.brain.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class NetworkConnection extends LiveData<Boolean> {

    Context context;
    ConnectivityManager connectivityManager;

    public NetworkConnection(Context context) {
        this.context = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    ConnectivityManager.NetworkCallback networkConnectionCallback;

    private ConnectivityManager.NetworkCallback connectivityManagerCallback() {
        networkConnectionCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                postValue(false);
            }

        };
        return networkConnectionCallback;
    }

    private void updateNetworkConnection() {
        NetworkInfo activeNetworkConnection = connectivityManager.getActiveNetworkInfo();
        postValue(activeNetworkConnection.isConnected() == true);
    }

    BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateNetworkConnection();
        }
    };

    @Override
    public void onActive() {
        super.onActive();
        updateNetworkConnection();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback());
        } else {
            context.registerReceiver(
                    networkReceiver,
                    new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            );
        }
    }


    @Override
    public void onInactive() {
        super.onInactive();
        if (networkConnectionCallback != null && connectivityManager != null) {
            try {
                connectivityManager.unregisterNetworkCallback(connectivityManagerCallback());
                networkConnectionCallback = null;
            } catch (Exception e) {
                Log.e("NETWORK CONNECTION", "unregister failed");
            }
        }
    }
}
