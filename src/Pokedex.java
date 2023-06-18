import java.io.IOException;

/**
 * La interfaz de la Pokédex.
 */
public interface Pokedex {

    /**
     * iniciar():
     * Este método contiene el menú principal del programa, y conduce al resto de métodos de cada
     * requerimiento. Contiene su respecto validación en caso de ingresar un dato inválido.
     * Contiene 6 opciones: desplegar un rango de Pokémon con su ID de forma creciente, desplegar
     * todos los Pokémon del sistema en orden alfabético, desplegar Pokémon de un tipo específico,
     * desplegar los Pokémon de primera evolución con su ID de forma decreciente, realizar una búsqueda
     * personalizada de un Pokémon a partir de su nombre o ID, y cerrar el programa.
     */
    public void iniciar();

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

    public void desplegarPokemonRango();

    /**
     * desplegarTodosLosPokemon():
     * Requerimiento del programa. Mediante un método de la lista, se ordenan los Pokémon en los nodos
     * de manera alfabética (retorna un booleano). Si es true, mediante un ciclo for, se obtienen los Pokémon
     * por posición a partir de la lista de nodos, y se despliegan mediante el método de despliegue. Como se
     * ordenaron alfabéticamente, deberían desplegar así.
     * Si se retorna un false, es porque la lista está vacía, o solo se posee un Pokémon (que igualmente se despliega).
     */
    public void desplegarTodosLosPokemon();

    /**
     * desplegarPokemonPorTipo():
     * Contrato requerido. Permite al usuario ingresar un tipo de Pokémon, y se despliegan los Pokémon
     * que lo posean (como tipo principal o secundario). Se tiene un arreglo de Strings, que posee los 15
     * tipos de Pokémon que existían en la primera generación. Cuando el usuario ingresa el tipo, se busca
     * en este arreglo, para comprobar si existe. Si no existe, se vuelve a pedir el tipo. En caso contrario,
     * se realiza el procedimiento de búsqueda.
     *
     * Se instancia una LinkedList, y se rellena con un ciclo for, con el método de la lista de nodos que permite
     * obtener Pokémon a partir de las posiciones (se rellena primeramente con todos los Pokémon de la lista).
     *
     * Luego, con la clase Iterador se recorre la LinkedList, y si al menos uno de los 2 tipos del Pokémon coincide
     * con el buscado, se despliega. Así hasta que se termine de recorrer. También se tiene un boolean, que permite
     * identificar si se desplegó al menos 1 Pokémon de ese tipo. Si no, se imprime que no hay Pokémon del tipo buscado.
     */
    public void desplegarPokemonPorTipo();

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
    public void desplegarPrimerasEvolucionesDecreciente();

    /**
     * busquedaPersonalizada():
     * Este requerimiento deja que el usuario pueda buscar un Pokémon mediante su nombre o ID.
     * En un sub-menú, se puede elegir entre buscar por nombre, ID o retroceder al menú principal.
     * Se valida también que el usuario ingrese una opción válida.
     *
     * Si se elige 1 (buscar por nombre), se ingresa el nombre del Pokémon y la lista con nodos realiza la búsqueda mediante el método de buscar por nombre.
     * Si el Pokémon retornado es nulo, se despliega mensaje de error diciendo que el Pokémon no existe. En caso contrario, se ejecuta el método de desplegar
     * datos de la Cadena Evolutiva: se imprime la información del Pokémon buscado, en primer lugar, y luego, los Pokémon que también pertenezcan a su cadena
     * evolutiva (pre-evoluciones / evoluciones) (se aclarará como funciona el método, en la documentación de este).
     *
     * Si se elige 2 (buscar por ID), se ingresa el ID del Pokémon (validando que sea dentro del rango de 1 a 151, y que no sean caracteres no-numéricos),
     * la lista con nodos busca el Pokémon con el ID, y lo retorna. Si es nulo, mensaje de error, y si no es nulo, se llama al método de la cadena evolutiva
     * mencionada anteriormente.
     */
    public void busquedaPersonalizada();

    /**
     * cierre():
     * Solamente despliega un mensaje de despedida, y permite que no se imprima
     * el mensaje de opción inválida en el menú principal.
     */
    public void cierre();

