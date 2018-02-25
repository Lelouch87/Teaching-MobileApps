package com.example.chase.project3;


import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.Result;

public class MainPage extends AppCompatActivity {

    MediaPlayer menuMusic;
    MediaPlayer drawingMusic;

    private ArrayList<String> fileNames;
    private ArrayList<String> labels;
    private ArrayList<String> correctResponses;

    private final int NUMBER_ROUNDS = 5;
    private static String accessToken;
    private String mCurrentPhotoPath;
    private static final int REQUEST_SCORE_SCREEN = 1;
    private static final int REQUEST_NEW_ROUND = 2;
    private static final int REQUEST_TAKE_PHOTO = 3;
    static final int REQUEST_GALLERY_IMAGE = 10;
    static final int REQUEST_CODE_PICK_ACCOUNT = 11;
    static final int REQUEST_ACCOUNT_AUTHORIZATION = 12;
    static final int REQUEST_PERMISSIONS = 13;
    private static final int REQUEST_INTERMEDIATE_SCORE_SCREEN = 15;
    private final String LOG_TAG = "MainPage";
    private ImageView targetImage;



    private TextView resultTextView;
    Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        labels = new ArrayList<>();
        initializeResponses();
        //fileNames = new ArrayList<>();
        menuMusic = MediaPlayer.create(MainPage.this, R.raw.introduction_in_love_with_a_ghost);
        drawingMusic = MediaPlayer.create(MainPage.this, R.raw.drawing_music);
        menuMusic.start();
        menuMusic.setLooping(true);

        //canvasView = findViewById(R.id.selected_image);

        Button startPlayingButton = findViewById(R.id.start_playing_button);
        Button selectImageButton = (Button) findViewById(R.id.select_image_button);
        //Button takePictureButton = findViewById(R.id.take_picture_button);
        targetImage = (ImageView) findViewById(R.id.selected_image);


