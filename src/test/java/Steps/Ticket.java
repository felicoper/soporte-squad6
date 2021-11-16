package Steps;

public class Ticket {
    String nombreApellidoCliente;
    int legajoCliente = 0;
    String tituloTicket;
    String descripcionTicket;
    String nombrePorducto;
    int idProducto = 0;
    String estadoTicket;
    String tipoTicket;
    int severidadTicket = 0;
    int identificador;

    public Ticket(String unNombreApellidoCliente, int unLegajoCliente, String unTitulo, String unaDescripcion, String unNombreProducto, int unIdProducto, String unEstadoTicket, String unTipoTicket, int unaSeveridad){
        this.nombreApellidoCliente = unNombreApellidoCliente;
        this.legajoCliente = unLegajoCliente;
        this.tituloTicket = unTitulo;
        this.descripcionTicket = unaDescripcion;
        this.nombrePorducto = unNombreProducto;
        this.idProducto = unIdProducto;
        this.estadoTicket = unEstadoTicket;
        this.severidadTicket = unaSeveridad;
        this.tipoTicket = unTipoTicket;
    }


}
