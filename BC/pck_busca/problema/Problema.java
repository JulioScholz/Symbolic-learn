/** Representação de um problema a ser resolvido por um algoritmo de busca clássica.
 * A formulação do problema - instância desta classe - reside na 'mente' do agente.
 *
 * @author Tacla
 */
package problema;

import problema.Estado;
import comuns.*;

/**
 *
 * @author tacla
 */
public class Problema implements PontosCardeais {

    /**Estado inicial para um problema de busca (linha, coluna) */
    public Estado estIni = new Estado(0, 0);
    /**Estado objetivo para um problema de busca (linha, coluna) */
    public Estado estObj = new Estado(0, 0);
    /**Crença do agente sobre o estado do labirinto, i.e. a posição de suas paredes */
    public Labirinto crencaLabir;
    public int maxLin;
    public int maxCol;
    public float custo[][];
    

    /**Este método instancia um labirinto - representa o que o agente crê ser
     * o labirinto. As paredes devem ser colocadas fora desta classe porque este
     * pacote é utilizado para construir labirintos diferentes.
     * @param maxLin máximo de linhas do labirinto
     * @param maxCol máximo de colunas do labirinto
     */
    public void criarLabirinto(int maxLin, int maxCol) {
        this.crencaLabir = new Labirinto(maxLin, maxCol);
        this.maxLin = maxLin;
        this.maxCol = maxCol;
        this.custo = new float [maxLin*maxCol][8];
    }

    /**
     * Define estado inicial
     * @param lin
     * @param col
     */
    public void defEstIni(int lin, int col) {
        estIni.setLinCol(lin, col);
    }

    /**
     * Define estado objetivo
     * @param lin
     * @param col
     */
    public void defEstObj(int lin, int col) {
        estObj.setLinCol(lin, col);
    }

    /**
     * Funcao sucessora: recebe um estado '(lin, col)' e calcula o estado
     * sucessor que resulta da execucao da acao = {N, NE, L, SE, S, SO, O, NO}
     * @param est
     * @param acao
     * @return 
     */
    public Estado suc(Estado est, int acao) {
        int lin = est.getLin();
        int col = est.getCol();
        
        // define incrementos para cada ação
        int incrLin[] = {-1,-1,0,1,1,1,0,-1};
        int incrCol[] = {0,1,1,1,0,-1,-1,-1};
        
        lin += incrLin[acao];
        col += incrCol[acao];
        
        // verifica limites do tabuleiro
        if (lin < 0)
            lin = 0;
        if (col < 0)
            col = 0;
        if (lin == crencaLabir.getMaxLin())
            lin = (crencaLabir.getMaxLin()-1);
        if (col == crencaLabir.getMaxCol())
            col = (crencaLabir.getMaxCol()-1);
        
        //verifica parede: caso tenha, fica na posicao original
        if (crencaLabir.parede[lin][col] == 1) {
            lin = est.getLin();
            col = est.getCol();
        }
            
        return new Estado(lin, col);
    }

