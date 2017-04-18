
package Coordenada;


public class Coordenada {
    
    protected String caminho;
    protected final int TAM = 40;
    protected int linha;
    protected int coluna;
    
            
    public Coordenada()
    {
        
    }
    
    public Coordenada(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
