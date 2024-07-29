package com.mtlarva.sandbox.mysql.factory

import app.cash.sqldelight.driver.jdbc.JdbcDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.mtlarva.sandbox.mysql.MysqlDB
import javax.sql.DataSource

class MysqlDBFactory {
    companion object {
        fun fromDataSource(dataSource: DataSource): Pair<JdbcDriver, MysqlDB> {
            val driver = dataSource.asJdbcDriver()

            return driver to MysqlDB(driver)
        }
    }
}
