package com.example.flexiblefragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCategoryFragment extends Fragment implements View.OnClickListener {


    public DetailCategoryFragment() {
        // Required empty public constructor
    }
    TextView tvCategoryName;
    TextView tvCategoryDescription;
    Button btnProfile;
    Button btnShowDialog;
    View view;
    public static String EXTRA_NAME= "extra_name";
    public static String EXTRA_DESCRIPTION="extra_description";
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_detail_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCategoryName= view.findViewById(R.id.tv_catgory_name);
        tvCategoryDescription= view.findViewById(R.id.tv_category_description);
        btnProfile= view.findViewById(R.id.btn_profile);
        btnShowDialog= view.findViewById(R.id.btn_show_dialog);
        btnProfile.setOnClickListener(this);
        btnShowDialog.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // get data use replace
        if(savedInstanceState != null){
            String descFromBundle= savedInstanceState.getString(EXTRA_DESCRIPTION);
            setDescription(descFromBundle);
        }
        if(getArguments()!= null) {
            String categoryName = getArguments().getString(EXTRA_NAME);
            tvCategoryName.setText(categoryName);
            tvCategoryDescription.setText(getDescription());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_profile:
                Intent intent= new Intent(getActivity(), ProfilActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_show_dialog:
                OptionDialogFragment mOptionDialogFragment= new OptionDialogFragment();
                FragmentManager mFragmentManager= getChildFragmentManager();
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment.class.getSimpleName());
                break;
        }
    }
    // tampilkan toast berdasarkan interface di option dialog fragment
    OptionDialogFragment.OnOptionDialogListener optionDialogListener= new OptionDialogFragment.OnOptionDialogListener() {
        @Override
        public void onOptionChosen(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    };
}
