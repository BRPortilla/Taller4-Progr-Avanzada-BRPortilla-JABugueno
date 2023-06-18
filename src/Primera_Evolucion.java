/**
 * Clase "Primera_Evolucion".
 * Clase hija de la clase Pokémon (hereda sus atributos).
 * Las instancias de esta clase son Pokémon de etapa evolutiva de primera evolución.
 */

public class Primera_Evolucion extends Pokemon{

    /**
     * La pre-evolución (etapa básica del Pokémon).
     * Contiene el nombre del Pokémon correspondiente a su pre-evolución.
     * Los Pokémon de primera evolución sí o sí poseen etapa básica.
     */
    private String pre_evolucion;

    /**
     * La evolución siguiente (segunda evolución de la cadena evolutiva).
     * Contiene el nombre del Pokémon correspondiente a su evolución siguiente.
     * Se puede tener o no (si no tiene segunda evolución, es un string vacío: "").
     */
    private String evolucion_sig;

    /**
     * El constructor para un Pokémon de primera evolución.
     * @param id (ID del Pokémon (desde el 1 hasta el 151)).
     * @param nombre (Nombre del Pokémon).
     * @param etapa (Etapa evolutiva del Pokémon. En este caso, "Primera Evolucion").
     * @param tipo1 (Tipo primario del Pokémon).
     * @param tipo2 (Tipo secundario del Pokémon).
     * @param pre_evolucion (Nombre de la pre-evolución).
     * @param evolucion_sig (Nombre de la siguiente evolución. Si no tiene, es un string vacío: "").
     */
    public Primera_Evolucion(int id, String nombre, String etapa, String tipo1, String tipo2, String pre_evolucion, String evolucion_sig){
        super(id, nombre, etapa, tipo1, tipo2);
        this.pre_evolucion = pre_evolucion;
        this.evolucion_sig = evolucion_sig;
    }

    /**
     * getPre_evolucion(): retorna el nombre de la pre-evolución.
     * @return el nombre del Pokémon correspondiente a su pre-evolución.
     */
    public String getPre_evolucion() {
        return pre_evolucion;
    }

    /**
     * getEvolucion_sig(): retorna el nombre de la evolución siguiente.
     * @return el nombre del Pokémon correspondiente a su evolución siguiente.
     */
    public String getEvolucion_sig() {
        return evolucion_sig;
    }
}
