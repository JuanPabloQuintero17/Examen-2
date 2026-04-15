package modelo;

public class EnvioAereo extends Envio {

    public EnvioAereo(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularCosto() {
        return (getDistancia() * 5000) + (getPeso() * 4000);
    }

    @Override
    public String getTipo() {
        return "Aéreo";
    }
}