package com.example.Beer.view

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.Beer.R
import com.example.Beer.model.databases.databaseHelper
import com.google.android.material.navigation.NavigationView

class Login : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var BDhelper: databaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        BDhelper = databaseHelper(this)

        val drawer : DrawerLayout = findViewById(R.id.drawer_layout_log)
        val navigationView: NavigationView = findViewById(R.id.nav_view_log)
        val btn_login: Button = findViewById(R.id.btn_logueo)
        val email: EditText = findViewById(R.id.email)
        val pass: EditText = findViewById(R.id.pass)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.salir -> {
                    //Toast.makeText(this, "Hola" , Toast.LENGTH_SHORT).show()

                    AlertDialog.Builder(this).apply {
                        setTitle("Aviso")
                        setMessage("??Deseas salir de la aplicaci??n?")
                        setPositiveButton("Si"){ _: DialogInterface, _:Int ->
                            aceptar()
                        }
                        setNegativeButton("No"){ _: DialogInterface, _:Int ->
                            drawer.closeDrawer(GravityCompat.START)
                        }


                    }.show()
                }

            }
            true
        }

        btn_login.setOnClickListener{
            if( email.text.toString().equals("")  ||  pass.text.toString().equals("")){
                Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show()
                email.setHint("Completar Campo")
                email.setHintTextColor(Color.RED)
                pass.setHint("Completar Campo")
                pass.setHintTextColor(Color.RED)
            }else if (BDhelper.inicioSesion(email.text.toString(),pass.text.toString()))
            {
                Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Listado::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show()
                email.text.clear()
                pass.text.clear()
            }
        }

    }

    fun aceptar() {
        finishAffinity()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

