package ExceptionAux;
/**
 * Classe que caso returne valor nulo exibe erro.
 * 
 * @author Daniela, Lucas, Maiza
 */
public class ParametroNuloException extends Exception {
    /**
     * Exibe mensagem de erro. 
     * @param msg parametro nulo.
     */
    public ParametroNuloException(String msg){
        super(msg);
    }
}
