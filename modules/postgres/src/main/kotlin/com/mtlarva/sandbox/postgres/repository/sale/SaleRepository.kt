package com.mtlarva.sandbox.postgres.repository.sale

import app.cash.sqldelight.driver.jdbc.JdbcDriver
import com.mtlarva.sandbox.postgres.PostgresDB
import com.mtlarva.sandbox.postgres.schemas.Sale

class SaleRepository(driver: JdbcDriver, database: PostgresDB) {
    val connection = driver.getConnection()

    private val saleQueries = database.saleQueries

    fun truncate() = saleQueries.truncate()

    fun getAll() = saleQueries.getAll().executeAsList()

    fun insert(sale: Sale) = saleQueries.insert(sale)
}
