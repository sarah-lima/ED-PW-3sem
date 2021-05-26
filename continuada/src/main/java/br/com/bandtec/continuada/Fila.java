package br.com.bandtec.continuada;

public class Fila {
        private int  tamanho;
        private String[] fila;

    public Fila(int capacidade) {
        tamanho=0;
        fila= new String[capacidade];
    }
    public Boolean isEmpty(){
        return tamanho==0;
    }
    public Boolean isFull(){
        return tamanho==fila.length;
    }
    public void insert(String info){
        if(isFull()){
            System.out.println("Fila cheia");
        }else{
            fila[tamanho++]= info;
        }
    }
    public String peek(){
        return fila[0];
    }
    public String pool(){
        String primeira= peek();
        if(!isEmpty()){
            for(int i=0; i<tamanho-1;i++){
                fila[i]=fila[i++];
            }
            fila[tamanho-1]= null;
            tamanho--;
        }
        return primeira;
    }
    public void exibe(){
        if(isEmpty()){
            System.out.printf("Fila vazia");
            }else{
            System.out.println("Elementos da fila:");
            for(int i=0; i<tamanho; i++){
                System.out.println(fila[i]);
            }
        }
    }
}
