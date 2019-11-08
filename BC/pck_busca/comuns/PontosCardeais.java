
package comuns;

/**Pontos cardeais: o agente se movimenta em uma direção apontada por um dos
 * pontos cardeais. São utilizados como parâmetros da ação ir(ponto)
*/
public interface PontosCardeais {
    public final static int N = 0;
    public final static int NE = 1;
    public final static int L = 2;
    public final static int SE = 3;
    public final static int S = 4;
    public final static int SO = 5;
    public final static int O = 6;
    public final static int NO = 7;

    /**Strings que correspondem as ações */
    public final static String acao[] = {"N","NE","L","SE","S","SO","O","NO"};
}