        resultTextView = (TextView) findViewById(R.id.result);
        resultTextView.setText("Hello");
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainPage.this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        REQUEST_PERMISSIONS);
            }
        });

        startPlayingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accessToken != null) {
                    fileNames = new ArrayList<>();
                    labels = new ArrayList<>();
                    //int index = 0;
                    menuMusic.pause();
                    drawingMusic.start();
                    for (int i = 0; i < NUMBER_ROUNDS; i++) {
                        Intent getDrawing = new Intent(MainPage.this, DrawingPage.class);
                        getDrawing.putExtra("index", i);
                        Log.d("test", "" + i);
                        //index++;
                        startActivityForResult(getDrawing, REQUEST_NEW_ROUND);
                    }
                }

            }
        });

    }

    private Bitmap loadImageFromStorage(String path, String fileName)
    {
        try {
            //the second parameter used to be "profile.jpg"
            File f=new File(path, fileName);
            //ImageView img=(ImageView)findViewById(R.id.target);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void launchImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"),
                REQUEST_GALLERY_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getAuthToken();
                } else {
                    Toast.makeText(MainPage.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
            if (resultCode == RESULT_OK) {
                String email = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                AccountManager am = AccountManager.get(this);
                Account[] accounts = am.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
                for (Account account : accounts) {
                    if (account.name.equals(email)) {
                        mAccount = account;
                        break;
                    }
                }
                getAuthToken();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "No Account Selected", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == REQUEST_ACCOUNT_AUTHORIZATION) {
            if (resultCode == RESULT_OK) {
                Bundle extra = data.getExtras();
                onTokenReceived(extra.getString("authtoken"));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Authorization Failed", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == REQUEST_NEW_ROUND)
        {
            //Bitmap mBitmap;

            //if the path is not yet set aka if we have not processed any drawings yet,
            //then set the path equal to the directory path
            mCurrentPhotoPath = data.getStringExtra("path");


            //Getting each individual drawing fileName and adding it to the array
            String fileName = data.getStringExtra("fileName");
            fileNames.add(fileName);


            //Log.d(LOG_TAG, mCurrentPhotoPath);
            //resultTextView.setText(message);
            //mBitmap = loadImageFromStorage(message, fileName);
            //Log.d(LOG_TAG, fileName);
            //currentDrawings.add(mBitmap);
            /*
            try {
                callCloudVision(mBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/


            //targetImage.setImageBitmap(mBitmap);


            drawingMusic.pause();
            //Log.d("Test", "im back");
            menuMusic.start();

            //when all the drawings have been processed we can go to the score screen
            if (fileNames.size() == NUMBER_ROUNDS) {
                //scoreScreen();


                Intent getScoreScreen = new Intent(MainPage.this, ScoreScreen.class);
                //pack file info into intent
                //packages the path of all the pictures
                getScoreScreen.putExtra("path", mCurrentPhotoPath);
                getScoreScreen.putExtra("access_token", accessToken);
                //packages the fileNames of all pictures in the list
                for (int i = 0; i < fileNames.size(); i++) {
                    getScoreScreen.putExtra("index" + i, fileNames.get(i));
                }
                //Gives us how many times to loop aka size of fileNames
                getScoreScreen.putExtra("size", fileNames.size());

                startActivityForResult(getScoreScreen, REQUEST_SCORE_SCREEN);

            }
        }

        if (requestCode == REQUEST_SCORE_SCREEN) {
            Log.d("Test", "im back");

        }

        if (requestCode == REQUEST_INTERMEDIATE_SCORE_SCREEN)
        {
            //launch final screen
        }
    }

    public void scoreScreen()
    {
        for (int i = 0; i < NUMBER_ROUNDS; i++) {
            Intent getScoreScreen = new Intent(MainPage.this, IntermediateScoreScreen.class);
            getScoreScreen.putExtra("access_token", accessToken);
            //pack file info into intent
            //packages the path of all the pictures
            getScoreScreen.putExtra("path", mCurrentPhotoPath);
            //packages the fileNames of all pictures in the list
            getScoreScreen.putExtra("fileName" + i, fileNames.get(i));
            //Gives us how many times to loop aka size of fileNames
            getScoreScreen.putExtra("currentIndex", i);
            Log.d("Packing", mCurrentPhotoPath);
            Log.d("Packing", fileNames.get(i));
            Log.d("Packing", i+"");


            startActivityForResult(getScoreScreen, REQUEST_INTERMEDIATE_SCORE_SCREEN);
        }

    }
    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                Bitmap bitmap = resizeBitmap(
                        MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
                callCloudVision(bitmap);
                targetImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        } else {
            Log.e(LOG_TAG, "Null image was returned.");
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void callCloudVision(final Bitmap bitmap) throws IOException {
        resultTextView.setText(R.string.WaitingMsg);

        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    Vision.Builder builder = new Vision.Builder
                            (httpTransport, jsonFactory, credential);
                    Vision vision = builder.build();

                    List<Feature> featureList = new ArrayList<>();
                    Feature labelDetection = new Feature();
                    labelDetection.setType("LABEL_DETECTION");
                    labelDetection.setMaxResults(10);
                    featureList.add(labelDetection);

                    List<AnnotateImageRequest> imageList = new ArrayList<>();
                    AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();
                    Image base64EncodedImage = getBase64EncodedJpeg(bitmap);
                    annotateImageRequest.setImage(base64EncodedImage);
                    annotateImageRequest.setFeatures(featureList);
                    imageList.add(annotateImageRequest);

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(imageList);

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision API containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    Log.d(LOG_TAG, "sending request");

                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToString(response);

                } catch (GoogleJsonResponseException e) {
                    Log.e(LOG_TAG, "Request failed: " + e.getContent());
                } catch (IOException e) {
                    Log.d(LOG_TAG, "Request failed: " + e.getMessage());
                }
                return "Cloud Vision API request failed.";
            }
            @Override
            protected void onPostExecute(String result) {
                resultTextView.setText(result);
                labels.add(result);
            }
        }.execute();



    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        //StringBuilder message = new StringBuilder("Results:\n\n");
        //message.append("Labels:\n");
        StringBuilder message = new StringBuilder("");
        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                message.append(label.getDescription());
                message.append(",");
            }
        } else {
            message.append("nothing\n");
        }
        return message.toString();
    }

    private int getScore(int index)
    {
        String current = labels.get(index);
        String compareString = "";
        StringBuilder sb = new StringBuilder();
        int points = 10;
        int i = 0;
        while(current != null) {
            if(current.charAt(i) != ',') {
                sb.append(current.charAt(i));
            } else {
                //compare it
                compareString = sb.toString();
                sb = new StringBuilder();
                if (compareString.equals(correctResponses.get(index-1))) {
                    return points;
                } else {
                    points--;
                }
            }
            i++;
        }

        return points;
    }

    public void initializeResponses()
    {
        correctResponses = new ArrayList<>();
        correctResponses.add("tree");
        correctResponses.add("smiley");
        correctResponses.add("apple");
        correctResponses.add("hat");
        correctResponses.add("bird");

    }

    public Bitmap resizeBitmap(Bitmap bitmap) {

        int maxDimension = 1024;
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    public Image getBase64EncodedJpeg(Bitmap bitmap) {
        Image image = new Image();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        image.encodeContent(imageBytes);
        return image;
    }

    private void pickUserAccount() {
        String[] accountTypes = new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
    }

    private void getAuthToken() {
        String SCOPE = "oauth2:https://www.googleapis.com/auth/cloud-platform";
        if (mAccount == null) {
            pickUserAccount();
        } else {
            new GetTokenTask(MainPage.this, mAccount, SCOPE, REQUEST_ACCOUNT_AUTHORIZATION)
                    .execute();
        }
    }

    public void onTokenReceived(String token) {
        accessToken = token;
        //launchImagePicker();
    }
}