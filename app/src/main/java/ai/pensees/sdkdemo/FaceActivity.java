package ai.pensees.sdkdemo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import ai.pensees.sdkdemo.utils.AvatarManager;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FaceActivity extends AppCompatActivity {
    private String deviceMac;
    private SimpleDraweeView iv_face;
    private EditText etUseId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_input);

        initView();
    }

    public void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("录入人脸");

        iv_face = findViewById(R.id.iv_face);
        iv_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvatarManager.getInstance().takePhoto(FaceActivity.this);
            }
        });
        etUseId = findViewById(R.id.et_userid);

        findViewById(R.id.tv_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == AvatarManager.REQUEST_CODE_PHOTO_CAMERA) {
            // 系统相机拍照完保存图片后进行缩放
            AvatarManager.getInstance().startCropPhotoForUCrop(AvatarManager.mAvatarUri, this);
        } else if (resultCode == RESULT_OK && requestCode == AvatarManager.REQUEST_CODE_PHOTO_ALBUM) {
            // 系统相册中选择图片后进行缩放
            if (data != null) {
                AvatarManager.getInstance().startCropPhotoForUCrop(data.getData(), this);
            }
        } else if (resultCode == RESULT_OK && requestCode == AvatarManager.REQUEST_CODE_PHOTO_COMPRESS) {
            uploadZoom();
        } else if (resultCode == UCrop.RESULT_ERROR) {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadZoom() {
        String path = AvatarManager.getInstance().getZoomImageSaveName();
        File file = new File(path);
        iv_face.setImageURI(Uri.parse("file://" + file));
    }

}

