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
        BasesDeDatos.execSQL("INSERT INTO t_categoria(id, nom_categoria, desp_categoria, activo) VALUES (1, 'Curiosidades ', 'Curiosidades ', 1), (2, 'Comida', 'Comida', 1),(3, 'Cine', 'cine', 1),(4, 'Musica / Arte', 'Musica / Arte', 1),(5, 'Deporte', 'Deporte', 1),(6, 'Prehispánica', 'Prehispánica', 1),(7, 'Nueva España', 'Nueva España', 1),(8, 'Independencia ', 'Independencia ', 1),(9, 'Revolución', 'Revolución', 1),(10, 'Historia Comtemponranea', 'Historia Comtemponranea', 1),(11, 'Geografía', 'Geografía', 1),(12, 'Legislación Política ', 'Legislación Política ', 1)");

// tabla de complemento
//        BasesDeDatos.execSQL("Create table t_complemento(id int primary Key, co_pregunta int, complemento text, activo int)");


//        BasesDeDatos.execSQL("Create table t_estadisticas(id int primary Key, co_estudios int, co_pregunta int, aprobada int)");
//        BasesDeDatos.execSQL("Create table t_estudios(id int primary Key, fecha date, co_actividad int)");
//        BasesDeDatos.execSQL("Create table t_preguntas(id int primary Key, co_categoria int, co_respuesta int, pregunta text, respuesta text, enlace_resp text, imagen_pre text, imagen_resp text, activo int)");

    // Tabla Arbol de repasos
//        BasesDeDatos.execSQL("Create table t_repaso_arbol(id int primary Key, co_categoria int, nom_repaso text, icono text, activo int)");
//        BasesDeDatos.execSQL("INSERT INTO t_repaso_arbol(id, co_categoria, nom_repaso, icono, activa) VALUES (1, 1, 'Básico', '0', 1), (2, 1, 'Medio', '0', 1), (3, 1, 'Avanzado', '0', 1), (4, 1, 'Experto', '0', 1), (5, 1, 'Leyenda', '0', 1)");


//       BasesDeDatos.execSQL("Create table t_repaso_preguntas(id int primary Key, co_repaso_arbol int, co_preguntas int, aprendida int, leida int, activo int)");

    // Tabla tipo Actividad
//        BasesDeDatos.execSQL("Create table t_tipo_actividad(id int primary Key, nom_actividad text, icono text, activo int)");
//        BasesDeDatos.execSQL("INSERT INTO t_tipo_actividad(id, nom_actividad, icono, activo ) VALUES (1, 'Cuestionario', '', 1), (2, 'Reta ', '', 1)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
