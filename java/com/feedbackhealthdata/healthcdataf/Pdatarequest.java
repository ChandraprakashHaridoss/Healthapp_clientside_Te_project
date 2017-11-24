package com.feedbackhealthdata.healthcdataf;


        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class Pdatarequest extends StringRequest {
    private static final String Pdata_REQUEST_URL="https://explicit-debits.000webhostapp.com/pdata.php";
    private Map<String,String> params;


    public Pdatarequest(String patient_name, String case_no,String case_details,String test_required,String additional_requirements,String patients_cellno,String patient_addresss,
                        Response.Listener<String> listener, Response.ErrorListener errorListener){


        super(Method.POST,Pdata_REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("patient_name",patient_name);
        params.put("case_no",case_no);
        params.put("case_details",case_details);
        params.put("test_required",test_required);
        params.put("additional_requirements",additional_requirements);
        params.put("patients_cellno",patients_cellno);
        params.put("patient_address",patient_addresss);




    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}