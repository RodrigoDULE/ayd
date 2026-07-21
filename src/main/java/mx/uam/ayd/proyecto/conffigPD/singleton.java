package mx.uam.ayd.proyecto.conffigPD;

public class singleton {

    // 1. Única instancia de la clase
    private static singleton instance;

    // 2. Atributo de instancia (aquí YA NO lleva static)
    private long idActivo;

    // 3. Constructor privado
    private singleton() {}

    // 4. Método global para obtener la instancia única
    public static singleton getInstance() {
        if (instance == null) {
            instance = new singleton();
        }
        return instance;
    }

    // 5. Métodos de instancia donde SÍ puedes usar 'this'
    public void iniciarSesion(long idActivo) {
        this.idActivo = idActivo;
    }

    public long getIdActivo() {
        return this.idActivo;
    }
}