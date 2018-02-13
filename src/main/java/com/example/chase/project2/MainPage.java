package com.example.chase.project2;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    ImageView targetImage;
    private String mCurrentPhotoPath;
    private Bitmap bmp;
    private Bitmap operation;
    private Bitmap newPicture;
    private static final int LOAD_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int REQUEST_TAKE_PHOTO = 3;
    private final int[] mColors =
            {Color.BLUE, Color.GREEN, Color.RED, Color.LTGRAY, Color.MAGENTA, Color.CYAN,
                    Color.YELLOW, Color.WHITE};
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
               Intent intent = new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent, "Select picture"), LOAD_PICTURE);
            }});


        takeImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               dispatchTakePictureIntent();
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
        if (requestCode == LOAD_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap temp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                targetImage.setImageBitmap(temp);
                newPicture = temp;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = targetImage.getWidth();
        int targetH = targetImage.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        bitmap = RotateBitmap(bitmap, 270);
        newPicture = bitmap;
        targetImage.setImageBitmap(bitmap);
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private void initMenu() {
        menu.addItem("Blue", R.drawable.ic_check);
        menu.addItem("Green", R.drawable.ic_check);
        menu.addItem("Red", R.drawable.ic_check);
        menu.addItem("Yellow", R.drawable.ic_check);
        menu.addItem("Magenta", R.drawable.ic_check);
        menu.addItem("Gray", R.drawable.ic_check);

        menu.addItem("Dark", R.drawable.ic_check);
        menu.addItem("Bright", R.drawable.ic_check);
        menu.addItem("Gama", R.drawable.ic_check);
        menu.addItem("Deep Blue", R.drawable.ic_check);
        menu.addItem("Deep Green", R.drawable.ic_check);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(MainPage.this,""+menuItem.getText(), Toast.LENGTH_SHORT).show();
                //textTargetUri.setText(menuItem.getText());
                String menuText = menuItem.getText();
                switch (menuText) {
                    case "Blue":
                        changeColorBlue();
                        break;
                    case "Green":
                        changeColorGreen();
                        break;
                    case "Red":
                        changeColorRed();
                        break;
                    case "Yellow":
                        changeColorYellow();
                        break;
                    case "Magenta":
                        changeColorMagenta();
                        break;
                    case "Gray":
                        toGrayscale(newPicture);
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
                    case "Deep Blue":
                        blue(targetImage);
                        break;
                    case "Deep Green":
                        green(targetImage);
                        break;
                }
            }
        });


    }

    private void changeColorBlue(){
        Bitmap sourceBitmap = newPicture;
        changeBitmapColor(sourceBitmap, targetImage, Color.BLUE);
    }

    private void changeColorGreen(){
        Bitmap sourceBitmap = newPicture;
        changeBitmapColor(sourceBitmap, targetImage, Color.GREEN);
    }

    private void changeColorRed(){
        Bitmap sourceBitmap = newPicture;
        changeBitmapColor(sourceBitmap, targetImage, Color.RED);
    }

    private void changeColorYellow(){
        Bitmap sourceBitmap = newPicture;
        changeBitmapColor(sourceBitmap, targetImage, Color.YELLOW);
    }
    private void changeColorMagenta(){
        Bitmap sourceBitmap = newPicture;
        changeBitmapColor(sourceBitmap, targetImage, Color.MAGENTA);
    }


    private void changeBitmapColor(Bitmap sourceBitmap, ImageView image, int color) {

        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);
        image.setImageBitmap(resultBitmap);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
    }


    public void toGrayscale(Bitmap bmpOriginal)
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
        targetImage.setImageBitmap(bmpGrayscale);
    }

    public void blue(View view){

        //BitmapDrawable abmp = (BitmapDrawable) targetImage.getDrawable();
        //bmp = abmp.getBitmap();
        bmp = newPicture;
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                Color.red(p);
                int r;
                Color.green(p);
                int g;
                int b = Color.blue(p);
                Color.alpha(p);

                r =  0;
                g =  0;
                b =  b+150;
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
                Color.red(p);
                int r;
                int g = Color.green(p);
                Color.blue(p);
                int b;
                Color.alpha(p);

                r =  0;
                g =  g+150;
                b =  0;
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
                Color.green(p);
                int g;
                Color.blue(p);
                int b;
                Color.alpha(p);

                r =  r + 150;
                g =  0;
                b =  0;
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