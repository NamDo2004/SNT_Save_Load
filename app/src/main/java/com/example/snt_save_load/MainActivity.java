package com.example.snt_save_load;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnSave, btnLoad, btnTinh;
    private EditText edt_a, edt_b;
    private TextView txtKQ;
    private StringBuilder SNT = new StringBuilder();
    private void findviews(){
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnTinh = findViewById(R.id.btnTinh);
        edt_a = findViewById(R.id.edt_a);
        edt_b = findViewById(R.id.edt_b);
        txtKQ = findViewById(R.id.txtKQ);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findviews();

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edt_a.getText().toString());
                int b = Integer.parseInt(edt_b.getText().toString());
                int d = 0;

                int Max = Math.max(a,b);
                int Min = Math.min(a,b);

                SNT.setLength(0); //Xoa cac so nguyen to da luu truoc do

                for(int i=Min; i<=Max; i++){
                    if(Is_Prime(i)){
                        SNT.append(i).append(" ");
                        d++;
                    }
                }
                txtKQ.setText(d+"");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_save", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("so_luong_SNT", Integer.parseInt(txtKQ.getText().toString()));
                editor.putString("cac_SNT", SNT.toString());

                editor.apply();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_save", MODE_PRIVATE);

                int so_luong_SNT = sharedPreferences.getInt("so_luong_SNT", 0);
                String cac_SNT = sharedPreferences.getString("cac_SNT", "");

                Toast.makeText(MainActivity.this, "So luong SNT: " + so_luong_SNT + "\n" + cac_SNT, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean Is_Prime(int n){
        if(n < 2) return false;
        for(int i = 2; i<n; i++){
            if(n%i == 0) return false;
        }
        return true;
    }
}