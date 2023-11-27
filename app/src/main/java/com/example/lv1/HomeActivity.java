package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Student> student;
    Spinner spinnerBtn;

    Button camera_capture_button;
    PreviewView view_finder;
    ExecutorService executor;
    ImageCapture imageCapture;
    File outputDirectory;
    //dio sa camerax - preview
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);
        spinnerBtn = findViewById(R.id.langBtn);

        student = StudentSingleton.getInstance().getStudents();

        StudentAdapter studentAdapter = new StudentAdapter(this, student);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btnNxt = this.findViewById(R.id.next_button);
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateNewRecordActivity.class);

                startActivity(i);
            }
        });

        //src: https://developer.android.com/develop/ui/views/components/spinner
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerBtn.setAdapter(adapter);

        ArrayList<Locale> listOfLocales = new ArrayList<Locale>();
        listOfLocales.add(new Locale("de"));
        listOfLocales.add(new Locale("en"));
        listOfLocales.add(new Locale("hr"));
        spinnerBtn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getResources().getConfiguration().setLocale(listOfLocales.get(0));
                        break;
                    case 1:
                        getResources().getConfiguration().setLocale(listOfLocales.get(1));
                        break;
                    case 2:
                        getResources().getConfiguration().setLocale(listOfLocales.get(2));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
//        camera_capture_button = this.findViewById(R.id.image_capture_button);
//        view_finder = this.findViewById(R.id.viewFinder);
//
//        camera_capture_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                @SuppressLint("RestrictedApi") File photoFile = new File(getBaseContext().getExternalCacheDir() + File.separator + System.currentTimeMillis() + ".png");
//                String name = new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis());
//
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
//                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
//                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//                    contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
//                }
//                //dio sa camerax - capture
//                ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
//                imageCapture.takePicture(outputOptions, executor,
//                        new ImageCapture.OnImageSavedCallback() {
//                            @Override
//                            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
//                                Uri savedUri = Uri.fromFile(photoFile);
//                                //Toast.makeText(MainActivity.this, "radi", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onError(@NonNull ImageCaptureException exception) {
//                                Log.d("myTag", exception.getMessage());
//                            }
//                        });
//            }
//        });
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
//            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                Toast.makeText(this, "You can't use image classification example without granting CAMERA permission", Toast.LENGTH_LONG).show();
//                finish();
//            } else {
//                startCamera();
//            }
//        }
    }

//    private void startCamera() {
//        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
//        cameraProviderFuture.addListener(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//                    bindPreview(cameraProvider);
//                }catch (ExecutionException | InterruptedException e){
//
//                }
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }
//
//    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
//
//        Preview preview = new Preview.Builder()
//                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
//                .build();
//
//        CameraSelector cameraSelector = new CameraSelector.Builder()
//                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                .build();
//
//        executor = Executors.newSingleThreadExecutor();
//
//        imageCapture = new ImageCapture.Builder().build();
//
//
//        cameraProvider.unbindAll();
//        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this,
//                cameraSelector, preview,imageCapture);
//
//        preview.setSurfaceProvider(view_finder.getSurfaceProvider());
//
//    }
//
//
//
//    private long mLastAnalysisResultTime;
//    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;
//    private static final String[] PERMISSIONS = {android.Manifest.permission.CAMERA};
//
//    private static final String TAG = "CameraXBasic";
//    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
//    private boolean checkPermission() {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    this,
//                    PERMISSIONS,
//                    REQUEST_CODE_CAMERA_PERMISSION);
//            return false;
//        }
//        return true;
//    }
}