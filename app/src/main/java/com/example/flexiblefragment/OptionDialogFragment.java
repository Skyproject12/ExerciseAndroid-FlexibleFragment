package com.example.flexiblefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {


    public OptionDialogFragment() {
        // Required empty public constructor
    }
    View view;
    Button btnChoose;
    Button btnClose;
    RadioButton rbSaf, rbMou, rbLvg, rbMoyes;
    OnOptionDialogListener onOptionDialogListener;
    RadioGroup rgOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_option_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnChoose= view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
        btnClose= view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        rgOptions= view.findViewById(R.id.rg_options);
        rbSaf= view.findViewById(R.id.rb_saf);
        rbLvg= view.findViewById(R.id.rb_lvg);
        rbMou= view.findViewById(R.id.rb_mou);
        rbMoyes= view.findViewById(R.id.rb_moyes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:
                // mengambil button id
                int checkedRadioButtonId= rgOptions.getCheckedRadioButtonId();
                // id tidak -1
                if(checkedRadioButtonId!=-1){
                    String coach=null;
                    switch (checkedRadioButtonId){
                        case R.id.rb_saf:
                            coach=rbSaf.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach= rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach= rbLvg.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach= rbMoyes.getText().toString().trim();
                            break;
                    }
                    // ketika option listener tidak sama dengan kosong
                    if(onOptionDialogListener != null){
                        onOptionDialogListener.onOptionChosen(coach);
                    }
                    getDialog().dismiss();
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment= getParentFragment();
        if(fragment instanceof DetailCategoryFragment){
            DetailCategoryFragment detailCategoryFragment= (DetailCategoryFragment) fragment;
            this.onOptionDialogListener= detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onOptionDialogListener=null;
    }

    public interface OnOptionDialogListener{
        void onOptionChosen(String text);
    }
}
