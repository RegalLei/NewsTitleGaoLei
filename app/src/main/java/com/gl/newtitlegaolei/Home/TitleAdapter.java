package com.gl.newtitlegaolei.Home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gl.newtitlegaolei.Beans.Bean;
import com.gl.newtitlegaolei.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/2/15 18:45
 * 班级：1501A
 */
public class TitleAdapter extends BaseAdapter {

    private Context context ;
    private List<Bean.T1370583240249Bean> lists=new ArrayList<>();
    public TitleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Rong rong = null;
        if (view == null) {
            rong = new Rong();
            view = View.inflate(context, R.layout.item_title, null);
            rong.tv_home_item = (TextView) view.findViewById(R.id.tv_home_item);
            view.setTag(rong);
        } else {
            rong = (Rong) view.getTag();
        }
        rong.tv_home_item.setText(lists.get(position).getDigest());
        return view;
    }

    public void addData(List<Bean.T1370583240249Bean> list,boolean isNeedClear) {
        if(list!=null){
            if(isNeedClear){
                lists.clear();
            }
           lists.addAll(list);
        }
    }

    static class Rong {
        TextView tv_home_item;
    }
}
