package com.nkdroid.qrcode.scanner;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.nkdroid.qrcode.scanner.barcode.BarcodeCaptureActivity;

public class MainActivity extends AppCompatActivity {


    private static final int BARCODE_READER_REQUEST_CODE = 1;
    private TextView scanResult;
    private Button scanButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanResult = (TextView) findViewById(R.id.result_textview);

        scanButton = (Button) findViewById(R.id.scan_barcode_button);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;
                    scanResult.setText(barcode.displayValue);
                } else {
                    scanResult.setText("No Result Found");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
