package mue.com.mycam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.client.Firebase;

import java.io.File;

public class MainActivity extends Activity {

    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://glowing-fire-4156.firebaseio.com/");
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);

            }
        });
    }

    private File getFile(){

        File folder = new File("sdcard/camera_app");
        boolean success = false;
        if(!folder.exists()){
            // Create folder if it doesn't exist
            success = folder.mkdir();
        }
        if(!success){
            Log.d("MAIN", "Folder not Created");
        } else {
            Log.d("MAIN", "Folder  Created");
        }
        // Create image file
        File image_file = new File(folder, "cam_image.jpg");

        return image_file;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));

    }

}
