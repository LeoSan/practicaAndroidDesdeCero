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

    // Tabla de categoria
        BasesDeDatos.execSQL("Create table t_categoria(id int primary Key, nom_categoria text, desp_categoria text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_categoria(id, nom_categoria, desp_categoria, activo) VALUES (1, 'Curiosidades ', 'curiosidad', 1), (2, 'Comida', 'comida', 1),(3, 'Cine', 'cine', 1),(4, 'Musica / Arte', 'arte', 1),(5, 'Deporte', 'deporte', 1),(6, 'Prehispánica', 'prehispanica', 1),(7, 'Nueva España', 'deporte', 1),(8, 'Independencia ', 'independencia', 1),(9, 'Revolución', 'revolucion', 1),(10, 'Historia Comtemponranea', 'historiamoderna', 1),(11, 'Geografía', 'geografia', 1),(12, 'Legislación Política ', 'legislacion', 1)");

     // Tabla Niveles de aprendizaje

        BasesDeDatos.execSQL("Create table t_niveles(id int primary Key, nom_nivel text, icono text, activo int)");
        BasesDeDatos.execSQL("INSERT INTO t_niveles(id,  nom_nivel, icono, activo) VALUES (1, 'Básico', 'basico', 1), (2, 'Medio', 'medio', 1), (3, 'Avanzado', 'avanzado', 1), (4, 'Experto', 'experto', 1), (5, 'Leyenda', 'leyenda', 1)");

    // Tabla de Preguntas

         BasesDeDatos.execSQL("Create table t_preguntas(id int primary Key, co_categoria int, co_nivel int, co_respuesta int, pregunta text, respuesta text, enlace_resp text, imagen_pre text, imagen_resp text, activo int)");

// tabla de complemento
         BasesDeDatos.execSQL("Create table t_complemento(id int primary Key, co_pregunta int, complemento text, imagen text, activo int)");


//        BasesDeDatos.execSQL("Create table t_estadisticas(id int primary Key, co_estudios int, co_pregunta int, aprobada int)");
//        BasesDeDatos.execSQL("Create table t_estudios(id int primary Key, fecha date, co_actividad int)");


//       BasesDeDatos.execSQL("Create table t_repaso_preguntas(id int primary Key, co_repaso_arbol int, co_preguntas int, aprendida int, leida int, activo int)");

    // Tabla tipo Actividad
//        BasesDeDatos.execSQL("Create table t_tipo_actividad(id int primary Key, nom_actividad text, icono text, activo int)");
//        BasesDeDatos.execSQL("INSERT INTO t_tipo_actividad(id, nom_actividad, icono, activo ) VALUES (1, 'Cuestionario', '', 1), (2, 'Reta ', '', 1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
