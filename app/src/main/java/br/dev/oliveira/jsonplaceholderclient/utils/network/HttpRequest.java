package br.dev.oliveira.jsonplaceholderclient.utils.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;

public class HttpRequest {

    private static RequestQueue mRequestQueue;
    private Context mContext;

    public HttpRequest(Context context) {
        this.mContext = context;
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
    }

    public void doPost(
            final String url,
            JSONObject body,
            final OnResponseRequestListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mRequestQueue.add(stringRequest);
    }

    public void doGet(final String url, final OnResponseRequestListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mRequestQueue.add(stringRequest);
    }


}
