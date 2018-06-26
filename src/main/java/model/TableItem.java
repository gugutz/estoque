package model;

public class TableItem {

    private Integer id;
    private String codigo;
    private String descricao;
    private String linha;
    private Integer qtde;
    private Double peso;

    public TableItem(Integer id, String codigo, Integer qtde) {
        this.id = id;
        this.codigo = codigo;
        this.qtde = qtde;
    }

    public TableItem(Integer id, String codigo, String descricao, String linha, Integer qtde, Double peso) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.linha = linha;
        this.qtde = qtde;
        this.peso = peso;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }


   public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
