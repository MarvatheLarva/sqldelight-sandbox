package com.mtlarva.sandbox.mysql.repository

import app.cash.sqldelight.driver.jdbc.JdbcDriver
import com.mtlarva.sandbox.mysql.MysqlDB
import com.mtlarva.sandbox.mysql.schemas.Sale

class SaleRepository(private val driver: JdbcDriver, private val database: MysqlDB) {
    fun getAll() = database.saleQueries.getAll().executeAsList()

    fun insertSale(sale: Sale) = database.saleQueries.insert(sale)

    fun truncate() = database.saleQueries.truncate()

    fun customQuery() {
        driver.execute(
            identifier = null,
            sql = "SELECT * FROM sale",
            parameters = 0
        )
    }
}
