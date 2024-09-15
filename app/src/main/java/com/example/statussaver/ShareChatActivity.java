package com.example.statussaver;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.statussaver.databinding.ActivityShareChatBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ShareChatActivity extends AppCompatActivity {
    private ActivityShareChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_share_chat);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_chat);
        binding.btnDownload.setOnClickListener(v -> {
            getShareChatData();
        });

    }

    private void getShareChatData() {
        URL url = null;
        try {
            url = new URL(binding.sharechatUrl.getText().toString());
            String host = url.getHost();
            if (host.contains("sharechat"))
                new CallGetShareChatData().execute(binding.sharechatUrl.getText().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    class CallGetShareChatData extends AsyncTask<String, Void, Document> {

        Document scDocument;

        @Override
        protected org.jsoup.nodes.Document doInBackground(String... strings) {
            try {
                scDocument = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Document document) {
            String videoUrl = document.select("meta[property=\"og;video:secure_url\"]")
                    .last().attr("content");
            if (!videoUrl.equals("")) {
                Util.download(videoUrl, Util.RootDirectoryShareChat, ShareChatActivity.this, "ShareChat "
                        + System.currentTimeMillis() + ".mp4");
            }
        }
    }


}