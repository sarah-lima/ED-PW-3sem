package br.com.bandtec.continuada;

import java.sql.Array;

public class Fila<T> {
        private int  tamanho;
        private T[] fila;

    public Fila(int capacidade) {
        tamanho=0;
        fila= (T[]) new Object[capacidade];
    }
    public Boolean isEmpty(){
        return tamanho==0;
    }
    public Boolean isFull(){
        return tamanho==fila.length;
    }
    public void insert(T info){
        if(isFull()){
            System.out.println("Fila cheia");
        }else{
            fila[tamanho++]= info;
        }
    }
    public T peek(){
        return fila[0];
    }
    public T pool(){
        T primeira= peek();
        if(!isEmpty()){
            for(int i=0; i<tamanho-1;i++){
                fila[i]=fila[i+1];
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