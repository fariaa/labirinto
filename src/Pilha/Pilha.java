package Pilha;

import java.lang.reflect.Method;

public class Pilha <X> implements Cloneable {
    protected int topo = -1;
    protected Object[] pilha;
    protected Object ultimoElemento;
    
    private Pilha()
    {
    }
	
    public Pilha(int capacidade) throws Exception
    {
        if(capacidade <= -1)
            throw new Exception("capacidade invalida");
        
        this.pilha = new Object[capacidade]; 
    }
    
    public void guarde(X x) throws Exception
    {
        if(x == null)
           throw new Exception("Valor invalido");
        
        Object elemento = null;
        /*
        if (x instanceof Cloneable)
        {
            Class classe            = x.getClass();
            Class<?>[] parmsFormais = null; // = null é desnecessário
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            elemento         = (X)metodo.invoke(x, parmsReais);
          
        }
        else
            */
            elemento = x;
        
	this.topo++;	
	this.pilha[this.topo] = (X)elemento;
    }
    
    public X remove() throws Exception
    {
        Object item = this.pilha[this.topo];
        if(this.pilha.length == 0)
            throw new Exception("Pilha vazia");
        
        this.ultimoElemento = this.pilha[this.topo];
        this.pilha[this.topo] = null;
	this.topo--;	
        return (X)item;
    }
    
    public boolean vazia()
    {
        if(this.pilha[0] == null)
            return true;
        
        return false;
    }
    
    public X recupera() throws Exception
    {
        if(this.pilha.length == 0)
            throw new Exception("Pilha vazia");
        
	return (X)pilha[this.topo];	
    }
    
    public String toStringElemento() throws Exception
    {
        if(this.topo == -1)
            throw new Exception("Pilha vazia");
        
        return (String)this.pilha[this.topo];
    }
    
    public int size() 
    {
        return this.pilha.length;
    }
    
    public int quantidade() 
    {
        int ret = 0;
        for(int x = 0; x < this.pilha.length; x++)
        {
            if(this.pilha[x] != null)
                ret++;
        }
        return ret;
    }
    
    public X ultimoElementoRemovido() throws Exception
    {
        if(this.ultimoElemento == null)
            throw new Exception("Pilha sem elemento removido");
        
        return (X)this.ultimoElemento;
    }
                
    public String toString ()
    {
        String ret = "";

        if (this.topo ==-1 )
            ret = "nenhum elemento";
        else
        {
            ret = (this.topo)+" elementos (ultimo:"+
                this.pilha[this.topo]+")";
        }

        return ret;
    }
	
    public boolean equals (Object obj)
    {
        if (obj==null)
            return false;

        if (this==obj)
            return true;

        if (this.getClass()!=obj.getClass())
            return false;

        Pilha<X> p = (Pilha<X>)obj;

        if (this.topo != p.topo)
            return false;

        for (int i=0; i <= this.pilha.length; i++)
            if (!this.pilha[i].equals(p.pilha[i]))
                return false;
        return true;
    }

    public int hashCode ()
    {
        int ret = 897;
        
        ret = 7*ret + new Integer(this.topo).hashCode();
        
        ret = 7*ret + this.ultimoElemento.hashCode();

        for (int i=0; i <= this.topo; i++)
            ret = 7*ret + this.pilha[i].hashCode();

        return ret;
    }
    
    public Pilha (Pilha<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.pilha = new Object [modelo.pilha.length];

        for (int i=0; i<=modelo.topo; i++)
            if (modelo.pilha[i] instanceof Cloneable)
            {
                Class classe            = modelo.pilha[i].getClass();
                Class<?>[] parmsFormais = null; // = null é desnecessário
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.pilha[i]            = (X)metodo.invoke(modelo.pilha[i],parmsReais);

              //this.vetor[i] = (x)modelo.vetor[i].clone();
            }
            else
                this.pilha[i] = modelo.pilha[i];

        this.topo = modelo.topo;
    }


    public Object clone ()
    {
        Pilha<X> ret=null;

        try
        {
            ret = new Pilha <X> (this);
        }
        catch (Exception erro)
        {} // tenho certeza que nao vai acontecer

        return ret;
    }
    
}