package com.example.statussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.statussaver.databinding.ActivityFacebookBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FacebookActivity extends AppCompatActivity {

    private ActivityFacebookBinding binding;
    private FacebookActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_facebook);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_facebook);
        activity = this;
        binding.btnDownload.setOnClickListener(v ->{
            getFaceBookData();
        });
    }

    private void getFaceBookData() {

        URL url = null;
        try {
            url = new URL(binding.facebookUrl.getText().toString());
            String host = url.getHost();
            if (host.contains("facebook.com")){
                new CallFacebookData().execute(binding.facebookUrl.getText().toString());
            }
            else {
                Toast.makeText(activity,"Url is invalid", Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
    class CallFacebookData extends AsyncTask <String, Void, Document>{

      Document fbDocument;
        private android.content.Context Context;

        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings) {
            try {
                fbDocument = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return fbDocument;
        }
        @Override
        protected  void onPostExecute(Document document){
            String videoUrl = document.select("meta[content$=.mp4]")
                    .last().attr("content");
            if (!videoUrl.equals(""))
                Util.download(videoUrl,Util.RootDirectoryFacebook, activity,"facebook "+System.currentTimeMillis()+".mp4");
        }
    }
}