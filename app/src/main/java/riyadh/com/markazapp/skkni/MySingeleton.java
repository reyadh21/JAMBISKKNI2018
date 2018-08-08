package riyadh.com.markazapp.skkni;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Riyadh on 06/06/2017.
 */

public class MySingeleton {
    private static MySingeleton mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;

    private MySingeleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {

        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized MySingeleton getmInstance(Context context){
        if (mInstance == null){
            mInstance = new MySingeleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestque(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
