package com.feedbackhealthdata.healthcdataf;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://pradnyapendse.net16.net/Register.php";
    private Map<String,String> params;


    public RegisterRequest(String hospital_name, String area_pincode, String hospital_category, String hospital_address, String email_ID, String pass_word, String contactNo,
                           Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST,REGISTER_REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("hospital_name",hospital_name);
        params.put("area_pincode",area_pincode);
        params.put("hospital_category",hospital_category);
        params.put("hospital_address",hospital_address);
        params.put("email_ID",email_ID);
        params.put("pass_word",pass_word);
        params.put("contact_no", contactNo);

    }

    @Override

    public Map<String,String> getParams(){
              return params;
    }
}
