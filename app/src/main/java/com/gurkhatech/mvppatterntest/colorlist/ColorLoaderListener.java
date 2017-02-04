package com.gurkhatech.mvppatterntest.colorlist;

import retrofit2.Call;
import retrofit2.Callback;

import static com.gurkhatech.mvppatterntest.R.id.colorText;

/**
 * Created by adventure on 2/4/17.
 * erlaxmanbhattarai@gmail.com
 * itzluxman@gurkhatech.com
 * copyright reserved
 * gurkhatech.com
 */
class ColorLoaderListener implements IColorLoaderListener,IColorPresenter {

private IColorView view;
private ColorModel model;


public static ColorLoaderListener getInstance( IColorView view){
    return new ColorLoaderListener (view);
}
private ColorLoaderListener ( IColorView view ){
    this(view,new ColorModel ());

}

private ColorLoaderListener ( IColorView view, ColorModel model ){
    this.view = view;
    this.model =model;
}
@Override
public void onColorLoaded ( String text) {
    view.setText ( text );

}

@Override
public void colorLoadFailed (String text ) {
    view.setText ( text );
}

@Override
public void loadColor ( ) {

    model.downloadColor(this);


}
}
