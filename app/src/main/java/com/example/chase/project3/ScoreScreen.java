package com.example.chase.project3;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreScreen extends AppCompatActivity {
    ArrayList<String> fileNames;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView totalScoreView;

    private ArrayList<String> correctResponses;
    private ArrayList<String> labels;
    private static String accessToken;
    private final String LOG_TAG = "ScoreScreen";
    static final int REQUEST_CODE_PICK_ACCOUNT = 11;
    static final int REQUEST_ACCOUNT_AUTHORIZATION = 12;
    static final int REQUEST_PERMISSIONS = 13;
    static final int REQUEST_GALLERY_IMAGE = 10;
    ImageView drawing2;
    Account mAccount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);
        fileNames = new ArrayList<>();
        labels = new ArrayList<>();
        initializeResponses();

        ImageView drawing1 = findViewById(R.id.drawing1);
        drawing2 = findViewById(R.id.drawing2);
        ImageView drawing3 = findViewById(R.id.drawing3);
        ImageView drawing4 = findViewById(R.id.drawing4);
        ImageView drawing5 = findViewById(R.id.drawing5);
        Button backButton = findViewById(R.id.back_button);
        Button experiment = findViewById(R.id.experiment);
        textView = findViewById(R.id.result1);
        textView1 = findViewById(R.id.result2);
        textView2 = findViewById(R.id.result3);
        textView3 = findViewById(R.id.result4);
        textView4 = findViewById(R.id.result5);
        totalScoreView = findViewById(R.id.final_score);


        accessToken = getIntent().getStringExtra("access_token");

        //unpack locations of all drawings
        String path = getIntent().getStringExtra("path");
        int size = getIntent().getIntExtra( "size",0);
        for (int i = 0; i < size; i++)
        {
            fileNames.add(getIntent().getStringExtra("index"+i));
        }

        //unpack all images to their respective imageViews
        for (int i = 0; i < fileNames.size(); i++)
        {
            Bitmap currentBitmap = loadImageFromStorage(path, fileNames.get(i));
            if (i == 0) {
                drawing1.setImageBitmap(currentBitmap);
                try {
                    callCloudVision(currentBitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (i == 1) {
                drawing2.setImageBitmap(currentBitmap);
                try {
                    callCloudVision(currentBitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (i == 2) {
                drawing3.setImageBitmap(currentBitmap);
                try {
                    callCloudVision(currentBitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (i == 3) {
                drawing4.setImageBitmap(currentBitmap);
                try {
                    callCloudVision(currentBitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (i == 4) {
                drawing5.setImageBitmap(currentBitmap);
                try {
                    callCloudVision(currentBitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        experiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int finalScore = 0;
                for (int i = 0; i < labels.size(); i++) {

                    int score;
                    score = getScore(i);
                    if (i == 0) {
                        textView.setText(score+"");
                        finalScore += score;
                    } else if (i == 1) {
                        textView1.setText(score+"");
                        finalScore += score;
                    } else if (i == 2) {
                        textView2.setText(score+"");
                        finalScore += score;
                    } else if (i == 3) {
                        textView3.setText(score+"");
                        finalScore += score;
                    } else if (i == 4) {
                        textView4.setText(score+"");
                        finalScore += score;
                    }
                }
                totalScoreView.setText("Your score: " + finalScore);

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

    private int getScore(int index)
    {
        String current = labels.get(index);
        String correctResponse = correctResponses.get(index);

        Log.d("GetScore", correctResponse);
        Log.d("string_of_possibilities", current);
        //number of items in the list
        int count = 0;

        String compareString = "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < current.length(); i++) {
            if(current.charAt(i) != ',') {
                sb.append(current.charAt(i));
                //Log.d("Current_string", sb.toString());
            } else {
                count++;
            }
        }
        sb = new StringBuilder();
        Log.d("Items in this list", count+"");
        int points = count;


        for (int i = 0; i < current.length(); i++) {
            if(current.charAt(i) != ',') {
                sb.append(current.charAt(i));
                Log.d("Current_string", sb.toString());
            } else {
                //compare it
                compareString = sb.toString();
                sb = new StringBuilder();
                if (compareString.equals(correctResponse)) {
                    Log.d("String did equal", compareString + " " + correctResponse);
                    Log.d("Going to return", points+"");
                    return points;
                } else {
                    Log.d("String did not equal", compareString + " " + correctResponse);
                    Log.d("Points is now", points+"");
                    points--;
                }
            }
        }

        //i currently have number of items in the list

        return points;
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
                    Toast.makeText(ScoreScreen.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

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
    }


    @SuppressLint("StaticFieldLeak")
    private void callCloudVision(final Bitmap bitmap, final int index) throws IOException {
        //textView.setText(R.string.WaitingMsg);

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
                //textView.setText(result+"\n");
                if (index == 0) {
                    int score;
                    labels.add(result);
                    //score = getScore(index);
                    //textView4.setText(score+"");
                } else if (index == 1) {
                    int score;
                    labels.add(result);
                    //score = getScore(index);
                    //textView3.setText(score+"");
                } else if (index == 2)  {
                    int score;
                    labels.add(result);
                    //score = getScore(index);
                    //textView2.setText(score+"");
                } else if (index == 3) {
                    int score;
                    labels.add(result);
                    //score = getScore(index);
                    //textView1.setText(score+"");
                } else if (index == 4) {
                    int score;
                    labels.add(result);
                    //score = getScore(index);
                    //textView4.setText(score+"");
                }

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


    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                Bitmap bitmap = resizeBitmap(
                        MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
                callCloudVision(bitmap, 3);
                drawing2.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        } else {
            Log.e(LOG_TAG, "Null image was returned.");
        }
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
            new GetTokenTask(ScoreScreen.this, mAccount, SCOPE, REQUEST_ACCOUNT_AUTHORIZATION)
                    .execute();
        }
    }

    public void onTokenReceived(String token) {
        accessToken = token;
        launchImagePicker();
    }

    public void initializeResponses()
    {
        correctResponses = new ArrayList<>();
        /*
        correctResponses.add("tree");
        correctResponses.add("smiley");
        correctResponses.add("apple");
        correctResponses.add("hat");
        correctResponses.add("bird");
        */
        correctResponses.add("bird");
        correctResponses.add("headgear");
        correctResponses.add("apple");
        correctResponses.add("smile");
        correctResponses.add("tree");


    }

    private void launchImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"),
                REQUEST_GALLERY_IMAGE);
    }




}
