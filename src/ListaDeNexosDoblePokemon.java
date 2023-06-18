/**
 * La clase de la lista con nexos/enlaces dobles circulares.
 * La lista contiene nodos, que cada uno tiene un Pokémon.
 * Al ser circular, el último nodo (tails, la cola) apunta a la cabeza.
 */

public class ListaDeNexosDoblePokemon {

    /**
     * La cabeza de la lista.
     * Corresponde al primer nodo, adelante del resto.
     * Su nodo anterior es la cola (tails).
     */
    private NodoDoblePokemon head;

    /**
     * La cola de la lista.
     * Corresponde al último nodo, atrás del resto.
     * Su nodo siguiente es la cabeza (head).
     */
    private NodoDoblePokemon tails;

    /**
     * ListaDeNexosDoblePokemon(): El constructor de la lista de nexos dobles.
     * Al crear la lista, estará vacía. Cuando la lista está vacía, la cabeza y la cola
     * no apuntan nada (nulos).
     */
    public ListaDeNexosDoblePokemon(){
        this.head = null;
        this.tails = null;
    }

    /**
     * agregarAdelante(Pokemon pokemon):
     * Este método permite añadir un nuevo nodo a la lista, que contiene un Pokémon (nuevoPokemon).
     * Existen 2 casos para agregar un Pokémon: que la cabeza sea nula (lista vacía) o no.
     * Si la cabeza es nula, el nuevo nodo será la cabeza y cola a la vez. A su vez, su siguiente
     * y anterior será si mismo.
     * En el otro caso, el nuevo nodo apuntará como siguiente a la cabeza actual, y será el anterior
     * de la cabeza. (N) <---> (H).
     * El anterior del nuevo nodo será la cola, y el siguiente de la cola es el nuevo nodo.
     * El nuevo nodo se vuelve la nueva cabeza. En resumen, el nuevo nodo con el nuevo Pokémon se
     * añade por delante de todos, siendo la nueva cabeza, y se realiza el ajuste de la cabeza y la cola.
     * @param pokemon (El Pokémon a añadir).
     */
    public void agregarAdelante(Pokemon pokemon){
        NodoDoblePokemon nuevoPokemon = new NodoDoblePokemon(pokemon);

        if (this.head == null){
            //Si la lista está vacía, el nuevo nodo será la cabeza y cola, y se referenciará
            //a sí mismo como anterior y siguiente.
            this.head = nuevoPokemon;
            this.tails = nuevoPokemon;
            nuevoPokemon.setAnterior(this.tails);
            nuevoPokemon.setSiguiente(this.head);
            return;
        }

        //Inserción de un nuevo nodo por delante de la cabeza.
        //El nuevo nodo pasa a ser la cabeza.
        nuevoPokemon.setSiguiente(this.head);
        this.head.setAnterior(nuevoPokemon);
        nuevoPokemon.setAnterior(this.tails);
        this.tails.setSiguiente(nuevoPokemon);
        this.head = nuevoPokemon;
    }

    /**
     * obtenerPokemon_Nombre(String nombre_Pokemon):
     * Este método permite buscar un Pokémon, a partir de un nombre específico, y lo retorna en caso de encontrarlo.
     * Se usa principalmente en el contrato de la búsqueda personalizada. Antes de realizar
     * la búsqueda, se realizan validaciones.
     * 1) Si el parámetro de nombre es un stirng vacío (""), se retorna null (no existe).
     * 2) Si la cabeza es nula (la lista está vacía), se retorna null.
     * 3) Si el nombre que llega como parámetro es del Pokémon en el nodo de la cabeza, se retorna
     * directamente.
     * 4) Se genera un nodo auxiliar (el nodo siguiente a la cabeza, como ya se analizó previamente).
     * Recorre la lista hasta que (el nodo auxiliar) sea la cabeza, buscando el Pokémon que coincida con el nombre,
     * mediante un ciclo while. Si lo encuentra, retorna el Pokémon. Si al recorrer la lista entera, no lo encuentra,
     * se retorna null.
     * @param nombre_Pokemon (el nombre del Pokémon a buscar en la lista).
     * @return el Pokémon que coincida con el nombre si se encuentra en la lista, null si no existe.
     */

