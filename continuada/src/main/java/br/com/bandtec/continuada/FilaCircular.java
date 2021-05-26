public class FilaCircular {
    private int  tamanho;
    private String[] fila;
    private int inicio;
    private int fim;

    public FilaCircular(int capacidade) {
        tamanho=0;
        inicio=0;
        fim=0;
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
            fila[fim]= info;
            fim=(fim+1)% fila.length;
            tamanho++;
        }
    }
    public String peek(){
        return fila[inicio];
    }
    public String pool(){
        String primeira= peek();
        if(!isEmpty()){
            inicio=(inicio+1)%fila.length;
            tamanho--;
        }
        return primeira;
    }
    public void exibe(){
        if(isEmpty()){
            System.out.printf("Fila vazia");
        }else{
            System.out.println("Elementos da fila:");
            for(int i=inicio; i<fim; i++){
                System.out.println(fila[i]);
            }
        }
    }
}
