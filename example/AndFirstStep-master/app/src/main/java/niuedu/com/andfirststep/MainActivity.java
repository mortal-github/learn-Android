package niuedu.com.andfirststep;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //保存刚注册的用户名和密码
    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //从layout资源文件中加资控件并设置给Activity。
        setContentView(R.layout.activity_main);

        //获取Action bar
        android.support.v7.app.ActionBar actionBar = this.getSupportActionBar();
        //显示返回图标
        actionBar.setDisplayHomeAsUpEnabled(true);

        //将第一个Fragment（即登录Fragment）加入Activity中
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        //设置Fragment间的转场动画，使用自定义动画，必须放在Fragment改变操作之前
        fragmentTransaction.setCustomAnimations(R.anim.in_anim1,R.anim.out_anim1);
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //点了Action bar上的返回图标
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(fragmentManager.getBackStackEntryCount() == 0){
                //如果后退栈空了，则说明回到了最初页面，显示退出提示对话框
                ExitDialogFragment dialogFragment = new ExitDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "exit");
            }else {
                //从后退栈中弹出当前的Fragment
                fragmentManager.popBackStack();
            }

            //处理过的条目必须返回true
            return true;
        }else if(id==R.id.action_settings){
            //选了设置菜单项，显示一条提示信息
            Snackbar.make(findViewById(R.id.fragment_container),
                    "你选了设置",
                    Snackbar.LENGTH_LONG).show();
        }else if(id == R.id.action_submenu_item){
            //选了子菜单下的子菜单项，显示一条提示信息
            Snackbar.make(findViewById(R.id.fragment_container),
                    "你选了子菜单项",
                    Snackbar.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取用于从资源创建菜单的工具
        MenuInflater inflater = getMenuInflater();
        //从资源创建菜单，传入menu表示把创建出来的菜单项放到menu中。
        inflater.inflate(R.menu.main, menu);
        //返回true，菜单就会被显示，否则不显示
        return true;
    }

    public static class ExitDialogFragment extends DialogFragment {
        //重写父类的方法，在此方法中创建Dialog对象并返回
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //创建对话框之前，设置一些对话框的配置或数据
            //设置对话框中显示的主内容
            builder.setMessage(R.string.exit_or_not)
                    //设置对话框中的正按钮以及按钮的响应方法，指相当于OK，YES之类的按钮
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //退出当前的Activity
                            getActivity().finish();
                        }
                    })
                    //设置对话框中的负按钮以及按钮的响应方法，指相当于取消之类的按钮
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //用户点了取消，什么也不干
                        }
                    });
            //创建对话框并返回它
            return builder.create();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() == 0){
            //如果后退栈空了，则说明回到了最初页面，显示退出提示对话框
            ExitDialogFragment dialogFragment = new ExitDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "exit");
            return true;
        }else {
            //执行默认的操作
            return super.onKeyDown(keyCode,event);
        }
    }
}

