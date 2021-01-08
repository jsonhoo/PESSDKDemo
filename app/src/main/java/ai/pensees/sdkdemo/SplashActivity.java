package ai.pensees.sdkdemo;

import android.Manifest;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tbruyelle.rxpermissions3.RxPermissions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.functions.Consumer;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getPermission();
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Throwable {
                            if (granted) { // Always true pre-M
                                loadAnimation();
                            } else {
                                finish();
                            }
                        }
                    });
        } else {
            loadAnimation();
        }
    }

    private void loadAnimation() {
        LinearLayout splash = (LinearLayout) findViewById(R.id.splash);
        splash.postDelayed(() -> {
            finish();
        }, 1000);
    }

}
