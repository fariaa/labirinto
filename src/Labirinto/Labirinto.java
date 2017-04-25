
package Labirinto;

import Coordenada.Coordenada;
import ExceptionAux.ParametroNuloException;
import ExceptionAux.SairDoLoopException;
import Fila.Fila;
import Pilha.Pilha;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Integer.parseInt;


public class Labirinto implements Cloneable {
    
    protected int tamLinha;
    protected int tamColuna;
    protected int linha;
    protected int coluna;
    protected String labirinto[][];
    protected Coordenada entrada;
    protected Coordenada saida;
    protected FileReader lab;
    protected BufferedReader leitor;
    protected boolean isSaidaEncontrada = false;
   
    public Labirinto() throws Exception
    {
        this.lerArquivo("C:\\fontes\\labirinto\\lib\\lab2.txt");
        this.lerTamanho();
        this.labirinto = new String[this.tamLinha][this.tamColuna];
        this.modelarMatriz();
        this.procuraEntrada();
        this.procuraSaida();
    }
    
    protected void lerArquivo(String caminho) throws java.io.FileNotFoundException, ParametroNuloException
    {
        if(caminho == null)
            throw new ParametroNuloException("o caminho do arquivo, nao pode ser nulo");
        
        this.lab = new FileReader(caminho);
        this.leitor = new BufferedReader(lab);
    }
  
    public int getTamLinha() {
        return new Integer(tamLinha);
    }

    public int getTamColuna() {
        return new Integer(tamColuna);
    }
    
    protected void lerTamanho() throws NumberFormatException, java.io.IOException
    {
        int aux = 1;
        String letra = "";
        while(aux < 3)
        {
            letra = this.leitor.readLine();
            if(aux == 1)
                this.tamLinha = parseInt(letra.toString());
            if(aux == 2)
                this.tamColuna = parseInt(letra.toString());
            
            aux++;
        }
    }
   
    protected void modelarMatriz() throws Exception
    {
        int auxLinha = 0;
        String frase = "";
        while((frase = this.leitor.readLine()) != null)
        {
            System.out.println(frase);
            char caractere[] = frase.toCharArray();
            for(int i = 0; i < caractere.length; i++)
            {
                this.labirinto[auxLinha][i] = caractere[i] + "";
            }
            auxLinha++;
        }
    }
    
    protected void procuraEntrada() throws Exception
    {
        int cont = 0;
        Coordenada ret = new Coordenada();
        for(int c = 0; c <= this.tamColuna-1; c++)
        {
            if(this.labirinto[0][c].equals("E")){
                ret.setLinha(0);
                ret.setColuna(c);
                cont++;
            }
            if(this.labirinto[this.tamLinha-1][c].equals("E")){
                ret.setLinha(this.tamLinha-1);
                ret.setColuna(c);
                cont++;
            }
        }
        for(int l = 0; l <= this.tamLinha-1; l++)
        {
            if(this.labirinto[l][0].equals("E")){
                ret.setLinha(l);
                ret.setColuna(0);
                cont++;
            }
            if(this.labirinto[l][this.tamColuna-1].equals("E")){
                ret.setLinha(l);
                ret.setColuna(this.tamColuna-1);
                cont++;
            }
        }
        
        if(cont != 1) 
            throw new Exception("nao pode ter duas entredas");
        
        this.entrada = ret;
    }

    public int getLinha() {
        return new Integer(linha);
    }

    public int getColuna() {
        return new Integer(coluna);
    }
    protected void procuraSaida() throws Exception
    {
        int cont = 0;
        Coordenada ret = new Coordenada();
        for(int c = 0; c <= this.tamColuna-1; c++)
        {
            if(this.labirinto[0][c].equals("S")){
                ret.setLinha(0);
                ret.setColuna(c);
                cont++;
            }
            if(this.labirinto[this.tamLinha-1][c].equals("S")){
                ret.setLinha(this.tamLinha-1);
                ret.setColuna(c);
                cont++;
            }
        }
        for(int l = 0; l <= this.tamLinha-1; l++)
        {
            if(this.labirinto[l][0].equals("S")){
                ret.setLinha(l);
                ret.setColuna(0);
                cont++;
            }
            if(this.labirinto[l][this.tamColuna-1].equals("S")){
                ret.setLinha(l);
                ret.setColuna(this.tamColuna-1);
                cont++;
            }
        }
        
        if(cont != 1) 
            throw new Exception("nao pode ter mais de uma saida");
        
        this.saida = ret;
    }
    public Coordenada getEntrada() throws Exception
    {
        return this.entrada.clone();
    }
    public Coordenada getSaida() throws Exception
    {
        return this.saida.clone();
    }
    
    public boolean isSaidaEncontrada()
    {
        return this.isSaidaEncontrada;
    }
    
    public Fila<Coordenada> posicoesAdj(Coordenada atual) throws Exception
    {
        Fila<Coordenada> fila = new Fila<Coordenada>(3);
        //posição da frente
        if(atual.getColuna()+1 <= this.tamColuna-1)
        {
            if(!(this.labirinto[atual.getLinha()][atual.getColuna()+1].equals("#") 
               || this.labirinto[atual.getLinha()][atual.getColuna()+1].equals("*")))
                fila.insere(new Coordenada(atual.getLinha(), atual.getColuna()+1));
        }
        //posição de cima
        if(atual.getLinha()-1 <= this.tamLinha-1)
        {
            if(!(this.labirinto[atual.getLinha()-1][atual.getColuna()].equals("#")
               || this.labirinto[atual.getLinha()-1][atual.getColuna()].equals("*")))
                fila.insere(new Coordenada(atual.getLinha()-1, atual.getColuna()));
        }
        //posição de baixo
        if(atual.getLinha()+1 <= this.tamLinha-1)
        {
            if(!(this.labirinto[atual.getLinha()+1][atual.getColuna()].equals("#")
                || this.labirinto[atual.getLinha()+1][atual.getColuna()].equals("*")))
                fila.insere(new Coordenada(atual.getLinha()+1, atual.getColuna()));
        }

        return fila;    
    }
    
