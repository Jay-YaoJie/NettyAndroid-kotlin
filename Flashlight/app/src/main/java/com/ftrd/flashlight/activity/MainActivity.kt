package com.ftrd.flashlight.activity


import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.ftrd.flashlight.FileKt.database
import com.ftrd.flashlight.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-01 16:47
 * @description: 主页，登录进入的第一个页面，任务列表和菜单列表的选择
 */
class MainActivity : BaseActivity() {
    override fun into(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        setContentView(R.layout.activity_main);
        //recyclerview
        //RecyclerView ds;
        var  fdsa:RecyclerView ;

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


