package ai.pensees.sdkdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.tbruyelle.rxpermissions3.RxPermissions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.functions.Consumer;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Throwable {
                        System.out.println("sssss," + granted);
                        if (granted) { // Always true pre-M
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // Oups permission denied
                        }
                    }
                });
    }
}
