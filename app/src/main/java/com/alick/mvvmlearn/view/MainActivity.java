package com.alick.mvvmlearn.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.model.User;
import com.alick.mvvmlearn.viewmodel.UserViewModel;
import com.alick.mvvmlearn.widget.HolderView;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private TextView tv_userinfo;
    private Button btn_serach;


    private UserViewModel userViewModel;
    private HolderView holderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    private void initViews() {
        tv_userinfo=findViewById(R.id.tv_userinfo);
        et_username=findViewById(R.id.et_username);
        btn_serach =findViewById(R.id.btn_serach);

        holderView=findViewById(R.id.holder_view_id);

        btn_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }

                holderView.showLoadingView();
                userViewModel.reload(username);
            }
        });
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    tv_userinfo.setText(user.toString());
                    holderView.showRealContentView();
                }
            }
        });
    }

}
