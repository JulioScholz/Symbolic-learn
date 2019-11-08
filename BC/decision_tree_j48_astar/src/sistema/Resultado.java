package src.sistema;

public class Resultado {
    private int baseline;
    private double custo;
    private int id_cenario;

     Resultado(){
         this.baseline =0;
         this.custo = 0;
         this.id_cenario =0;
     }
     Resultado(int b, double c, int id){
         this.baseline =b;
         this.custo = c;
         this.id_cenario =id;
     }

     public void print(){
        // System.out.print(" id_cenario: "+ this.id_cenario);
         if(baseline == 0){
            // System.out.print(" baseline: random ");
         }
         else{
            // System.out.print(" baseline: J48 ");
         }
         System.out.print(this.custo );

     }

}
