package sg.edu.ro.c346.id16046530.c347_l09_gettingmylocation;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class CheckRecords extends AppCompatActivity {
    Button btnRefresh;
    TextView tvRecords;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_records);

        btnRefresh = findViewById(R.id.btnRefresh);
        tvRecords = findViewById(R.id.tvRecords);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();

        String folder = getFilesDir().getAbsolutePath() + "/Folder";
        File file = new File(folder, "P09LocationData.txt");
        if (file.exists() == true) {
            int numberOfRecords = 0;
            try {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);

                String line = br.readLine();
                while (line != null) {
                    al.add(line);
                    line = br.readLine();
                    numberOfRecords+=1;
                }
                aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);
                tvRecords.setText("Number of records: "+numberOfRecords);
                br.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    al.clear();
                    File file = new File(folder, "P09LocationData.txt");
                    if (file.exists() == true) {
                        int numberOfRecords = 0;
                        try {
                            FileReader reader = new FileReader(file);
                            BufferedReader br = new BufferedReader(reader);

                            String line = br.readLine();
                            while (line != null) {
                                al.add(line);
                                line = br.readLine();
                                numberOfRecords += 1;
                            }
                            aa = new ArrayAdapter(CheckRecords.this, android.R.layout.simple_list_item_1, al);
                            lv.setAdapter(aa);
                            tvRecords.setText("Number of records: " + numberOfRecords);
                            br.close();
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }
}