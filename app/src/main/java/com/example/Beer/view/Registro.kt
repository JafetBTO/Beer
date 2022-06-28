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


class Registro : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var BDhelper: databaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrer)

        BDhelper = databaseHelper(this)

        val drawer : DrawerLayout = findViewById(R.id.drawer_layout_reg)
        val navigationView: NavigationView = findViewById(R.id.nav_view_reg)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        val guardar: Button = findViewById(R.id.btn_guardar)
        val nom: EditText = findViewById(R.id.edt_nom)
        val ap: EditText = findViewById(R.id.edt_ap)
        val email: EditText = findViewById(R.id.edt_email)
        val pass: EditText = findViewById(R.id.edt_pass)
        val btn_log: Button = findViewById(R.id.btn_log)



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
                        setMessage("¿Deseas salir de la aplicación?")
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

        guardar.setOnClickListener{
                if(nom.text.toString().equals("")  ||  ap.text.toString().equals("")  ||  email.text.toString().equals("")  ||  pass.text.toString().equals("")){
                Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show()
                    nom.setHint("Completar Campo")
                    nom.setHintTextColor(Color.RED)
                    ap.setHint("Completar Campo")
                    ap.setHintTextColor(Color.RED)
                    email.setHint("Completar Campo")
                    email.setHintTextColor(Color.RED)
                    pass.setHint("Completar Campo")
                    pass.setHintTextColor(Color.RED)
                }else {
                    BDhelper.insertaUsuario(
                        nom.text.toString(),
                        ap.text.toString(),
                        email.text.toString(),
                        pass.text.toString()
                    )
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                    nom.text.clear()
                    ap.text.clear()
                    email.text.clear()
                    pass.text.clear()
                }
        }

        btn_log.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
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
