
import Coordenada.Coordenada;
import Fila.Fila;
import Labirinto.Labirinto;
import Pilha.Pilha;


public class Principal {
    
    public static void main(String[] args)
    {
       //Pilha<Coordenada> caminho;
       //Pilha<Fila<Coordenada>> possibilidades;
       //Fila<Coordenada> fila;
       Labirinto labirinto;
       
       try
       {
           //caminho = new Pilha <Coordenada> (40);
           //possibilidades = new Pilha <Fila<Coordenada>>(40);
           labirinto = new Labirinto(); 
           labirinto.toString();
           Coordenada c = labirinto.getEntrada();
           System.out.println("Entrada, linha: " + c.getLinha() + ", coluna: " + c.getColuna());
           //fila = new Fila<Coordenada>(3);
           //Coordenada atual = labirinto.getEntrada();
           //fila = labirinto.posicoesAdj(atual);
           
          // Crdenada saida = labirinto.getSaida();oo
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
        
    }
    
    
}
