package ExceptionAux;
/**
 * Esta classe é uma excessão para sair do Looping.
 * 
 * @author Maiza, Daniela, Lucas.
 */
public class SairDoLoopException extends Exception{
    
    /**
     * Exibe mensagem de erro.
     * @param msg parametro nulo.
     */
    public SairDoLoopException(String msg){
        super(msg);
    }
}
