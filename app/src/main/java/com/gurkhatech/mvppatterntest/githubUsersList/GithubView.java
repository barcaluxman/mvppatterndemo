package com.gurkhatech.mvppatterntest.githubUsersList;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gurkhatech.mvppatterntest.R;
import com.gurkhatech.mvppatterntest.databinding.ActivityGithubListBinding;
import com.gurkhatech.mvppatterntest.githubUsersList.models.GithubUserData;
import com.gurkhatech.mvppatterntest.utils.MyApplication;
import com.gurkhatech.mvppatterntest.utils.commons.CommonAdapter;
import com.gurkhatech.mvppatterntest.utils.di.AppDaggerComponent;
import com.gurkhatech.mvppatterntest.utils.di.ContextModule;
import com.gurkhatech.mvppatterntest.utils.di.DaggerAppDaggerComponent;
import com.gurkhatech.mvppatterntest.utils.lib.SwissKnifeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.gurkhatech.mvppatterntest.R.id.userList;

/**
 * Created by adventure on 3/7/17.
 * erlaxmanbhattarai@gmail.com
 * itzluxman@gurkhatech.com
 * copyright reserved
 * gurkhatech.com
 */

public class GithubView extends AppCompatActivity implements GithubContract.View {

    private static AppDaggerComponent appDaggerComponent;
    @Inject
    LinearLayoutManager linearLayoutManager;
    CommonAdapter userListAdapter = new CommonAdapter();


    public static ActivityGithubListBinding binding;
    GithubPresenter presenter = new GithubPresenter(this, new GithubModel(this));

    public static AppDaggerComponent getDaggerComponent() {

        return appDaggerComponent == null ? MyApplication.getInstance().getComponent() : appDaggerComponent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this, R.layout.activity_github_list);
        appDaggerComponent = DaggerAppDaggerComponent.builder().contextModule(new ContextModule(this)).build();
        appDaggerComponent.injectGithubView(this);
        binding.userList.setLayoutManager(linearLayoutManager);
        binding.userList.setAdapter(userListAdapter);

    }

    public void search(View v) {
        presenter.searchUsers(binding.userInput.getText().toString());
    }

    /*@Override
    public void displayUsers(List<GithubUserData> userList) {
       // userListAdapter.replaceData(userList);
        List<SwissKnifeRecyclerViewAdapter.SwissKnifeModel> data = new ArrayList<>();
        try {
            for (int i = 0; i < userList.size(); i++) {
                data.add(userList.get(i));
            }

           userListAdapter.replaceData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/


    @Override
    public void displayUsers(List<GithubUserData> userList) {
       // userListAdapter.replaceData(userList);
        userListAdapter.replaceData( new ArrayList<SwissKnifeRecyclerViewAdapter.SwissKnifeModel>(userList));
    }

    @Override
    public void enableSearch(boolean enable) {
        binding.userInput.setClickable ( enable );
        binding.search.setClickable ( enable );
        binding.userInput.setEnabled ( enable );
        binding.search.setEnabled ( enable );

    }

    @Override
    public void displayNoDataError(String title, String body, @DrawableRes int imageID) {

    }

    @Override
    public void displayNoInternetConnection(String title, String body, @DrawableRes int imageID) {

    }

    @Override
    public void displayNetworkError(String title, String body, @DrawableRes int imageID) {

    }

    @Override
    public void displayGenericError(String title, String body, @DrawableRes int imageID) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.cancelRequests();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.enableSearch(true);
    }
}
