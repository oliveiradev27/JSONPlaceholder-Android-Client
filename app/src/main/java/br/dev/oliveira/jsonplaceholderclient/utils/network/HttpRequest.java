package br.dev.oliveira.jsonplaceholderclient.utils.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;

public class HttpRequest {

    private static RequestQueue mRequestQueue;

    private HttpRequest() {
    }

    public static void initialize(Context context) {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
    }


    public static void doPost(
            final String url,
            final String requestBody,
            final OnResponseRequestListener listener) {

        //final String body = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                NetworkConstants.ROOT + url,
                //"https://reqres.in/api/users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
                error.printStackTrace();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    Log.d("JSONPlaceholder", "Json enviado na requisição: " + requestBody);
                    return requestBody.getBytes("utf-8");
                    //return body.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        mRequestQueue.add(stringRequest);

    }

    public static void doGet(final String url, final OnResponseRequestListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                NetworkConstants.ROOT + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
                error.printStackTrace();

            }
        });

        mRequestQueue.add(stringRequest);
    }


    public static void doPut(
            final String url,
            final String requestBody,
            final OnResponseRequestListener listener
    ) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                NetworkConstants.ROOT + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
                error.printStackTrace();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return NetworkConstants.CONTENT_TYPE;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                final byte[] body = requestBody.getBytes();
                if (body != null) {
                    return body;
                }
                return super.getBody();
            }
        };

        mRequestQueue.add(stringRequest);
    }

    public static void doDelete(final String url, final OnResponseRequestListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                NetworkConstants.ROOT + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
                error.printStackTrace();

            }
        });

        mRequestQueue.add(stringRequest);
    }
}
