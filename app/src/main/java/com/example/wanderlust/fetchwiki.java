package com.example.wanderlust;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchwiki extends AsyncTask<Void,Void,Void> {
    String data="";
    String parseddata="";
    String city;


    public fetchwiki(String data) {
        this.city = data;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&exsentences=10&exlimit=1&titles="+city+"&explaintext=1&formatversion=2&format=json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null) {
                line = bufferedReader.readLine();
                data=data+line;
            }

            JSONObject reader = new JSONObject(data);
            JSONObject jo  = reader.getJSONObject("query");
            data= jo.getString("pages");

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                parseddata+=JO.get("extract");

            }
//



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Funfacts.fetchdata.setText(this.parseddata);


    }
}
