package com.example.statussaver.fragments;

import static android.os.Build.VERSION.SDK_INT;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.statussaver.R;
import com.example.statussaver.adapter.WhatsappAdapter;
import com.example.statussaver.databinding.FragmentImageBinding;
import com.example.statussaver.model.WhatsappStatusModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageFragment extends Fragment {


    private FragmentImageBinding binding;
    private ArrayList<WhatsappStatusModel> list;
    private WhatsappAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_image,
                container,false);

        list = new ArrayList<>();

        getData();
        binding.refresh.setOnRefreshListener(()->{
            list = new ArrayList<>();
            getData();
            binding.refresh.setRefreshing(false);
        });



        return binding.getRoot();
    }

    private void getData() {
        WhatsappStatusModel model;

        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Android/media/com.whatsapp/WhatsApp/Media/.statuses";
        File targetDirectory = new File(targetPath);
        File[] allFiles = targetDirectory.listFiles();

        String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Android/media/com.whatsapp.w4b/WhatsApp Business/Media/.statuses";
        File targetDirectoryBusiness = new File(targetPathBusiness);
        File[] allFilesBusiness = targetDirectoryBusiness.listFiles();


        // sort recent data whatsapp
        Arrays.sort(allFiles,((o1, o2) ->{
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) {
                return +1;
            }
            else return 0;
        }));

        //add all whats app data
        for (int i=0; i< allFiles.length; i++) {
            File file = allFiles[i];
            if (Uri.fromFile(file).toString().endsWith(".png") ||
                    Uri.fromFile(file).toString().endsWith(".jpg")) {
                model = new WhatsappStatusModel("whats " + i,
                        Uri.fromFile(file),
                        allFiles[i].getAbsolutePath(),
                        file.getName());
                list.add(model);
            }
        }
            // sort recent data Business whatsapp
            Arrays.sort(allFilesBusiness,((o1, o2) ->{
                if (o1.lastModified() > o2.lastModified()) return -1;
                else if (o1.lastModified() < o2.lastModified()) {
                    return +1;
                }
                else return 0;
            }));

            //add all business data
            for (int j =0; j< allFilesBusiness.length; j++){
                File file1 = allFilesBusiness[j];
                if (Uri.fromFile(file1).toString().endsWith(".png") ||
                        Uri.fromFile(file1).toString().endsWith(".jpg")){
                    model = new WhatsappStatusModel("whatsBusiness "+j,
                            Uri.fromFile(file1),
                            allFilesBusiness[j].getAbsolutePath(),
                            file1.getName());
                    list.add(model);
                }
        }
            adapter = new WhatsappAdapter(list,getActivity());
           binding.whatsappRecycler.setAdapter(adapter);


}


     private void getDataBelow() {
        WhatsappStatusModel model;

        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp/Media/.statuses";
        File targetDirectory = new File(targetPath);
        File[] allFiles = targetDirectory.listFiles();

        String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp Business/Media/.statuses";
        File targetDirectoryBusiness = new File(targetPathBusiness);
        File[] allFilesBusiness = targetDirectoryBusiness.listFiles();


        // sort recent data whatsapp
        Arrays.sort(allFiles,((o1, o2) ->{
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) {
                return +1;
            }
            else return 0;
        }));

        //add all whats app data
        for (int i=0; i< allFiles.length; i++) {
            File file = allFiles[i];
            if (Uri.fromFile(file).toString().endsWith(".png") ||
                    Uri.fromFile(file).toString().endsWith(".jpg")) {
                model = new WhatsappStatusModel("whats " + i,
                        Uri.fromFile(file),
                        allFiles[i].getAbsolutePath(),
                        file.getName());
                list.add(model);
            }
        }
            // sort recent data Business whatsapp
            Arrays.sort(allFilesBusiness,((o1, o2) ->{
                if (o1.lastModified() > o2.lastModified()) return -1;
                else if (o1.lastModified() < o2.lastModified()) {
                    return +1;
                }
                else return 0;
            }));

            //add all business data
            for (int j =0; j< allFilesBusiness.length; j++){
                File file1 = allFilesBusiness[j];
                if (Uri.fromFile(file1).toString().endsWith(".png") ||
                        Uri.fromFile(file1).toString().endsWith(".jpg")){
                    model = new WhatsappStatusModel("whatsBusiness "+j,
                            Uri.fromFile(file1),
                            allFilesBusiness[j].getAbsolutePath(),
                            file1.getName());
                    list.add(model);
                }
        }
            adapter = new WhatsappAdapter(list,getActivity());
           binding.whatsappRecycler.setAdapter(adapter);


}



}