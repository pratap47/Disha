package test.com.disha.tearcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import test.com.disha.R;

public class tquestion extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static int IT;
    public static int ETC;
    public static int Meach;
    public static int mba;
    public static int medical;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.tquestion,container,false);
        loadFragment(new it());
        BottomNavigationView navigation = v.findViewById(R.id.navi);
        navigation.setOnNavigationItemSelectedListener(this);

        return v;

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {

                    getFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.itbranch:
                fragment = new it();
                 IT =1;
                ETC=0;
                 Meach=0;
                 mba=0;
                 medical=0;
                break;

            case R.id.etcbranch:
                fragment = new it();
                IT =0;
                ETC=1;
                Meach=0;
                mba=0;
                medical=0;

                break;

            case R.id.mechanicalbranch:
                fragment = new it();
                IT =0;
                ETC=0;
                Meach=1;
                mba=0;
                medical=0;

                break;

            case R.id.mbabranch:
                fragment = new it();
                IT =0;
                ETC=0;
                Meach=0;
                mba=1;
                medical=0;


                break;

            case R.id.medicalbranch:
                fragment = new it();

                IT =0;
                ETC=0;
                Meach=0;
                mba=0;
                medical=1;
                break;
        }

        return loadFragment(fragment);

    }
}
