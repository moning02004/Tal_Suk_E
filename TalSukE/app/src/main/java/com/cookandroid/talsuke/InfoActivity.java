package com.cookandroid.talsuke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cookandroid.talsuke.Adapter.InfoAdapter;
import com.cookandroid.talsuke.Model.InfoItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<InfoItem> data ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle("정보 게시판");

        ListView listView = (ListView) findViewById(R.id.info_board);

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date( );
        String dTime = currentTime.toString();

        //아이템 설정 -> JSON으로 변경
        this.data = new ArrayList<InfoItem>();
        InfoItem item1 = new InfoItem("[공지사항] ", " 김준희", dTime, "여기에 내용");
        InfoItem item2 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item3 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item4 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item5 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item6 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item7 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item8 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item9 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item10= new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");
        InfoItem item11 = new InfoItem("[공지사항] 날", " 기무준희", dTime, "여기에 내용조립다");


        //리스트에 추가
        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        data.add(item8);
        data.add(item9);
        data.add(item10);
        data.add(item11);


        //아이템 연결
        InfoAdapter adapter = new InfoAdapter(this, R.layout.activity_info_item, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View v, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InfoClickedActivity.class);
                intent.putExtra("info_title", data.get(i).getTitle());
                intent.putExtra("info_author", data.get(i).getAuthor());
                intent.putExtra("info_date", data.get(i).getDate());
                intent.putExtra("info_content", data.get(i).getContent());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
