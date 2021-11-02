package cn.edu.ujn.EqnoSms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SmsActivity extends AppCompatActivity implements SmsReceiver.Info {

    private EditText phoneNum, message;
    private ListView recordList;
    private List<ChatItem> record;
    private SmsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        // TODO: 2021/10/31 instantiation
        phoneNum = findViewById(R.id.phonenum);
        message = findViewById(R.id.message);
        recordList = findViewById(R.id.record_list);
        record = new ArrayList<>();

        // TODO: 2021/10/21 permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);

        // TODO: 2021/10/21 sender
        findViewById(R.id.send).setOnClickListener(new sendListener());

        // TODO: 2021/10/31 receiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        SmsReceiver smsReceiver = new SmsReceiver();
        smsReceiver.setInstance(this);
        registerReceiver(smsReceiver, intentFilter);

        // TODO: 2021/10/31 log
        Log.d("Eqnoxx", "实例创建成功!");

        // TODO: 2021/10/31 set adapter
        recordList.setAdapter(adapter = new SmsAdapter(this, R.layout.view_item, record));
    }

    // TODO: 2021/11/1 add listview item
    public void addItem(int type, String phone, String msg) {
        record.add(new ChatItem(type, phone, msg));
        adapter.notifyDataSetChanged();
        recordList.smoothScrollToPosition(recordList.getBottom());
    }

    // TODO: 2021/10/31 call back interface
    @Override
    public void getString(String phone, String msg) {
        addItem(ChatItem.RECEIVE, phone, msg);
    }

    // TODO: 2021/10/31 onclick listener
    public class sendListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO: 2021/10/31 get information
            String phone = phoneNum.getText().toString();
            String msg = message.getText().toString();

            // TODO: 2021/10/31 checkout
            if (phone.equals("")) {
                Toast.makeText(SmsActivity.this, "号码不能为空！", Toast.LENGTH_LONG).show();
                return;
            }
            if (msg.equals("")) {
                Toast.makeText(SmsActivity.this, "内容不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            // TODO: 2021/10/31 record sent
            addItem(ChatItem.SEND, phone, msg);

            // TODO: 2021/10/31 send message
            SmsManager.getDefault().sendTextMessage(phone, null, msg, null, null);
            message.setText("");

            // TODO: 2021/10/31 log and toast
            Log.d("Eqnoxx", "短信发送成功！");
            Toast.makeText(SmsActivity.this, "短信发送成功！", Toast.LENGTH_LONG).show();
        }
    }
}