package com.example.ohdoc.data

import com.mysql.jdbc.Driver
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionFactory {
    companion object{
        fun getConnection() : Connection? {
            var connection:Connection?
            try {
                // 1.通过DriverManger注册驱动，注意此时Driver是在com.mysql.jdbc包中
                DriverManager.registerDriver(Driver())
                connection = DriverManager.getConnection(
                    "jdbc:mysql://101.37.76.144:3306/document_sys",
                    "root",
                    "12345678"
                )
            } catch (e: SQLException) {
                e.printStackTrace()
                connection = null
            }
            return connection
        }
    }


}