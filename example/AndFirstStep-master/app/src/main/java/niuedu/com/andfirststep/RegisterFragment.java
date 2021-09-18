package niuedu.com.andfirststep;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        //取得取消按钮
        Button buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        //响应取消按钮的点击事件
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭当前Activity
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();//从栈中弹出当前的Fragment
            }
        });

        //取得Ok按钮
        Button buttonOk = (Button) view.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取控件
                EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
                EditText editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
                EditText editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
                EditText editTextPhone = (EditText) view.findViewById(R.id.editTextPhone);
                EditText editTextAddress = (EditText) view.findViewById(R.id.editTextAddress);
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

                //获取控件中的数据
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String address = editTextAddress.getText().toString();
                boolean sex = false; //性别，我们设true代表男，false代表女。默认为女。
                //获取单选按钮组中被选中的按钮的ID
                int checkRadioId = radioGroup.getCheckedRadioButtonId();
                //如果这个id等于代表男的单选按钮的id，则把sex置为true
                if(checkRadioId == R.id.radioButtonMale){
                    sex = true;
                }

                //注册
                //TODO：做好后台服务器后要实现此处代码

                //注册完成后，将用户名和密码保存到Activity中。
                MainActivity activity = (MainActivity) getActivity();
                if(activity != null){
                    activity.userName = name;
                    activity.password = password;
                }

                //回到上一个页面
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();//从栈中弹出当前的Fragment
            }
        });

        return view;
    }
}
