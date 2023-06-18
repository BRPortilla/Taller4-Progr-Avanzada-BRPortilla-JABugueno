/**
 * Clase abstracta (padre) Pokémon.
 * Posee como atributos: la id del Pokémon, el nombre del Pokémon, la etapa evolutiva,
 * su tipo primario y su tipo secundario (características comunes en todos los Pokémon).
 */
public abstract class Pokemon {

    /**
     * ID del Pokémon. Va desde el número 1 hasta el 151
     * (pertenecientes a la Pokédex Regional de Kanto).
     */
    private int id;

    /**
     * Nombre del pokemon.
     */
    private String nombre;

    /**
     * Etapa evolutiva del Pokémon.
     * (básico, primera evolución, segunda evolución).
     */
    private String etapa;

    /**
     * Tipo primario del Pokémon.
     */
    private String tipo1;

    /**
     * Tipo secundario del Pokémon.
     */
    private String tipo2;

    /**
     * Constructor de la clase Pokémon.
     * (los atributos se heredan hacia las clases hijas).
     * @param id (ID del Pokémon (1 - 151)).
     * @param nombre (Nombre del Pokémon).
     * @param etapa (Etapa evolutiva del Pokémon).
     * @param tipo1 (Tipo primario).
     * @param tipo2 (Tipo secundario).
     */
    public Pokemon(int id, String nombre, String etapa, String tipo1, String tipo2) {
        //Se valida que el ID no sea menor a 1 o mayor a 151.
        this.id = id;
        this.nombre = nombre;
        this.etapa = etapa;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
    }

    /**
     * getId(): Retorna la ID del Pokémon.
     * @return la ID del Pokémon.
     */
    public int getId() {
        return id;
    }

    /**
     * getNombre(): Retorna el nombre del Pokémon.
     * @return el nombre del Pokémon.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * getEtapa(): Retorna la etapa evolutiva del Pokémon.
     * @return la etapa evolutiva del Pokémon.
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * getTipo1(): Retorna el tipo primario del Pokémon.
     * @return el tipo primario del Pokémon.
     */
    public String getTipo1() {
        return tipo1;
    }

    /**
     * getTipo2(): Retorna el tipo secundario del Pokémon.
     * @return el tipo secundario del Pokémon.
     */
    public String getTipo2() {
        return tipo2;
    }
}