/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.sistema;
import java.io.FileNotFoundException;
import java.util.Random;

import ambiente.*;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.trees.J48;


/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) throws Exception {
        // Cria o ambiente (modelo) = labirinto com suas paredes
        Model model = new Model(9, 9);
        model.labir.porParedeHorizontal(0, 1, 0);
        model.labir.porParedeHorizontal(4, 7, 0);
        model.labir.porParedeHorizontal(0, 0, 1);
        model.labir.porParedeHorizontal(6, 6, 2);
        model.labir.porParedeHorizontal(5, 7, 3);
        model.labir.porParedeVertical(5, 7, 7);
        model.labir.porParedeVertical(5, 6, 5);
        model.labir.porParedeVertical(6, 7, 4);
        model.labir.porParedeVertical(5, 6, 2);
        model.labir.porParedeVertical(6, 6, 1);


        // String filename = "data/dataset-rudes-gentis-Treinamento.arff";
        String filename = "data/Oponentes.arff";


        DataSource ds = new DataSource(filename);
        Instances ins = ds.getDataSet();
        ins.setClassIndex(4);

        Oponente matriz_op[][] = new Oponente[9][9];
        boolean gentil;
        String rude_gentil;
        String cor;
        String dentes;
        int dent;
        int co;
        int count_ins = 0;
        Resultado matriz_result[][]= new Resultado[2][100];


        // Cria um agente
        Agente ag = new Agente(model);

        int k = 0;

        while(k <2){
            //se k = 0 estrategia baseline , se k igual 1 estrategia j48
            ag.set_estrategia(k);
            int id_cenario = 0;
            while (id_cenario < 100) {

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (model.has_wall(i, j) != -1) {
                            dentes = ins.get(count_ins).toString(2);
                            if (dentes == "afiados") {
                                dent = 1;
                            } else {
                                dent = 0;
                            }

                            cor = ins.get(count_ins).toString(3);
                            if (cor == "escura") {
                                co = 0;
                            } else if (dentes == "clara")
                                co = 1;
                            else {
                                co = 2;
                            }

                            rude_gentil = ins.get(count_ins).toString(4);
                            if (rude_gentil == "S") {
                                gentil = true;
                            } else {
                                gentil = false;
                            }
                            matriz_op[i][j] = new Oponente(ins.get(count_ins).value(0), ins.get(count_ins).value(1), dent, co, gentil, count_ins);
                           //matriz_op[i][j].print();
                            count_ins++;
                            if (count_ins == 5500) {
                                count_ins = 0;
                            }
                        }
                    }
                }

                // seta a posição inicial do agente no ambiente - corresponde ao estado inicial=-
                model.setPos(ag.prob.estIni.getLin(), ag.prob.estIni.getCol());
                // marca no ambiente onde estah o objetivo - somente para visualizacao
                model.setObj(ag.prob.estObj.getLin(), ag.prob.estObj.getCol());

                // Ciclo de execucao do sistema
                // desenha labirinto
                //model.desenhar();

                // agente escolhe proxima açao e a executa no ambiente (modificando
                // o estado do labirinto porque ocupa passa a ocupar nova posicao)
                ag.setOponentes(matriz_op);
                System.out.println("\n*** Inicio do ciclo de raciocinio do agente ***\n");
                while (ag.deliberar() != -1) {
                  //  model.desenhar();
                }
                matriz_result[k][id_cenario] = new Resultado(k,ag.get_custo(),id_cenario);
                id_cenario++;
                ag.reset();
            }
            k++;
        }

            for(int j=0;j<100;j++){
                matriz_result[0][j].print();
                System.out.print(" ");
                matriz_result[1][j].print();
                System.out.println();
            }
    }
}
