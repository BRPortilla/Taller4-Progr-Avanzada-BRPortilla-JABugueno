/**
 * Clase "Basico".
 * Clase hija de la clase Pokémon (hereda sus atributos).
 * Las instancias de esta clase son Pokémon de etapa evolutiva básica.
 */
public class Basico extends Pokemon{

    /**
     * Primera evolución.
     * Contiene el String del nombre de la primera evolución (si tiene).
     * Si no tiene primera evolución, se guarda como un string vacío: "".
     */
    private String primera_evolucion;

    /**
     * Segunda evolución.
     * Contiene el String del nombre de la segunda evolución (si tiene).
     * Si no tiene segunda evolución, se guarda como un string vacío: "".
     */
    private String segunda_evolucion;

    /**
     * Constructor de un Pokémon de etapa evolutiva básica.
     * Hereda los atributos de la clase padre Pokemon.
     * Si no tiene primera evolución, no puede tener segunda. Por ende, si la primera evolución
     * es un string vacío, la segunda también lo será (en la lectura de archivo igualmente se comprueba esto).
     * @param id (ID del Pokémon, entre 1 y 151).
     * @param nombre (Nombre del Pokémon).
     * @param etapa (Etapa evolutiva del Pokémon: en esta clase, siempre será "Basico").
     * @param tipo1 (Tipo primario del Pokémon).
     * @param tipo2 (Tipo secundario del Pokémon).
     * @param primera_evolucion (String del nombre de la primera evolución. Si no tiene, es un string vacío: "").
     * @param segunda_evolucion (String del nombre de la segunda evolución. Si no tiene, es un string vacío: "")
     */
    public Basico(int id, String nombre, String etapa, String tipo1, String tipo2, String primera_evolucion, String segunda_evolucion) {
        //Hereda atributos de la clase padre Pokemon.
        super(id, nombre, etapa, tipo1, tipo2);
        this.primera_evolucion = primera_evolucion;
        this.segunda_evolucion = segunda_evolucion;
        if(this.primera_evolucion.equals("")){
            //Si no tiene primera evolución, no tiene segunda evolución.
            this.segunda_evolucion = "";
        }
    }

    /**
     * getPrimera_evolucion(): Retorna el nombre de la primera evolución del Pokémon.
     * @return nombre de la primera evolución del Pokémon.
     */
    public String getPrimera_evolucion() {
        return primera_evolucion;
    }

    /**
     * getSegunda_evolucion(): Retorna el nombre de la segunda evolución del Pokémon.
     * @return nombre de la segunda evolución del Pokémon.
     */
    public String getSegunda_evolucion() {
        return segunda_evolucion;
    }
}
