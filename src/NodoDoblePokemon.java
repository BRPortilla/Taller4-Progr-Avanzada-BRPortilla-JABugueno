/**
 * Nodo doble de Pokémon.
 * Un nodo de tipo doble enlace/nexo (tiene un anterior y un siguiente),
 * que almacena un Pokémon.
 */

public class NodoDoblePokemon {

    /**
     * El Pokémon contenido en el nodo.
     */
    private Pokemon pokemon;

    /**
     * El nodo anterior.
     */
    private NodoDoblePokemon anterior;

    /**
     * El nodo siguiente.
     */
    private NodoDoblePokemon siguiente;

    /**
     * Constructor del nodo.
     * Define por defecto como nulo su anterior y siguiente.
     * @param pokemon (El Pokémon almacenado).
     */
    public NodoDoblePokemon(Pokemon pokemon){
        this.pokemon = pokemon;
        this.anterior = null;
        this.siguiente = null;
    }

    /**
     * getPokemon(): retorna el Pokémon del nodo.
     * @return el Pokémon del nodo.
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * setPokemon(): permite settear o insertar el nuevo Pokémon dentro del nodo.
     * Tiene su utilidad principalmente en los métodos para ordenar la lista de nodos.
     * @param pokemon (el Pokémon de entrada en el nodo).
     */

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * getAnterior(): retorna el nodo anterior.
     * @return el nodo anterior.
     */

    public NodoDoblePokemon getAnterior() {
        return anterior;
    }

    /**
     * setAnterior(): settea o define el nodo anterior.
     * @param anterior (el nodo anterior al actual).
     */
    public void setAnterior(NodoDoblePokemon anterior) {
        this.anterior = anterior;
    }

    /**
     * getSiguiente(): retorna el nodo siguiente.
     * @return el nodo siguiente.
     */
    public NodoDoblePokemon getSiguiente() {
        return siguiente;
    }

    /**
     * setSiguiente(): permite settear o definir el siguiente nodo.
     * Tiene su utilidad en los métodos de ordenamiento de los nodos.
     * @param siguiente (el nodo siguiente al actual).
     */

    public void setSiguiente(NodoDoblePokemon siguiente) {
        this.siguiente = siguiente;
    }
}