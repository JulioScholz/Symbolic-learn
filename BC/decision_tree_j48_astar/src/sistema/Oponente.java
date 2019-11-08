package src.sistema;



public class Oponente {
    private double massa;
    private double altura;
    private int dentes;
    private int corolhos;
    private boolean gentil;
    private int id;

    public static final int normais = 0, afiados = 1, escura = 0, clara = 1, vermelha = 2;

    public Oponente(){
        this.massa = 0;
        this.altura = 0;
        this.gentil = true;
        this.dentes = normais;
        this.corolhos = escura;
        this.gentil = true;
        this.id =0;
    }
    public Oponente( double m, double a, int d, int c, boolean g, int id){
        this.massa = m;
        this.altura = a;
        this.dentes = d;
        this.corolhos = c;
        this.gentil = g;
        this.id = id;
    }

    public boolean get_gentil(){
        return this.gentil;
    }
    public double get_massa(){
        return this.massa;
    }
    public double get_altura(){
        return this.altura;
    }
    public int get_dentes(){
        return this.dentes;
    }
    public int get_corolhos(){
        return this.dentes;
    }

    public void print(){
        System.out.println(this.id +" "+ this.massa +" "+
        this.altura +" "+
        this.dentes +" "+
        this.corolhos+" " +
        this.gentil);
    }


}

