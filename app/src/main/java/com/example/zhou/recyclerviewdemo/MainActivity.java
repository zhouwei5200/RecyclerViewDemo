package com.example.zhou.recyclerviewdemo;

import android.app.Activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {
    RecyclerView  id_recyclerview;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);

        //垂直布局
        // id_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //cardView
       // id_recyclerview.setLayoutManager(new GridLayoutManager(this,5));
        //瀑布流
        id_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        id_recyclerview.setItemAnimator(new DefaultItemAnimator());
       //创建adapter对象
        MyAdapter myAdapter = new MyAdapter();
        id_recyclerview.setAdapter(myAdapter);
        myAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"1111", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });



    }

    private void initData() {
        arrayList = new ArrayList<>();
        for(int i = 0;i<170;i++){
            arrayList.add(i + "");
        }
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

     class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder>{
         LinearLayout ll_randler;
public  OnItemClickLitener onItemClickLitener;
         public  void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
             this.onItemClickLitener = onItemClickLitener;
         }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recyclerview,viewGroup,false));

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
            //得到一个随机数用于设置控件高度
            Random random = new Random();
            int ranHeight = (random.nextInt(10)+5)*15;
            ViewGroup.LayoutParams linearLayout = ll_randler.getLayoutParams();
            linearLayout.height = ranHeight;
            ll_randler.setLayoutParams(linearLayout);
            myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickLitener != null){
                        onItemClickLitener.onItemClick(v,i);
                    }

                }
            });
        }



        @Override
        public int getItemCount() {
            return arrayList.size();
        }
        class  MyViewHolder extends RecyclerView.ViewHolder{
            ImageView tv;



            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (ImageView) itemView.findViewById(R.id.text);
                ll_randler = (LinearLayout) itemView.findViewById(R.id.ll_randler);
            }

        }
    }


}
