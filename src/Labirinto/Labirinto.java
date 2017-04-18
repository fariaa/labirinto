
package Labirinto;

import Coordenada.Coordenada;
import Fila.Fila;
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
    
    
    public Labirinto() throws Exception
    {
        this.lerArquivo();
        this.lerTamanho();
        this.labirinto = new String[this.tamLinha][this.tamColuna];
        this.modelarMatriz();
        //this.labirinto = new String [][];
    }
    
    protected void lerArquivo() throws Exception
    {
        this.lab = new FileReader("H:\\Maligno\\labirinto\\lib\\lab.txt");//endereço completo com o nome do arquivo
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
    
    protected void entrada()
    {
        this.entrada = null;
    }
    protected void saida()
    {
        this.saida = null; 
    
    }
    public Coordenada getEntrada() throws Exception
    {
        int cont = 0;
        Coordenada ret = new Coordenada();
        for(int c = 0; c < 10; c++)
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
            
            if(this.labirinto[c][0].equals("E")){
                ret.setLinha(c);
                ret.setColuna(0);
                cont++;
            }
            
            if(this.labirinto[c][this.tamColuna-1].equals("E")){
                ret.setLinha(c);
                ret.setColuna(this.tamColuna-1);
                cont++;
            }
        }
        
        if(cont != 1) 
            throw new Exception("nao pode ter duas entredas");
        
        return this.entrada;
    }
    public Coordenada getSaida()
    {
        return this.saida;
    
    }
    public Fila<Coordenada> posicoesAdj(Coordenada atual)
    {
        return null;
    }
    public Coordenada retirarCoor(Fila<Coordenada> fila)
    {
        return null;
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
    
    
}
