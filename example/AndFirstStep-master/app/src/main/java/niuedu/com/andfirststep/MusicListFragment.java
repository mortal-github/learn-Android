package niuedu.com.andfirststep;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListFragment extends Fragment {
    private RecyclerView musicListView;
    private List<Object> data = new ArrayList<>();

    public MusicListFragment() {
        data.add(new MusicInfo("马云云","踩蘑菇的小姑娘",4));
        data.add(new MusicInfo("贝克汗脚","我是真的还想再借五百元",2));
        data.add(new MusicInfo("杰克孙","一行白鹭上西天",2));
        //插入一条广告
        data.add(new Advertising("蓝翔","中国航天人才的摇蓝指定生产厂家"));
        data.add(new MusicInfo("牛德华","一个爱上浪嫚的人",2));
        data.add(new MusicInfo("王钢烈","菊花残",5));
        data.add(new MusicInfo("罗金凤","一天到晚游泳的驴",4));

        //不调用这一句，Fragment的菜单显示不出来
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_music_list, container, false);

        musicListView = (RecyclerView) view.findViewById(R.id.musicListView);
        musicListView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicListView.setAdapter(new MyAdapter());

        return view;
    }

    private abstract class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyViewHolder extends BaseViewHolder{
        TextView viewSinger ;
        TextView viewTitle;
        RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            viewSinger=(TextView) itemView.findViewById(R.id.textViewSinger);
            viewTitle=(TextView) itemView.findViewById(R.id.textViewTitle);
            ratingBar=(RatingBar) itemView.findViewById(R.id.ratingBar);

            //让行的根View响应点击事件来实现选择一行的效果
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //取出当前行的信息，显示出来
                    int position = getAdapterPosition();
                    MusicInfo musicInfo = (MusicInfo)data.get(position);
                    Snackbar.make(v, "你选了第"+position+"行,歌名是："+musicInfo.getTitle(),
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    private class AdvertisingViewHolder extends BaseViewHolder{
        //显示广告主的名字
        TextView textViewAdvertiser;
        //显示广告内容
        TextView textViewContent;
        public AdvertisingViewHolder(View itemView) {
            super(itemView);
            textViewAdvertiser = itemView.findViewById(R.id.textViewAdvertiser);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //响应本Fragment中的菜单项的选择
        int id = item.getItemId();
        if(id == R.id.add_one_music_info){
            //向列表添加一项
            MusicInfo musicInfo = new MusicInfo("新歌手","一首新歌",1);
            data.add(1,musicInfo);
            //利用Adapter 通知 RecyclerView，刷新数据
            musicListView.getAdapter().notifyItemInserted(1);
            return true;//返回true表示此菜单项被响应了
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //从资源创建菜单
        inflater.inflate(R.menu.music_list_menu, menu);
        //别忘了调用父类的方法
        super.onCreateOptionsMenu(menu,inflater);
    }

    //用于为RecyclerView提供数据的适配器类
    private class MyAdapter extends RecyclerView.Adapter<BaseViewHolder>{

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater(null);
            //viewType就是layout的id
            View view=inflater.inflate(viewType, parent, false);
            BaseViewHolder viewHolder;
            if(viewType==R.layout.music_list_item) {
                viewHolder = new MyViewHolder(view);
            }else{
                viewHolder = new AdvertisingViewHolder(view);
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
            //获取这一行对应的List项
            Object item = data.get(position);
            if(item instanceof MusicInfo) {
                //将数据设置到对应的控件中
                MusicInfo musicInfo = (MusicInfo) item;
                MyViewHolder vh = (MyViewHolder) viewHolder;
                vh.viewSinger.setText(musicInfo.getSinger());
                vh.viewTitle.setText(musicInfo.getTitle());
                vh.ratingBar.setRating(musicInfo.getLike());
            }else{
                Advertising advertising= (Advertising) item;
                AdvertisingViewHolder vh= (AdvertisingViewHolder) viewHolder;
                vh.textViewAdvertiser.setText(advertising.getAdvertiser());
                vh.textViewContent.setText(advertising.getContent());
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public int getItemViewType(int position) {
            //根据参数position，返回每行对应的ViewType的值，为了方便
            //我们直接将行layout的ID做为ViewType的值
            if(data.get(position) instanceof MusicInfo){
                //这条对应的数据是MusicInfo
                return R.layout.music_list_item;
            }else{
                //这条对应的信息是Advertising
                return R.layout.music_list_advertising_item;
            }
        }
    }

}
