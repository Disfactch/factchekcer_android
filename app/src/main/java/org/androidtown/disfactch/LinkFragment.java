package org.androidtown.disfactch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kakao.sdk.link.LinkClient;
import com.kakao.sdk.link.model.LinkResult;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import static android.content.ContentValues.TAG;

public class LinkFragment extends Fragment {

    MainActivity mainActivity;

    Button kakao_msg;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_link, container, false);

        kakao_msg = (Button)v.findViewById(R.id.btn_msg);
        kakao_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    /**
                     * new Link() : 파라미터 순서대로 webLink, mobileLink, androidLink, iosLink
                     */
                    FeedTemplate feedTemplate = new FeedTemplate(new Content("title","imageUrl",    //메시지 제목, 이미지 url
                            new Link("https://www.naver.com"),"description",                    //메시지 링크, 메시지 설명
                            300,300));                                                     //이미지 사이즈

                    LinkClient.getInstance().defaultTemplate(view.getContext(), feedTemplate,null,new Function2<LinkResult, Throwable,Unit>() {
                        @Override
                        public Unit invoke(LinkResult linkResult, Throwable throwable) {
                            if (throwable != null) {
                                Log.e("TAG", "카카오링크 보내기 실패", throwable);
                            }
                            else if (linkResult != null) {
                                view.getContext().startActivity(linkResult.getIntent());

                                // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                                Log.w("TAG", "Warning Msg: "+ linkResult.getWarningMsg());
                                Log.w("TAG", "Argument Msg: "+ linkResult.getArgumentMsg());
                            }
                            return null;
                        }
                    });
            }
        });

        return v;
    }
}