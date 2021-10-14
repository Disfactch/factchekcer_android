package org.androidtown.disfactch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import org.androidtown.disfactch.R;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingFragment extends Fragment {

/*
    MainActivity mainActivity;

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
*/
    Button btn_github;
    Button button_2;


    Switch sw_dark;

    BrowseFragment web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        //TODO: 버튼 이름 기능에 따라 변경 필요 _ button1 이런건 나중에 뭔지 헷갈릴 것 같아요
        btn_github = (Button) v.findViewById(R.id.btn_github);
        button_2 = (Button) v.findViewById(R.id.button_2);

        //btn_github: disfactch github를 webview 방식으로 앱 내에서 보여줌
        btn_github.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("link","https://github.com/Disfactch");//번들에 넘길 값 저장
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                web = new BrowseFragment();//프래그먼트2 선언
                web.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.main_frame, web);
                transaction.commit();
            }
        });


        //button_2 기능: 임시로 전화기능 설정
        button_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:010"));
                startActivity(intent);
            }
        });


        sw_dark = (Switch)v.findViewById(R.id.sw);

        sw_dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        //TODO: 여기서 onConfigurationChanged 메서드를 써야할 것 같은데
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(isChecked){
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        return v;



    }

}


