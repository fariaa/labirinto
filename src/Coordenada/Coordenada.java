
package Coordenada;

import ExceptionAux.ParametroNuloException;

/**
 * Classe de coordenadas, para indicação de posição em uma matriz, informando assim sua linha e coluna
 * <p>
 * Esta Classe tem como objetivo informar as coordenadas, ou posição de algo em uma matriz,
 * informando a sua posição como a linha e coluna.
 * 
 * @author Lucas, Daniela e Maisa
 */
public class Coordenada {
    
    protected String caminho;
    protected int linha;
    protected int coluna;
    
    /**
     * Este construtor é default
     */
    public Coordenada()
    {
        
    }
    /**
     * Este Construtor tem como objetivo setar o valor da linha e coluna do objeto.
     * <p>
     * 
     * @param linha informar a posição com o valor da linha de uma matriz
     * @param coluna informar a posição com o valor da coluna de uma matriz 
     */
    public Coordenada(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }

    /**
     * Este método serve para setar valor no atributo linha de coordenada
     * 
     * @param linha setar valor em linha
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * Este método serve para setar valor no atributo coluna de coordenada.
     * 
     * @param coluna Setar valor em coluna
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    /**
     * Esse método visualiza o atributo linha da classe
     * 
     * @return valor inteiro da linha 
     */
    public int getLinha() {
        return linha;
    }
 
    /**
     * Este métdo visualiza o atributo coluna da classe
     * 
     * @return valor inteiro de coluna 
     */
    public int getColuna() {
        return coluna;
    }
    
    /**
     * Este construtor serve para auxiliar o método clone
     * 
     * @param modelo parametro do tipo Coordenada
     * @throws Exception
     * @throws ParametroNuloException 
     */
    public Coordenada(Coordenada modelo) throws Exception, ParametroNuloException
    {
        if(modelo == null)
            throw new ParametroNuloException("modelo de clone nao pode ser nulo");
        
        this.caminho = modelo.caminho;
        this.linha = modelo.linha;
        this.coluna = modelo.coluna;
    }
    
    /**
     *Este método faz clonagem da classe.
     * 
     * @return Coordenada.
     */
    public Coordenada clone()
    {
        Coordenada ret = null;
        try
        {
          ret = new Coordenada(this);  
        }
        catch(Exception erro)
        {}
        
        return ret;
    }
    /**
     * Este métdo serve para retornar valor único e inteiro para identificar a classe
     * 
     * @return Valor
     */
    public int hashCode()
    {
        int ret = 789;
        ret = 9*ret + this.caminho.hashCode();
        ret = 9*ret + new Integer(this.linha).hashCode();
        ret = 9*ret + new Integer(this.coluna).hashCode();
                
        return ret;
    }
    /**
     * Este método serve para printar a classe .
     * 
     * @return valor String
     */
    public String toString()
    {
        return ("("+this.linha+", "+this.coluna+")");
    }
}