    /**
     * leerArchivo():
     * La lectura del archivo (kanto.txt), que permite el registro de datos en el programa.
     * Primeramente, se define el archivo de entrada (kanto.txt). La estructura de los campos
     * varía dependiendo de qué etapa evolutiva sea el Pokémon, pero se tienen varios casos, y
     * es seguro que los primeros 3 campos son: el ID, el nombre y la etapa evolutiva. A partir de la etapa
     * evolutiva, se pueden tomar diferentes casos. Se borran también los espacios en el campo, con el método .trim().
     *
     * 1) Pokémon básico.
     * Caso 1: Sin primera ni segunda evolución (el campo 6, 7 y 8 son nulos, el 4 y 5 son los tipos). Máximo de campos: 5.
     * Caso 2: Con primera, sin segunda evolución (el campo 7 y 8 son nulos, el 5 y 6 son los tipos y el 4 es la primera evolución). Máximo de campos: 6.
     * Caso 3: Con primera y segunda evolución (el campo 8 es nulo, el 6 y 7 son los tipos, el 4 es la primera evolución, el 5 es la segunda). Máximo de campos: 7.
     * Caso 4: El Pokémon es Eevee (ningún campo nulo, 7 y 8 son los tipos, desde el 4 al 6 son Vaporeon, Flareon y Jolteon). Máximo de campos: 8.
     *
     * En cada caso, se construye el Pokémon con cada campo correspondiente. Para Eevee (único Pokémon de los primeros 151 que posee
     * más de una primera evolución), se designa al azar cuál será el Pokémon que estará en su atributo de "primera evolución".
     *
     * 2) Pokémon de primera evolución.
     * Caso 1: Sin segunda evolución (campo 7 y 8 nulos, el campo 5 y 6 son los tipos, y el campo 4 es la pre-evolución).
     * Caso 2: Con segunda evolución (campo 8 nulo, el campo 6 y 7 son los tipos, el campo 4 es la evolución siguiente, el 5 es la pre-evolución).
     *
     * 3) Pokémon de segunda evolución.
     * Único caso: (campo 8 nulo, campo 6 y 7 son los tipos, el campo 4 es la pre-evolución, el campo 5 es la etapa básica).
     *
     * Si el Pokémon no es nulo, se agrega a la lista. Se tiene un try-catch para capturar las excepciones al intentar transformar el ID
     * de una línea vacía, y se valida si el Pokémon tiene un ID menor a 1 o mayor a 151.
     *
     * @throws IOException (excepción).
     */
    public void leerArchivo() throws IOException;

    /**
     * desplegarDatosPokemon(Pokemon pokemon):
     * Uno de los métodos más importantes del programa. Permite desplegar la información
     * del Pokémon del parámetro. Primeramente, se despliega su ID, nombre, y su(s) tipo(s)
     * (se comparan los 2 tipos. Si son iguales, se despliega como un tipo único. En caso contrario,
     * por separado). Dependiendo de qué etapa evolutiva sea, puede variar el despliegue de la información
     * de la cadena evolutiva (evoluciones o pre-evoluciones):
     *
     * 1) El Pokémon es básico (si el objeto Pokemon es una instancia de Basico), se analiza su primera y segunda evolución.
     * Si posee ambas evoluciones, se despliegan directamente. Si solo posee primera evolución, se divide en otros 2 casos más.
     * Si el Pokémon en cuestión es Eevee (el único entre los 151 primeros Pokémon que posee más de una primera evolución), se despliega
     * que tiene 3 primeras evoluciones: Vaporeon, Jolteon y Flareon. Si solo posee primera evolución y no es Eevee, se despliega su
     * primera evolución solamente. No se despliega nada adicional si no tiene ni primera ni segunda evolución.
     *
     * 2) El Pokémon es de primera evolución (Pokemon es instancia de Primera_Evolucion), se analiza su pre-evolución (básico) y evolución
     * siguiente (segunda evolución). Como las primeras evoluciones deben poseer pre-evolución, se despliega directamente. Y, en el caso
     * que posea evolución siguiente, también se despliega (puede no tener).
     *
     * 3) El Pokémon es de segunda evolución (Pokemon es instancia de Segunda_Evolucion). Solamente se despliega la etapa básica y la pre-evolución
     * (primera evolución).
     *
     * @param pokemon (El Pokémon al cual se le desplegará su información).
     */
    public void desplegarDatosPokemon(Pokemon pokemon);

    /**
     * desplegarDatosCadenaEvolutiva(Pokemon pokemon_buscado):
     * Este método llama al otro de desplegar datos de un Pokémon, pero tiene la utilidad de buscar las otras etapas evolutivas del Pokémon.
     * Se usa en el requerimiento de búsqueda personalizada, para desplegar los datos de la cadena evolutiva del Pokémon.
     * Primero, llama al otro subprograma de despliegue, con el Pokémon buscado. Nuevamente, la situación puede variar dependiendo de la etapa evolutiva.
     *
     * 1) El Pokémon es básico. Para los Pokémon básicos que no sean Eevee, se obtiene, en primer lugar, su primera y segunda evolución de la lista,
     * al conseguir los Strings a partir del Pokémon buscado. Si la primera evolución es nula, no se imprime y la segunda tampoco. Pero si la primera evolución
     * existe en la lista, puede imprimirse o no la segunda. Para el caso de Eevee, se busca a Vaporeon, Jolteon y Flareon directamente en la lista y se imprimen
     * con el método, si existen.
     *
     * 2) El Pokémon es de primera evolución. Como los Pokémon de primera evolución deben tener etapa básica, se llama al otro método de despliegue con su pre-evolución,
     * y en caso de que exista una evolución siguiente, también sigue el mismo procedimiento.
     *
     * 3) El Pokémon es de segunda evolución. Como los de segunda evolución deben poseer etapa básica y primera evolución, se despliegan directamente.
     * @param pokemon_buscado (El Pokémon al cuál desplegar sus datos y los de su cadena evolutiva).
     */
    public void desplegarDatosCadenaEvolutiva(Pokemon pokemon_buscado);
}