package com.mtlarva.sandbox.postgres.factory

import app.cash.sqldelight.driver.jdbc.JdbcDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.mtlarva.sandbox.postgres.PostgresDB
import javax.sql.DataSource

class PostgresDBFactory {
    companion object {
        fun fromDataSource(dataSource: DataSource): Pair<JdbcDriver, PostgresDB> {
            val driver = dataSource.asJdbcDriver()

            return driver to PostgresDB(driver)
        }
    }
}
