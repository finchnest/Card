package com.abeniapps.card.recyclerview;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.abeniapps.card.recyclerview.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Book_Activity extends AppCompatActivity {

    private ImageView img;
    private int image;
    private Button setImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        Toolbar toolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        Toolbar toolbar3 = findViewById(R.id.my_toolbar3);
        setSupportActionBar(toolbar3);

        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img = findViewById(R.id.bookthumbnail);
        image = getIntent().getExtras().getInt("Thumbnail");
        img.setImageResource(image);

        setImage=findViewById(R.id.btn_set);
        setImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setResource(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(Book_Activity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.for_iv, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        final int imageSource = getIntent().getIntExtra("Thumbnail", 0);
        switch (item.getItemId()) {
            case R.id.fav_img:
                Toast.makeText(this, "You Saved the Image to Fav", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.share_wall:
                Bitmap b = BitmapFactory.decodeResource(getResources(), imageSource);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "image", null);
                Uri imageUri = Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Share Wallpaper Via"));
                return true;

            case R.id.store_img:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageSource);
                OutputStream outputStream;
                File filePath = Environment.getExternalStorageDirectory();
                File dir = new File(filePath + "/Wally/");
                dir.mkdirs();

                File file = new File(dir, "wallpaper_" + ((int) (Math.random() * 100000 + 50000)) + "_" + ((int) (Math.random() * 100000 + 50000)) + ".jpg");
                try {
                    outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.d("getMessage1", "onCreate: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("getMessage2", "onCreate: " + e.getMessage());
                    e.printStackTrace();
                }
                Toast.makeText(this, "Saved in the Storage", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}