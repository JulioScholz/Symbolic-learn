package ambiente;

/**Desenha o ambiente (o que está representado no Model) em
 * formato texto.
 *
 * @author tacla 
 */
class View {
    private Model model;
    protected View(Model m) {
        this.model = m;
    }

    /**Desenha o labirinto representado no modelo
    *
    */
    protected void desenhar() {
        System.out.println("--- Estado do AMBIENTE ---");
        System.out.println(model.pos[0] + "," + model.pos[1]);
        
        // imprime números das colunas
        System.out.print("   ");
        for (int col = 0; col < model.labir.getMaxCol(); col++) {
            System.out.printf(" %2d ", col);
        }
        System.out.print("\n");
        for (int lin = 0; lin < model.labir.getMaxLin(); lin++) {
            System.out.print("   ");
            for (int col = 0; col < model.labir.getMaxCol(); col++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            System.out.printf("%2d ", lin);
            for (int col = 0; col < model.labir.getMaxCol(); col++) {
                if (model.labir.parede[lin][col] == 1) {
                    System.out.print("|XXX");  // desenha parede
                } else if (model.pos[0] == lin && model.pos[1] == col) {
                    System.out.print("| A ");  // desenha agente
                } else if (model.posObj[0] == lin && model.posObj[1] == col) {
                    System.out.print("| G ");
                } else {
                    System.out.print("|   ");  // posicao vazia
                }
            }
            System.out.print("|");
            if (lin == (model.labir.getMaxLin() - 1)) {
                System.out.print("\n   ");
                for (int x = 0; x < model.labir.getMaxCol(); x++) {
                    System.out.print("+---");
                }
                System.out.println("+\n");
            }
            System.out.print("\n");
        }
    }
}
