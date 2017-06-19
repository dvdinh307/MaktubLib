
package maktub.vn.maktub.utils.network;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Hss on 8/19/2015.
 */
public class ServerConnection {

    public static final int TIME_OUT_TO_REQUEST = 10000;

    private static AsyncHttpClient client = new AsyncHttpClient();

    // Have params.
    public static void getWithParams(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(TIME_OUT_TO_REQUEST);
        client.get(url, params, responseHandler);
    }

    // No params.
    public static void getNoParams(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, null, responseHandler);
    }

    /**
     * Using method post. Get JSONObject.
     *
     * @param url
     * @param params
     * @param request
     */
    public static void requestToServer(String url, RequestParams params, final onRequestResult request) {
        postWithParams(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                int sttCode = response.optInt("status", 1);
                if (sttCode == 0) {
                    try {
                        if (request != null) {
                            if (response.has("result")) {
                                request.onSuccess(response.getJSONObject("result"));
                            } else
                                request.onSuccess(response);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (request != null)
                            request.onFail(statusCode);
                    }
                } else {
                    if (request != null)
                        request.onFail(statusCode);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (request != null)
                    request.onFail(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (request != null)
                    request.onFail(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (request != null)
                    request.onFail(0);
            }
        });
    }

    /**
     * Using method post. Get ArrayJSONObject.
     *
     * @param url
     * @param params
     * @param request
     */
    public static void requestToServerGetArrayData(String url, RequestParams params, final onRequestResultArrayData request) {
        postWithParams(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                int sttCode = response.optInt("status", 1);
                if (sttCode == 0) {
                    try {
                        JSONArray arrayData = response.getJSONArray("result");
                        if (arrayData.length() > 0)
                            request.onSuccess(arrayData);
                        else
                            request.onNoData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        request.onFail(statusCode);
                    }
                } else {
                    request.onFail(statusCode);
                }
//                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                request.onFail(statusCode);
//                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                request.onFail(statusCode);
//                super.onFailure(statusCode, headers, responseString, throwable);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                request.onFail(statusCode);
//                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });
    }

    public static void postWithParams(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(TIME_OUT_TO_REQUEST);
        client.post(url, params, responseHandler);
    }

    public static void postNoParams(String url, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(TIME_OUT_TO_REQUEST);
        client.post(url, null, responseHandler);
    }

    public interface onRequestResult {
        void onSuccess(JSONObject object);

        void onFail(int errorCode);
    }

    public interface onRequestResultArrayData {
        void onSuccess(JSONArray arrayData);

        void onFail(int errorCode);

        void onNoData();
    }


}
