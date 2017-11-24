package com.feedbackhealthdata.healthcdataf;


        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class feedbackRequest extends StringRequest {
    private static final String FEEDBACK_REQUEST_URL="https://explicit-debits.000webhostapp.com/feedback.php";
    private Map<String,String> params;


    public feedbackRequest(String no_of_cases, String virus,
                           Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST,FEEDBACK_REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("no_of_cases",no_of_cases);
        params.put("virus",virus);



    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}
