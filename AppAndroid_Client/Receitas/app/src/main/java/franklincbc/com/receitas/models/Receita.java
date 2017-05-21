package franklincbc.com.receitas.models;

import java.io.Serializable;

/**
 * Created by frank on 18/05/2017.
 */

public class Receita implements Serializable {

    String id;
    String titulo;
    String ingredientes;
    String modoDePreparo;
    String dataCriacao;
    String custo;



    public Receita(){
        this.titulo = "";
        this.ingredientes = "";
        this.modoDePreparo = "";
        this.dataCriacao = "";
        this.custo = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }
}
