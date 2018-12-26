package com.electricity.hasee.electricity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.SearchActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.tab2)
public class LaptopIndividualFragment extends Fragment {
    @ViewInject(R.id.notfinish_title)
    private TextView notfinish_title;
    private boolean injected = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }
}
