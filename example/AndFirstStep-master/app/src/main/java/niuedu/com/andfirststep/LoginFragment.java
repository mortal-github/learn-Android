package niuedu.com.andfirststep;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by nkm on 2017/6/28.
 */

public class LoginFragment extends Fragment {
    EditText editTextName;
    EditText editTextPassword;
    View imageView ;
    RelativeLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载Fragment的界面
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        layout = (RelativeLayout) v.findViewById(R.id.layout);
        //获取用户名和密码输入控件
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextPassword = (EditText) v.findViewById(R.id.editTextPassword);
        imageView = v.findViewById(R.id.imageViewHead);

        //用id找到用户输入控件
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        //用代码设置它的提示
        editTextName.setHint("请输入用户名");

//        //利用RoundedBitmapDrawable搞出圆形图像
//        //获取头像控件，实际上是把图像文件解码后创建Bitmap对象
//        //必须是ImageView
//        ImageView imageView=v.findViewById(R.id.imageViewHead);
//        //从Drawable资源获取Bitmap
//        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
//        //创建RoundedBitmapDrawable对象
//        RoundedBitmapDrawable roundedBitmapDrawable =
//                RoundedBitmapDrawableFactory.create(getResources(), src);
//        //设置圆角半径（根据实际需求）
//        roundedBitmapDrawable.setCornerRadius(400);
//        //设置反锯齿
//        roundedBitmapDrawable.setAntiAlias(true);
//        //将Drawable设置给ImageView控件，这会覆盖掉在界面设计器中的设置的图像
//        imageView.setImageDrawable(roundedBitmapDrawable);

        //找到登录按钮
        Button buttonLogin = (Button) v.findViewById(R.id.buttonLogin);
        //添加侦听器，响应按钮的click事件
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MusicListFragment fragment = new MusicListFragment();
                //使用默认转场动画
                fragmentTransaction.setCustomAnimations(R.anim.in_anim1,R.anim.in_anim2,R.anim.out_anim1,R.anim.out_anim2);
                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("music_list");
                fragmentTransaction.commit();
            }
        });

        //找到注册按钮，为它设置点击事件侦听器
        Button buttonRegister = (Button) v.findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当注册按钮被执行时调用此方法
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegisterFragment fragment = new RegisterFragment();

                //设置Fragment间的转场动画，使用自定义动画，必须放在Fragment改变操作之前
                fragmentTransaction.setCustomAnimations(R.anim.in_anim1,R.anim.out_anim1,R.anim.in_anim2,R.anim.out_anim2);

                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("login");
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    //从资源加载动画并播放
    private void testAnimateResource() {
        //利用AnimatorInflater从资源加载动画
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(
                getContext(), R.animator.test_animate);
        //资源中并没有指定动画要应用到哪个控件上，所以在这里指定。
        set.setTarget(imageView);
        set.start();
    }

    //测试视图动画
    public void testViewAnimation(){
        //创建一个旋转动画（动哪里?动角度）
        RotateAnimation animation =new RotateAnimation(0.0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        //设置重复模式,REVERSE的意思是动完一次后接着反向动（如何重复）
        animation.setRepeatMode(Animation.RESTART);
        //设置持续时间,1000毫秒（动多长时间）
        animation.setDuration(1000);
        //设置重复次数
        animation.setRepeatCount(10);
        //设置为匀速动画，默认是先慢后快再慢。（如何动?保持同一速度）
        animation.setInterpolator(new LinearInterpolator());

        //创建一个缩放动画，在X和Y坐轴上都是从0.5到1.5.
        ScaleAnimation scaleAnimation=new ScaleAnimation(0.5f,1.5f,0.5f,1.5f);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setDuration(2000);
        //设置动画次数为永不停止
        scaleAnimation.setRepeatCount(animation.INFINITE);

        //创建动画对象，参数表示是否所有动画共享同一个插值函数
        AnimationSet animationSet=new AnimationSet(false);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setStartOffset(1000);

        //启动动画（动谁?动imageView）
        imageView.startAnimation(animationSet);
    }

    //测试ViewGroup的Layout动画
    void testLayoutAnimate(){
        //创建一个新按钮
        Button btn=new Button(getContext());
        //设置它显示的文本
        btn.setText("我是被动态添加的");
        //创建一个排版参数对象
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //把应用这个LayoutParams（排版参数）的控件放在注册按钮的下面
        layoutParams.addRule(RelativeLayout.BELOW,R.id.buttonRegister);
        //左边与注册按钮对齐
        layoutParams.addRule(RelativeLayout.ALIGN_START,R.id.buttonRegister);
        //右边也与注册按钮对齐
        layoutParams.addRule(RelativeLayout.ALIGN_END,R.id.buttonRegister);
        //设置外部空白，参数分别为左，上，右，下，实际效果是设置顶部与注册按钮相距
        //24个像素，注意这里的单位不是dp了。
        layoutParams.setMargins(0,24,0,0);
        //将这个排版参数应用到新建的按钮中
        btn.setLayoutParams(layoutParams);

        //==============设置Layout动画==================
        LayoutTransition transition = new LayoutTransition();
        //当一个控件出现时，我希望它是的大小变化有动画
        //利用AnimatorInflater从资源加载动画
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(
                getContext(), R.animator.test_animate);
        //设置控件出现时的动画
        transition.setAnimator(LayoutTransition.APPEARING,set);
        //设置一个控件出现时，其它控件位置改变动画的持续时间
        transition.setDuration(LayoutTransition.CHANGE_APPEARING,4000);
        //将包含动画的LayoutTransition对象设置到ViewGroup控件中
        layout.setLayoutTransition(transition);
        //============================================
        //将按钮加到RelativeLayout中
        layout.addView(btn);
    }

    //测试属性动画
    public void testPropertyAnimator(){
        //创建一个旋转动画
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView,"rotation",0.f,180.f);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(2);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //创建一个缩放动画，X轴
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(imageView,"scaleX",0.5f,1.5f);
        scaleAnimatorX.setDuration(1000);
        scaleAnimatorX.setRepeatCount(10);
        scaleAnimatorX.setRepeatMode(ValueAnimator.REVERSE);

        //创建一个缩放动画，y轴
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(imageView,"scaleY",0.5f,1.5f);
        scaleAnimatorY.setDuration(1000);
        scaleAnimatorY.setRepeatCount(10);
        scaleAnimatorY.setRepeatMode(ValueAnimator.REVERSE);

        //创建一个动画组
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(rotateAnimator);
        animatorSet.start();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);

        //如果用户名和密码非空，则赋给相应控件
        MainActivity activity = (MainActivity) getActivity();
        if(activity != null) {
            if (activity.userName != null) {
                editTextName.setText(activity.userName);
            }
            if (activity.password != null) {
                editTextPassword.setText(activity.password);
            }
        }
    }
}
