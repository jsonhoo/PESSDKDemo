package ai.pensees.sdkdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

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
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.CAMERA)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Throwable {
                            if (granted) { // Always true pre-M
                                onPermissionRequest();
                            } else {
                                finish();
                            }
                        }
                    });
        } else {
            onPermissionRequest();
        }
    }

    private void onPermissionRequest() {
        FaceHelper.INSTANCE.setMCallback(new FaceHelper.Callback() {
            @Override
            public void onInitSuccess() {
                gotoHomeActivity();
            }
        });
        FaceHelper.INSTANCE.init(this);
    }

    private void gotoHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
