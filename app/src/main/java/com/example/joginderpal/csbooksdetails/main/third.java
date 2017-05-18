package com.example.joginderpal.csbooksdetails;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by joginderpal on 01-04-2017.
 */
public class third extends AppCompatActivity {

    ImageView im;
    RelativeLayout bottom_rl,books_rl;
    List<String> li;
    List<String> li1;
    TextView tx,tx1,tx2,tx3,tx4;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        toolbar= (Toolbar) findViewById(R.id.toolBar_third);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_third);
        appBarLayout= (AppBarLayout) findViewById(R.id.app_bar_layout_third);
        im= (ImageView) findViewById(R.id.image_books_third);
        tx= (TextView) findViewById(R.id.description);
        tx1= (TextView) findViewById(R.id.publisher);
        tx2= (TextView) findViewById(R.id.By);
        tx3= (TextView) findViewById(R.id.Year);
        tx4= (TextView) findViewById(R.id.Pages);

        postponeEnterTransition();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int scroll =-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                 if (scroll==-1){
                     scroll=appBarLayout.getTotalScrollRange();
                 }

                 if (scroll+verticalOffset==0){

                     collapsingToolbarLayout.setTitle(" ");
                 }
                else{
                     collapsingToolbarLayout.setTitle(" ");
                 }

            }
        });

        String s=getIntent().getExtras().getString("image");

        Picasso.with(third.this).load("http://it-ebooks.info" +s).fit().into(im,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        scheduleStartPostponedTransition(im);
                    }

                    @Override
                    public void onError() {

                    }
                });


        Glide.with(third.this).load("http://it-ebooks.info" +s).bitmapTransform(new BlurTransformation(third.this,100)).into(new SimpleTarget<GlideDrawable>() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                collapsingToolbarLayout.setBackground(resource);


            }
        });



         new doit().execute();
    }

    public class doit extends AsyncTask<Void,Void,Void>{

        String text=" ";
        String text1=" ";
        String text2=" ";
        String text3=" ";
        String text4=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                li=new ArrayList<>();
                li1=new ArrayList<>();
                Document doc= Jsoup.connect("http://it-ebooks.info"+getIntent().getExtras().getString("link")).get();
                String img=doc.getElementsByTag("img").first().attr("src");
                li.add(img);
                text=doc.getElementsByTag("span").first().text();
                Elements tr=doc.getElementsByTag("tr");
                for (Element e:tr){

                    if (e.text().contains("By:")){
                      text2=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Publisher:")){
                        text1=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Year:")){
                        text3=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Pages:")){
                        text4=e.getElementsByTag("b").first().text();
                    }

                }



            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!li.isEmpty()) {
                tx.setText(text);
                tx1.setText(text1);
                tx2.setText(text2);
                tx3.setText(text3);
                tx4.setText(text4);
            }
            else{

            }
        }
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }


}
