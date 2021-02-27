package ai.pensees.sdkdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.UUID;

import ai.pensees.commons.ImageUtils;
import ai.pensees.sdk.common.SDKConstant;
import ai.pensees.sdk.common.data.FaceInfo;
import ai.pensees.sdk.facedetect.PESFaceDetect;
import ai.pensees.sdk.facefeature.PESFeature;
import ai.pensees.sdk.maskdetect.PESMask;
import ai.pensees.sdkdemo.gen.DaoSession;
import ai.pensees.sdkdemo.model.FaceFeature;
import ai.pensees.sdkdemo.model.UserModel;
import ai.pensees.sdkdemo.utils.DaoManager;
import ai.pensees.sdkdemo.widget.ClearEditText;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private final static int REQUEST_CODE_CAPTURE_CAMEIA = 0x1;
    private static final String SAVED_IMAGE_DIR_PATH = "/sdcard/DCIM/";
    //口罩阈值(0.75)(0-1)
    private final static double MASK = 0.75;
    //人脸置信度阈值(90)(0-100)
    private final static int SCORE = 90;

    private ClearEditText input_user_no;
    private ClearEditText input_user_name;
    private ClearEditText input_user_carNo;
    private ClearEditText input_user_phone;
    private ClearEditText input_user_address;
    private RadioButton rb_manage;
    private ImageView img_avatar;

    private String capturePath = "";
    private FaceFeature faceFeature;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("增加用户");

        input_user_no= findViewById(R.id.input_user_no);
        input_user_name= findViewById(R.id.input_user_name);
        input_user_carNo= findViewById(R.id.input_user_carNo);
        input_user_phone= findViewById(R.id.input_user_phone);
        input_user_address= findViewById(R.id.input_user_address);

        rb_manage = findViewById(R.id.rb_manage);
        img_avatar = findViewById(R.id.img_avatar);

        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        img_avatar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btn_save){
            saveUser();
            this.finish();
        }else if(v.getId() ==  R.id.img_avatar){
            getImageFromCamera();
        }
    }

    protected void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            String out_file_path = SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            capturePath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg";
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(capturePath)));
            getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(getImageByCamera,REQUEST_CODE_CAPTURE_CAMEIA);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA ) {
            Picasso.get().load("file://"+capturePath).into(img_avatar);
            checkFace();
        }
    }

    private void saveUser(){

        String userNo = input_user_no.getText().toString();
        String userName = input_user_name.getText().toString();
        String userCard = input_user_carNo.getText().toString();
        String userPhone = input_user_phone.getText().toString();
        String userAddress = input_user_address.getText().toString();

        if(StringUtils.isEmpty(capturePath)){
            Toast.makeText(this,"请设置头像",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringUtils.isEmpty(userNo)){
            Toast.makeText(this,"用户编号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(userName)){
            Toast.makeText(this,"用户名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(userPhone)){
            Toast.makeText(this,"用户电话不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(userAddress)){
            Toast.makeText(this,"用户地址不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        boolean checked = rb_manage.isChecked();

        UserModel userModel = new UserModel();
        userModel.setUserId(userNo);

        userModel.setServerPhotoUrl(capturePath);
        userModel.setFeatureId(faceFeature.getFeatureId());

//        userModel.setUserName(userName);
//        userModel.setCarNo(userCard);
//        userModel.setUserPhone(userPhone);
//        userModel.setUserAddress(userAddress);

        userModel.setUpdateTime(System.currentTimeMillis());
        userModel.setCreateTime(System.currentTimeMillis());
        userModel.setIsAdmin(checked);

        DaoSession daoSession = DaoManager.getInstance().getDaoSession();

//        long index1 = daoSession.getFaceFeatureDao().insert(faceFeature);
        long index2 = daoSession.getUserModelDao().insert(userModel);

    }
    //检测图片中是否存在人脸
    private void checkFace(){
        Bitmap bitmap = BitmapFactory.decodeFile(capturePath);
        byte[] bgr = ImageUtils.bitmapToBGR(bitmap);
        int count = PESFaceDetect.check(bgr, bitmap.getWidth(), bitmap.getHeight(), SDKConstant.IMAGE_FORMAT_BGR);

        if(count != 1){
            Toast.makeText(this,"请保持图片中只有一个人脸",Toast.LENGTH_SHORT).show();
            capturePath = "";
            return;
        }
        List<FaceInfo> result = PESFaceDetect.detect(bgr, bitmap.getWidth(), bitmap.getHeight(), SDKConstant.IMAGE_FORMAT_BGR);
        if(result.size() ==1){
            FaceInfo faceInfo = result.get(0);

            // 人脸置信度
            if(faceInfo.rect.score < SCORE){
                Toast.makeText(this,"人脸图片质量偏低，请重新拍照",Toast.LENGTH_SHORT).show();
                capturePath = "";
                return;
            }
            //检测是否戴口罩
            float detect = PESMask.detect(faceInfo.landmark.getRaw(), bgr, bitmap.getWidth(), bitmap.getHeight(), SDKConstant.IMAGE_FORMAT_BGR);
            if(detect>MASK){
                Toast.makeText(this,"采集人脸数据请不要带口罩",Toast.LENGTH_SHORT).show();
                capturePath = "";
                return;
            }
            //获取脸特征
            byte[] extract = PESFeature.extract(faceInfo.landmark.getRaw(), bgr, bitmap.getWidth(), bitmap.getHeight(), SDKConstant.IMAGE_FORMAT_BGR);

            //保持人脸特征
            faceFeature = new FaceFeature();
            String uuid = UUID.randomUUID().toString();
            faceFeature.setFeature(extract);
            faceFeature.setFeatureId(uuid);
        }else{
            Toast.makeText(this,"识别多张人脸，请保持只有一张人脸",Toast.LENGTH_SHORT).show();
        }
    }
}
