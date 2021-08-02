package mx.unam.ingenieria.tienda.recyclerview;

public class MuestraProducto {


    private String imagen;
    private String descripcion;

    private String nombre;
    private String titulo;
    private float precio;
    private float descuento;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public MuestraProducto(){

    }

    public MuestraProducto(String titulo, String imagen, String descripcion) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public MuestraProducto(String nombre, String titulo, float precio, float descuento) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.precio = precio;
        this.descuento = descuento;
    }




}