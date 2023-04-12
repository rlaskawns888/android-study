package com.example.samplesocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    TextView textView;
    TextView textView2;

    Handler handler = new Handler();

    static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    //클라이언트 쪽 로그를 화면에 있는 텍스트뷰에 출력하기 위해 핸들러 사용
    public void printClientLog(final String data) {
        Log.d(TAG, data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }

    //서버 쪽 로그를 화면에 있는 텍스트뷰에 출력하기 위해 핸들러 사용
    public void printServerLog(final String data) {
        Log.d(TAG, data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data + "\n");
            }
        });
    }

    public void send(String data) {
        try {
          int portNumber = 5001;
            Socket socket = new Socket("localhost", portNumber);
            printClientLog("소켓 연결 완료");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터 전송함");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로부터 받음: " + inputStream.readObject());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            int portNumber = 5001;

            ServerSocket server = new ServerSocket(portNumber);
            printServerLog("서버 시작함 : " + portNumber);

            while(true) {
                Socket socket = server.accept();
                InetAddress clientHost = socket.getLocalAddress();
                int clientPort = socket.getPort();
                printServerLog("클라이언트 연결됨: " + clientHost + " : " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터 받음: " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(obj + " from server.");
                outputStream.flush();
                printServerLog("데이터 보냄");

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}