    public void setPosicao(Coordenada c) throws ArrayIndexOutOfBoundsException, ParametroNuloException, SairDoLoopException
    {
        if(c == null)
            throw new ParametroNuloException("Coordenada nao pode estar vazia");
        
        if(this.labirinto[c.getLinha()][c.getColuna()].equals("S"))
        {
            this.isSaidaEncontrada = true;
            throw new SairDoLoopException("achou a saida");
        }
        
        this.labirinto[c.getLinha()][c.getColuna()] = "*";
    }
    
    protected void tirarPosicao(Coordenada c) throws ArrayIndexOutOfBoundsException, ParametroNuloException
    {
        if(c == null)
            throw new ParametroNuloException("Coordenada nao pode estar vazia");
                    
        this.labirinto[c.getLinha()][c.getColuna()] = " ";
    }
    
    public boolean progressivo(Coordenada atual, Pilha<Fila<Coordenada>> possibilidades, Pilha<Coordenada> caminho) throws Exception
    {
        if(atual == null)
            throw new ParametroNuloException("Coordenada atual nao pode começar vazia");
        
        int cont =0;
        while(atual != null || !(this.isSaidaEncontrada))
        {
             Fila fila = new Fila<Coordenada>(3);
             fila = this.posicoesAdj(atual);
             if(fila.vazia())
                 return true;
             
             atual = (Coordenada)fila.remove();
             this.setPosicao(atual);
             caminho.guarde(atual);
             possibilidades.guarde(fila);
             
             System.out.println("passo: " + cont++);
             System.out.println(this.toString());
        }
        return false;
    }
    
    public Coordenada regressiva(Coordenada atual, Pilha<Coordenada> caminho, Pilha<Fila<Coordenada>> possibilidades) throws Exception
    {
        Fila<Coordenada> fila = new Fila<Coordenada>(3);
        fila = possibilidades.remove();
        while(fila.vazia())
        {
             System.out.println(this.toString());
             atual = caminho.remove();
             this.tirarPosicao(atual);
             fila = possibilidades.remove();
             
        }
        return fila.remove();
    }
    
    public Labirinto(Labirinto modelo) throws Exception
    {
        if(modelo == null)
            throw new ParametroNuloException("Coordenada atual nao pode começar vazia");
        
        this.coluna = modelo.coluna;
        this.entrada = modelo.entrada;
        this.isSaidaEncontrada = modelo.isSaidaEncontrada;
        this.lab.equals(modelo.lab);
        
        for(int l = 0; l < modelo.tamLinha; l++)
            for(int c = 0; c < modelo.tamColuna; c++)
                this.labirinto[l][c].equals(modelo.labirinto[l][c]);
                
        this.labirinto.equals(modelo.labirinto);
        this.leitor.equals(modelo.leitor);
        this.linha = modelo.linha;
        this.saida.equals(modelo.saida);
        this.tamColuna = modelo.tamColuna;
        this.tamLinha = modelo.tamLinha;
    }
    
    public Object clone()
    {
        Labirinto ret = null;
        try
        {
          ret = new Labirinto(this);  
        }
        catch(Exception erro)
        {}
        
        return ret;
    }
    
    public int hashCode()
    {
        int ret = 999;
        ret = 7*ret + new Integer(this.tamLinha).hashCode();
        ret = 7*ret + new Integer (this.tamLinha).hashCode();
        ret = 7*ret + new Integer (this.linha).hashCode();
        ret = 7*ret + new Integer (this.coluna).hashCode();
        ret = 7*ret + this.entrada.hashCode();
        ret = 7*ret + this.saida.hashCode();
        ret = 7*ret + this.lab.hashCode();
        ret = 7*ret + this.leitor.hashCode();
        ret = 7*ret + new Boolean(this.isSaidaEncontrada).hashCode();
        
        for(int l = 0; l < this.tamLinha; l++)
            for (int c = 0; c < this.tamColuna; c++)
                ret = 7*ret + this.labirinto[l][c].hashCode();
     
        return ret;
    }
    
    public String toString()
    {
        String ret = "";
        for(int l = 0; l < this.tamLinha; l++){
            for(int c = 0; c < this.tamColuna; c++){
                ret += this.labirinto[l][c];
            }
            ret += "\n";
        }
        return ret;
    }
    
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        
        Labirinto l = (Labirinto)obj;
        
        if(this.tamLinha!=l.tamLinha ||
           this.tamColuna!=l.tamColuna ||
           this.linha!=l.linha ||
           this.linha!=l.coluna ||
           !this.entrada.equals(l.entrada) ||
           !this.saida.equals(l.saida) ||
           !this.lab.equals(l.lab) ||
           !this.leitor.equals(l.leitor) ||
           this.isSaidaEncontrada != l.isSaidaEncontrada)
            return false;
        
        for(int lin = 0; lin < this.tamLinha; lin++)
        {
            for(int c = 0; c < this.tamColuna; c++)
            {
                if(!this.labirinto[lin][c].equals(l.labirinto[lin][c]))
                    return false;
            }
        }
        return true;
    }
}
