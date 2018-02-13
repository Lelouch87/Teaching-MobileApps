package com.example.chase.project2;

import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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
    private Bitmap bmp;
    private Bitmap operation;
    private Bitmap newPicture;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Button buttonLoadImage = findViewById(R.id.loadimage);
        Button takeImage = findViewById(R.id.take_picture);
        Button restoreButton = findViewById(R.id.restore);
        targetImage = findViewById(R.id.target_image);
        menu = findViewById(R.id.expanded_menu);


        int w = 500, h = 500;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap default_bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap


        targetImage.setImageBitmap(default_bmp);








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

        restoreButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                targetImage.setImageBitmap(newPicture);
            }});



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int requestCode1 = 20;
        if(requestCode1 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            targetImage.setImageBitmap(bitmap);
            newPicture = bitmap;

        }
    }

    private void initMenu() {
        menu.addItem("Blue", R.drawable.ic_payment);
        menu.addItem("Green", R.drawable.ic_payment);
        menu.addItem("Dark", R.drawable.ic_payment);
        menu.addItem("Bright", R.drawable.ic_payment);
        menu.addItem("Gama", R.drawable.ic_payment);
        menu.addItem("Gray", R.drawable.ic_payment);


        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(MainPage.this,""+menuItem.getText(), Toast.LENGTH_SHORT).show();
                //textTargetUri.setText(menuItem.getText());
                String menuText = menuItem.getText();
                switch (menuText) {
                    case "Blue":
                        blue(targetImage);
                        break;
                    case "Green":
                        green(targetImage);
                        break;
                    case "Dark":
                        dark(targetImage);
                        break;
                    case "Bright":
                        bright(targetImage);
                        break;
                    case "Gama":
                        gama(targetImage);
                        break;
                    case "Gray":
                        gray(targetImage);
                        break;
                }
            }
        });


    }

    public void gray(View view) {
        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();

        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());
        double red = 0.33;
        double green = 0.59;
        double blue = 0.11;

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);

                r = (int) red * r;
                g = (int) green * g;
                b = (int) blue * b;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }

    public void blue(View view){

        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();

        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  0;
                g =  0;
                b =  b+150;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }

    public void green(View view){

        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();

        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for(int i=0; i < bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  0;
                g =  g+150;
                b =  0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }

    public void gama(View view) {


        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();

        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  r + 150;
                g =  0;
                b =  0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }

    public void dark(View view){

        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();

        operation= Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  r - 50;
                g =  g - 50;
                b =  b - 50;
                alpha = alpha -50;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }



    public void bright(View view){

        BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        bmp = abmp.getBitmap();


        operation= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = 100  +  r;
                g = 100  + g;
                b = 100  + b;
                alpha = 100 + alpha;
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        targetImage.setImageBitmap(operation);
    }




}