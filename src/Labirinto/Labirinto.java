
package Labirinto;

import Coordenada.Coordenada;
import Fila.Fila;
import Pilha.Pilha;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Integer.parseInt;


public class Labirinto {
    
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
        this.lerArquivo();
        this.lerTamanho();
        this.labirinto = new String[this.tamLinha][this.tamColuna];
        this.modelarMatriz();
        this.entrada();
        this.saida();
        //this.labirinto = new String [][];
    }
    
    protected void lerArquivo() throws Exception
    {
        this.lab = new FileReader("C:\\fontes\\labirinto\\lib\\lab.txt");//endereço completo com o nome do arquivo
        this.leitor = new BufferedReader(lab);
    }
    
    protected void lerTamanho() throws Exception
    {
        int aux = 1;
        String letra = "";
        while(aux < 3)
        {
            letra = this.leitor.readLine();
            //System.out.println(letra);
            if(aux == 1)
            {
                this.tamLinha = parseInt(letra.toString());
            }
            if(aux == 2)
            {
                this.tamColuna = parseInt(letra.toString());
            }
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
    
    protected void entrada() throws Exception
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
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    protected void saida() throws Exception
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
        return this.entrada;
    }
    public Coordenada getSaida() throws Exception
    {
        return this.saida;
    }
    public Fila<Coordenada> posicoesAdj(Coordenada atual) throws Exception
    {
        //pegar a posição de cima
        //pegar a posição de frente
        //pegar a posição de baixo
        Fila<Coordenada> fila = new Fila<Coordenada>(3);
        
        if(!(atual.getColuna()+1 > this.tamColuna-1))
        {
            if(!(this.labirinto[atual.getLinha()][atual.getColuna()+1].equals("#")))
                fila.insere(new Coordenada(atual.getLinha(), atual.getColuna()+1));
        }
        
        if(atual.getLinha()-1 > 0 && atual.getLinha()-1 < this.linha-1)
        {
            if(!(this.labirinto[atual.getLinha()-1][atual.getColuna()].equals("#")))
                fila.insere(new Coordenada(atual.getLinha()-1, atual.getColuna()));
        }
        
        if(atual.getLinha()+1 > 0 && atual.getLinha()+1 < this.linha-1)
        {
            if(!(this.labirinto[atual.getLinha()+1][atual.getColuna()].equals("#")))
                fila.insere(new Coordenada(atual.getLinha()+1, atual.getColuna()));
        }

        return fila;    
    }
    public Coordenada retirarCoor(Fila<Coordenada> fila)
    {
        return null;
    }
    
    protected void setPosicao(Coordenada c) throws Exception
    {
        if(this.labirinto[c.getLinha()][c.getColuna()].equals("S"))
            this.isSaidaEncontrada = true;
        
        this.labirinto[c.getLinha()][c.getColuna()] = "*";
    }
    
    protected void tirarPosicao(Coordenada c) throws Exception
    {
        this.labirinto[c.getLinha()][c.getColuna()] = " ";
    }
    
    public String toString()
    {
        String ret = "";
        for(int l = 0; l < this.tamLinha; l++){
            for(int c = 0; c < this.tamColuna; c++){
                ret += "lin: " + l + ", col: " + c + " result: " +this.labirinto[l][c];
            }
        }
        return ret;
    }
    
    
    public boolean progressivo(Coordenada atual, Pilha<Fila<Coordenada>> possibilidades, Pilha<Coordenada> caminho) throws Exception
    {
        while(atual != null || this.isSaidaEncontrada)
        {
             Fila fila = new Fila<Coordenada>(3);
             fila = this.posicoesAdj(atual);
             atual = (Coordenada)fila.remove();
             this.setPosicao(atual);
             caminho.guarde(atual);
             possibilidades.guarde(fila);
        }
        return true;
    }
    
    public boolean regressiva(Fila<Coordenada> fila, Coordenada atual, Pilha<Coordenada> caminho, Pilha<Fila<Coordenada>> possibilidades) throws Exception
    {
        while(fila.vazia())
        {
             atual = caminho.remove();
             this.tirarPosicao(atual);
             fila = possibilidades.remove();
        }
        return true;
    }
    
    public boolean getIsSaidaEncontrada()
    {
        return this.isSaidaEncontrada;
    }
    
}
