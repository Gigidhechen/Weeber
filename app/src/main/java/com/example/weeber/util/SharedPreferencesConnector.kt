package com.example.appclima.util

import android.content.Context
import android.content.SharedPreferences
import com.example.weeber.R
import java.io.File

class SharedPreferencesConnector /**
 * Constructor que recibe todos los parametros
 *
 * @param sharedPreferences Interface que ayuda a la comunicación de datos.
 */
    (private val sharedPreferences: SharedPreferences) {

    /**
     * Método que escribe un valor booleano.
     *
     * @param key   Nombre de la llave con la que se guardará
     * @param value Valor booleano a guardar
     * @return Regresa true si se guardó exitosamente o false si no se guardó
     */
    fun writeBoolean(key: String, value: Boolean): Boolean {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putBoolean(key, value)
        return prefsEditor.commit()
    }

    /**
     * Método que recupera un valor booleano emparejado con la llave dada.
     *
     * @param key Llave que recuperará el valor booleano.
     * @return Regresa el valor leído de memoria, si no, regresará un false por predeterminado.
     */
    fun readBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

//    /**
//     * Método que remueve un valor previamente guardado.
//     *
//     * @param key Llave que es perteneciente al valor a borrar.
//     */
//    fun removeValue(key: String) {
//        val prefsEditor = sharedPreferences.edit()
//        prefsEditor.remove(key)
//        prefsEditor.apply()
//    }

    /**
     * Método que escribe en memoria un valor tipo String
     *
     * @param key  Llave con la que se guardará el String
     * @param data Valor que se guardará
     * @return Regresa true si la información fue guardada exitosamente, si no, regresa un false.
     */
    fun writeString(key: String, data: String): Boolean {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putString(key, data)
        return prefsEditor.commit()
    }

    /**
     * Método que regresa el valor guardado con la llave dada.
     *
     * @param key Llave con la que se buscará el valor guardado.
     * @return Regresa el valor guardado en memoria.
     */
    fun readString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun writeInt(key: String, data: Int): Boolean {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putInt(key, data)
        return prefsEditor.commit()
    }

    fun readInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }


    fun clearJSON(cacheDir: File): Boolean {
        val applicationDirectory: File =  File(cacheDir.parent!!)
        if(applicationDirectory.exists()){
            val fileName =  applicationDirectory.list()
            for (archive in fileName!!){
                if(archive != "lib"){
                    deleteFile(File(applicationDirectory, archive))
                }
            }
        }
        val editor = sharedPreferences.edit()
        editor.clear()
        return editor.commit()
    }

    private fun deleteFile(file: File): Boolean{
        var deletedAll = true
        if(file.isDirectory){
            val children = file.list()
            for(child in children!!){
                deletedAll = deleteFile(File(file, child)) && deletedAll
            }
        } else {
            deletedAll = file.delete()
        }
        return deletedAll
    }

    companion object {

        fun getInstance(context: Context): SharedPreferencesConnector {
            return SharedPreferencesConnector(
                context.getSharedPreferences(
                    context.getString(R.string.app_name),
                    Context.MODE_PRIVATE
                )
            )
        }
    }
}