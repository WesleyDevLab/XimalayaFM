package ximalayafm.beiing.com.ximalayafm.fragments.dicover;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.TaskAction;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;
import ximalayafm.beiing.com.ximalayafm.tasks.DiscoverCategoryTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverCatergoryFragment extends BaseFragment implements TaskCallBack {


    public DiscoverCatergoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DiscoverCategoryTask task = new DiscoverCategoryTask(this);
//        task.execute();
//        Toast.makeText(getActivity(), "DiscoverCatergoryFragment : onCreate" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if(result != null){
            int action = result.action;
            if(action == TaskAction.TASK_ACTION_DISCOVER_CATEGORIES){
                // TODO 结果是从 发现-分类任务中返回的,获取的是分类
                if(result.resultCode == Constants.TASK_RESULT_OK){
                    //TODO 加载成功
                    Object data = result.data;
                    if(data != null && data instanceof List){
                        List list = (List) data;
//                        Log.d("list", "list : " + list);
//                        resultTv.setText(list.toString());
//                        Toast.makeText(getActivity(), "size:" + list.size(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // TODO 加载失败
                }

            }
        }
    }

    /**
     * 一共有5个fragment，如果ViewPager不做任何设置时，每当一个fragment与当前显示的fragment间隔1时就会销毁view，但不会销毁fragment，
     * 显示该界面时，该方法会重新调用，onCreate不会再次调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_catergory, container, false);
//        Toast.makeText(getActivity(), "DiscoverCatergoryFragment : onCreateView" , Toast.LENGTH_SHORT).show();

        ListView listView = (ListView) ret.findViewById(R.id.text_list);
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            data.add("jjj" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity()
            ,android.R.layout.simple_list_item_1,data
        );
        listView.setAdapter(adapter);
        return ret;
    }


    @Override
    public String getFragmentTitle() {
        return "分类";
    }


}
