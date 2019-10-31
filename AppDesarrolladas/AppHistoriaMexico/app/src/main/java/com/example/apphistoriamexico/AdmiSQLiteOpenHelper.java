package com.example.apphistoriamexico;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdmiSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdmiSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BasesDeDatos) {

    // Tabla tipo Actividad
        BasesDeDatos.execSQL("Create table t_tipo_actividad(id int primary Key, nom_actividad text, icono text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_tipo_actividad(id, nom_actividad, icono, activo ) VALUES (1, 'Reto Saber', '', 1), (2, 'Cuestionario', '', 1)");

    // Tabla Estudios
        BasesDeDatos.execSQL("CREATE TABLE t_estudios(id INTEGER PRIMARY KEY AUTOINCREMENT, fecha date, co_actividad int)");
        BasesDeDatos.execSQL("CREATE TABLE t_estadisticas(id INTEGER PRIMARY KEY AUTOINCREMENT, co_estudios int, co_pregunta int, validacion int)");


    // Tabla de categoria
        BasesDeDatos.execSQL("CREATE TABLE t_categoria(id int primary Key, nom_categoria text, desp_categoria text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_categoria(id, nom_categoria, desp_categoria, activo) VALUES (1, 'Curiosidades ', 'curiosidad', 1), (2, 'Comida', 'comida', 1),(3, 'Cine', 'cine', 1),(4, 'Musica / Arte', 'arte', 1),(5, 'Deporte', 'deporte', 1),(6, 'Prehispánica', 'prehispanica', 1),(7, 'Nueva España', 'deporte', 1),(8, 'Independencia ', 'independencia', 1),(9, 'Revolución', 'revolucion', 1),(10, 'Historia Comtemponranea', 'historiamoderna', 1),(11, 'Geografía', 'geografia', 1),(12, 'Legislación Política ', 'legislacion', 1)");

     // Tabla Niveles de aprendizaje

        BasesDeDatos.execSQL("CREATE TABLE t_niveles(id int primary Key, nom_nivel text, icono text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_niveles(id,  nom_nivel, icono, activo) VALUES (1, 'Básico', 'basico', 1), (2, 'Medio', 'medio', 1), (3, 'Avanzado', 'avanzado', 1), (4, 'Experto', 'experto', 1), (5, 'Leyenda', 'leyenda', 1)");


    // Tabla de Preguntas
        BasesDeDatos.execSQL("CREATE TABLE t_preguntas(id int primary Key, co_categoria int, co_nivel int, co_respuesta int, pregunta text, respuesta text, enlace_resp text, imagen_pre text, imagen_resp text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_preguntas (id, co_categoria, co_nivel, co_respuesta, pregunta, respuesta, enlace_resp, imagen_pre, imagen_resp, activo) VALUES (1, 1, 1, 1, '¿Cuáles son los Premios Nobel por mexicanos?', 'Premio Nobel de la Paz en 1982 Alfonso García Robles', 'https://es.wikipedia.org/wiki/Categor%C3%ADa:Premios_Nobel_de_M%C3%A9xico', '0', '0', 1), (2, 1, 1, 2, '¿ Cuál es el Árbol Nacional? ', 'Ahuehuete o árbol de tule', 'https://www.gob.mx/conafor/articulos/el-arbol-nacional?idiom=es', '0', '0', 1), (3, 1, 1, 3, '¿ Cuál es el Animal Nacional ?', 'Aguila Real', 'https://www.mexicodesconocido.com.mx/aguila-real-mexicana.html', '0', '0', 1), (4, 1, 1, 4, '¿ Cuál es la flor Nacional ? ', 'Dalia', 'https://www.gob.mx/semarnat/articulos/dalia-flor-representativa-de-mexico?idiom=es', '0', '', 1), (5, 1, 1, 5, '¿ El tema dominante del himno nacional mexicano ? ', 'Una exhortación a la guerra defensiva ', 'https://www.gob.mx/semarnat/articulos/dalia-flor-representativa-de-mexico?idiom=es', '0', '', 1)");
        BasesDeDatos.execSQL("INSERT INTO t_preguntas (id, co_categoria, co_nivel, co_respuesta, pregunta, respuesta, enlace_resp, imagen_pre, imagen_resp, activo) VALUES (6, 1, 1, 6, '¿ Cuando fue declarado oficial el himno nacional ?', 'En 1943 durante la presidencia de Manuel Ávila Camacho', 'https://www.gob.mx/inafed/articulos/conmemoramos-que-hace-76-anos-se-emitio-el-decreto-por-el-que-se-establece-la-version-oficial-de-nuestro-himno-nacional', '0', '0', 1), (7, 1, 1, 7, '¿ Cual de los símbolos patrios fue reconocido oficialmente en 1943 ?', 'El Himno Nacional', 'https://www.gob.mx/inafed/articulos/conmemoramos-que-hace-76-anos-se-emitio-el-decreto-por-el-que-se-establece-la-version-oficial-de-nuestro-himno-nacional', '0', '0', 1), (8, 1, 1, 8, '¿ El águila y la serpiente que aparecen en el escudo nacional mexicano, incluido en la franja blanca de la bandera evoca a una ?', 'Evocan una leyenda prehispánica', 'https://es.wikipedia.org/wiki/Escudo_Nacional_de_M%C3%A9xico', '0', '0', 1), (9, 1, 1, 9, '¿ Perro Originario de México o Nombre de raza de perro prehispánico de poco pelo ?', 'Xoloitzcuintle', 'https://es.wikipedia.org/wiki/Xoloitzcuintle', '0', '', 1), (10, 1, 1, 10, '¿ A qué se le llama Fonda ?', 'Es un pequeño restaurante económico', '', '0', '', 1)");


    // tabla de complemento
        BasesDeDatos.execSQL("CREATE TABLE t_complemento(id int primary Key, co_pregunta int, complemento text, imagen text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (1, 1,  'Premio Nobel de la Paz en 1982 Manuel Tolsa', '0', 1), (2, 1, 'Premio Nobel de la Paz en 1982 Maria Felix', '0', 1), (3, 1, 'Premio Nobel de la Paz en 1982 Octavio Paz', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (4, 2,  'Arbol Nacional', '0', 1), (5, 2, 'Sabino', '0', 1), (6, 2, 'Cedro Blanco', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (7, 3,  'Xoloitzcuintle', '0', 1), (8, 3, 'Águila calva', '0', 1), (9, 3, 'Los colibríes', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (10, 4, 'Cempasúchil', '0', 1), (11, 4, 'Tulipanes', '0', 1), (12, 4, 'Orquidea', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (13, 5, 'Paz Universal', '0', 1), (14, 5, 'Venganza a nuestros pueblos', '0', 1), (15, 5, 'Siempre venceremos', '0', 1) ");

        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (16, 6,  'En 1821 con el mandato de Agustín de Iturbide', '0', 1), (17, 6, 'En 1821 en el mandato de José Magdaleno Ocampo', '0', 1), (18, 6, 'En 1916 en la epoca del Gral Veustiano Carranza', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (19, 7,  'Paz Universal', '0', 1), (20, 7, 'Venganza a nuestros pueblos', '0', 1), (21, 7, 'Siempre venceremos', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (22, 8,  'Evoca a la paz universal', '0', 1), (23, 8, 'Evocan a una venganza a nuestros pueblos', '0', 1), (24, 8, 'Evocan nuestros origenes', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (25, 9,  'Chihuahua', '0', 1), (26, 9, 'Labrador Retriever', '0', 1), (27, 9, 'Bulldog', '0', 1) ");
        BasesDeDatos.execSQL("INSERT INTO t_complemento (id, co_pregunta, complemento, imagen, activo) VALUES (28, 10, 'Un Lugar de comida rapida.', '0', 1), (29, 10, 'Restaurantes', '0', 1), (30, 10, 'Lugar de Comida', '0', 1) ");

//        BasesDeDatos.execSQL("Create table t_estudios(id int primary Key, fecha date, co_actividad int)");


//       BasesDeDatos.execSQL("Create table t_repaso_preguntas(id int primary Key, co_repaso_arbol int, co_preguntas int, aprendida int, leida int, activo int)");



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
