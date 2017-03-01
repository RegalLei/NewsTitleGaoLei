package com.gl.newtitlegaolei.Home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gl.newtitlegaolei.Beans.VideoBean;
import com.gl.newtitlegaolei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 作者：高镭
 * 时间：2017/2/18 14:11
 * 班级：1501A
 */
public class ViewAdapter extends BaseAdapter {
    private Context context;
    private List<VideoBean> list = new ArrayList<>();
    private final DisplayImageOptions options;

    public ViewAdapter(Context context) {
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
        return list.size();
    }

    @Override
    public VideoBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
    VRong vrong = null;
    if(view==null){
        vrong=new VRong();
        view=View.inflate(context,R.layout.video_item,null);
        vrong.video_jiecao=  (JCVideoPlayerStandard) view.findViewById(R.id.video_jiecao);
        vrong.vitem_tv_1= (TextView) view.findViewById(R.id.vitem_tv_1);
        vrong.vitem_tv_2= (TextView) view.findViewById(R.id.vitem_tv_2);
        view.setTag(vrong);
    }else{
     vrong= (VRong) view.getTag();
    }
        vrong.video_jiecao.setUp(list.get(position).getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST,list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getCover(),vrong.video_jiecao.thumbImageView,options);
        vrong.vitem_tv_1.setText(list.get(position).getTopicName());
        vrong.vitem_tv_2.setText(":"+list.get(position).getPlayCount());
        return view;
    }

    public void addvData(List<VideoBean> vlist, boolean isNeedClear) {
        if (vlist != null) {
            if (isNeedClear) {
                list.clear();
            }
            list.addAll(vlist);
        }
    }

    static class VRong {
        JCVideoPlayerStandard video_jiecao;
        TextView vitem_tv_1,vitem_tv_2;
    }
}
