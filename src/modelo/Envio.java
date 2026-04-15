package modelo;

public abstract class Envio {
    private int codigo;
    private String cliente;
    private double peso;
    private double distancia;

    public Envio(int codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }

    public int getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public double getPeso() { return peso; }
    public double getDistancia() { return distancia; }

    public abstract double calcularCosto();
    public abstract String getTipo();
}
