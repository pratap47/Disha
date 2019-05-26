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
                break;

            case R.id.etcbranch:
                fragment = new etc();
                break;

            case R.id.mechanicalbranch:
                fragment = new mechanical();
                break;

            case R.id.mbabranch:
                fragment = new mba();
                break;

            case R.id.medicalbranch:
                fragment = new medical();
                break;
        }

        return loadFragment(fragment);

    }
}
