package com.example.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

            sendToActivity(context, sender, contents, receivedDate);
        }
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

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent myIntent = new Intent(context, SmsActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //FLAG_ACTIVITY_NEW_TASK: 수신자 화면이 없을 경우 플래그 추가
        //FLAG_ACTIVITY_SINGLE_TOP: SmsAcvitity가 이미 메모리에 생성이 되어있을 경우, 해당 엑티비티를 재생성 하지 않도록 플래그 추가

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate", format.format(receivedDate));

        context.startActivity(myIntent);
    }
}