package franklincbc.com.receitas.http;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import franklincbc.com.receitas.models.Usuario;

/**
 * Created by frank on 20/05/2017.
 */

public class ReceitaDeleteTask extends AsyncTaskLoader<Boolean> {

    private String receitaId;
    Context ctx;

    public ReceitaDeleteTask(Context context, Integer loaderID, Bundle params) {
        super(context);
        this.ctx = context;
        this.receitaId = (String) params.getString("receitaId");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Boolean loadInBackground() {
        boolean retorno = false;
        try {
            retorno = ReceitaHttp.Receita_DELETE(receitaId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return retorno;
    }

}
