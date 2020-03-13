package com.visualstudy.visualmath.ui.dashboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visualstudy.visualmath.VM_ENUM;
import com.visualstudy.visualmath.R;
import com.visualstudy.visualmath.calendarListAdapater;
import com.visualstudy.visualmath.date_data;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardListFragment extends Fragment {

    //lhj_0_start
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    calendarListAdapater recyclerViewAdapter;

    //점찍힌 날짜 배열
    private ArrayList<CalendarDay> dotted_date_list;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.KOREA);
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd",Locale.KOREA);

    //1. 현재 시간으로 가져오는 방법
    Integer year = Integer.parseInt(yearFormat.format(date));
    Integer month = Integer.parseInt(monthFormat.format(date));
//    Integer day = Integer.parseInt(dayFormat.format(date));

    //2. 무조건 1월부터 시작하는 방법
//    Integer year = Integer.parseInt(yearFormat.format(date));
//    Integer month = 0;
    Integer day = 1;

    //lhj_0_end

    ViewGroup rootView;

    private final List<String> dates=CalendarData.dates; //포스트 데이터 전체 리스트 post/id/date

    //** 사용자 최초 회원 가입 날짜
    private String user_join_date;

    public DashboardListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.w(VM_ENUM.TAG,"[DashboardListFragment] onCreateView 호출");

        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_dashboard_list, container, false);

        //배열 초기화
        dotted_date_list = new ArrayList<CalendarDay>();

        //lhj_1_start
        recyclerView = rootView.findViewById(R.id.continuous_cal_rview);
        linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        //** 사용자 최초 회원 가입 날짜
        user_join_date=null;
        if(getArguments().containsKey(VM_ENUM.IT_ARG_USER_JOIN_DATE)){
            user_join_date= getArguments().getString(VM_ENUM.IT_ARG_USER_JOIN_DATE);
            Log.d(VM_ENUM.TAG, "[DashListFragment] 사용자 최초 회원가입 일자 "+user_join_date);
        }
//        달력에 데이터 넣는 부분
        List<date_data> date_list = new ArrayList<>();

        //"회원 가입" 연도, 월 ~ "현재" 연도, 월까지 표시해야함
        String[] user_join_arr = user_join_date.split("-");
//        for(int i=0;i<user_join_arr.length;i++) {
//            Log.d("가입 날짜 확인",user_join_arr[i]);
//        }
        Integer user_join_year = Integer.parseInt(user_join_arr[0]);
        Integer user_join_month = Integer.parseInt(user_join_arr[1]);



        for(int i=user_join_year;i<=year;i++){
//            Log.d("가입 연도 확인",i+"년도 ");
            for(int j=user_join_month-1;j<=month-1;j++){
//                Log.d("가입 월 확인",j+"월");
//                Log.d("가입 연도, 월 확인",i+"년도 "+j+"월");
                date_list.add(new date_data(i, j, day));
            }
        }

        //1월부터 12월까지 테스트로 넣는 코드
//        for(int i=0;i<12;i++) {
//            date_list.add(new date_data(year, month + i, day));
//        }
        //>> ds.shim 2020-01-26

        for(int i=0;i<dates.size();i++){
            String year=dates.get(i).split("-")[0];
            String month=dates.get(i).split("-")[1];
            String day=dates.get(i).split("-")[2];

//            date_list.add(new date_data(Integer.parseInt(year),
//                    Integer.parseInt(month),
//                    Integer.parseInt(day)));

            CalendarDay test_dotted_date = CalendarDay.from(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            dotted_date_list.add(test_dotted_date);

        }


        recyclerViewAdapter = new calendarListAdapater(this,date_list,dotted_date_list);
        recyclerView.setAdapter(recyclerViewAdapter);

        //lhj_1_end

        return rootView;

    }


}