package franklincbc.com.receitas.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import franklincbc.com.receitas.models.Receita;
import franklincbc.com.receitas.models.Usuario;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by frank on 20/05/2017.
 */

class ReceitaHttp {


    public static final String URL_BASE = "http://192.168.25.105:3000";
    public static final String URL_RECEITA_GET_ALL        = URL_BASE + "/receitas2/";
    public static final String URL_RECEITA_POST        = URL_BASE + "/receitas2/";
    public static final String URL_RECEITA_GET            = URL_BASE + "/receitas2/%s";
    public static final String URL_RECEITA_PUT            = URL_BASE + "/receitas2/%s/";
    public static final String URL_RECEITA_PATCH          = URL_BASE + "/receitas2/%s/";
    public static final String URL_RECEITA_DELETE         = URL_BASE + "/receitas2/%s/";


    private static OkHttpClient newClientOkHttp() {
        try {
            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.readTimeout(10000, TimeUnit.MILLISECONDS);
            b.writeTimeout(10000, TimeUnit.MILLISECONDS);
            return b.build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Receita> ReceitasGetAll(){

        ArrayList<Receita> lstReceitas = new ArrayList<>();

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //String url = String.format(URL_USUARIO_GETALL);
        Request request = new Request.Builder()
                .url(URL_RECEITA_GET_ALL)
                .build();

        Response response = null;
        try {
            // Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            // response.body retorna o corpo da resposta, que no nosso caso é JSON
            String json = response.body().string();
            //JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            //Percorre a Lista de objetos
            for (int i = 0; i < jsonArray.length(); i++) {
                //Ler o objecto JSON
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Receita receita = new Receita();
                receita.setId(jsonObject.getString("_id"));
                receita.setTitulo(jsonObject.getString("titulo"));
                receita.setIngredientes(jsonObject.getString("ingredientes"));
                receita.setModoDePreparo(jsonObject.getString("modoDePreparo"));
                receita.setCusto(jsonObject.getString("custo"));
                receita.setDataCriacao(jsonObject.getString("dataCriacao"));

                lstReceitas.add(receita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstReceitas;

    };

    public static Receita NovaReceita_POST(Receita receita) throws JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //Monta o Json
        JSONObject json = new JSONObject();
        json.put("titulo", receita.getTitulo());
        json.put("ingredientes", receita.getIngredientes());
        json.put("modoDePreparo", receita.getModoDePreparo());
        json.put("custo", receita.getCusto());
        json.put("dataCriacao", receita.getDataCriacao());

        String jsonString = json.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);

        //String url = String.format(URL_USUARIO_GETALL);
        Request request = new Request.Builder()
                .url(URL_RECEITA_POST)
                .post(body)
                .build();

        Response response = null;
        try {
            //Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            //response.body retorna o corpo da resposta, que no nosso caso é JSON
            String jsonRetorno = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonRetorno);

            Receita receitaRetorno = new Receita();
            receitaRetorno.setId(jsonObject.getString("_id"));
            receitaRetorno.setTitulo(jsonObject.getString("titulo"));
            receitaRetorno.setIngredientes(jsonObject.getString("ingredientes"));
            receitaRetorno.setModoDePreparo(jsonObject.getString("modoDePreparo"));
            receitaRetorno.setCusto(jsonObject.getString("custo"));
            receitaRetorno.setDataCriacao(jsonObject.getString("dataCriacao"));

            return receitaRetorno;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    };

    public static boolean ReplaceReceita_PUT(Receita receita) throws JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        //Monta o Json
        JSONObject json = new JSONObject();
        json.put("titulo", receita.getTitulo());
        json.put("ingredientes", receita.getIngredientes());
        json.put("modoDePreparo", receita.getModoDePreparo());
        json.put("custo", receita.getCusto());
        json.put("dataCriacao", receita.getDataCriacao());

        String jsonString = json.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);

        String url = String.format(URL_RECEITA_PUT, receita.getId());

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = null;
        try {
            //Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            //response.body retorna o corpo da resposta, que no nosso caso é JSON
            String jsonRetorno = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonRetorno);
            boolean retorno = jsonObject.getBoolean("success");

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    };

    public static boolean Receita_DELETE(String id) throws JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Abre a conexão com o servidor
        OkHttpClient client = newClientOkHttp();

        String url = String.format(URL_RECEITA_DELETE, id);

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Response response = null;
        try {
            //Realiza a chamada ao servidor
            response = client.newCall(request).execute();
            //response.body retorna o corpo da resposta, que no nosso caso é JSON
            String jsonRetorno = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonRetorno);
            boolean retorno = jsonObject.getBoolean("success");

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    };


}
