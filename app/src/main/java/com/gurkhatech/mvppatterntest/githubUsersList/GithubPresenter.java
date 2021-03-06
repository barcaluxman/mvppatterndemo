package com.gurkhatech.mvppatterntest.githubUsersList;

import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gurkhatech.mvppatterntest.R;
import com.gurkhatech.mvppatterntest.utils.lib.SwissKnifeRecyclerViewAdapter;
import com.gurkhatech.mvppatterntest.githubUsersList.models.GithubUserData;
import com.gurkhatech.mvppatterntest.githubUsersList.viewholders.GitHubUserListViewHolder;
import com.gurkhatech.mvppatterntest.githubUsersList.viewholders.GitHubUserListViewHolderAlter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adventure on 3/7/17.
 * erlaxmanbhattarai@gmail.com
 * itzluxman@gurkhatech.com
 * copyright reserved
 * gurkhatech.com
 */

class GithubPresenter implements GithubContract.Presenter {

    private GithubContract.View view;
    private GithubContract.Model model;

    GithubPresenter(GithubContract.View view, GithubContract.Model model) {

        this.view = view;
        this.model = model;
    }

    @Override
    public void searchUsers(String userName) {
        model.getUsersAndPromptPresenter(userName, this);
        enableSearch(false);
    }

    @Override
    public void loadUsers(List<GithubUserData> data) {
        ViewGroup parent = GithubView.binding.activityGithubList;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SwissKnifeRecyclerViewAdapter.SwissKnifeViewHolder viewHolder;

        if (data != null) {  //Do not check userList.size directly coz it may return nullPointer
            if (!data.isEmpty()) {
                List<SwissKnifeRecyclerViewAdapter.SwissKnifeData> users = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    try {
                        viewHolder = data.get(i).getUserName().length() > 10 ?
                            new GitHubUserListViewHolder(inflater.
                                inflate(R.layout.item_github_use_list, parent, false)) :
                            new GitHubUserListViewHolderAlter(inflater.
                                inflate(R.layout.item_github_use_list_alter, parent, false));

                        users.add(new SwissKnifeRecyclerViewAdapter.SwissKnifeData(data.get(i), viewHolder));

                    } catch (Exception ignored) {
                    }
                }
                view.displayUsers(users);
            } else {
                view.displayNoDataError(model.getNoDataErrorTitle(), model.getNoDataErrorBody(), model.getNoDataErrorImage());
            }
        } else {
            alertGenericError(model.getGenericErrorTitle(), model.getGenericErrorBody(), model.getGenericErrorImage());
        }
        enableSearch(true);

    }


    @Override
    public void cancelRequests() {
        enableSearch(true);
        model.cancelRequests();
    }

    @Override
    public void enableSearch(boolean enable) {
        view.enableSearch(enable);
    }

    @Override
    public void alertGenericError(String title, String body, @DrawableRes int imageID) {
        view.displayGenericError(title, body, imageID);
        enableSearch(true);
    }

    @Override
    public void alertNetworkError(String title, String body, @DrawableRes int imageID) {
        view.displayNetworkError(title, body, imageID);
        enableSearch(true);
    }

    @Override
    public void alertNoInternetConnection(String title, String body, @DrawableRes int imageID) {
        view.displayNoInternetConnection(title, body, imageID);
        enableSearch(true);
    }

}
