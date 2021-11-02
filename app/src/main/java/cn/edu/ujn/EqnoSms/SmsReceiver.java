package cn.edu.ujn.EqnoSms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    // TODO: 2021/10/31 call back instance
    private Info info;
    interface Info { void getString(String phone, String msg); }
    public void setInstance(Info info) { this.info = info; }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: 2021/10/31 get information
        SmsMessage message = SmsMessage.createFromPdu(
                (byte[]) ((Object[]) intent.getExtras().get("pdus"))[0],
                intent.getStringExtra("format"));
        String phone = message.getOriginatingAddress();
        String msg = message.getMessageBody();

        // TODO: 2021/10/31 call back
        if (info != null) info.getString(phone, msg);

        // TODO: 2021/10/31 log and toast
        Log.d("Eqnoxx", "短信接收成功！");
        // Toast.makeText(context, "短信接收成功！", Toast.LENGTH_LONG).show();
    }
}