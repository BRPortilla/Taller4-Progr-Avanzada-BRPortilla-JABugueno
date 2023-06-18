/**
 * Clase "Segunda_Evolucion".
 * Clase hija de la clase Pokemon (hereda sus atributos).
 * Las instancias de esta clase corresponden a Pokémon de etapa evolutiva de segunda evolución.
 */
public class Segunda_Evolucion extends Pokemon{

    /**
     * Pre-evolución (pertenece a la primera evolución de la cadena).
     * El String con el nombre de la primera pre-evolución (en la cadena
     * evolutiva, es la primera evolución).
     */
    private String pre_evolucion;

    /**
     * Pre-evolución básico (la etapa básica del Pokémon en la cadena).
     * El String con el nombre de la segunda pre-evolución (en la cadena evolutiva, es
     * la etapa básica).
     */
    private String pre_evolucion_basico;

    /**
     * getPre_evolucion(): retorna el nombre de la primera pre-evolución.
     * @return el nombre de la primera pre-evolución (la primera evolución en la cadena evolutiva).
     */
    public String getPre_evolucion() {
        return pre_evolucion;
    }

    /**
     * getPre_evolucion_basico(): retorna el nombre de la segunda pre-evolución (etapa básica).
     * @return el nombre de la segunda pre-evolución (la etapa básica en la cadena evolutiva).
     */
    public String getPre_evolucion_basico() {
        return pre_evolucion_basico;
    }

    /**
     * El constructor del Pokémon de segunda evolución.
     * @param id (ID del Pokémon (1-151)).
     * @param nombre (Nombre del Pokémon).
     * @param etapa (Etapa evolutiva: en este caso, "Segunda Evolucion").
     * @param tipo1 (El tipo primario del Pokémon).
     * @param tipo2 (El tipo secundario del Pokémon).
     * @param pre_evolucion (El nombre del Pokémon que es la pre-evolución (primera evolución)).
     * @param pre_evolucion_basico (El nombre del Pokémon que es de la etapa evolutiva básica).
     */
    public Segunda_Evolucion(int id, String nombre, String etapa, String tipo1, String tipo2, String pre_evolucion, String pre_evolucion_basico){
        super(id, nombre, etapa, tipo1, tipo2);
        this.pre_evolucion = pre_evolucion;
        this.pre_evolucion_basico = pre_evolucion_basico;
    }
}
