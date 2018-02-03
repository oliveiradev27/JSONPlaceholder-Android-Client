package br.dev.oliveira.jsonplaceholderclient.utils.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
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

    private static HttpRequest mHttpRequest;
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
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
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
            }
        }){
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

    public static void doGet(final String url, final OnResponseRequestListener listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                NetworkConstants.ROOT+ url,
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
                }
            }){
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
                Request.Method.PUT,
                NetworkConstants.ROOT+ url,
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
