package com.example.dhairya.complaintsystem;

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

import com.android.volley.toolbox.StringRequest;
import com.example.dhairya.complaintsystem.R;

public class FragAddUser extends android.support.v4.app.Fragment {

    String name = null;
    String username = null;
    String entryno = null;
    String hostel = null;
    String logintype = null;
    String usertype = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_add_user_layout, container,false);
        final NoDefaultSpinner spinner = (NoDefaultSpinner) view.findViewById(R.id.category);
        final NoDefaultSpinner spinner1 = (NoDefaultSpinner) view.findViewById(R.id.usertype);
        final NoDefaultSpinner spinner2 = (NoDefaultSpinner) view.findViewById(R.id.hostel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position) != null)
                    logintype = parent.getItemAtPosition(position).toString();
                    if(logintype!=(null)){
                        if(logintype.equals("Admin")) {
                            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.admin, android.R.layout.simple_spinner_item);
                            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner1.setAdapter(adapter1);
                            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (parent.getItemAtPosition(position) != null)

                                    if(usertype!=(null)) {
                                        if (!(usertype.equals("Dean Academics") || usertype.equals("Other Faculty") || usertype.equals("Electrician") || usertype.equals("Plumber") || usertype.equals("Security"))) {
                                            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.hostel, android.R.layout.simple_spinner_item);
                                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            spinner2.setAdapter(adapter2);
                                            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (parent.getItemAtPosition(position) != null)
                                                        hostel = parent.getItemAtPosition(position).toString();


                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {
                                                }
                                            });
                                        }
                                    }
                                    usertype = "Normal Student";


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });
                        }
                        else if(logintype.equals("User")) {
                            final NoDefaultSpinner spinner1 = (NoDefaultSpinner) view.findViewById(R.id.usertype);
                            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.user, android.R.layout.simple_spinner_item);
                            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner1.setAdapter(adapter1);
                            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (parent.getItemAtPosition(position) != null)
                                        usertype = parent.getItemAtPosition(position).toString();
                                        if(usertype!=(null)) {
                                            if (!(usertype.equals("Dean Academics") || usertype.equals("Other Faculty") || usertype.equals("Electrician") || usertype.equals("Plumber") || usertype.equals("Security"))) {
                                                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.hostel, android.R.layout.simple_spinner_item);
                                                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinner2.setAdapter(adapter2);
                                                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if (parent.getItemAtPosition(position) != null)
                                                            hostel = parent.getItemAtPosition(position).toString();

                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                    }
                                                });
                                            }
                                        }
                                        if(usertype.equals("Other Student"))
                                            usertype = "Normal Student";
                                        else if(usertype.equals("Other Faculty"))
                                            usertype = "Normal Faculty";
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });

                        }}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




                EditText editText = (EditText) view.findViewById(R.id.usernameEdit);
                username = editText.getText().toString().trim();
                if(username!=null) {
                    if (username.equals(""))
                        username = null;
                }

                editText = (EditText) view.findViewById(R.id.nameEdit);
                name = editText.getText().toString().trim();
                if(name!=null){
                    if(name.equals(""))
                        name = null;
                }


                editText = (EditText) view.findViewById(R.id.entryNumberEdit);
                entryno = editText.getText().toString().trim();
                if(entryno!=null) {
                    if (entryno.equals(""))
                        entryno = null;
                }

        Button button = (Button) view.findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n = name;
                final String u = username;
                final String p = username;
                final String e = entryno;
                final String h = hostel;
                final String ut = usertype;
                final String l = logintype;
            }
        });

                return view;//inflater.inflate(R.layout.frag_add_user_layout,null);

        }

    public void addUser(View v) {
        final String n = name;
        final String u = username;
        final String p = username;
        final String e = entryno;
        final String h = hostel;
        final String ut = usertype;
        final String l = logintype;



    }


}
