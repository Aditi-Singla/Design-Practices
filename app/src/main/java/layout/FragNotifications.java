package layout;

/**
 * Created by Aditi Singla on 27-Mar-16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhairya.complaintsystem.R;

public class FragNotifications extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notifications_layout,null);


    }
}
