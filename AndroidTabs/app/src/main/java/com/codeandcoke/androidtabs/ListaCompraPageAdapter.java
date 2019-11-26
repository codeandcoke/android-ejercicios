package com.codeandcoke.androidtabs;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListaCompraPageAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] {"Productos", "Supermercados"};

    public ListaCompraPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return ProductsFragment.newInstance(position + 1);
        else
            return SupermarketsFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
