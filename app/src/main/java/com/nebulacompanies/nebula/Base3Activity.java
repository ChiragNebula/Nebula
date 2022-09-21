package com.nebulacompanies.nebula;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;


import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * Created by Palak Mehta on 4/15/2017.
 */

public class Base3Activity extends Fragment {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changelanguage();
    }

    private void changelanguage() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Configuration config = getActivity().getBaseContext().getResources().getConfiguration();
        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getActivity().getBaseContext().getResources().updateConfiguration(config,getActivity().getBaseContext().getResources().getDisplayMetrics());
        }
    }

}
