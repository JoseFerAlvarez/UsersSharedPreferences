package com.jfalvarezdev.userssp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jfalvarezdev.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    //Declaracion de variables
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    //Metodo onCreate de la Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflamos el layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Constante para las preferencias que se guardaran
        val preferences = getPreferences(Context.MODE_PRIVATE)

        //Constante para la primera vez que abrimos la app
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        //Si es la primera vez, nos aparece un dialog para registrarnos en la app
        if(isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

            //Dialog para registrarnos con un boton positivo y uno para cancelar
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)

                 //Si pulsamos el boton registrar guarda el nombre del usuario en las preferencias, el dialog no volvera a aparecer
                .setPositiveButton(R.string.dialog_confirm, { dialog, which ->
                    val username = dialogView.findViewById<EditText>(R.id.etUsername).text.toString()

                    with(preferences.edit()) {
                        preferences.edit().putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
                })

                 //Si pulsamos el boton cancelar se cierra el dialog, que nos volvera a aparecer la proxima vez que abramos la aplicacion
                .setNeutralButton(R.string.dialog_neutral, null).show()
        } else{
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }

        //Guarda los usuarios creados en el recyclerview
        userAdapter = UserAdapter(getUsers(), this)

        linearLayoutManager = LinearLayoutManager(this)

        binding.rvUsers.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    //Metodo en el que creamos varios usuarios a mano
    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val userKratos = User(1, "Robert", "Downey Jr", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/220px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg")
        val userRagnar = User(2, "Travis", "Fimmel", "https://upload.wikimedia.org/wikipedia/commons/9/9f/Travis_Fimmel_by_Gage_Skidmore.jpg")
        val userGandalf = User(3, "Ian", "Mckellen", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/SDCC13_-_Ian_McKellen.jpg/1200px-SDCC13_-_Ian_McKellen.jpg")
        val userTommy = User(4, "Cillian", "Murphy", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Cillian_Murphy_Press_Conference_The_Party_Berlinale_2017_02cr.jpg/220px-Cillian_Murphy_Press_Conference_The_Party_Berlinale_2017_02cr.jpg")

        users.add(userKratos)
        users.add(userRagnar)
        users.add(userGandalf)
        users.add(userTommy)
        users.add(userKratos)
        users.add(userRagnar)
        users.add(userGandalf)
        users.add(userTommy)
        users.add(userKratos)
        users.add(userRagnar)
        users.add(userGandalf)
        users.add(userTommy)
        users.add(userKratos)
        users.add(userRagnar)
        users.add(userGandalf)
        users.add(userTommy)


        return users
    }

    //Cuando pulsamos en un usuario nos aparece su nombre completo
    override fun onClick(user: User, position: Int) {
        Toast.makeText(this,"$position:  $user.getFullName()", Toast.LENGTH_SHORT).show()
    }


}