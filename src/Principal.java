
import Coordenada.Coordenada;
import ExceptionAux.SairDoLoopException;
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
           int tam = labirinto.getTamLinha()* labirinto.getTamColuna();
           caminho = new Pilha <Coordenada> (tam);
           possibilidades = new Pilha <Fila<Coordenada>>(labirinto.getTamLinha() * labirinto.getTamColuna());
           labirinto.toString();
           Coordenada atual = labirinto.getEntrada();
           
           Coordenada entrada = labirinto.getEntrada();
           Coordenada saida = labirinto.getSaida();
           System.out.println("Entrada, linha: " + entrada.getLinha() + ", coluna: " + entrada.getColuna());
           System.out.println("Saida, linha: " + saida.getLinha() + ", coluna: " + saida.getColuna());
           
           do
           {
               try
               {
                   if(labirinto.progressivo(atual, possibilidades, caminho))
                   {
                       atual = labirinto.regressiva(atual, caminho, possibilidades);
                       labirinto.setPosicao(atual);
                       caminho.guarde(atual);
                       System.out.println(labirinto.toString());
                   }
                       
               }
               catch(SairDoLoopException err)
               {
                   System.out.println(err.getMessage());
                   Pilha<Coordenada> cam = new Pilha<Coordenada>(caminho.size()); 
                   while(!caminho.vazia())
                   {
                       cam.guarde(caminho.remove());
                   }
                   while(!cam.vazia())
                      System.out.println((cam.remove()).toString());
                  
               }
               catch(Exception ex)
               {
                   
               } 
           }
           while(!labirinto.isSaidaEncontrada());
                  
       }
       catch(Exception ex)
       {
           System.out.print("MSG: " + ex.getMessage());
       }
        
    }
    
    
}
