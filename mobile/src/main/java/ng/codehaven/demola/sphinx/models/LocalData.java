package ng.codehaven.demola.sphinx.models;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import ng.codehaven.demola.sphinx.Common;


public class LocalData {
    private JSONObject jo;
    private String url;
    private String title;
    private Intent intent;

    public LocalData(Intent intent) {
        this.intent = intent;
    }

    public JSONObject getJo() {
        return jo;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public LocalData invoke() {
        jo = null;
        url = null;
        title = null;

        if (intent != null) {
            try {
                jo = new JSONObject(intent.getStringExtra(Common.DEAL_TO_STRING));
                url = jo.getString(Common.DEAL_IMG_URL);
                title = jo.getString(Common.DEAL_TITLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
