package com.example.chase.project2;

import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import java.util.StringTokenizer;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;

public class MainPage extends AppCompatActivity {
    HorizontalScrollMenuView menu;
    TextView textTargetUri;
    ImageView targetImage;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Button buttonLoadImage = findViewById(R.id.loadimage);
        Button takeImage = findViewById(R.id.takepicture);
        Button greyscale = findViewById(R.id.greyscale);
        textTargetUri = findViewById(R.id.targeturi);
        targetImage = findViewById(R.id.target_image);
        menu = (HorizontalScrollMenuView) findViewById(R.id.expanded_menu);

        initMenu();


        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }});


        takeImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, 20);
            }});

        greyscale.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                targetImage.setImageBitmap(toGrayscale(targetImage.getDrawingCache()));

                //startActivityForResult(photoCaptureIntent, 20);
            }});



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int requestCode1 = 20;
        if(requestCode1 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            targetImage.setImageBitmap(bitmap);
        }
    }

    private void initMenu() {
        menu.addItem("Transaction", R.drawable.ic_payment);
        menu.addItem("2nd", R.drawable.ic_payment);
        menu.addItem("3rd", R.drawable.ic_payment);
        menu.addItem("4th", R.drawable.ic_payment);
        menu.addItem("4th", R.drawable.ic_payment);
        menu.addItem("4th", R.drawable.ic_payment);


        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(MainPage.this,""+menuItem.getText(), Toast.LENGTH_SHORT).show();
                textTargetUri.setText(menuItem.getText());
            }
        });
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

}