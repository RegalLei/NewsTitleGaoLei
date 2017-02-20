package com.gl.newtitlegaolei.Home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gl.newtitlegaolei.Beans.Bean;
import com.gl.newtitlegaolei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/2/15 18:45
 * 班级：1501A
 */
public class TitleAdapter extends BaseAdapter {

    private Context context;
    private List<Bean> lists = new ArrayList<>();

    private int TYPE_1 = 0;
    private int TYPE_2 = 1;
    private int TYPE_3 = 2;
    private final DisplayImageOptions options;

    public TitleAdapter(Context context) {

        this.context = context;
        //.displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                //.displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                .build();

    }

    @Override
    public int getCount() {

        return lists.size();
    }

    //说明一共有几种类型的Item (条目)
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    //根据position 来确定返回View 类型

    @Override
    public int getItemViewType(int position) {
        Bean item = getItem(position);
        if (item.getImgextra() != null) {
            return TYPE_3;
        } else if (item.getImgsrc() != null) {
            return TYPE_2;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public Bean getItem(int i) {

        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Rong rong = null;
        Rong2 rong2 = null;
        Rong3 rong3 = null;
        if (view == null) {
            switch (getItemViewType(position)){
                case 0:
                    rong = new Rong();
                    view = View.inflate(context, R.layout.home_item, null);
                    rong.home_tv_item = (TextView) view.findViewById(R.id.home_tv_item);
                    rong.item_tv_1= (TextView) view.findViewById(R.id.item_tv_1);
                    rong.item_tv_2= (TextView) view.findViewById(R.id.item_tv_2);
                    rong.item_tv_3= (TextView) view.findViewById(R.id.item_tv_3);
                    view.setTag(rong);
                    break;

                case 1:
                    rong2 = new Rong2();
                    view=View.inflate(context, R.layout.home_item2, null);
                    rong2.home_tv_item2= (TextView) view.findViewById(R.id.home_tv_item2);
                    rong2.item_tv2_1= (TextView) view.findViewById(R.id.item_tv2_1);
                    rong2.item_tv2_2= (TextView) view.findViewById(R.id.item_tv2_2);
                    rong2.item_tv2_3= (TextView) view.findViewById(R.id.item_tv2_3);
                    rong2.home_iv_item2 = (ImageView) view.findViewById(R.id.home_iv_item2);
                    view.setTag(rong2);
                    break;

                case 2:
                    rong3 = new Rong3();
                    view=View.inflate(context, R.layout.home_item3, null);
                    rong3.home_tv_item3= (TextView) view.findViewById(R.id.home_tv_item3);
                    rong3.item_tv3_1= (TextView) view.findViewById(R.id.item_tv3_1);
                    rong3.item_tv3_2= (TextView) view.findViewById(R.id.item_tv3_2);
                    rong3.item_tv3_3= (TextView) view.findViewById(R.id.item_tv3_3);
                    rong3.home_iv_item3_1 = (ImageView) view.findViewById(R.id.home_iv_item3_1);
                    rong3.home_iv_item3_2 = (ImageView) view.findViewById(R.id.home_iv_item3_2);
                    rong3.home_iv_item3_3 = (ImageView) view.findViewById(R.id.home_iv_item3_3);
                    view.setTag(rong3);
                    break;
            }

        } else {

            switch (getItemViewType(position)){

                case 0:
                    rong = (Rong) view.getTag();
                    break;
                case 1:
                    rong2 = (Rong2) view.getTag();
                    break;
                case 2:
                    rong3 = (Rong3) view.getTag();
                    break;
            }
            }
                switch (getItemViewType(position)){
                    case 0:
                        rong.home_tv_item.setText(lists.get(position).getTitle());
                        rong.item_tv_1.setText(lists.get(position).getSource());
                        rong.item_tv_2.setText("评论数:"+lists.get(position).getReplyCount()+"");
                        rong.item_tv_3.setText(lists.get(position).getLmodify());
                        break;
                    case 1:
                        rong2.home_tv_item2.setText(lists.get(position).getTitle());
                        rong2.item_tv2_1.setText(lists.get(position).getSource());
                        rong2.item_tv2_2.setText("评论数:"+lists.get(position).getReplyCount()+"");
                        rong2.item_tv2_3.setText(lists.get(position).getLmodify());
                        ImageLoader.getInstance().displayImage(lists.get(position).getImgsrc(),rong2.home_iv_item2, options);
                        break;
                    case 2:
                        rong3.home_tv_item3.setText(lists.get(position).getTitle());
                        rong3.item_tv3_1.setText(lists.get(position).getSource());
                        rong3.item_tv3_2.setText("评论数:"+lists.get(position).getReplyCount()+"");
                        rong3.item_tv3_3.setText(lists.get(position).getLmodify());
                        ImageLoader.getInstance().displayImage(lists.get(position).getImgextra().get(0).getImgsrc(), rong3.home_iv_item3_1, options);
                        ImageLoader.getInstance().displayImage(lists.get(position).getImgextra().get(1).getImgsrc(), rong3.home_iv_item3_2, options);
                        ImageLoader.getInstance().displayImage(lists.get(position).getImgsrc(), rong3.home_iv_item3_3, options);
                        break;
                }
        return view;
    }

    public void addData(List<Bean> list, boolean isNeedClear) {
        if (list != null) {
            if (isNeedClear) {
                lists.clear();
            }
            lists.addAll(list);
        }
    }

    static class Rong {
        TextView home_tv_item,item_tv_1,item_tv_2,item_tv_3;
    }

    static class Rong2 {
        TextView home_tv_item2,item_tv2_1,item_tv2_2,item_tv2_3;
        ImageView home_iv_item2;
    }

    static class Rong3 {
        TextView home_tv_item3,item_tv3_1,item_tv3_2,item_tv3_3;
        ImageView home_iv_item3_1;
        ImageView home_iv_item3_2;
        ImageView home_iv_item3_3;
    }
}
