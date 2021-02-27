package ai.pensees.sdkdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yalantis.ucrop.UCrop;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import ai.pensees.sdkdemo.face.FaceHelper;
import ai.pensees.sdkdemo.model.UserModel;
import ai.pensees.sdkdemo.utils.AvatarManager;
import ai.pensees.sdkdemo.utils.DaoManager;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FaceInputActivity extends AppCompatActivity {
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
                AvatarManager.getInstance().takePhoto(FaceInputActivity.this);
            }
        });
        etUseId = findViewById(R.id.et_userid);

        findViewById(R.id.tv_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractFeatureAndSaveUser();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == AvatarManager.REQUEST_CODE_PHOTO_CAMERA) {
                // 系统相机拍照完保存图片后进行缩放
                AvatarManager.getInstance().startCropPhotoForUCrop(AvatarManager.mAvatarUri, this);
            } else if (requestCode == AvatarManager.REQUEST_CODE_PHOTO_ALBUM) {
                // 系统相册中选择图片后进行缩放
                if (data != null) {
                    AvatarManager.getInstance().startCropPhotoForUCrop(data.getData(), this);
                }
            } else if (requestCode == AvatarManager.REQUEST_CODE_PHOTO_COMPRESS) {
                uploadZoom();
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadZoom() {
        String path = AvatarManager.getInstance().getZoomImageSaveName();
        File file = new File(path);
        final Uri uri = Uri.parse("file://" + file);
        iv_face.setImageURI(uri);
    }

    private void extractFeatureAndSaveUser() {
        String path = AvatarManager.getInstance().getZoomImageSaveName();
        File file = new File(path);
        final Uri uri = Uri.parse("file://" + file);
        FaceHelper.INSTANCE.extractFeature(uri, new FaceHelper.ExtractCallback() {
            @Override
            public void onExtractError() {
                Toast.makeText(FaceInputActivity.this, "提取特征值失败，请重新拍照！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onExtractSuccess(@NotNull byte[] featureBytes) {
                saveUser(path, featureBytes);
            }
        });
    }

    private void saveUser(String photoPath, byte[] featureBytes) {
        UserModel userModel = new UserModel();
        String uid = etUseId.getText().toString().trim();
        userModel.setUserName(uid);
        userModel.setCarNo(uid);
        userModel.setFeature(featureBytes);
        userModel.setFeatureId(uid);
        userModel.setPhotoLocalUri(photoPath);
        final long currentTimeMillis = System.currentTimeMillis();
        userModel.setCreateTime(currentTimeMillis);
        userModel.setUpdateTime(currentTimeMillis);
        DaoManager.getInstance().getDaoSession().getUserModelDao().insertOrReplace(userModel);
        FaceHelper.INSTANCE.reloadCompareDB();
        Toast.makeText(FaceInputActivity.this, "用户信息保存成功", Toast.LENGTH_SHORT).show();
    }
}

