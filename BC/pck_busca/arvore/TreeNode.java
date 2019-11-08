/*
 * Implementa nó de árvore de busca. 
 */
package arvore;

import java.util.ArrayList;
import java.util.List;
import problema.Estado;

/**
 *
 * @author tacla
 */
public class TreeNode {
    private final TreeNode parent;
    private final List<TreeNode> children = new ArrayList<>();
    private int depth=0; // armazena a profundidade do nó
    private float gn;  // g(n) custo acumulado até o nó n
    private float hn;  // h(n) heurística a partir do nó n
    private Estado st;  // estado par <linha, coluna>
    private int   action; // acao que levou ao estado 

    public TreeNode(TreeNode pai) {
        this.parent = pai;
            }

    public Estado getState() {
        return st;
    }

    public int getDepth() {
        return this.depth;
    }
    public void setState(Estado est) {
        this.st = est;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public float getFn() {
        return gn + hn;
    }

    public void setGn(float g) {
        this.gn = g;
    }

    public float getGn() {
        return gn;
    }

    public float getHn() {
        return hn;
    }

    public void setHn(float hn) {
        this.hn = hn;
    }
    
    public void setAction(int a) {
        this.action = a;
    }
    public int getAction() {
        return this.action;
    }
    /**
     * Este método instancia um nó filho de this e cria uma associação entre
     * o pai (this) e o filho
    */
    public TreeNode addChild() {
        TreeNode child = new TreeNode(this);
        child.depth = this.depth+1;
        this.children.add(child);
        return child;
    }
    /**
     * Remove-se da arvore cortando a ligacao com o no pai.
     */
    public void remove() {
        // remove-se da lista de filhos do pai
        boolean removed = this.parent.children.remove(this);
        if (!removed) {
            System.out.println("### erro remocao noh: "+this.gerarStr());
        }
       
    }

    public String gerarStr() {
        String str;
        str = String.format("<%s g:%.2f h:%.2f f=%.2f>",
        this.st.getString(), gn, hn, this.getFn());
        return str;
    }

    /*
     * Atribui valores aos atributos gn, hn e fn. fn recebe gn+hn.
     * @param gn float que representa o custo acumulado da raiz até o nó n
     * @param hn float que representa o valor da heurística de n até o nó 
     *           objetivo
     */
    public void setGnHn(float gn, float hn) {
        this.gn = gn;
        this.hn = hn;
    }

    private static void printSubTreeRec(TreeNode node, int nivel) {
        String spaces = String.format("%"+nivel+"s","");
        System.out.println(spaces + node.gerarStr());
        nivel++;
        for (TreeNode each : node.getChildren()) {
            printSubTreeRec(each, nivel);
        }
    }


    /**
     * Imprime a subárvore do nó em questão (this).
     */
    public void printSubTree( ) {
        System.out.println("\nA R V O R E"); 
        printSubTreeRec(this, 1);

    }
}
