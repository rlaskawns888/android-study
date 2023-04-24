package com.example.samplehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    DatabaseHelper dbHelper;
    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = editText2.getText().toString();
                createTable(tableName);

                insertRecord();
            }
        });
    }

    private void createDatabase(String name) {
        println("createDatabase()");

        dbHelper = new DatabaseHelper(this);
//        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
        database = dbHelper.getWritableDatabase();

        println("데이터 베이스 생성함: " + name);
    }

    private void createTable(String name) {
        println("createTable Call.");

        if(null == database) {
            println("database is null");

            return;
        }

        database.execSQL("CREATE TABLE IF NOT EXISTS " + name + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT, "
                + "age INTEGER, "
                + "mobile TEXT)");

        println("테이블 생성함: " + name);
    }

    private void insertRecord() {
        println("insertRecord 호출됨.");

        if(null == database) {
            println("데이터베이스를 먼저 생성하세요.");

            return;
        }

        if(null == tableName) {
            println("테이블을 먼저 생성하세요.");

            return;
        }

        database.execSQL("INSERT INTO " + tableName
                + "(name, age, mobile)"
                + " VALUES "
                + "( 'John', 20, '010-1234-1234' )");

        println("레코드 추가 완료.");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}