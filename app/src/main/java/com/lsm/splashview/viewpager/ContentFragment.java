package com.lsm.splashview.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lsm.splashview.MainActivity;
import com.lsm.splashview.R;

public class ContentFragment extends Fragment {
    private static final String RESOUCE_ID = "resid";
    private static final String SHOW_BUTTO = "btnshow";

    public static ContentFragment getInstance(int resId, boolean showBtn) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(RESOUCE_ID, resId);
        bundle.putBoolean(SHOW_BUTTO, showBtn);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Button btn = view.findViewById(R.id.id_btn);
        RelativeLayout content = view.findViewById(R.id.id_content);
        int resId = getArguments().getInt(RESOUCE_ID, -1);
        content.setBackgroundResource(resId);
        boolean show = getArguments().getBoolean(SHOW_BUTTO, false);
        btn.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
