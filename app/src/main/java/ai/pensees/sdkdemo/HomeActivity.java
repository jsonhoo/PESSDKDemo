package ai.pensees.sdkdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.het.tencentliteavrtc.model.TRTCCalling;
import com.het.tencentliteavrtc.model.TRTCCallingDelegate;
import com.het.tencentliteavrtc.model.impl.TRTCCallingImpl;
import com.het.tencentliteavrtc.ui.TRTCCallingEntranceActivity;
import com.het.tencentliteavrtc.ui.audiocall.TRTCAudioCallActivity;
import com.het.tencentliteavrtc.ui.videocall.TRTCVideoCallActivity;
import com.het.tencentliteavrtc.usr.GenerateTestUserSig;
import com.het.tencentliteavrtc.usr.ProfileManager;
import com.het.tencentliteavrtc.usr.UserModel;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.PictureFormat;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import ai.pensees.sdkdemo.face.FaceHelper;
import ai.pensees.sdkdemo.face.IflytekHelper;
import ai.pensees.sdkdemo.layout.DialLayout;
import ai.pensees.sdkdemo.utils.DensityUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "liu.js";
    private static final int TYPE_NONE = -1;
    private static final int TYPE_PASSWORD_OPEN = 1;
    private static final int TYPE_PHONE_CALL = 2;
    private static final int TYPE_HOUSE_CALL = 3;
    private static final int TYPE_PROPERTY_CALL = 4;

    private DialLayout mPasswordLayout;
    private DialLayout mPhoneCallLayout;
    private DialLayout mHouseCallLayout;
    private DialLayout mPropertyLayout;//物业
    private int mCallType = TYPE_NONE;
    private CameraView mCameraView;
    private View mMenuLayout;
    private ImageView mArrowView;

    private TRTCCalling mTRTCCalling;

    private TRTCCallingDelegate mTRTCCallingDelegate = new TRTCCallingDelegate() {
        // <editor-fold  desc="视频监听代码">
        @Override
        public void onError(int code, String msg) {
        }

        @Override
        public void onInvited(String sponsor, final List<String> userIdList, boolean isFromGroup, final int callType) {
            //1. 收到邀请，先到服务器查询
            ProfileManager.getInstance().getUserInfoByUserId(sponsor, new ProfileManager.GetUserInfoCallback() {
                @Override
                public void onSuccess(final UserModel model) {
                    if (callType == TRTCCalling.TYPE_VIDEO_CALL) {
                        TRTCVideoCallActivity.UserInfo selfInfo = new TRTCVideoCallActivity.UserInfo();
                        selfInfo.userId = ProfileManager.getInstance().getUserModel().userId;
                        selfInfo.userAvatar = ProfileManager.getInstance().getUserModel().userAvatar;
                        selfInfo.userName = ProfileManager.getInstance().getUserModel().userName;
                        TRTCVideoCallActivity.UserInfo callUserInfo = new TRTCVideoCallActivity.UserInfo();
                        callUserInfo.userId = model.userId;
                        callUserInfo.userAvatar = model.userAvatar;
                        callUserInfo.userName = model.userName;
                        TRTCVideoCallActivity.startBeingCall(HomeActivity.this, selfInfo, callUserInfo, null);
                    } else if (callType == TRTCCalling.TYPE_AUDIO_CALL) {
                        TRTCAudioCallActivity.UserInfo selfInfo = new TRTCAudioCallActivity.UserInfo();
                        selfInfo.userId = ProfileManager.getInstance().getUserModel().userId;
                        selfInfo.userAvatar = ProfileManager.getInstance().getUserModel().userAvatar;
                        selfInfo.userName = ProfileManager.getInstance().getUserModel().userName;
                        TRTCAudioCallActivity.UserInfo callUserInfo = new TRTCAudioCallActivity.UserInfo();
                        callUserInfo.userId = model.userId;
                        callUserInfo.userAvatar = model.userAvatar;
                        callUserInfo.userName = model.userName;
                        TRTCAudioCallActivity.startBeingCall(HomeActivity.this, selfInfo, callUserInfo, null);
                    }
                }

                @Override
                public void onFailed(int code, String msg) {

                }
            });
        }

        @Override
        public void onGroupCallInviteeListUpdate(List<String> userIdList) {

        }

        @Override
        public void onUserEnter(String userId) {

        }

        @Override
        public void onUserLeave(String userId) {

        }

        @Override
        public void onReject(String userId) {

        }

        @Override
        public void onNoResp(String userId) {

        }

        @Override
        public void onLineBusy(String userId) {

        }

        @Override
        public void onCallingCancel() {

        }

        @Override
        public void onCallingTimeout() {

        }

        @Override
        public void onCallEnd() {

        }

        @Override
        public void onUserVideoAvailable(String userId, boolean isVideoAvailable) {

        }

        @Override
        public void onUserAudioAvailable(String userId, boolean isVideoAvailable) {

        }

        @Override
        public void onUserVoiceVolume(Map<String, Integer> volumeMap) {

        }
        // </editor-fold  desc="视频监听代码">
    };

    private void initTRTCCallingData() {

        ProfileManager.getInstance().login("123", "", new ProfileManager.ActionCallback() {
            @Override
            public void onSuccess() {
                //登录成功
                mTRTCCalling = TRTCCallingImpl.sharedInstance(HomeActivity.this);
                mTRTCCalling.addDelegate(mTRTCCallingDelegate);
                int appid = GenerateTestUserSig.SDKAPPID;
                String userId = ProfileManager.getInstance().getUserModel().userId;
                String userSig = ProfileManager.getInstance().getUserModel().userSig;
                mTRTCCalling.login(appid, userId, userSig, new TRTCCalling.ActionCallBack() {
                    @Override
                    public void onError(int code, String msg) {
                        ToastUtils.showShort("腾讯IM登录失败");
                    }

                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(HomeActivity.this, TRTCCallingEntranceActivity.class);
                        intent.putExtra("TITLE", "视频通话");
                        intent.putExtra("TYPE", TRTCCalling.TYPE_VIDEO_CALL);
                        HomeActivity.this.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailed(int code, String msg) {

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTRTCCalling != null) {
            mTRTCCalling.removeDelegate(mTRTCCallingDelegate);
        }
        PesHelper.INSTANCE.releaseSDK();
        IflytekHelper.INSTANCE.releaseSDK();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "HomeActivity onCreate--");
        PesHelper.INSTANCE.init(this);
        IflytekHelper.INSTANCE.init(this);

        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mCameraView = findViewById(R.id.camera_view);
        mCameraView.setPlaySounds(false);
        mCameraView.setLifecycleOwner(this);
        mCameraView.setPictureFormat(PictureFormat.JPEG);
        mCameraView.setFrameProcessingFormat(ImageFormat.NV21);
        FaceHelper.INSTANCE.init(mCameraView);

        findViewById(R.id.cover_view).setOnClickListener(v -> {
            if (isMenuShow()) {
                if (mCallType != TYPE_NONE) {
                    hideAllPanelLayout();
                } else {
                    slideDown();
                }
            }
        });

        mMenuLayout = findViewById(R.id.menu_layout);
        mMenuLayout.setTranslationY(getDownTranslationY());
        mArrowView = findViewById(R.id.arrow);
        mArrowView.setOnClickListener(v -> {
            if (isMenuShow()) {//展示状态
                slideDown();
            } else {
                slideUp();
            }
        });

        mPasswordLayout = findViewById(R.id.dial_password_open);
        mPasswordLayout.setInputHint("输入住户密码，按“确认”键开门");
        mPasswordLayout.setMListener(new DialLayout.DialListener() {
            @Override
            public void onConfirm(@NotNull String inputText) {
                callPhone(inputText);
            }

            @Override
            public void onClose() {
                super.onClose();
                mCallType = TYPE_NONE;
            }
        });

        mPhoneCallLayout = findViewById(R.id.dial_phone_call);
        mPhoneCallLayout.setInputHint("输入手机号码，按“确认”键呼叫");
        mPhoneCallLayout.setMListener(new DialLayout.DialListener() {
            @Override
            public void onConfirm(@NotNull String inputText) {
                callPhone(inputText);
            }

            @Override
            public void onClose() {
                super.onClose();
                mCallType = TYPE_NONE;
            }
        });

        mHouseCallLayout = findViewById(R.id.dial_house_call);
        mHouseCallLayout.setInputHint("输入住户手机号码，按“确认”键呼叫");
        mHouseCallLayout.setMListener(new DialLayout.DialListener() {
            @Override
            public void onConfirm(@NotNull String inputText) {
                callPhone(inputText);
            }

            @Override
            public void onClose() {
                super.onClose();
                mCallType = TYPE_NONE;
            }
        });

        mPropertyLayout = findViewById(R.id.dial_property_call);
        mPropertyLayout.setInputHint("输入物业号码，按“确认”键呼叫");
        mPropertyLayout.setMListener(new DialLayout.DialListener() {
            @Override
            public void onConfirm(@NotNull String inputText) {
                callPhone(inputText);
            }

            @Override
            public void onClose() {
                super.onClose();
                mCallType = TYPE_NONE;
            }
        });

        findViewById(R.id.password_open).setOnClickListener(this);
        findViewById(R.id.phone_call).setOnClickListener(this);
        findViewById(R.id.house_call).setOnClickListener(this);
        findViewById(R.id.property_call).setOnClickListener(this);
        findViewById(R.id.settings).setOnClickListener(this);
    }

    private boolean isMenuShow() {
        return mMenuLayout.getTranslationY() == 0;
    }

    private int getDownTranslationY() {
        return DensityUtils.dip2px(this, 252);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (mCallType != TYPE_NONE) {//防止快速点击
            return;
        }
        switch (v.getId()) {
            case R.id.password_open:
                FaceHelper.INSTANCE.extractFeature();
//                mCallType = TYPE_PASSWORD_OPEN;
//                mPasswordLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.phone_call:
                mCallType = TYPE_PHONE_CALL;
                mPhoneCallLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.house_call:
                mCallType = TYPE_HOUSE_CALL;
                mHouseCallLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.property_call:
                mCallType = TYPE_PROPERTY_CALL;
                mPropertyLayout.setVisibility(View.VISIBLE);
                initTRTCCallingData();
                break;
            case R.id.settings:
                FaceHelper.INSTANCE.takePictureAndCompare();
                break;
        }
    }

    private void hideAllPanelLayout() {
        mPasswordLayout.close();
        mPhoneCallLayout.close();
        mHouseCallLayout.close();
        mPropertyLayout.close();
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private void slideUp() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mMenuLayout, View.TRANSLATION_Y, 0);
        objectAnimator.setDuration(500);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mMenuLayout.setTranslationY(0);
                mArrowView.setImageResource(R.mipmap.arrow_down);
            }
        });
        objectAnimator.start();
    }

    private void slideDown() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mMenuLayout, View.TRANSLATION_Y, getDownTranslationY());
        objectAnimator.setDuration(500);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mMenuLayout.setTranslationY(getDownTranslationY());
                mArrowView.setImageResource(R.mipmap.arrow_up);
            }
        });
        objectAnimator.start();
    }
}
