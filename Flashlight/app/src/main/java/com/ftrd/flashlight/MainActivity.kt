package com.ftrd.flashlight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.ftrd.flashlight.FileKt.database
import com.ftrd.flashlight.FlashLight.Companion.instance
import com.ftrd.flashlight.nettys.NettyConnect
import com.ftrd.flashlight.nettys.NettyConnect.channel


import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-01 16:47
 * @description:
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
     hello.setText("");
       hello.setOnClickListener {
           Toast.makeText(instance, "Hello, views!", LENGTH_SHORT).show();
           Log.d("wxl", "retrolambda test");

       }
//nnew Thread(new Runnable() {
        Thread(Runnable {


            database.use {

               var sss:SelectQueryBuilder =select("User", "name")
                        .whereArgs("(_id > {userId}) and (name = {userName})",
                                "userName" to "John",
                                "userId" to 42)



                select("User", "email").exec {
                    // Doing some stuff with emails
                    val rowParser = classParser<String>()
                }

                createTable("Customer", true,
                    "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                    "name" to TEXT,
                    "photo" to BLOB) }
        });

//        suspend fun doSomething(foo: Foo): Bar {
//        }

       // activity.hello.text = "Hello World!"
    }
}


