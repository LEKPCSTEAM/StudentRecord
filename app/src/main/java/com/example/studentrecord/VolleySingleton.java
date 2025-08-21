package com.example.studentrecord;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue queue;
    private VolleySingleton(Context c) { queue = Volley.newRequestQueue(c.getApplicationContext()); }
    public static synchronized VolleySingleton get(Context c) {
        if (instance == null) instance = new VolleySingleton(c);
        return instance;
    }
    public <T> void add(Request<T> r) { queue.add(r); }
}
