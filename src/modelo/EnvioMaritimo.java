package modelo;

public class EnvioMaritimo extends Envio {

    public EnvioMaritimo(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularCosto() {
        return (getDistancia() * 800) + (getPeso() * 1000);
    }

    @Override
    public String getTipo() {
        return "Marítimo";
    }
}