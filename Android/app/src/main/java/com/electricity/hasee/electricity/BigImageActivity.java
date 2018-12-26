package com.electricity.hasee.electricity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_big_image)
public class BigImageActivity extends AppCompatActivity {

    @ViewInject(R.id.iv_big_img)
    private ImageView iv_big_img;
    @ViewInject(R.id.bigphoto_back_page)
    private ImageButton back_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_big_image);
        x.view().inject(this);


      //  x.image().bind(iv_big_img, getIntent().getStringExtra("url"), imageOptions);
       // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        Glide.with(this).load(getIntent().getStringExtra("url"))//.apply(options)
                .into(iv_big_img);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigImageActivity.this.finish();
            }
        });
        // assets file
        //x.image().bind(iv_big_img, "assets://test.gif", imageOptions);

        // local file
        //x.image().bind(iv_big_img, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
        //x.image().bind(iv_big_img, "/sdcard/test.jpg", imageOptions);
        //x.image().bind(iv_big_img, "file:///sdcard/test.gif", imageOptions);
        //x.image().bind(iv_big_img, "file:/sdcard/test.gif", imageOptions);
    }
}
