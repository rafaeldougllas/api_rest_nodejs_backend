package franklincbc.com.receitas.http;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import franklincbc.com.receitas.models.Usuario;

/**
 * Created by frank on 18/05/2017.
 */

public class UsuarioGetAllTask extends AsyncTaskLoader<List<Usuario>> {

    private List<Usuario> mLstUsuarios = null;
    Context ctx;

    public UsuarioGetAllTask(Context context, Integer loaderID, Bundle params) {
        super(context);
        this.ctx = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Usuario> loadInBackground() {
        try {
            mLstUsuarios = UsuarioHttp.UsuariosGetAll();
            if (mLstUsuarios == null) {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mLstUsuarios;
    }

}
