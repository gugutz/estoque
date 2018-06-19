package model;

public class TableItem {

    private Integer id;
    private String codigo;
    private String description;
    private String line;
    private Double peso;

    public TableItem(Integer id, String codigo, String description, String line, Double peso) {
        this.id = id;
        this.codigo = codigo;
        this.description = description;
        this.line = line;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
