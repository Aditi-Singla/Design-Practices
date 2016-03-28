package layout;

/**
 * Created by Aditi Singla on 27-Mar-16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dhairya.complaintsystem.NoDefaultSpinner;
import com.example.dhairya.complaintsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragWriteComplaint extends android.support.v4.app.Fragment {
    String level = null;
    String title = null;
    String desc = null;
    String type = null;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_write_complaint_layout,container,false);
        final NoDefaultSpinner spinner1 = (NoDefaultSpinner)view.findViewById(R.id.level);
        final NoDefaultSpinner spinner2 = (NoDefaultSpinner)view.findViewById(R.id.type);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.level, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position) != null)
                    level = parent.getItemAtPosition(position).toString();
                if (level != null) {
                    if (level.equals("Individual")) {
                        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.IndividualTypes, android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (parent.getItemAtPosition(position) != null)
                                    type = level + parent.getItemAtPosition(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else if (level.equals("Hostel")) {
                        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.HostelTypes, android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (parent.getItemAtPosition(position) != null)
                                    type = level + parent.getItemAtPosition(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.InstituteTypes, android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (parent.getItemAtPosition(position) != null)
                                    type = level + " " + parent.getItemAtPosition(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button = (Button)view.findViewById(R.id.writeComplaint);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)view.findViewById(R.id.tit);
                title = et.getText().toString().trim();

                et = (EditText)view.findViewById(R.id.des);
                desc = et.getText().toString().trim();

                final String ti = title;
                final String ty = type;
                final String d = desc;
                final String l = level;

                String URL = "http://10.192.58.152:80/complaint_management/complaint/submitcomplaint.php";

                StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JSONObject response1 = null;
                                try {
                                    response1 = new JSONObject(response);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    if (response1.getInt("success") == 1) {
                                        TextView tv = (TextView)view.findViewById(R.id.titleText);
                                        tv.setText(response1.getString("message").toUpperCase()+"!");
                                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams)tv.getLayoutParams();
                                        layoutparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                                        tv.setLayoutParams(layoutparams);
                                        EditText et = (EditText)view.findViewById(R.id.tit);
                                        et.setVisibility(View.GONE);
                                        tv = (TextView)view.findViewById(R.id.descriptionText);
                                        tv.setVisibility(View.GONE);
                                        et = (EditText)view.findViewById(R.id.des);
                                        et.setVisibility(View.GONE);
                                        NoDefaultSpinner spinner = (NoDefaultSpinner)view.findViewById(R.id.level);
                                        spinner.setVisibility(View.GONE);
                                        spinner = (NoDefaultSpinner)view.findViewById(R.id.type);
                                        spinner.setVisibility(View.GONE);
                                        Button button = (Button)view.findViewById(R.id.writeComplaint);
                                        button.setVisibility(View.GONE);
                                    } else
                                        Toast.makeText(getActivity(), response1.getString("message"), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/x-www-form-urlencoded");
                        if(ti!=null)
                            params.put("title", ti);
                        if(ty!=null)
                            params.put("type", ty);
                        if(l!=null)
                            params.put("level", l);
                        if(d!=null)
                            params.put("description",d);

                        return params;
                    }
                };


                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsObjRequest);


            }
        });

        return view;

    }
}
