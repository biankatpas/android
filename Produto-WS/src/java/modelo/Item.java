package modelo;


public class Item
{
    private Long id;
    private double valor;
    private String descricao;
    private String nome;

    public Item() {}

    public Item(double valor, String descricao, String nome)
    {
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
        
    }
    
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
}
