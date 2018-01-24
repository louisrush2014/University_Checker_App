package com.example.android.universitychecker;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jadaa on 1/22/2018.
 */

public class ListActivity extends ListFragment {
    private ListView view;
    private ProgressBar pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.list_frag, container, false);
        view = (ListView)view.findViewById(R.id.android_list);
        pb = (ProgressBar) view.findViewById(R.id.progressBar);
       return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setVisibility(View.VISIBLE);
        NetworkUtil.setFieldsValue(true,true,true);
        String schoolSize = NetworkUtil.getSchoolYearSize();

        new SynchTask().execute(schoolSize);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
    public class SynchTask extends AsyncTask<String, Void,String[]> {
        @Override
        protected String[] doInBackground(String...params) {

            URL connect = NetworkUtil.buildUrl();
            String schoolSize =params[0];
            try{
                String returnedJson = NetworkUtil.URLResponse(connect);
                String[] data = JSONHandler.jsonData(returnedJson,schoolSize);
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            pb.setVisibility(View.INVISIBLE);
        }
    }

}
