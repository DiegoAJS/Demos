package org.developerjs.demos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.tvShare);
        imageView=(ImageView)findViewById(R.id.ivImage);

        //lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottieAnimationViewSplash);

        //lottieAnimationView.playAnimation();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePic();
            }
        });

    }

    private void SharePic(){
        /**
        //Se carga la imagen que se quiere compartir
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.p);
        //Se guarda la imagen en la SDCARD
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File( Environment.getExternalStorageDirectory() + File.separator +
             "tmp" + File.separator + "peter.jpg");
        try {
               f.createNewFile();
               FileOutputStream fo = new FileOutputStream(f);
               fo.write(bytes.toByteArray());
           } catch (IOException e) {
               Log.e("ERROR", e.getMessage() );
           }
        //compartir imagen
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        share.putExtra(Intent.EXTRA_TEXT, "Mi imagen");
        startActivity(Intent.createChooser(share, "Compartir imagen"));

         */
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

/***** COMPARTIR IMAGEN *****/
        try {
            File file = new File(this.getCacheDir(), bitmap + ".png");
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            intent.putExtra(Intent.EXTRA_TEXT, "Mi imagen");
            //startActivity(intent);
            startActivity(Intent.createChooser(intent, "Compartir imagen"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        }

}