    /**
     * Retorna as acoes possiveis de serem executadas em um estado 
     * O valor retornado é um vetor de inteiros. Se o valor da posicao é -1
     * então a ação correspondente não pode ser executada, caso contrario valera 1.
     * Por exemplo, 
     * [-1, -1, -1, 1, 1, -1, -1, -1] indica apenas que S e SO podem ser executadas.
     * @param est
     * @return 
     */
    public int[] acoesPossiveis(Estado est) {
        int acoes[] = {1,1,1,1,1,1,1,1};

        // testa se pode ir para o N, NE ou NO sem sair do limite do labirinto
        if (est.getLin() == 0) {
            acoes[0] = acoes[1] = acoes[7] = -1;
        }
        // testa se pode ir para o NE, L ou SE sem sair do limite do labirinto
        if (est.getCol() == (crencaLabir.getMaxCol() - 1)) {
            acoes[1] = acoes[2] = acoes[3] = -1;
        }
        // testa se pode ir para o SE, S ou SO sem sair do limite do labirinto
        if (est.getLin() == (crencaLabir.getMaxLin() - 1)) {
            acoes[3] = acoes[4] = acoes[5] = -1;
        }
        // testa se pode ir para o SO, O ou NO sem sair do limite do labirinto
        if (est.getCol() == 0) {
            acoes[5] = acoes[6] = acoes[7] = -1;
        }
        // testa se ha paredes no entorno l, c
        int l = est.getLin();
        int c = est.getCol();

        // testa se ha parede na direcao N
        if (acoes[0] != -1 && crencaLabir.parede[l - 1][c] == 1) {
            acoes[0] = -1;
        }

        // testa se ha parede na direcao NE
        if (acoes[1] != -1 && crencaLabir.parede[l - 1][c + 1] == 1) {
            acoes[1] = -1;
        }

        // testa se ha parede na direcao l
        if (acoes[2] != -1 && crencaLabir.parede[l][c + 1] == 1) {
            acoes[2] = -1;
        }

        // testa se ha parede na direcao SE
        if (acoes[3] != -1 && crencaLabir.parede[l + 1][c + 1] == 1) {
            acoes[3] = -1;
        }

        // testa se ha parede na direcao S
        if (acoes[4] != -1 && crencaLabir.parede[l + 1][c] == 1) {
            acoes[4] = -1;
        }

        // testa se ha parede na direcao SO
        if (acoes[5] != -1 && crencaLabir.parede[l + 1][c - 1] == 1) {
            acoes[5] = -1;
        }

        // testa se ha parede na direcao O
        if (acoes[6] != -1 && crencaLabir.parede[l][c - 1] == 1) {
            acoes[6] = -1;
        }

        // testa se ha parede na direcao NO
        if (acoes[7] != -1 && crencaLabir.parede[l - 1][c - 1] == 1) {
            acoes[7] = -1;
        }
        return acoes;
    }
    /**
     * Retorna as acoes possiveis desconsiderando os movimentos na
     * diagonal a partir de um estado. 
     * O valor retornado é um vetor de inteiros. Se o valor da posicao é -1
     * então a ação correspondente não pode ser executada, caso contrario valera 1.
     * Por exemplo, [1, -1, -1, -1, -1, -1, -1, -1] indica apenas que N 
     * pode ser executadas.
     * @param est
     * @return 
     */
    public int[] acoesPossiveisSemColaterais(Estado est) {
        int acoes[] = {1, -1, 1, -1, 1, -1, 1, -1};

        // testa se pode ir para o N, NE ou NO sem sair do limite do labirinto
        if (est.getLin() == 0) {
            acoes[N] = -1;
        }
        // testa se pode ir para o NE, L ou SE sem sair do limite do labirinto
        if (est.getCol() == (crencaLabir.getMaxCol() - 1)) {
            acoes[L] = -1;
        }
        // testa se pode ir para o SE, S ou SO sem sair do limite do labirinto
        if (est.getLin() == (crencaLabir.getMaxLin() - 1)) {
            acoes[S] = -1;
        }
        // testa se pode ir para o SO, O ou NO sem sair do limite do labirinto
        if (est.getCol() == 0) {
            acoes[O] = -1;
        }
        // testa se ha paredes no entorno l, c
        int l = est.getLin();
        int c = est.getCol();

        // testa se ha parede na direcao N
        if (acoes[N] != -1 && crencaLabir.parede[l - 1][c] == 1) {
            acoes[N] = -1;
        }

        // testa se ha parede na direcao l
        if (acoes[L] != -1 && crencaLabir.parede[l][c + 1] == 1) {
            acoes[L] = -1;
        }

        // testa se ha parede na direcao S
        if (acoes[S] != -1 && crencaLabir.parede[l + 1][c] == 1) {
            acoes[S] = -1;
        }

        // testa se ha parede na direcao O
        if (acoes[O] != -1 && crencaLabir.parede[l][c - 1] == 1) {
            acoes[O] = -1;
        }
        acoes[NE] = -1;
        acoes[SE] = -1;
        acoes[SO] = -1;
        acoes[NO] = -1;
        return acoes;
    }
    /**
     * Retorna o custo da acao para ir do estado 1 ao estado 2
     * @param est1
     * @param acao
     * @param est2
     * @return 
     */
    public float obterCustoAcao(Estado est1, int acao, Estado est2) {
        if (acao == N || acao == L || acao == O || acao == S) {
            return (float) 1;
        } else {
            return (float) 1.5;
        }
    }
        /**
     * Retorna o custo da acao no estado 1 PARTIR da 
     * matriz do atributo custo
     * @param est1
     * @param acao
     * @return 
     */
    public float obterCustoAcao(Estado est1, int acao) {
        int i = est1.getLin()*maxCol + est1.getCol();
        return this.custo[i][acao];
    }

    /**
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     * @param estAtu estado atual
     * @return true se o estado atual for igual ao estado objetivo
     */
    public boolean testeObjetivo(Estado estAtu) {
        return this.estObj.igualAo(estAtu);
    }
}
