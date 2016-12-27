package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;

public class Hello extends CordovaPlugin {

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("shareToFacebook")) {

            this.callbackContext = callbackContext;
            Intent intent1 = new Intent();
            intent1.setClassName("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerAlphabeticalAlias");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", data.getString(0));
            cordova.setActivityResultCallback (this);
            cordova.getActivity().startActivityForResult(intent1, 1051);
            return true;

        } else {
            
            return false;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1051 && callbackContext != null){
            callbackContext.success("dialog returned");
        }
    }
}
