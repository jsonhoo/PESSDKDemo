package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;

import org.greenrobot.eventbus.EventBus;

import ai.pensees.sdkdemo.model.ServerConfig;
import ai.pensees.sdkdemo.msg.MessageWrap;
import ai.pensees.sdkdemo.msg.MqttConstant;
import ai.pensees.sdkdemo.utils.ACache;
import ai.pensees.sdkdemo.widget.ClearEditText;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServerConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText input_server_address;
    private ClearEditText input_server_port;
    private ClearEditText input_protocol;
    private ClearEditText input_client_id;
    private ClearEditText input_client_name;
    private ClearEditText input_client_key;
    private Button btn_save;
    private ACache mCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_config);

        mCache = ACache.get(this);

        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("服务配置");

        input_server_address = findViewById(R.id.input_server_address);
        input_server_port = findViewById(R.id.input_server_port);
        input_protocol = findViewById(R.id.input_protocol);
        input_client_id = findViewById(R.id.input_tv_client_id);
        input_client_name = findViewById(R.id.input_client_name);
        input_client_key = findViewById(R.id.input_client_key);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        readCache();
    }

    private void readCache(){

        ServerConfig serverConfig = (ServerConfig)mCache.getAsObject("ServerConfig");
        if(serverConfig == null){
            btn_save.setVisibility(View.VISIBLE);

            input_server_address.setEnabled(true);
            input_server_port.setEnabled(true);
            input_protocol.setEnabled(true);
            input_client_id.setEnabled(true);
            input_client_name.setEnabled(true);
            input_client_key.setEnabled(true);

        }else {
            btn_save.setVisibility(View.GONE);

            input_server_address.setEnabled(false);
            input_server_port.setEnabled(false);
            input_protocol.setEnabled(false);
            input_client_id.setEnabled(false);
            input_client_name.setEnabled(false);
            input_client_key.setEnabled(false);

            input_server_address.setText(serverConfig.getServerAddress());
            input_server_port.setText(""+serverConfig.getServerPort());
            input_protocol.setText(serverConfig.getProtocol());
            input_client_id.setText(serverConfig.getClientId());
            input_client_name.setText(serverConfig.getClientName());
            input_client_key.setText(serverConfig.getClientKey());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_save){
            saveServerConfig();
            this.finish();
        }
    }

    private void saveServerConfig(){

        String serverAddress = input_server_address.getText().toString();
        String serverPort = input_server_port.getText().toString();
        String protocol = input_protocol.getText().toString();
        String clientId = input_client_id.getText().toString();
        String clientName = input_client_name.getText().toString();
        String clientKey = input_client_key.getText().toString();

        if(StringUtils.isEmpty(serverAddress)){
            Toast.makeText(this,"服务器地址不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(serverPort)){
            Toast.makeText(this,"服务器端口不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(protocol)){
            Toast.makeText(this,"通信协议不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(clientId)){
            Toast.makeText(this,"客户端ID不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(clientName)){
            Toast.makeText(this,"客户端名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(clientKey)){
            Toast.makeText(this,"秘钥不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setServerAddress(serverAddress);
        serverConfig.setServerPort(serverPort);
        serverConfig.setProtocol(protocol);
        serverConfig.setClientId(clientId);
        serverConfig.setClientName(clientName);
        serverConfig.setClientKey(clientKey);

        mCache.put("ServerConfig",serverConfig);

        EventBus.getDefault().post(new MessageWrap(MqttConstant.START_MQTT_SERVICE,""));
    }
}
