package ambiente;
import comuns.PontosCardeais;
import comuns.Labirinto;

/**Model implementa um ambiente na forma de um labirinto com paredes e com um
 * agente. A indexação da posição do agente é feita sempre por um 
 * par ordenado (lin, col). Ver classe Labirinto. 
 *
 * @author Tacla 
 */
public class Model implements PontosCardeais {
    /**Posição do agente (linha, coluna) */
    protected int pos[]={0,0}; 
    /**Posição do objetivo (linha, coluna) */
    protected int posObj[]={0,0}; 
    /**Representação do labirinto */
    public Labirinto labir;  
    /** Associação com a classe View que faz o print do labirinto */
    protected View view;



   /** Construtora de modelo do ambiente físico (labirinto)
    * @param linhas número de linhas do labirinto
    * @param colunas número de colunas do labirinto
    */
    public Model(int linhas, int colunas) {
        if (linhas <= 0) {
            linhas = 5;
        }
        if (colunas <= 0) {
            colunas = 5;
        }
        
        // instancia o labirinto; paredes não devem ser colocadas aqui porque
        // a classe model é utilizada para configurar varios labirintos
        labir = new Labirinto(linhas, colunas);

        // instancia a visualizacao do ambiente associando-a ao model
        view = new View(this);
    }
    /**Faz um print do labirinto */
    public void desenhar() {
        view.desenhar();
    }
    
    /**Utilizada para colocar o agente na posição inicial.
     * @param lin a linha onde o agente está situado
     * @param col a coluna onde o agente está situado
     * @return int 1 se o posicionamento é possível (dentro do labirinto e fora
     *             das paredes) e -1 se o posicionamento não é possível
    */
    public int setPos(int lin, int col) {
        // verifica se está fora do grid
        if (col < 0 || col >= labir.getMaxCol() || lin < 0 || lin >= labir.getMaxLin()) 
            return -1;

        // verifica se estah sobre uma parede
        if (labir.parede[lin][col] == 1) 
            return -1;
            
        this.pos[0] = lin;
        this.pos[1] = col;
        return 1;
    }
     /**Utilizada para colocar uma marca onde se encontra o estado objetivo.
     * @param lin a linha onde o agente está situado
     * @param col a coluna onde o agente está situado
     * @return int 1 se o posicionamento é possível (dentro do labirinto e fora
     *             das paredes) e -1 se o posicionamento não é possível
    */
    public int setObj(int lin, int col) {
        // verifica se está fora do grid
        if (col < 0 || col >= labir.getMaxCol() || lin < 0 || lin >= labir.getMaxLin()) 
            return -1;

        // verifica se estah sobre uma parede
        if (labir.parede[lin][col] == 1) 
            return -1;
            
        this.posObj[0] = lin;
        this.posObj[1] = col;
        return 1;
    }
    

   /**Colocar parede horizontal no labirinto.
    * @param ini: coluna inicial entre 0 e número máximo de colunas - 1
    * @param fim: coluna final (deve ser maior que a coluna inicial)
    * @param linha: em qual linha por a parede (entre 0 e máx. de linhas - 1)
    */
    public void porParedeHorizontal(int ini, int fim, int linha) {
        labir.porParedeHorizontal(ini, fim, linha);
    }
    
    /**Constroi parede vertical
    * @param ini: linha inicial entre 0 e  máximo de linhas - 1
    * @param fim: linha final (deve ser maior que linha inicial)
    * @param coluna: em qual coluna por a parede (entre 0 e máx. de colunas - 1)
    */
    public void porParedeVertical(int ini, int fim, int coluna) {
         labir.porParedeVertical(ini, fim, coluna);
    }

     /**Retorna a posição do agente no labirinto
     * @return vetor de 2 inteiros = par ordenado = [linha, coluna]
     */
    public int[] lerPos() {
        return pos;
    }
    /**Coloca o agente na posição solicitada pela ação ir, desde que seja
     * possível. Não pode ultrapassar os limites do labirinto nem estar em
     * uma posição ocupada por uma parede.
     * @param coord int de 0 a 7 iniciando por N no sentido horário
     */
    public void ir(int coord) {
        int lin = this.pos[0];
        int col = this.pos[1];
        switch (coord) {
            case N:
                lin--;
                break;
            case NE:
                col++;
                lin--;
                break;
            case L:
                col++;
                break;
            case SE:
                col++;
                lin++;
                break;
            case S:
                lin++;
                break;
            case SO:
                col--;
                lin++;
                break;
            case O:
                col--;
                break;
            case NO:
                col--;
                lin--;
                break;
        }
        // verifica se está fora do grid
        if (col < 0 || col >= labir.getMaxCol() || lin < 0 || lin >= labir.getMaxLin()) {
            lin = pos[0];
            col = pos[1];  // fica na posicao atual
            
        }
        // verifica se bateu em algum obstaculo
        if (labir.parede[lin][col] == 1) {
            lin = pos[0];
            col = pos[1];  // fica na posicao atual
        }

        // atribui nova posicao
        this.pos[0] = lin;
        this.pos[1] = col;
    }

    public int has_wall(int i, int j){
        if (labir.parede[i][j] == 1)
            return -1;
        else{
            return 1;
        }
    }
}