package com.gurkhatech.mvppatterntest.githubList.view.modules;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gurkhatech.mvppatterntest.githubList.view.GithubListActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adventure on 2/11/17.
 * erlaxmanbhattarai@gmail.com
 * itzluxman@gurkhatech.com
 * copyright reserved
 * gurkhatech.com
 */

@Module(includes = { GithubListActivityModule.class })
class GlideActivityModule {

@Provides
@GithubListActivityScope
RequestManager requestManager ( GithubListActivity activity ) {
    return Glide.with ( activity );
}
}
