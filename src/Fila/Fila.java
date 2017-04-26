package Fila;

import java.lang.reflect.Method;
/**
 * Fila é a classe que armazena valores genéricos enfileirados.
 * <br>
 * 
 * @author Lucas, Maiza e Dani.
 * @param <X> valor genérico
 */
public class Fila <X> implements Cloneable {
    	
    protected Object[] fila;
    protected int inicio = 0, fim = -1, qtd = -1;
    
    /**
     * Construor default
     */
    private Fila()
    {

    }
    /**
     * Contrutor que define o tamanho da fila.
     * 
     * @param capacidade valor inteiro
     * @throws Exception  erro genérico.
     * 
     */
    public Fila(int capacidade) throws Exception
    {
        if(capacidade < 0)
                throw new Exception("Capacidade invalida");

        this.fila  = new Object[capacidade];
    }
    /**
     * Este metodo faz inserção de valores na fila.
     * 
     * @param x valor definido pelo usuário.
     * @throws Exception erro genérico.
     */
    public void insere(X x) throws Exception
    {
        if(x == null)
            throw new Exception("Valor invalido");
        
        if(this.qtd == this.fila.length-1)
            throw new Exception("Fila está cheia");
        
        this.fim++;
        this.qtd++;
        
        if (x instanceof Cloneable)
        {
          //this.vetor[this.topo] = x.clone();
            Class classe            = x.getClass();
            Class<?>[] parmsFormais = null; // = null é desnecessário
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            this.fila[this.fim]   = (X)metodo.invoke(x,parmsReais);
        }
        else
            this.fila[this.fim] = x;
        
        if(this.fim == this.fila.length-1 && this.inicio > 0)
            this.fim = 0;
    }
    /**
     * Este método faz remoção de valores da fila.
     * 
     * @return valor digitado pelo usuário
     * @throws Exception  erro genérico.
     */
    public X remove() throws Exception
    {
        if(this.qtd == -1)
           throw new Exception("Fila vazia");

        Object ret = this.fila[this.inicio];
        this.fila[this.inicio] = null;
        this.inicio++;
        this.qtd--;
        
        if(this.inicio == this.fila.length)
            this.inicio = 0;
        
        return (X)ret;
    }
    /**
     * Este método verifica situação da fila.
     * 
     * @return retorna quantidade.
     * @throws Exception erro genérico
     */
    public boolean vazia() throws Exception
    {
        return this.qtd == -1;
    }
    /**
     * Este método retorna o tamanho da fila.
     * 
     * @return tamanho
     * @throws Exception  erro genérico
     */
    public int size() throws Exception
    {
        return this.fila.length;
    }
    /**
     * Este é um método de auxílio ao método clone.
     * 
     * @param modelo Fila genérica.
     * @throws Exception erro genérico.
     */
    public Fila (Fila<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.inicio = modelo.inicio;
        this.fim = modelo.fim;
        this.qtd = modelo.qtd;
        
        this.fila = new Object [modelo.fila.length];

        for (int i=0; i <= modelo.qtd; i++)
            if (modelo.fila[i] instanceof Cloneable)
            {
                Class classe            = modelo.fila[i].getClass();
                Class<?>[] parmsFormais = null; // = null é desnecessário
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.fila[i]            = (X)metodo.invoke(modelo.fila[i],parmsReais);
              //this.fila[i] = (X)modelo.fila[i].clone();
            }
            else
                this.fila[i] = modelo.fila[i];
    }

    /**
     * Construtor do tipo object Clone.
     * 
     * @return Object
     */
    public Object clone ()
    {
        Fila<X> ret=null;
        
        try
        {
            ret = new Fila <X> (this);
        }
        catch (Exception erro)
        {} // tenho certeza que nao vai acontecer

        return ret;
    }
    /**
     * Este método serve para printar a classe.
     * 
     * @return Valor String
     */
    public String toString ()
    {
        String ret = "";

        if (this.qtd==-1)
            ret = "nenhum elemento";
        else
        {
            ret = (this.qtd)+" elementos (ultimo:"+
                this.fila[this.fim]+")";
        }

        return ret;
    }
    /**
     * Método de comparação.
     * 
     * @param obj
     * @return 
     */
    public boolean equals (Object obj)
    {
        if (obj==null)
            return false;

        if (this==obj)
            return true;

        if (this.getClass()!=obj.getClass())
            return false;

        Fila<X> f = (Fila<X>)obj;

        if (this.qtd!=f.qtd)
            return false;

        for (int i=0; i <= this.fila.length; i++)
            if (!this.fila[i].equals(f.fila[i]))
                return false;
        return true;
    }
    /**
     * Serve para retornar valor único inteiro para identificar a classe.
     * 
     * @return 
     */
    public int hashCode ()
    {
        int ret = 897;
        
        ret = 7*ret + new Integer(this.qtd).hashCode();

        for (int i=0; i <= this.qtd; i++)
            ret = 7*ret + this.fila[i].hashCode();

        return ret;
    }

}