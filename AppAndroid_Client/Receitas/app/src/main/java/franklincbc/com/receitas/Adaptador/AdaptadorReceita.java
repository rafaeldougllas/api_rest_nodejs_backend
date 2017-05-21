package franklincbc.com.receitas.Adaptador;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import franklincbc.com.receitas.R;
import franklincbc.com.receitas.models.Receita;

/**
 * Created by frank on 20/05/2017.
 */

public class AdaptadorReceita extends BaseAdapter {

    ArrayList<Receita> lstReceitas;
    Activity act;

    public AdaptadorReceita(Activity act, ArrayList<Receita> lst){
        this.act = act;
        this.lstReceitas = lst;
    }

    @Override
    public int getCount() {
        return lstReceitas.size();
    }

    @Override
    public Object getItem(int position) {
        return lstReceitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_lista_receita, parent, false);
        Receita receita = lstReceitas.get(position);

        TextView titulo = (TextView) view.findViewById(R.id.item_lista_receita_textView_titulo);
        TextView data = (TextView) view.findViewById(R.id.item_lista_receita_textView_data_criacao);
        titulo.setText(receita.getTitulo());
        data.setText(receita.getDataCriacao());

        return view;
    }
}
