package server.domain.model;
import java.util.Objects;

public class Coche {
    int matricula;
    String modelo;
    String marca;


    public Coche() {
    }

    public Coche(int matricula, String modelo, String marca) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Coche matricula(int matricula) {
        setMatricula(matricula);
        return this;
    }

    public Coche modelo(String modelo) {
        setModelo(modelo);
        return this;
    }

    public Coche marca(String marca) {
        setMarca(marca);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coche)) {
            return false;
        }
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula) && Objects.equals(modelo, coche.modelo) && Objects.equals(marca, coche.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, modelo, marca);
    }

    @Override
    public String toString() {
        return "{" +
            " matricula='" + getMatricula() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", marca='" + getMarca() + "'" +
            "}";
    }
    
}
