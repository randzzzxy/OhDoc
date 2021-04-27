package com.example.ohdoc.data

import com.example.ohdoc.data.model.LoggedInUser
import java.io.IOException
import java.sql.Connection

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // 通过mysql登陆
            //1，得到Connection对象，
            val connection: Connection = ConnectionFactory.getConnection()!!
            //2，通过Connection获取一个操作sql语句的对象Statement
            val statement = connection.createStatement()
            //3，拼接sql语句
            val sql =
                "select user_id,pass_word,user_name from user where user_id = "+ username +" and pass_word = '"+password+"'"
            //4，查询，返回的结果放入ResultSet对象中。
            val resultSet = statement.executeQuery(sql)
            //5，将游标后移一位
            resultSet.next()
            //6，获取数据
            val userId = resultSet.getInt(1)
            val userName = resultSet.getString(3)
            //7，释放资源
            statement.close()
            connection.close()

            val user = LoggedInUser(
                userId.toString(),
                userName
            )
            return Result.Success(user)
        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}