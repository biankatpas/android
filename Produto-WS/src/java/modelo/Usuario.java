package modelo;

public class Usuario
{
    private Long id;
    
    private String login;
    private String senha;
    private String numeroCartao;
    private int codigoSeguranca;
    private String dataValidade;

    public Usuario() {}

    public Usuario(String login, String senha, String numeroCartao, int codigoSeguranca, String dataValidade)
    {
        this.login = login;
        this.senha = senha;
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.dataValidade = dataValidade;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getNumeroCartao()
    {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao)
    {
        this.numeroCartao = numeroCartao;
    }

    public int getCodigoSeguranca()
    {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(int codigoSeguranca)
    {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getDataValidade()
    {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade)
    {
        this.dataValidade = dataValidade;
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
