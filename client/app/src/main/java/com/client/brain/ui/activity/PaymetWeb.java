package com.client.brain.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.client.brain.DTO.UserDTO;
import com.client.brain.R;
import com.client.brain.interfacess.Consts;
import com.client.brain.preferences.SharedPrefrence;
import com.client.brain.utils.ProjectUtils;


public class PaymetWeb extends AppCompatActivity {
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private Context mContext;
    private WebView wvPayment;
    private ImageView IVback;
    private static String url = "";
    private static String surl = Consts.PAYMENT_SUCCESS;
    private static String furl = Consts.PAYMENT_FAIL;
    private static String surl_paypal = Consts.PAYMENT_SUCCESS_paypal;
    private static String furl_paypal = Consts.PAYMENT_FAIL_Paypal;

    private static String surl_stripe_book = Consts.INVOICE_PAYMENT_SUCCESS_Stripe;
    private static String furl_stripe_book = Consts.INVOICE_PAYMENT_FAIL_Stripe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymet_web);
        mContext = PaymetWeb.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);
        if (getIntent().hasExtra(Consts.PAYMENT_URL)) {
            url = getIntent().getStringExtra(Consts.PAYMENT_URL);

        }

        wvPayment = (WebView) findViewById(R.id.wvPayment);


        WebSettings settings = wvPayment.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        wvPayment.loadUrl(url);

        wvPayment.setWebViewClient(new SSLTolerentWebViewClient());
        init();
    }

    private void init() {
        IVback = (ImageView) findViewById(R.id.IVback);
        IVback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.click_event));
                finish();
            }
        });
    }

    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(PaymetWeb.this);
            builder.setMessage("SSL error has occured - no secure connection could be made");
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            ProjectUtils.pauseProgressDialog();
            //Page load finished
            if (url.equals(surl)) {
                ProjectUtils.showToast(mContext, "Payment was successful.");
                super.onPageFinished(view, surl);
                prefrence.setValue(Consts.SURL, surl);
                finish();

                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            } else if (url.equals(furl)) {
                ProjectUtils.showToast(mContext, "Payment fail.");
                //view.loadUrl("https://www.youtube.com");
                super.onPageFinished(view, furl);
                prefrence.setValue(Consts.FURL, furl);
                finish();
                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            }else if (url.equals(surl_stripe_book)) {
                ProjectUtils.showToast(mContext, "Payment was successful.");
                super.onPageFinished(view, surl_stripe_book);
                prefrence.setValue(Consts.SURL, surl_stripe_book);
                finish();

                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            } else if (url.equals(furl_stripe_book)) {
                ProjectUtils.showToast(mContext, "Payment fail.");
                //view.loadUrl("https://www.youtube.com");
                super.onPageFinished(view, furl_stripe_book);
                prefrence.setValue(Consts.FURL, furl_stripe_book);
                finish();
                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            } else if (url.equals(surl_paypal)) {
                ProjectUtils.showToast(mContext, "Payment was successful.");
                //view.loadUrl("https://www.youtube.com");
                super.onPageFinished(view, surl_paypal);
                prefrence.setValue(Consts.SURL, surl_paypal);
                finish();
                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            } else if (url.equals(furl_paypal)) {
                ProjectUtils.showToast(mContext, "Payment fail.");
                //view.loadUrl("https://www.youtube.com");
                super.onPageFinished(view, furl_paypal);
                prefrence.setValue(Consts.FURL, furl_paypal);
                finish();
                wvPayment.clearCache(true);

                wvPayment.clearHistory();

                wvPayment.destroy();
            } else {
                super.onPageFinished(view, url);
            }

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }


}
