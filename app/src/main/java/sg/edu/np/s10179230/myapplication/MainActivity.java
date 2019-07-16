package sg.edu.np.s10179230.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private TextView Name;
    private TextView Long;
    private TextView Lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=findViewById(R.id.txtName);
        Long=findViewById(R.id.txtLong);
        Lat=findViewById(R.id.txtLAt);
        mQueue= Volley.newRequestQueue(this);
        jsonParse();
    }

    private void jsonParse() {
        String url="https://api.data.gov.sg/v1/environment/2-hour-weather-forecast";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,null,
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray1 = response.getJSONArray("area_metadata");

                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject area_data = jsonArray1.getJSONObject(i);

                        String Name = area_data.getString("name");
                        JSONArray jsonArray2 = jsonArray1.getJSONArray(1);
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject innerele = jsonArray2.getJSONObject(j);
                            Double lat_id = innerele.getDouble("latitude");
                            Double long_id = innerele.getDouble("longitude");

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },  new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
    }
}
