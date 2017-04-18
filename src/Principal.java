
import Coordenada.Coordenada;
import Fila.Fila;
import Labirinto.Labirinto;
import Pilha.Pilha;


public class Principal {
    
    public static void main(String[] args)
    {
       Pilha<Coordenada> caminho;
       Pilha<Fila<Coordenada>> possibilidades;
       Fila<Coordenada> fila;
       Labirinto labirinto;
       
       try
       {
           labirinto = new Labirinto(); 
           caminho = new Pilha <Coordenada> (labirinto.getLinha() * labirinto.getColuna());
           possibilidades = new Pilha <Fila<Coordenada>>(labirinto.getLinha() * labirinto.getColuna());
           labirinto.toString();
           Coordenada atual = labirinto.getEntrada();
           
           Coordenada entrada = labirinto.getEntrada();
           Coordenada saida = labirinto.getSaida();
           System.out.println("Entrada, linha: " + entrada.getLinha() + ", coluna: " + entrada.getColuna());
           System.out.println("Saida, linha: " + saida.getLinha() + ", coluna: " + saida.getColuna());
           
           do
           {
                labirinto.progressivo(atual, possibilidades, caminho);
                fila = new Fila<Coordenada>(3);
                fila = possibilidades.remove();
                labirinto.regressiva(fila, atual, caminho, possibilidades);
                atual = fila.remove();
           }
           while(labirinto.getIsSaidaEncontrada());
                  
       }
       catch(Exception ex)
       {
           System.out.println("MSG: " + ex.getMessage());
       }
        
    }
    
    
}
