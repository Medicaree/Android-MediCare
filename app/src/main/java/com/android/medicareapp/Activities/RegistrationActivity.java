package com.android.medicareapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.medicareapp.Firebase.User.UserModel;
import com.android.medicareapp.R;
import com.android.medicareapp.Utils.Constants;
import com.android.medicareapp.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    ActivityRegistrationBinding binding;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    String cameraPermission[];
    String storagePermission[];
    Uri imageuri;
    String as;
    String[] types = { "Pharmacy", "Hospital", "Blood Banks", "Testing Labs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        // set Views for register as Customer and Business
        as = getIntent().getExtras().get(Constants.as).toString(); // receive data from previous activity
        if(as.equalsIgnoreCase(Constants.customer)) setForCustomer();
        else setForBusiness();

        binding.name.field.setHint("Name");
        binding.address.field.setHint("Address");
        binding.address.field.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.ic_map_loc), null);
        binding.contact.field.setHint("Contact Number");
        binding.email.field.setHint("e-mail");
        binding.password.field.setHint("Password");
        binding.liecense.field.setHint("License Image");

        binding.registerBtn.setOnClickListener(this);
        binding.uploadImageBtn.setOnClickListener(this);
    }
    void setForCustomer() { // for setting customer reg. view
        binding.regImage.setImageDrawable(getResources().getDrawable(R.drawable.injured_user));
    }
    void setForBusiness() { // for setting Business reg. view
        binding.regImage.setImageDrawable(getResources().getDrawable(R.drawable.business));
        binding.liecense.getRoot().setVisibility(View.VISIBLE);
        binding.liecense.field.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.ic_cam), null);
        binding.timeRadioGroup.setVisibility(View.VISIBLE);
        binding.businessType.setVisibility(View.VISIBLE);
        binding.businessType.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.businessType.setAdapter(aa);
    }
    private FirebaseAuth mAuth;
    void signUp(UserModel userModel){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(userModel.getEmail(), userModel.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userModel.setUid(mAuth.getUid());
                            saveUserToFirebase(userModel, userModel.isUser());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registerBtn:
                if(binding.terms.isChecked()) {
                    String type = getBusinessType()!=null?getBusinessType():"";
                    String name = binding.name.field.getText().toString();
                    String address = binding.address.field.getText().toString();
                    String contact = binding.contact.field.getText().toString();
                    String email = binding.email.field.getText().toString();
                    String password = binding.password.field.getText().toString();
                    if (as.equalsIgnoreCase(Constants.customer)) {
                        //validations
                        if (!name.isEmpty() && !address.isEmpty() && !contact.isEmpty() && !email.isEmpty()
                                && !password.isEmpty() && !TextUtils.isEmpty(getImageString())) {
                            UserModel userModel = new UserModel(name, address, contact, imageString, email, password, true);
                            signUp(userModel);
                        }
                    } else if (as.equalsIgnoreCase(Constants.business)) {
                        //validations
                        if (!name.isEmpty() && !address.isEmpty() && !contact.isEmpty() && !email.isEmpty()
                                && !password.isEmpty()
                                && !TextUtils.isEmpty(getBusinessType()) && !TextUtils.isEmpty(getImageString())
                                && binding.timeRadioGroup.getCheckedRadioButtonId()!=-1) {
                            UserModel userModel = new UserModel(name, address, contact, imageString, email, password, false,
                                    getBusinessType());
                            signUp(userModel);
                        }
                    }
                }
                else Toast.makeText(this, "Please agree to terms and condition", Toast.LENGTH_SHORT).show();
                break;
            case R.id.uploadImageBtn:
                uploadPic();
                break;

        }
    }
    void uploadPic(){
        showImagePicDialog();
    }
    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        pickFromGallery();
                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }

    // checking storage permissions
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // Requesting  gallery permission
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(storagePermission, STORAGE_REQUEST);
        }
    }

    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    // Requesting camera permission
    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(cameraPermission, CAMERA_REQUEST);
        }
    }

    // Requesting camera and gallery
    // permission if not given
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accepted && writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Camera and Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }

    // Here we will pick image from gallery or camera
    private void pickFromGallery() {
        CropImage.activity().start(RegistrationActivity.this);
    }
    String imageString;
    String businessType;

    public String getBusinessType() {
        return businessType;
    }

    public String getImageString() {
        return imageString!=null?imageString:"";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Bitmap bitmap= null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);
                    // initialize byte stream
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    // compress Bitmap
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    // Initialize byte array
                    byte[] bytes=stream.toByteArray();
                    // get base64 encoded string
                    imageString= Base64.encodeToString(bytes,Base64.DEFAULT);
                    Picasso.with(this).load(resultUri).into(binding.image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        businessType = types[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void saveUserToFirebase(UserModel userModel, boolean isUser){
        DatabaseReference reference;
        if(isUser) reference = FirebaseDatabase.getInstance().getReference("Users");
        else reference = FirebaseDatabase.getInstance().getReference("BusinessUsers");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.setValue(userModel);
                if(isUser) startActivity(new Intent(RegistrationActivity.this, CustomerHomeActivity.class));
                else startActivity(new Intent(RegistrationActivity.this, BusinessHomeActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrationActivity.this, "Error in register user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}