package com.mtlarva.sandbox.postgres

import com.mtlarva.sandbox.postgres.factory.PostgresDBFactory
import com.mtlarva.sandbox.postgres.repository.sale.SaleRepository
import javax.sql.DataSource
import org.flywaydb.core.Flyway;

data class Container(
    val postgresDB: PostgresDB,
    val saleRepository: SaleRepository
)

fun initPostgresDB(dataSource: DataSource): Container {
    Flyway.configure()
        .dataSource(dataSource)
        .locations("**/generated/migrations/postgres")
        .load()
        .migrate()

    val (driver, postgresDB) = PostgresDBFactory.fromDataSource(dataSource)

    return Container(
        postgresDB = PostgresDB(driver),
        saleRepository = SaleRepository(driver, postgresDB)
    )
}
