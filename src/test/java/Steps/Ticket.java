package Steps;

public class Ticket {
    int legajoCliente = 0;
    int legajoPersonaAsignada = 0;
    int idProducto = 0;
    String estado;


    public Ticket(String estadoInicial){
        this.estado = estadoInicial;
    }

    public void cambiarEstado(String nuevoEstado){
        if(this.estado != "cerrado") {
            this.estado = nuevoEstado;
        }
    }
    public String getEstado(){
        return this.estado;
    }

}
