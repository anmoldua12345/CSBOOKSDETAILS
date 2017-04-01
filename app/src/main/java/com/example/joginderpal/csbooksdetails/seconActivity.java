package com.example.joginderpal.csbooksdetails;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
public class seconActivity extends AppCompatActivity {

    List<String> li;
    List<String> li1;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        recyclerView= (RecyclerView) findViewById(R.id.rvactivity_main);
        new doit().execute();
    }

    public class doit extends AsyncTask<Void,Void,Void> {


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(seconActivity.this);
            pd.setMessage("Loading..");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                li=new ArrayList<>();
                String sear=getIntent().getExtras().getString("search");
                Document doc= Jsoup.connect("http://it-ebooks.info/search/?q="+sear).get();
                Elements ele=doc.getElementsByTag("table");
                for (Element el1:ele){

                    String align=el1.attr("width");
                    if (align.equals("100%")){

                        Elements tr=el1.getElementsByTag("tr");
                        for (Element tr1:tr){

                            String src=tr1.getElementsByTag("img").first().attr("src");
                            li.add(src);

                        }

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
            pd.dismiss();
            layoutManager = new GridLayoutManager(seconActivity.this,2);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapter(li,seconActivity.this);
            recyclerView.setAdapter(adapter);
     //       ed2.setText(li.get(0));
        }
    }
}
