import ucn.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PokedexImpl implements Pokedex{

    /**
     * La Pokédex Completa (la lista de nodos dobles circulares, cada nodo contiene un pokémon).
     */
    ListaDeNexosDoblePokemon pokedex_completa = new ListaDeNexosDoblePokemon();

    /**
     * Constructor del Impl (implementación de la interface Pokédex).
     * Primeramente, se lee el archivo kanto.txt, y guarda los datos en el sistema.
     * Luego, se inicia el programa con el método iniciar.
     * @throws IOException (excepción).
     */
    public PokedexImpl() throws IOException {
        //Primero se lee el archivo y luego se inicia el programa.
        this.leerArchivo();
        this.iniciar();
    }


    /**
     * iniciar():
     * Este método contiene el menú principal del programa, y conduce al resto de métodos de cada
     * requerimiento. Contiene su respecto validación en caso de ingresar un dato inválido.
     * Contiene 6 opciones: desplegar un rango de Pokémon con su ID de forma creciente, desplegar
     * todos los Pokémon del sistema en orden alfabético, desplegar Pokémon de un tipo específico,
     * desplegar los Pokémon de primera evolución con su ID de forma decreciente, realizar una búsqueda
     * personalizada de un Pokémon a partir de su nombre o ID, y cerrar el programa.
     */
    @Override
    public void iniciar() {
        String opcion = "";
        while (!opcion.equalsIgnoreCase("6")){
            StdOut.println("******************************************************");
            StdOut.println("¡Bienvenid@ a la Pokédex de Kanto!");
            StdOut.println("Elija una opción: ");
            StdOut.println("[1] Desplegar Pokémon dado un rango (según su ID, crecientemente).");
            StdOut.println("[2] Desplegar todos los Pokémon del sistema (ordenados alfabéticamente).");
            StdOut.println("[3] Desplegar todos los Pokémon de un tipo en específico.");
            StdOut.println("[4] Desplegar las primeras evoluciones, por ID de forma decreciente.");
            StdOut.println("[5] Realizar una búsqueda personalizada (por ID o nombre).");
            StdOut.println("[6] Cerrar el programa.");
            StdOut.println("******************************************************");
            opcion = StdIn.readString();
            switch (opcion){
                case "1" -> desplegarPokemonRango();
                case "2" -> desplegarTodosLosPokemon();
                case "3" -> desplegarPokemonPorTipo();
                case "4" -> desplegarPrimerasEvolucionesDecreciente();
                case "5" -> busquedaPersonalizada();
                case "6" -> cierre();
                default -> StdOut.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    /**
     * desplegarPokemonRango():
     * Uno de los requerimientos del programa. Permite desplegar Pokémon en un rango indicado
     * (con su límite inferior y superior, e incluso permite desplegar un solo Pokémon si se indica
     * como límite superior e inferior la misma ID). Se pide primeramente el ID del Pokémon en el
     * comienzo del intervalo (igual o mayor al Pokémon con el ID menor, obtenido a partir del método
     * de la lista "limiteInferior" y menor o igual al Pokémon con mayor ID (a partir del método
     * "limiteSuperior" de la lista)). Luego, se ingresa el ID del Pokémon en el final del rango, que
     * debe ser mayor o igual al Pokémon del inicio del rango, y a su vez, igual o menor al ID mayor de
     * todos (el límite superior). En ambos casos, se realizan las validaciones para ingresar datos que
     * no salgan de los límites establecidos, e incluso para las opciones inválidas como letras.
     * Incluso, con respecto a las validaciones, no se podrá ejecutar este método si la lista está vacía.
     * Y en el caso que solo exista un Pokémon en la lista (el límite superior e inferior son iguales),
     * se despliega directamente.
     * Luego, la lista se ordena con su ID crecientemente (método de la lista), y se tiene un contador que
     * cuenta cuantos Pokémon existen dentro del rango. Después, se instancia una ArrayList, y mediante un
     * ciclo for, se rellena el arreglo con Pokémon que su ID está dentro del rango (mayor o igual al inferior,
     * menor o igual al superior). Luego, se usa el Iterador para recorrer la ArrayList, y desplegar uno por uno
     * a los Pokémon (mediante el método de despliegue de Pokémon). Si el contador de Pokémon en el rango es 0, se despliega
     * que no hay Pokémon en el rango.
     */
    @Override
    public void desplegarPokemonRango() {
        //Se inicializa el pokémon inferior (el inicio del rango), y superior (final del rango).
        int pokemon_inferior_int;
        int pokemon_superior_int;


        //Si la lista no está vacía, se ejecuta la lógica.
        //Si está vacía, mensaje de error.
        if (!pokedex_completa.Lista_Vacia()){

            //
            int limite_inferior = pokedex_completa.limiteInferior();
            int limite_superior = pokedex_completa.limite_superior();

            //Si el ID menor es diferente al mayor (o sea, hay más de un Pokémon en la lista),
            //se comienza a elegir los límites del rango.
            if (limite_inferior != limite_superior){
                while (true){
                    try {
                        //Primero, se pide el límite inicial para el rango.
                        StdOut.println("Desplegar Pokémon dado un rango.");
                        StdOut.println("Ingrese la ID del primer Pokémon (desde " + limite_inferior + " hasta " + limite_superior + ").");
                        String pokemon_inferior_string = StdIn.readString();
                        pokemon_inferior_int = Integer.parseInt(pokemon_inferior_string);

                        //Mientras la ID ingresada es menor al límite mínimo o mayor al límite máximo, se vuelve a pedir.
                        while (pokemon_inferior_int < limite_inferior || pokemon_inferior_int > limite_superior){
                            StdOut.println("La ID no puede ser menor a " + limite_inferior + ", ni mayor a " + limite_superior + ". Intente de nuevo.");
                            pokemon_inferior_string = StdIn.readString();
                            pokemon_inferior_int = Integer.parseInt(pokemon_inferior_string);
                        }
                        break;
                    }catch (Exception exception){
                        //Captura de excepción, si el usuario no ingresa un número.
                        StdOut.println("ID invállido, intente nuevamente.");
                    }
                }

                while (true){
                    try {
                        //Luego, se elige el límite final del rango.
                        //El final del rango puede ser el mismo Pokémon inicial, o uno con mayor ID.

                        StdOut.println("Ingrese la ID del Pokémon del final del rango: ");
                        if(pokemon_inferior_int < limite_superior){
                            //Si el Pokémon inicial tiene menor ID que el límite mayor.
                            StdOut.println("Entre " + pokemon_inferior_int + " hasta " + limite_superior + ".");
                        }else{
                            //Si el Pokémon inicial tiene ID igual al límite mayor.
                            StdOut.println("Como el límite inferior es " + limite_superior + " (el límite superior), ingrese nuevamente el número de ID para desplegar el Pokémon.");
                        }

                        String pokemon_superior_string = StdIn.readString();
                        pokemon_superior_int = Integer.parseInt(pokemon_superior_string);

                        //Validación.
                        //Si el ID del final es menor al elegido como inicial, o supera al ID mayor de la lista.
                        while (pokemon_superior_int < pokemon_inferior_int || pokemon_superior_int > limite_superior){

                            //En el caso que el elegido como final sea menor al inicial, se despliega su mensaje de error.
                            if (pokemon_superior_int < pokemon_inferior_int){
                                StdOut.println("Error: el Pokémon final del intervalo tiene una ID menor al inicial. Intente de nuevo.");
                                StdOut.println("Ingrese la ID del Pokémon del final del rango: ");

                                if (pokemon_inferior_int == limite_superior && pokemon_superior_int != limite_superior){
                                    //Y si a su vez, el inicial es igual al límite superior, también se informa de esto (que se debe elegir el final como el límite superior).
                                    StdOut.println("Si eligió como Pokémon inicial al límite superior, debe volver a ingresarlo para poder desplegarlo.");
                                    StdOut.println("Pokémon inicial: " + pokemon_inferior_int);
                                    StdOut.println("Pokémon del límite superior: " + limite_superior);
                                }else{
                                    StdOut.println("Entre " + (pokemon_inferior_int) + " hasta " + limite_superior);
                                }

                                pokemon_superior_string = StdIn.readString();
                                pokemon_superior_int = Integer.parseInt(pokemon_superior_string);

                            }else if (pokemon_superior_int > limite_superior){
                                //Si la ID del final supera el límite superior.
                                StdOut.println("Error: El valor máximo es " + limite_superior);
                                StdOut.println("Ingrese la ID del Pokémon del final del rango: ");
                                if (pokemon_inferior_int == limite_superior && pokemon_superior_int != limite_superior){
                                    //Si la ID del inicio es igual al límite superior.
                                    StdOut.println("Si eligió como Pokémon inicial al límite superior, debe volver a ingresarlo para poder desplegarlo.");
                                    StdOut.println("Pokémon inicial: " + pokemon_inferior_int);
                                    StdOut.println("Pokémon del límite superior: " + limite_superior);
                                }else{
                                    StdOut.println("Entre " + (pokemon_inferior_int) + " hasta " + limite_superior);
                                }
                                pokemon_superior_string = StdIn.readString();
                                pokemon_superior_int = Integer.parseInt(pokemon_superior_string);
                            }
                        }
                        break;
                    }catch (Exception exception){
                        //Captura de la excepción.
                        StdOut.println("Error: opción inválida, intente de nuevo.");
                    }
                }

                int pokemon_dentro_del_rango = 0;
                boolean ordenado = pokedex_completa.ordenar_ID_Creciente();
                //Si se puede ordenar, se ejecuta su lógica.

                if (ordenado){
                    //INSTANCIA DE ARRAYLIST:
                    //El arreglo, mediante un ciclo for se rellena con el método de obtener Pokémon,
                    //por posición en la lista de nodos. Se agrega si su ID está dentro del rango.
                    //(Mayor o igual al Pokémon inicial, menor o igual al Pokémon final).

                    ArrayList<Pokemon> arreglo_de_pokemon= new ArrayList<>();
                    for (int i = 0; i < pokedex_completa.tamanioDeLaLista(); i++){
                        Pokemon pokemon = pokedex_completa.obtenerPokemonPosicion(i+1);
                        if (pokemon.getId() >= pokemon_inferior_int && pokemon.getId() <= pokemon_superior_int){
                            arreglo_de_pokemon.add(pokemon);
                            pokemon_dentro_del_rango++;
                        }
                    }

                    //Se instancia el Iterador, que permite recorrer la ArrayList.
                    //Se despliega cada uno de los Pokémon almacenados en el arreglo, y se
                    //aumenta el contador de Pokémon en el rango.

                    Iterator<Pokemon> pokemon_iterador = arreglo_de_pokemon.iterator();
                    while (pokemon_iterador.hasNext()){
                        Pokemon pokemon = pokemon_iterador.next();
                        if (pokemon != null){
                            desplegarDatosPokemon(pokemon);
                        }
                    }
                    //Si el contador continúa en 0, se imprime que no hay ningún Pokémon en el rango.
                    if (pokemon_dentro_del_rango == 0){
                        StdOut.println("No se encontraron Pokémon dentro del rango.");
                    }
                }else{
                    //Cuando no se puede ordenar (lista vacía o 1 solo Pokémon).
                    StdOut.println("Error: la lista no se pudo ordenar de manera creciente.");
                }
            }else{
                //Si, en cambio, el ID menor y mayor son iguales (o sea, solo hay un Pokémon),
                //se despliega automáticamente.
                StdOut.println("Error: Hay solamente un Pokémon en el sistema: ");
                desplegarDatosPokemon(pokedex_completa.obtenerPokemonPorID(limite_inferior));
            }
        }else{
            //Mensaje de error.
            StdOut.println("Error: La lista está vacía.");
        }
    }


    /**
     * desplegarTodosLosPokemon():
     * Requerimiento del programa. Mediante un método de la lista, se ordenan los Pokémon en los nodos
     * de manera alfabética (retorna un booleano). Si es true, mediante un ciclo for, se obtienen los Pokémon
     * por posición a partir de la lista de nodos, y se despliegan mediante el método de despliegue. Como se
     * ordenaron alfabéticamente, deberían desplegar así.
     * Si se retorna un false, es porque la lista está vacía, o solo se posee un Pokémon (que igualmente se despliega).
     */
    @Override
    public void desplegarTodosLosPokemon() {
        StdOut.println("Desplegar todos los Pokémon en orden alfabético: ");
        boolean ordenado = pokedex_completa.ordenar_alfabetico();
        if (ordenado){
            //Se ordenan los Pokémon en los nodos, de manera alfabética, y se rellenan
            //con el ciclo for y método de obtención de Pokémon por posición.
            //Se despliegan sus datos si el Pokémon no es nulo (la lista está ordenada
            //por orden alfabético).

            for (int i = 0; i < pokedex_completa.tamanioDeLaLista(); i++){
                Pokemon pokemon = pokedex_completa.obtenerPokemonPosicion(i+1);
                if(pokemon != null){
                    desplegarDatosPokemon(pokemon);
                }
            }
        }else{
            StdOut.println("Error: no se pudo ordenar la lista (está vacía o solo hay un Pokémon.)");
        }

        //Si no se pudo ordenar la lista, es porque la lista está vacía o hay un solo Pokémon
        if (!ordenado && pokedex_completa.tamanioDeLaLista() == 1){

            //Si solamente hay un Pokémon, se despliega de forma directa.
            StdOut.println("Solo hay un Pokémon en la lista: ");
            Pokemon unico_pokemon = pokedex_completa.obtenerPokemonPorID(pokedex_completa.limite_superior());
            desplegarDatosPokemon(unico_pokemon);

        } else if (!ordenado && pokedex_completa.Lista_Vacia()) {

            //Si la lista está vacía, mensaje de error.
            StdOut.println("La lista está vacía.");

        }
    }

    /**
     * desplegarPokemonPorTipo():
     * Contrato requerido. Permite al usuario ingresar un tipo de Pokémon, y se despliegan los Pokémon
     * que lo posean (como tipo principal o secundario). Se tiene un arreglo de Strings, que posee los 15
     * tipos de Pokémon que existían en la primera generación. Cuando el usuario ingresa el tipo, se busca
     * en este arreglo, para comprobar si existe. Si no existe, se vuelve a pedir el tipo. En caso contrario,
     * se realiza el procedimiento de búsqueda.
     * <p>
     * Se instancia una LinkedList, y se rellena con un ciclo for, con el método de la lista de nodos que permite
     * obtener Pokémon a partir de las posiciones (se rellena primeramente con todos los Pokémon de la lista).
     * <p>
     * Luego, con la clase Iterador se recorre la LinkedList, y si al menos uno de los 2 tipos del Pokémon coincide
     * con el buscado, se despliega. Así hasta que se termine de recorrer. También se tiene un boolean, que permite
     * identificar si se desplegó al menos 1 Pokémon de ese tipo. Si no, se imprime que no hay Pokémon del tipo buscado.
     */
    @Override
    public void desplegarPokemonPorTipo() {
        //Se tiene un arreglo con todos los 15 tipos Pokémon de la primera generación.
        String[] tiposPokemon = {"Agua", "Fuego", "Hierba", "Normal", "Volador", "Electrico", "Insecto", "Veneno", "Psiquico", "Hielo", "Tierra", "Roca", "Dragon", "Fantasma", "Lucha"};
        String tipo;

        //Si la lista no está vacía, se ejecuta la lógica.
        if (!pokedex_completa.Lista_Vacia()) {
            while (true) {
                StdOut.println("Desplegar Pokémon de un tipo en específico.");
                StdOut.println("Ingrese el tipo que desee:");
                StdOut.println("Tipos disponibles: Agua, Fuego, Hierba, Normal, Volador, Eléctrico, Insecto, Veneno, Psíquico, Hielo, Tierra, Roca, Dragón, Fantasma, Lucha.");
                tipo = StdIn.readString();
                boolean tipoExiste = false;

                //Adicional: si el usuario ingresa el tipo Dragón, Eléctrico o Psíquico con tilde,
                //se reemplaza para que se pueda realizar la búsqueda dentro del arreglo.

                if (tipo.equalsIgnoreCase("Dragón")){
                    String tipo_con_tilde = tipo;
                    tipo = tipo_con_tilde.replace("ó","o");
                }else if (tipo.equalsIgnoreCase("Eléctrico")){
                    String tipo_con_tilde = tipo;
                    tipo = tipo_con_tilde.replace("é","e");
                }else if (tipo.equalsIgnoreCase("Psíquico")){
                    String tipo_con_tilde = tipo;
                    tipo = tipo_con_tilde.replace("í","i");
                }

                //Se recorre el arreglo de tipos, comprobando si existe.
                for (int i = 0; i < tiposPokemon.length; i++) {
                    if (tipo.equalsIgnoreCase(tiposPokemon[i])) {
                        tipoExiste = true;
                        break;
                    }
                }

                //Si no existe, mensaje de error.
                if (!tipoExiste) {
                    StdOut.println("El tipo buscado no existe. Intente de nuevo.");
                } else {
                    //Si existe, se rompe el ciclo y se realiza la búsqueda de Pokémon de ese tipo.
                    break;
                }
            }

            //INSTANCIA DE LINKEDLIST:
            //Nuevamente, se rellena mediante ciclo for y obtención de Pokémon en los nodos.

            LinkedList<Pokemon> lista_pokemon = new LinkedList<>();
            for (int i = 0; i < pokedex_completa.tamanioDeLaLista(); i++){
                lista_pokemon.add(i,pokedex_completa.obtenerPokemonPosicion(i+1));
            }

            //Se define un booleano, comprobando si existe al menos 1 Pokémon que tenga ese tipo.
            boolean pokemon_del_tipo_encontrado = false;

            //Iterador: recorre la LinkedList.
            Iterator <Pokemon> iterator = lista_pokemon.iterator();
            while (iterator.hasNext()){
                Pokemon pokemon_iterador = iterator.next();
                if (pokemon_iterador != null) {
                    //Si el tipo primario o secundario del Pokémon coincide con el tipo buscado, se despliega.
                    if (pokemon_iterador.getTipo1().equalsIgnoreCase(tipo) || pokemon_iterador.getTipo2().equalsIgnoreCase(tipo)) {
                        desplegarDatosPokemon(pokemon_iterador);
                        pokemon_del_tipo_encontrado = true;
                        //Booleano = true;
                    }
                }
            }

            //Si ningún Pokémon en la lista posee el tipo buscado (que existe),
            //se despliega que no hay Pokémon en la lista con ese tipo.
            if (!pokemon_del_tipo_encontrado){
                StdOut.println("No se encontró ningún Pokémon con el tipo buscado.");
            }
        }else{
            StdOut.println("Error: La lista está vacía.");
        }
    }

    /**
     * desplegarPrimerasEvolucionesDecreciente():
     * Requerimiento del problema. Acá, los Pokémon que sean de primera evolución, se despliegan con su ID de manera decreciente.
     * Se tiene un contador de los Pokémon desplegados, y la lista se ordena de manera que el ID esté de manera descendiente.
     * Si el booleano del método de ordenamiento es true, se recorre la lista de nodos con el ciclo for y el método de obtener Pokémon
     * por posición. Si el Pokémon obtenido es una instancia de Primera_Evolucion, se despliega y aumenta el contador de Pokémon desplegados.
     * Si el contador continúa en 0 y la lista se había ordenado, significa que en la lista no hay Pokémon de primera evolución.
     * En el caso que el boolean del método de ordenamiento sea false, se revisa si la ilsta está vacía o posee solo un Pokémon.
     * Si está vacía, se imprime mensaje de error. Pero si hay únicamente un Pokémon en la lista, se comprueba si es una instancia de Primera_Evolucion.
     * Si es así, se imprime su información, y en el caso contrario se informa que el único Pokémon que hay en la lista no es de primera evolución.
     */
    @Override
    public void desplegarPrimerasEvolucionesDecreciente() {
        StdOut.println("Desplegar las primeras evoluciones, con su ID de forma decreciente.");
        int contador_primeras_evo = 0;
        //Contador de primeras evoluciones en la lista.

        //Se ordena con los ID descendientemente.
        boolean ordenado = pokedex_completa.ordenar_ID_Decreciente();


        if (ordenado){
            for (int i = 0; i < pokedex_completa.tamanioDeLaLista(); i++){
                //Recorrido de la lista de nodos, se van obteniendo cada Pokémon
                //en la lista.
                Pokemon pokemon = pokedex_completa.obtenerPokemonPosicion(i+1);
                if (pokemon != null){
                    if (pokemon instanceof Primera_Evolucion){
                        //Si el Pokémon obtenido es instancia de Primera_Evolucion (es de primera evolución),
                        //se despliega con el método de despliegue.
                        desplegarDatosPokemon(pokedex_completa.obtenerPokemonPosicion(i+1));
                        contador_primeras_evo++;
                        //Incremento del contador.
                    }
                }
            }
        }else{
            StdOut.println("Error. No se pudo ordenar (la lista está vacía o solo hay un Pokémon)");
        }

        //Si la lista se pudo ordenar (más de 1 Pokémon), y el contador de primeras evoluciones
        //continúa en 0, significa que no hay Pokémon de primera evolución en la lista.
        if (ordenado && contador_primeras_evo == 0){
            StdOut.println("No se ha encontrado ningún Pokémon de primera evolución.");
        }

        //Si la lista no se pudo ordenar, puede ser porque la lista está vacía, o solo hay un Pokémon.
        //En el caso que haya un único Pokémon, se comprueba si es de primera evolución o no. En el primer
        //caso, se despliega. En el segundo, se imprime que el único Pokémon de la lista no es de primera evolución.

        if (!ordenado && pokedex_completa.Lista_Vacia()){
            StdOut.println("La lista está vacía.");
        }else if (!ordenado && pokedex_completa.tamanioDeLaLista() == 1){
            //Si solo hay un Pokémon en la lista.
            Pokemon unico_pokemon = pokedex_completa.obtenerPokemonPorID(pokedex_completa.limite_superior());
            if (unico_pokemon instanceof Primera_Evolucion){
                //Si es de primera evolución.
                StdOut.println("El único Pokémon de la lista es de primera evolución.");
                desplegarDatosPokemon(unico_pokemon);
            }else{
                //Si no es de primera evolución.
                StdOut.println("El único Pokémon de la lista no es de primera evolución.");
            }
        }
    }

    /**
     * busquedaPersonalizada():
     * Este requerimiento deja que el usuario pueda buscar un Pokémon mediante su nombre o ID.
     * En un sub-menú, se puede elegir entre buscar por nombre, ID o retroceder al menú principal.
     * Se valida también que el usuario ingrese una opción válida.
     * <p>
     * Si se elige 1 (buscar por nombre), se ingresa el nombre del Pokémon y la lista con nodos realiza la búsqueda mediante el método de buscar por nombre.
     * Si el Pokémon retornado es nulo, se despliega mensaje de error diciendo que el Pokémon no existe. En caso contrario, se ejecuta el método de desplegar
     * datos de la Cadena Evolutiva: se imprime la información del Pokémon buscado, en primer lugar, y luego, los Pokémon que también pertenezcan a su cadena
     * evolutiva (pre-evoluciones / evoluciones) (se aclarará como funciona el método, en la documentación de este).
     * <p>
     * Si se elige 2 (buscar por ID), se ingresa el ID del Pokémon (validando que sea dentro del rango de 1 a 151, y que no sean caracteres no-numéricos),
     * la lista con nodos busca el Pokémon con el ID, y lo retorna. Si es nulo, mensaje de error, y si no es nulo, se llama al método de la cadena evolutiva
     * mencionada anteriormente.
     */
    @Override
    public void busquedaPersonalizada() {
        String opcion = "";
        StdOut.println("Búsqueda personalizada:");

        while (!opcion.equalsIgnoreCase("3")){
            //Menú de búsqueda personalizada.
            StdOut.println("Ingrese una opción, para realizar la búsqueda de un Pokémon: ");
            StdOut.println("[1] Buscar por nombre");
            StdOut.println("[2] Buscar por ID");
            StdOut.println("[3] Salir al menú principal.");
            opcion = StdIn.readString();

            if (opcion.equalsIgnoreCase("1")) {
                //Búsqueda por nombre.
                StdOut.println("Buscar por nombre de Pokémon: ");
                StdOut.println("Ingrese el nombre del Pokémon a buscar (se desplegará la información de su cadena evolutiva): ");
                String nombrePokemon = StdIn.readString();

                //Se busca el Pokémon en la lista de nodos, con el nombre.
                //Nulo = Error. No nulo = Despliegue.
                Pokemon pokemon_buscado = pokedex_completa.obtenerPokemon_Nombre(nombrePokemon);
                if (pokemon_buscado != null){
                    desplegarDatosCadenaEvolutiva(pokemon_buscado);
                }else{
                    StdOut.println("El Pokémon buscado no existe, intente de nuevo.");
                }

            }else if (opcion.equalsIgnoreCase("2")){
                //Búsqueda por ID.
                int id_a_buscar;
                String id_string;

                while (true){
                    //El ciclo se repite hasta que se ingrese un ID válido (entre 1 y 151, que no sea una letra).
                    try {
                        StdOut.println("Buscar por la ID del Pokémon: ");
                        StdOut.println("Ingrese la ID del Pokémon a buscar (se desplegará información de su cadena evolutiva): ");
                        id_string = StdIn.readString();
                        id_a_buscar = Integer.parseInt(id_string);

                        if (id_a_buscar < 1 || id_a_buscar > 151){
                            //Validación (1-151).
                            StdOut.println("La ID de Pokémon no puede ser menor a 1 ni mayor a 151. Intente de nuevo.");

                        }else{
                            break;
                        }
                    }catch (Exception exception){
                        StdOut.println("ID de Pokémon inválida, intente de nuevo.");
                    }
                }

                //Búsqueda del Pokémon a partir del ID. Se retorna y si no es nulo, se despliega.
                Pokemon pokemon = pokedex_completa.obtenerPokemonPorID(id_a_buscar);
                if (pokemon != null){
                    desplegarDatosCadenaEvolutiva(pokemon);
                }else{
                    StdOut.println("El Pokémon buscado no está en el sistema. Intente de nuevo.");
                }
            }
            //Validación del menú de búsqueda personalizada.
            if (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
                StdOut.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    /**
     * cierre():
     * Solamente despliega un mensaje de despedida, y permite que no se imprima
     * el mensaje de opción inválida en el menú principal.
     */
    @Override
    public void cierre() {
        //Mensaje de despedida.
        StdOut.println("¡Hasta luego!");
    }

    /**
     * leerArchivo():
     * La lectura del archivo (kanto.txt), que permite el registro de datos en el programa.
     * Primeramente, se define el archivo de entrada (kanto.txt). La estructura de los campos
     * varía dependiendo de qué etapa evolutiva sea el Pokémon, pero se tienen varios casos, y
     * es seguro que los primeros 3 campos son: el ID, el nombre y la etapa evolutiva. A partir de la etapa
     * evolutiva, se pueden tomar diferentes casos. Se borran también los espacios en el campo, con el método .trim().
     * <p>
     * 1) Pokémon básico.
     * Caso 1: Sin primera ni segunda evolución (el campo 6, 7 y 8 son nulos, el 4 y 5 son los tipos). Máximo de campos: 5.
     * Caso 2: Con primera, sin segunda evolución (el campo 7 y 8 son nulos, el 5 y 6 son los tipos y el 4 es la primera evolución). Máximo de campos: 6.
     * Caso 3: Con primera y segunda evolución (el campo 8 es nulo, el 6 y 7 son los tipos, el 4 es la primera evolución, el 5 es la segunda). Máximo de campos: 7.
     * Caso 4: El Pokémon es Eevee (ningún campo nulo, 7 y 8 son los tipos, desde el 4 al 6 son Vaporeon, Flareon y Jolteon). Máximo de campos: 8.
     * <p>
     * En cada caso, se construye el Pokémon con cada campo correspondiente. Para Eevee (único Pokémon de los primeros 151 que posee
     * más de una primera evolución), se designa al azar cuál será el Pokémon que estará en su atributo de "primera evolución".
     * <p>
     * 2) Pokémon de primera evolución.
     * Caso 1: Sin segunda evolución (campo 7 y 8 nulos, el campo 5 y 6 son los tipos, y el campo 4 es la pre-evolución).
     * Caso 2: Con segunda evolución (campo 8 nulo, el campo 6 y 7 son los tipos, el campo 4 es la evolución siguiente, el 5 es la pre-evolución).
     * <p>
     * 3) Pokémon de segunda evolución.
     * Único caso: (campo 8 nulo, campo 6 y 7 son los tipos, el campo 4 es la pre-evolución, el campo 5 es la etapa básica).
     * <p>
     * Si el Pokémon no es nulo, se agrega a la lista. Se tiene un try-catch para capturar las excepciones al intentar transformar el ID
     * de una línea vacía, y se valida si el Pokémon tiene un ID menor a 1 o mayor a 151.
     *
     * @throws IOException (excepción).
     */
    @Override
    public void leerArchivo() throws IOException {
        try {
            //Se genera el archivo de entrada ("kanto.txt").
            ArchivoEntrada Kanto = new ArchivoEntrada("kanto.txt");
            while (!Kanto.isEndFile()){
                try {
                    Registro registro = Kanto.getRegistro();
                    String id_string = registro.getString();
                    String nombre_Pokemon = registro.getString();
                    String etapa_evolutiva = registro.getString();
                    String campo4 = registro.getString();
                    String campo5 = registro.getString();
                    String campo6 = registro.getString();
                    String campo7 = registro.getString();
                    String campo8 = registro.getString();

                    //Los primeros 3 campos sí o sí son el ID, nombre y etapa.
                    //Los otros 5 campos varían dependiendo de la etapa, y cantidad de evoluciones/pre-evoluciones.

                    //Se eliminan los espacios del campo del ID, nombre y etapa.
                    //El String del ID se convierte a int. En caso de que el campo esté nulo,
                    //lanzará una excepción que será capturada.

                    id_string = id_string.trim();
                    int id = Integer.parseInt(id_string);
                    nombre_Pokemon = nombre_Pokemon.trim();
                    etapa_evolutiva = etapa_evolutiva.trim();


                    Pokemon pokemon = null;
                    //Se valida que el ID sea mayor o igual a 1, y menor o igual a 151.
                    if (id < 1 || id > 151){
                        throw new Exception();
                    }


                    if (etapa_evolutiva.equalsIgnoreCase("Basico")){
                        //Si la etapa evolutiva del Pokémon es básico.
                        if (campo6 == null && campo7 == null && campo8 == null){
                            //El campo 4 y el campo 5 corresponden a los tipos.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo4,campo5,"","");
                        }else if (campo6 != null && campo7 == null && campo8 == null){
                            //El campo 4 es la primera evolución, el campo 5 y 6 son los tipos.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo5,campo6,campo4,"");
                        }else if (campo6 != null && campo7 != null && campo8 == null){
                            //El campo 6 y 7 son los tipos, pero el campo 4 y 5 son la primera y segunda evolución.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            campo7 = campo7.trim();
                            pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo6,campo7,campo4,campo5);
                        }else if (campo6 != null && campo7 != null && campo8 != null && nombre_Pokemon.equalsIgnoreCase("Eevee")){
                            //Ningún campo es nulo. El 7 y 8 son los tipos, y desde el campo 4 hasta el 6 pertenecen
                            //a Vaporeon, Jolteon o Flareon. Este el caso especial para Eevee.

                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            campo7 = campo7.trim();
                            campo8 = campo8.trim();

                            int vap_jolt_fla = (int) (Math.random() * 3);
                            if (vap_jolt_fla == 1){
                                pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo7,campo8,campo4,"");
                            }else if (vap_jolt_fla == 2){
                                pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo7,campo8,campo5,"");
                            }else if (vap_jolt_fla == 3 || vap_jolt_fla == 0){
                                pokemon = new Basico(id,nombre_Pokemon,etapa_evolutiva,campo7,campo8,campo6,"");
                            }
                        }
                    }else if (etapa_evolutiva.equalsIgnoreCase("Primera Evolucion")){
                        //Si la etapa evolutiva del Pokémon es de primera evolución.
                        if (campo6 != null && campo7 == null && campo8 == null){
                            //Los campos 5 y 6 pertenecen a los tipos, el campo 4 es la pre-evolución.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            pokemon = new Primera_Evolucion(id,nombre_Pokemon,etapa_evolutiva,campo5,campo6,campo4,"");
                        }else if (campo6 != null && campo7 != null && campo8 == null){
                            //Los campos 6 y 7 son los tipos, el campo 4 es la evolución siguiente, y el 5 la pre-evolución.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            campo7 = campo7.trim();
                            pokemon = new Primera_Evolucion(id,nombre_Pokemon,etapa_evolutiva,campo6,campo7,campo5,campo4);
                        }
                    }else if (etapa_evolutiva.equalsIgnoreCase("Segunda Evolucion")){
                        //Si la etapa evolutiva del Pokémon es de segunda evolución.
                        if (campo8 == null){
                            //El único caso es que el campo 8 sea nulo.
                            //Los campos 6 y 7 son los tipos, el campo 4 es la primera evolución, y el campo 5 es de la etapa básica.
                            campo4 = campo4.trim();
                            campo5 = campo5.trim();
                            campo6 = campo6.trim();
                            campo7 = campo7.trim();
                            pokemon = new Segunda_Evolucion(id,nombre_Pokemon,etapa_evolutiva,campo6,campo7,campo4,campo5);
                        }
                    }
                    //Si el Pokémon no es nulo, finalmente se agrega a la lista.
                    if (pokemon != null){
                        pokedex_completa.agregarAdelante(pokemon);
                    }
                }catch (Exception exception){
                    //Captura de excepción.
                }
            }
        }catch (Exception exception){
            //Captura de excepción.
        }
    }

    /**
     * desplegarDatosPokemon(Pokemon pokemon):
     * Uno de los métodos más importantes del programa. Permite desplegar la información
     * del Pokémon del parámetro. Primeramente, se despliega su ID, nombre, y su(s) tipo(s)
     * (se comparan los 2 tipos. Si son iguales, se despliega como un tipo único. En caso contrario,
     * por separado). Dependiendo de qué etapa evolutiva sea, puede variar el despliegue de la información
     * de la cadena evolutiva (evoluciones o pre-evoluciones):
     * <p>
     * 1) El Pokémon es básico (si el objeto Pokemon es una instancia de Basico), se analiza su primera y segunda evolución.
     * Si posee ambas evoluciones, se despliegan directamente. Si solo posee primera evolución, se divide en otros 2 casos más.
     * Si el Pokémon en cuestión es Eevee (el único entre los 151 primeros Pokémon que posee más de una primera evolución), se despliega
     * que tiene 3 primeras evoluciones: Vaporeon, Jolteon y Flareon. Si solo posee primera evolución y no es Eevee, se despliega su
     * primera evolución solamente. No se despliega nada adicional si no tiene ni primera ni segunda evolución.
     * <p>
     * 2) El Pokémon es de primera evolución (Pokemon es instancia de Primera_Evolucion), se analiza su pre-evolución (básico) y evolución
     * siguiente (segunda evolución). Como las primeras evoluciones deben poseer pre-evolución, se despliega directamente. Y, en el caso
     * que posea evolución siguiente, también se despliega (puede no tener).
     * <p>
     * 3) El Pokémon es de segunda evolución (Pokemon es instancia de Segunda_Evolucion). Solamente se despliega la etapa básica y la pre-evolución
     * (primera evolución).
     *
     * @param pokemon (El Pokémon al cual se le desplegará su información).
     */
    @Override
    public void desplegarDatosPokemon(Pokemon pokemon) {
        try {
            //Se imprimen primeramente, los datos que poseen todos los Pokémon por igual:
            //ID, Nombre, y su(s) tipo(s).

            StdOut.println("[*************************]");
            StdOut.println("[" + pokemon.getId() + "] " + "Nombre del Pokémon: " +  pokemon.getNombre());
            StdOut.println("ID del Pokémon: " + pokemon.getId());
            if (pokemon.getTipo1().equalsIgnoreCase(pokemon.getTipo2())){
                //Si el tipo primario coincide con el tipo secundario, se despliega solo uno.
                //En el caso contrario, por separado.
                StdOut.println("Tipo del Pokémon: " + pokemon.getTipo1());
            }else{
                StdOut.println("Tipos del Pokémon: " + pokemon.getTipo1() + " / " + pokemon.getTipo2());
            }

            if (pokemon instanceof Basico){
                //Si el Pokémon es básico.
                StdOut.println("Etapa evolutiva: Básico");
                if (!((Basico) pokemon).getPrimera_evolucion().equalsIgnoreCase("") && ((Basico) pokemon).getSegunda_evolucion().equalsIgnoreCase("")
                        && pokemon.getNombre().equalsIgnoreCase("Eevee")){
                    //Si solo posee primera evolución, y el Pokémon en cuestión es Eevee.
                    StdOut.println("3 posibles primeras evoluciones: ");
                    StdOut.println("Primera posible evolución: Vaporeon");
                    StdOut.println("Segunda posible evolución: Jolteon");
                    StdOut.println("Tercera posible evolución: Flareon");
                }else if (!((Basico) pokemon).getPrimera_evolucion().equalsIgnoreCase("") && !((Basico) pokemon).getSegunda_evolucion().equalsIgnoreCase("")){
                    //Si tiene primera y segunda evolución.
                    StdOut.println("Primera evolución: " + ((Basico) pokemon).getPrimera_evolucion());
                    StdOut.println("Segunda evolución: " + ((Basico) pokemon).getSegunda_evolucion());
                }else if (!((Basico) pokemon).getPrimera_evolucion().equalsIgnoreCase("") && ((Basico) pokemon).getSegunda_evolucion().equalsIgnoreCase("")){
                    //Si solo tiene primera evolución, y el Pokémon no es Eevee.
                    StdOut.println("Primera evolución: " + ((Basico) pokemon).getPrimera_evolucion());
                }
                //No se despliega nada adicionalmente, si no posee primera ni segunda evolución.
            }else if (pokemon instanceof Primera_Evolucion){
                //Si el Pokémon es de primera evolución.
                StdOut.println("Etapa evolutiva: Primera evolución");

                //Se despliega la pre-evolución, porque sí o sí debe tener.
                StdOut.println("Pre-evolución: " + ((Primera_Evolucion) pokemon).getPre_evolucion());
                if (!((Primera_Evolucion) pokemon).getEvolucion_sig().equalsIgnoreCase("")){
                    //Si posee también evolución siguiente.
                    StdOut.println("Siguiente evolución: " + ((Primera_Evolucion) pokemon).getEvolucion_sig());
                }
            }else if (pokemon instanceof Segunda_Evolucion){
                //Si el Pokémon es de segunda evolución.
                StdOut.println("Etapa evolutiva: Segunda evolución");
                StdOut.println("Etapa básica: " + ((Segunda_Evolucion) pokemon).getPre_evolucion_basico());
                StdOut.println("Etapa previa: " + ((Segunda_Evolucion) pokemon).getPre_evolucion());
            }
        }catch (Exception exception){
            //Captura de excepción.
        }
    }

    /**
     * desplegarDatosCadenaEvolutiva(Pokemon pokemon_buscado):
     * Este método llama al otro de desplegar datos de un Pokémon, pero tiene la utilidad de buscar las otras etapas evolutivas del Pokémon.
     * Se usa en el requerimiento de búsqueda personalizada, para desplegar los datos de la cadena evolutiva del Pokémon.
     * Primero, llama al otro subprograma de despliegue, con el Pokémon buscado. Nuevamente, la situación puede variar dependiendo de la etapa evolutiva.
     * <p>
     * 1) El Pokémon es básico. Para los Pokémon básicos que no sean Eevee, se obtiene, en primer lugar, su primera y segunda evolución de la lista,
     * al conseguir los Strings a partir del Pokémon buscado. Si la primera evolución es nula, no se imprime y la segunda tampoco. Pero si la primera evolución
     * existe en la lista, puede imprimirse o no la segunda. Para el caso de Eevee, se busca a Vaporeon, Jolteon y Flareon directamente en la lista y se imprimen
     * con el método, si existen.
     * <p>
     * 2) El Pokémon es de primera evolución. Como los Pokémon de primera evolución deben tener etapa básica, se llama al otro método de despliegue con su pre-evolución,
     * y en caso de que exista una evolución siguiente, también sigue el mismo procedimiento.
     * <p>
     * 3) El Pokémon es de segunda evolución. Como los de segunda evolución deben poseer etapa básica y primera evolución, se despliegan directamente.
     *
     * @param pokemon_buscado (El Pokémon al cuál desplegar sus datos y los de su cadena evolutiva).
     */
    @Override
    public void desplegarDatosCadenaEvolutiva(Pokemon pokemon_buscado) {

        try {
            //Primero se despliega la información del Pokémon buscado.
            desplegarDatosPokemon(pokemon_buscado);
            if (pokemon_buscado instanceof Basico){
                //Si el Pokémon es básico y no es Eevee.
                if (!pokemon_buscado.getNombre().equalsIgnoreCase("Eevee")){
                    //Se obtienen las evoluciones mediante búsqueda en la lista.
                    Pokemon primera_evo = pokedex_completa.obtenerPokemon_Nombre(((Basico) pokemon_buscado).getPrimera_evolucion());
                    Pokemon segunda_evo = pokedex_completa.obtenerPokemon_Nombre(((Basico) pokemon_buscado).getSegunda_evolucion());

                    //Se despliegan la primera y segunda evolución, en el caso que no sean nulos.
                    if (primera_evo != null){
                        StdOut.println("Datos de su cadena evolutiva: ");
                        desplegarDatosPokemon(primera_evo);
                    }

                    if (segunda_evo != null){
                        desplegarDatosPokemon(segunda_evo);
                    }
                }else{
                    //En el caso que sea Eevee, se busca a Vaporeon, Jolteon y Flareon en la lista, y se despliegan
                    //si están en la lista.
                    Pokemon Vaporeon = pokedex_completa.obtenerPokemon_Nombre("Vaporeon");
                    Pokemon Jolteon = pokedex_completa.obtenerPokemon_Nombre("Jolteon");
                    Pokemon Flareon = pokedex_completa.obtenerPokemon_Nombre("Flareon");

                    if (Vaporeon != null){
                        desplegarDatosPokemon(Vaporeon);
                    }

                    if (Jolteon != null){
                        desplegarDatosPokemon(Jolteon);
                    }

                    if (Flareon != null){
                        desplegarDatosPokemon(Flareon);
                    }
                }
            }else if (pokemon_buscado instanceof Primera_Evolucion){
                //Si el Pokémon es de primera evolución, se obtiene su pre-evolución y evolución siguiente.
                Pokemon preevolucion = pokedex_completa.obtenerPokemon_Nombre(((Primera_Evolucion) pokemon_buscado).getPre_evolucion());
                Pokemon evolucion_sig = pokedex_completa.obtenerPokemon_Nombre(((Primera_Evolucion) pokemon_buscado).getEvolucion_sig());

                //Se imprime directamente la pre-evolución (sí o sí debe tener).
                StdOut.println("Datos de su cadena evolutiva: ");
                desplegarDatosPokemon(preevolucion);
                if (evolucion_sig != null){
                    //Si existe su evolución siguiente, también se imprime.
                    desplegarDatosPokemon(evolucion_sig);
                }
            }else if (pokemon_buscado instanceof Segunda_Evolucion){
                //Si el Pokémon es de segunda evolución, se obtiene su etapa básica y su pre-evolución, se despliegan.
                Pokemon etapa_basica = pokedex_completa.obtenerPokemon_Nombre(((Segunda_Evolucion) pokemon_buscado).getPre_evolucion_basico());
                Pokemon primera_evo = pokedex_completa.obtenerPokemon_Nombre(((Segunda_Evolucion) pokemon_buscado).getPre_evolucion());
                StdOut.println("Datos de su cadena evolutiva: ");
                desplegarDatosPokemon(etapa_basica);
                desplegarDatosPokemon(primera_evo);
            }
        }catch (Exception exception){
            //Captura de excepción.
        }
    }


}