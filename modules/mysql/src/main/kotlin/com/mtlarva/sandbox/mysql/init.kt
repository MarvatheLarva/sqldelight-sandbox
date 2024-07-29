package com.mtlarva.sandbox.mysql

import com.mtlarva.sandbox.mysql.factory.MysqlDBFactory
import com.mtlarva.sandbox.mysql.repository.SaleRepository
import javax.sql.DataSource
import org.flywaydb.core.Flyway;

data class Container(
    val mysqlDB: MysqlDB,
    val saleRepository: SaleRepository
)

fun initMysqlDB(dataSource: DataSource): Container {
    Flyway.configure()
        .dataSource(dataSource)
        .locations("classpath:generated/migrations/mysql")
        .load()
        .migrate()

    val (driver, mysqlDB) = MysqlDBFactory.fromDataSource(dataSource)

    return Container(
        mysqlDB = MysqlDB(driver),
        saleRepository = SaleRepository(driver, mysqlDB)
    )
}
