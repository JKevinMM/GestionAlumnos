package gestionalumnos;

public class Students {
    String dni, registry, name, surname1, surname2;

    public Students() {
        this.dni = "";
        this.registry = "";
        this.name = "";
        this.surname1 = "";
        this.surname2 = "";
    }

    public String getDni() {
        return dni;
    }

    public String getRegistry() {
        return registry;
    }

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }
}