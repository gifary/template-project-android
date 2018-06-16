package com.gifary.commontemplate;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.gifary.commontemplate.configuration.Constants;
import com.gifary.commontemplate.customlayout.DialogCustom;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by gifary on 6/7/18.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = BaseActivity.class.getSimpleName();
    protected Context context;
    protected Activity activity;
    protected Intent intent;
    DialogCustom dialogCustom;
    private static final String IMAGE_DIRECTORY = "/common";
    private int GALLERY = 100, CAMERA = 200;
    protected String realPath;
    protected static final int ZXING_CAMERA_PERMISSION = 9999;
    protected Class<?> mClss;

    public BaseActivity() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showInfoToast(String text){
        Toasty.info(context,text).show();
    }

    public void showDangerToast(String text){
        Toasty.error(context,text).show();
    }

    public void initProgressDialog(String title, String message){
        dialogCustom = new DialogCustom(context,title,message);
    }

    public void showProgress(){
        if(dialogCustom == null){
            dialogCustom = new DialogCustom(context,"Connecting","Loading...");
        }
        dialogCustom.show();
    }

    public void hiddenProgress(){
        if(dialogCustom!=null){
            dialogCustom.dismiss();
        }
    }

    public void initIntent(Class nextClass){
        intent = new Intent(context,nextClass);
    }

    public void nextActivity(boolean statusFinish){
        try {
            if(intent==null){
                if(Constants.DEBUG){
                    Log.e(TAG,"Please call iniIntent class first");
                }
                throw new NullPointerException("initIntent no initialize");
            }
            startActivity(intent);
            if(statusFinish){
                finish();
            }
        }catch (NullPointerException e){
            Log.e(TAG,e.getMessage());
        }

    }

    public void addStringExtra(String key, String content){
        intent.putExtra(key,content);
    }

    public void addIntExtra(String key, int content){
        intent.putExtra(key,content);
    }

    public String getStringExtra(String key){
        return intent.getStringExtra(key);
    }

    public int getIntExtra(String key){
        return intent.getIntExtra(key,0);
    }

    public Object getObjectExtra(String key){
        return intent.getParcelableExtra(key);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void checkRunTimePermission() {
        String[] permissionArrays = new String[]{
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArrays, 11111);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean openDialogOnce = true;
        if (requestCode == 11111) {
            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];

                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    if(Build.VERSION.SDK_INT>=23){
                        boolean showRationale = shouldShowRequestPermissionRationale(permission);
                        if (!showRationale) {
                            //execute when 'never Ask Again' tick and permission dialog not show
                            showDangerToast("Applikasi memerlukan izin");
                        } else {
                            if (openDialogOnce) {
                                alertView();
                            }
                        }
                    }
                }
            }
        }
    }

    private void alertView() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);

        dialog.setTitle("Permission Denied")
                .setInverseBackgroundForced(true)
                //.setIcon(R.drawable.ic_info_black_24dp)
                .setMessage("Without those permission the app is unable to save your profile. App needs to save profile image in your external storage and also need to get profile image from camera or external storage.Are you sure you want to deny this permission?")

                .setNegativeButton("I'M SURE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                    }
                })
                .setPositiveButton("RE-TRY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                        checkRunTimePermission();

                    }
                }).show();
    }

    protected void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }



    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public String saveImage(Bitmap myBitmap) {
        Bitmap newBimpap = scaleDown(myBitmap,500,false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        newBimpap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            if(Constants.DEBUG){
                Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            }

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private String convertImageTo64(String path){
        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY) {
            if(resultCode==RESULT_OK){
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        realPath = saveImage(bitmap);
                        Toasty.info(context ,"Image Saved!").show();
                        //imgUpload.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toasty.info(context ,"Failed!").show();
                    }
                }

            }
        } else if (requestCode == CAMERA) {
            if(resultCode==RESULT_OK){
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                //imgUpload.setImageBitmap(thumbnail);
                realPath = saveImage(thumbnail);
                Toasty.info(context ,"Image Saved!").show();
            }

        }
    }

    protected void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }
}
