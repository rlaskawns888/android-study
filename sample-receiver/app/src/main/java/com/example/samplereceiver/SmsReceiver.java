package com.example.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨.");

        Bundle bundle = intent.getExtras();
        //인텐트에서 Bundle객체 가져오기
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null
                && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender: " + sender);
            //발신자 번호

            String contents = messages[0].getMessageBody();
            Log.i(TAG, "SMS contents: " + contents);
            //문자 내용

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "SMS received date: " + receivedDate.toString());
            //시간
        }

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Build.VERSION_CODES.M : 마시멜로우

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];
        //Bundle 객체에 들어가 있는 부가 데이터 중에서 pdus 가져오기

        int smsCount = objs.length;
        for(int i=0; i<smsCount; i++) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
                //단말기 OS버전에 따라 다른 방식으로 메서드 호출하기
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return messages;
    }
}