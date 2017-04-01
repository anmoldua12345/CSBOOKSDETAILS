package com.example.joginderpal.csbooksdetails;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 01-04-2017.
 */
public class third extends Activity {

    ImageView im;
    List<String> li;
    List<String> li1;
    TextView tx,tx1,tx2,tx3,tx4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        im= (ImageView) findViewById(R.id.image_books_third);
        tx= (TextView) findViewById(R.id.description);
        tx1= (TextView) findViewById(R.id.publisher);
        tx2= (TextView) findViewById(R.id.By);
        tx3= (TextView) findViewById(R.id.Year);
        tx4= (TextView) findViewById(R.id.Pages);
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
            Picasso.with(third.this).load("http://it-ebooks.info"+li.get(0)).fit().into(im);

            tx.setText(text);
            tx1.setText(text1);
            tx2.setText(text2);
            tx3.setText(text3);
            tx4.setText(text4);
           // Glide.with(third.this).load("http://it-ebooks.info"+li.get(0)).into(im);

        }
    }


}
