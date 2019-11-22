package com.example.visualmath;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.visualmath.dummy.DummyContent;
import com.google.android.material.appbar.CollapsingToolbarLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherItemDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;


    public TeacherItemDetailFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_teacher_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
           ((TextView) rootView.findViewById(R.id.tv_detail)).setText(mItem.details);
            TextView textView=(TextView)rootView.findViewById(R.id.tv_title);
            textView.setText(mItem.content);

            ImageView imageView=(ImageView)rootView.findViewById(R.id.iv_problem);

            String res_name="@drawable/img_math"+(Integer.parseInt(mItem.id)%4);
            int resID = getResources().getIdentifier(res_name, "drawable",  getActivity().getPackageName());
            imageView.setImageResource(resID);
        }

        return rootView;
    }

}