    public Pokemon obtenerPokemon_Nombre(String nombre_Pokemon){
        if (nombre_Pokemon.equals("")){
            //Si el nombre es un string vacío, se retorna null.
            return null;
        }

        if (this.head == null){
            //Si la cabeza es nula, se retorna null.
            return null;
        }

        if (this.head.getPokemon().getNombre().equalsIgnoreCase(nombre_Pokemon)){
            //Si el Pokémon que está en el nodo de la cabeza, coincide con el nombre
            //buscado, se retorna directamente.
            return this.head.getPokemon();
        }

        //Como se analizó la cabeza, el nodo auxiliar que recorrerá la lista empieza del siguiente.
        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();

        //Mientras el nodo auxiliar no sea la cabeza, recorrerá la lista.
        while (nodoAuxiliar != this.head){

            if (nodoAuxiliar.getPokemon().getNombre().equalsIgnoreCase(nombre_Pokemon)){
                //Si el Pokémon en el nodo auxiliar tiene el mismo nombre que el nombre ingresado, se retorna.
                return nodoAuxiliar.getPokemon();
            }
            //Iteración del nodo al siguiente.
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }

        //Si no se encuentra, se retorna null.
        return null;
    }

    /**
     * obtenerPokemonPorID(posicion_id):
     * Permite buscar un Pokémon, a partir del número de ID, y retorna el Pokémon si lo encuentra.
     * Este método se utiliza en el requerimiento de buscar por ID, o cuando existe solo un Pokémon en la lista.
     * Se realizan validaciones primeramente. Si la cabeza es nula, retorna null.
     * Si el ID del Pokémon en el nodo de la cabeza coincide con el ID ingresado, se retorna directamente.
     * Al analizar la cabeza, se inicializa un nodo auxiliar (como el siguiente de la cabeza), y recorre la
     * lista mientras no sea igual a la cabeza. Si durante el recorrido, encuentra un Pokémon cuyo ID coincida con el
     * ingresado, se retorna. Si después de recorrer no es encontrado, se retorna null.
     * @param posicion_id (la ID del Pokémon buscado).
     * @return El Pokémon si se encuentra en la lista, null si no existe o la lista está vacía.
     */
    public Pokemon obtenerPokemonPorID(int posicion_id){

        if (this.head == null){
            //Cabeza nula = null.
            return null;
        }

        if (this.head.getPokemon().getId() == posicion_id){
            //Si el ID del Pokémon de la cabeza coincide con el ingresado, se retorna el Pokémon.
            return this.head.getPokemon();
        }

        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();

        while (nodoAuxiliar != this.head){
            //Si el ID del Pokémon en el nodo auxiliar coincide con el ingresado, se retorna.
            if (nodoAuxiliar.getPokemon().getId() == posicion_id){
                return nodoAuxiliar.getPokemon();
            }
            //Iteración.
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        //No se encuentra = return null.
        return null;
    }

    /**
     * Lista_Vacia():
     * Método que retorna un booleano, que indica si la lista está vacía o no.
     * @return "true" si la lista está vacía (cabeza == null), "false" si no está vacía.
     */
    public boolean Lista_Vacia(){
        if(this.head == null){
            return true;
        }
        return false;
    }

    /**
     * obtenerPokemonPosicion(int posicion):
     * Método que permite obtener un Pokémon a partir de una posición.
     * Acá, se supone que la cabeza ocupa el lugar número 1. Como parámetro se tiene
     * la posición indicada. Si la cabeza es nula, no hay nada (retorna null). Se tiene un
     * contador iniciado en 0. Como la cabeza no es nula, se incrementa en 1. Si la posición
     * requerida es 1, se devuelve el Pokémon de la cabeza. Luego, mediante el nodo auxiliar
     * se recorre la lista (hasta que sea la cabeza), y al avanzar incrementará el contador por cada nodo.
     * Cuando el contador coincida con la posición ingresada, se retorna el Pokémon de esa posición.
     * Si al recorrer la lista no se encuentra la posición indicada, se retorna null.
     * @param posicion (la posición indicada para retornar un Pokémon).
     * @return el Pokémon de una posición indicada si se encuentra, null si la lista está vacía o
     * la posición ingresada es más grande que el tamaño.
     */
    public Pokemon obtenerPokemonPosicion(int posicion){
        int contador = 0;
        if (this.head == null){
            return null;
        }

        contador++;
        if (posicion == 1){
            //Si la posición indicada es 1, se retorna el Pokémon de la cabeza.
            return this.head.getPokemon();
        }
        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();
        while (nodoAuxiliar != this.head){
            contador++;
            if (posicion == contador){
                //Si la posición coincide con el contador, se retorna el Pokémon del
                //nodo actual del auxiliar.
                return nodoAuxiliar.getPokemon();
            }
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        //Se retorna null si la posición está fuera del límite (tamaño).
        return null;
    }

    /**
     * tamanioDeLaLista():
     * Método que permite conocer el tamaño de la lista (cuántos nodos hay).
     * Si la lista está vacía, se retorna 0 directamente.
     * Si no está vacía, el contador se incrementa a 1, y se recorre la lista mediante
     * un nodo auxiliar (inicializado en el siguiente de la cabeza), contando por cada nodo
     * hasta que el auxiliar sea la cabeza. Se retorna el contador.
     * @return el contador que indica cuantos nodos con Pokémon hay en la lista.
     */
    public int tamanioDeLaLista(){
        int contador = 0;

        if (this.head == null){
            //Si la cabeza es nula, se retorna el contador (0).
            return contador;
        }

        //Como la cabeza no es nula, aumenta a 1.
        contador++;

        //Y como se analizó la cabeza, el nodo auxiliar comienza en su siguiente.
        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();

        while (nodoAuxiliar != this.head){
            //Si no es la cabeza, se aumenta en 1 el contador e itera al siguiente.
            contador++;
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        //Se retorna el contador.
        return contador;
    }

    /**
     * ordenar_ID_Creciente():
     * Método que retorna un booleano, indicando si se puede ordenar o no.
     * Este método permite ordenar los Pokémon en cada nodo, utilizando una metodología
     * similar a la de un ordenamiento simple en un arreglo. El método retorna false
     * si la lista está vacía, o si hay un solo elemento (la cabeza apunta a la cabeza).
     * En cambio, retorna true si se pudo ordenar.
     * Para ordenar, se tienen 2 nodos auxiliares: el "anterior", y el "siguiente", iniciados
     * en la cabeza, y el siguiente de la cabeza, respectivamente. Se tienen 2 ciclos while,
     * uno dentro de otro, en los que los auxiliares iterarán para poder ordenar la lista.
     * El auxiliar siguiente iterará hasta que alcance la cabeza, y se comparará el valor del
     * ID con el del auxiliar anterior. Cuando el segundo auxiliar alcance la cabeza, el primero
     * iterará uno más. Y así, hasta que el primer auxiliar (anterior) llegue hasta la cola.
     * Este método es similar al de ordenamiento simple con arreglos. El "anterior" (denotado
     * como i normalmente), se compara con el "siguiente" (denotado comúnmente como j).
     * Si el ID del nodo "anterior" es mayor al del siguiente, se guarda el Pokemon del anterior,
     * se reemplaza el Pokémon del siguiente en el anterior, y el siguiente contendrá el Pokémon
     * guardado previamente.
     * @return true si se pudo ordenar la lista, false si no es así.
     */
    public boolean ordenar_ID_Creciente(){
        if (this.head == null){
            //Si la lista está vacía, se retorna false.
            return false;
        }

        if (this.head.getSiguiente().equals(this.head)){
            //Si la lista solo tiene un nodo, se retorna false.
            return false;
        }

        //Preparación de los nodos auxiliares.
        NodoDoblePokemon nodoAuxiliarAnterior = this.head;
        NodoDoblePokemon nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();

        while (nodoAuxiliarAnterior != this.tails){
            while (nodoAuxiliarSiguiente != this.head){
                if (nodoAuxiliarAnterior.getPokemon().getId() > nodoAuxiliarSiguiente.getPokemon().getId()){
                    //Si el Pokémon del nodo anterior tiene mayor ID que el del nodo siguiente, se intercambian
                    //sus Pokémon.
                    Pokemon pokemonAuxiliar = nodoAuxiliarAnterior.getPokemon();
                    nodoAuxiliarAnterior.setPokemon(nodoAuxiliarSiguiente.getPokemon());
                    nodoAuxiliarSiguiente.setPokemon(pokemonAuxiliar);
                }
                //Iteración del siguiente.
                nodoAuxiliarSiguiente = nodoAuxiliarSiguiente.getSiguiente();
            }
            //Iteración de ambos al mismo tiempo.
            nodoAuxiliarAnterior = nodoAuxiliarAnterior.getSiguiente();
            nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();
        }
        return true;
    }

    /**
     * ordenar_alfabetico():
     * Método que permite ordenar alfabéticamente los Pokémon en la lista.
     * Al igual que el método de ordenar por ID crecientemente, se usa la misma estrategia
     * para ordenar a partir de los nodos. Se retorna false si la lista está vacía o solo tiene
     * un nodo. Esta vez, se utiliza el método compareTo para comparar los nombres de los Pokémon.
     * Si el nombre del Pokémon en el nodo auxiliar "anterior", comparado con el del nodo "siguiente",
     * retorna un valor mayor a 0 (o sea, que alfabéticamente posee letras "mayores"), se realiza el proceso
     * de ordenamiento, igualmente como el método de ordenar por ID crecientemente: reemplazando los Pokémon
     * entre el anterior y el siguiente, iterando hasta recorrer todo (como ordenamiento simple de arreglos).
     * Idealmente, en la cabeza estaría el Pokémon con las letras iniciales más cercanas a "A", y en la cola, a "Z".
     * @return true si la lista se pudo ordenar, false si la lista está vacía o solo posee un nodo.
     */
    public boolean ordenar_alfabetico(){
        if (this.head == null){
            //Lista vacía = false
            return false;
        }

        if (this.head.getSiguiente().equals(this.head)){
            //Solo un nodo = false
            return false;
        }

        //Nodos auxiliares.
        NodoDoblePokemon nodoAuxiliarAnterior = this.head;
        NodoDoblePokemon nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();

        while (nodoAuxiliarAnterior != this.tails){
            while (nodoAuxiliarSiguiente != this.head){

                if (nodoAuxiliarAnterior.getPokemon().getNombre().compareTo(nodoAuxiliarSiguiente.getPokemon().getNombre()) > 0){
                    //Si el Pokémon del nodo anterior tiene un nombre "mayor" al del nodo siguiente, se reemplaza como
                    //en los métodos de ordenamiento.
                    Pokemon pokemonAuxiliar = nodoAuxiliarAnterior.getPokemon();
                    nodoAuxiliarAnterior.setPokemon(nodoAuxiliarSiguiente.getPokemon());
                    nodoAuxiliarSiguiente.setPokemon(pokemonAuxiliar);
                }
                nodoAuxiliarSiguiente = nodoAuxiliarSiguiente.getSiguiente();
            }
            nodoAuxiliarAnterior = nodoAuxiliarAnterior.getSiguiente();
            nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();
        }
        return true;
    }

    /**
     * ordenar_ID_Decreciente():
     * Permite ordenar la lista de nodos, con su ID de manera decreciente.
     * Retorna un booleano como todos los métodos de ordenamiento: false si está vacía la lista
     * o solo tiene un nodo, true si se pudo ordenar.
     * Se realiza exactamente el mismo procedimiento de orden que en el método de ordenar por
     * ID creciente, pero esta vez es al revés: si el ID del nodo anterior, es menor al del nodo
     * siguiente, se reemplazan y se intercambian los Pokémon.
     * El mejor caso, sería tener en la cabeza a Mew (ID 151), y en la cola estaría Bulbasaur (ID 1).
     * @return true si se pudo ordenar, false si está vacía la lista, o solo tiene un nodo.
     */
    public boolean ordenar_ID_Decreciente(){
        if (this.head == null){
            //Cabeza nula, se retorna false.
            return false;
        }
        if (this.head.getSiguiente().equals(this.head)){
            //Solo un nodo, retorna false.
            return false;
        }

        //Nodos auxiliares: anterior y siguiente.
        NodoDoblePokemon nodoAuxiliarAnterior = this.head;
        NodoDoblePokemon nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();

        while (nodoAuxiliarAnterior != this.tails){
            while (nodoAuxiliarSiguiente != this.head){
                if (nodoAuxiliarAnterior.getPokemon().getId() < nodoAuxiliarSiguiente.getPokemon().getId()){
                    //Lo mismo que en el método de ordenar crecientemente por ID, pero es lo contrario:
                    //se intercambian si el Pokémon del nodo anterior posee un ID menor que el del siguiente.
                    Pokemon pokemonAuxiliar = nodoAuxiliarAnterior.getPokemon();
                    nodoAuxiliarAnterior.setPokemon(nodoAuxiliarSiguiente.getPokemon());
                    nodoAuxiliarSiguiente.setPokemon(pokemonAuxiliar);
                }
                nodoAuxiliarSiguiente = nodoAuxiliarSiguiente.getSiguiente();
            }
            nodoAuxiliarAnterior = nodoAuxiliarAnterior.getSiguiente();
            nodoAuxiliarSiguiente = nodoAuxiliarAnterior.getSiguiente();
        }
        return true;
    }

    /**
     * limiteInferior():
     * Método que encuentra el menor ID posible dentro de la lista. Se usa en el requerimiento
     * de desplegar Pokémon en un rango, para encontrar el menor ID desplegable. La variable de
     * ID menor se inicia en 999999999, y se actualiza cada vez que se encuentra un menor ID.
     * Si no hay nada en la lista, se retorna directamente. Luego, al analizar la cabeza se actualiza
     * el valor al ID del Pokémon en la cabeza. Recorriendo la lista con un nodo auxiliar iniciado en
     * el siguiente de la cabeza (hasta que sea igual que la cabeza), la variable se actualizará y
     * se retornará al terminar de recorrer. Idealmente, el menor ID posible sería 1 (perteneciente al Pokémon Bulbasaur).
     * @return el ID menor de toda la lista de Pokémon.
     */
    public int limiteInferior(){
        int id_menor = 999999999;

        //Se retorna el valor directamente si no hay nada en la lista.
        if (this.head == null){
            return id_menor;
        }

        //La cabeza tendrá un valor menor, y la variable se actualizará.
        if (this.head.getPokemon().getId() < id_menor){
            id_menor = this.head.getPokemon().getId();
        }

        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();

        //Se recorre la lista buscando el menor ID.
        while (nodoAuxiliar != this.head){
            if (nodoAuxiliar.getPokemon().getId() < id_menor){
                //Si el Pokémon del nodo auxiliar posee un menor ID que el valor de la variable,
                //se actualiza de nuevo.
                id_menor = nodoAuxiliar.getPokemon().getId();
            }
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        //Se retorna el menor valor de ID posible en la lista.
        return id_menor;
    }

    /**
     * limite_superior():
     * Este método devuelve, el ID mayor de la lista. Es utilizado para
     * el requerimiento de desplegar Pokémon en un rango (el Pokémon con mayor ID desplegable), y también para el caso
     * en que haya un solo Pokémon en la lista, desplegarlo directamente.
     * La variable de ID mayor se inicia en -1. Si está vacía la lista, se retorna así. Si el Pokémon de la
     * cabeza posee un ID mayor a -1 (cosa que sucederá sí o sí), se actualiza el
     * valor. Y usando el nodo auxiliar inicializado en el siguiente de la cabeza,
     * recorre la lista, actualizando el valor del ID mayor cada vez que encuentre un ID más grande.
     * Cuando se termina de recorrer, se retorna. El máximo valor del ID, es de 151 (perteneciente al Pokémon Mew).
     * @return el ID más grande encontrado en la lista.
     */
    public int limite_superior(){
        int id_mayor = -1;

        //Retorna -1 si la lista está vacía.
        if (this.head == null){
            return id_mayor;
        }

        //El valor de la cabeza será mayor al de la variable.
        if (this.head.getPokemon().getId() > id_mayor){
            id_mayor = this.head.getPokemon().getId();
        }

        NodoDoblePokemon nodoAuxiliar = this.head.getSiguiente();

        //Se recorre la lista buscando el mayor valor de ID.
        while (nodoAuxiliar != this.head){
            if (nodoAuxiliar.getPokemon().getId() > id_mayor){
                //Si el ID del Pokémon del nodo tiene un mayor ID que el de la
                //variable, se actualiza.
                id_mayor = nodoAuxiliar.getPokemon().getId();
            }
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        //Se retorna el mayor ID posible de la lista.
        return id_mayor;
    }
}