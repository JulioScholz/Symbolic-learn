package arvore;
import java.util.Comparator;

/**
 * Compara valores de f(n) - utilizado para ordenar a fronteira. Serve tanto
 * para custo uniforme como para A*. No custo uniforme, o valor de h(n) deve
 * estar zerado para todo nó n.
 * @author tacla
 */
public class fnComparator implements Comparator<TreeNode> {
    @Override
    public int compare(TreeNode a, TreeNode b) {
        int res;
        if (a.getFn() < b.getFn()) {
            res = -1;
        } else if (a.getFn() == b.getFn()) {
            res = 0;
        } else {
            res = 1;
        }
        return res;
    }
}
