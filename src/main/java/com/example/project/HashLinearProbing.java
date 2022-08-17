package com.example.project;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] personas; // array que representa la tabla hash
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.personas = new Persona[hsize];
        this.hsize = hsize;
        this.size = 0;
    }

    public void insertHash(Persona p) {
        if (isFull()) { //Si esta lleno el hash
            System.out.println("Tabla hash esta llena!");
            return;
        }
        if (findPersona(p.DNI)==null){ //Si no encuentra a la persona
            personas[size]= p;          //La agrega
            size++;                     //Aumenta el tamaño (nro de elementos validos)
        }
        else{ //En caso el dni ya este registrado
            System.out.println("La persona ya fue registrada");
        }
    }

    public void deleteHash(String dni) {
        if (isEmpty()) { //Si esta vacio el hash
            System.out.println("Tabla hash esta vacia!");
            return;
        }        
        if (findPersona(dni)==null){ //Si el dni no fue registrado
            System.out.println("La persona no esta regitrada");
        }
        else{
            for(int i= 0; i< size; i++){                
                if(personas[i]== null && i< hsize -1){ //Esto sirve para acomodar el hash
                    personas[i]= personas[i+1];         //luego de remover a alguna persona
                    personas[i+1]= null;
                    continue;
                }
                if(personas[i].DNI.equals(dni)){        //Esto remueve a la persona
                    personas[i]= null;
                    i--;                                //Vuelve a recorrer la iteracion
                }                                       //para acomodar correctamente
            }
            size--;
        }
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (personas[i] == null) {
                System.out.println("Registro " + i + ": Vacia");
            } else {
                System.out.println("DNI: " + personas[i].DNI + " - " + personas[i].nombre);
            }
        }
    }

    public Persona findPersona(String dni){
        for(int i= 0; i<size; i ++){
            if(personas[i].DNI.equals(dni)){
                return personas[i];
            }
        }
        return null;
    }
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        for (int i = 0; i < hsize; i++) {
            if (personas[i] != null) {
                return false;
            }
        }
        return true;
    }
}