package modelo;

public class EnvioTerrestre extends Envio {

    public EnvioTerrestre(int codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularCosto() {
        return (getDistancia() * 1500) + (getPeso() * 2000);
    }

    @Override
    public String getTipo() {
        return "Terrestre";
    }
}