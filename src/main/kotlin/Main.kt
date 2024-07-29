import com.mtlarva.sandbox.mysql.initMysqlDB
import com.mtlarva.sandbox.mysql.schemas.Sale as SaleMysql
import com.mtlarva.sandbox.postgres.schemas.Sale as SalePostgres
import com.mtlarva.sandbox.postgres.initPostgresDB
import com.mtlarva.sandbox.postgres.repository.sale.queries.insert
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import java.util.UUID

internal fun buildMysqlDataSource(): HikariDataSource {
    val config = HikariConfig()
    config.jdbcUrl = "jdbc:mysql://localhost:3306/MysqlDB"
    config.username = "root"
    config.password = ""
    config.driverClassName = "com.mysql.cj.jdbc.Driver"

    // Enable optimization to rewrite and collapse compatible INSERT statements that are batched.
    config.addDataSourceProperty("reWriteBatchedInserts", "true")

    return HikariDataSource(config)
}

internal fun buildPostgresDataSource(): HikariDataSource {
    val config = HikariConfig()
    config.jdbcUrl = "jdbc:postgresql://localhost:5432/PostgresDB?rewriteBatchedStatements=true"
    config.username = "postgres"
    config.password = ""
    config.driverClassName = "org.postgresql.Driver"

    // Enable optimization to rewrite and collapse compatible INSERT statements that are batched.
    config.addDataSourceProperty("reWriteBatchedInserts", "true")

    return HikariDataSource(config)
}

internal val logger = LoggerFactory.getLogger("MainKt")

fun main() {
    with(initMysqlDB(buildMysqlDataSource())) {
        saleRepository.truncate()

        saleRepository.insertSale(SaleMysql(
            id = UUID.randomUUID().toString(),
            code = "",
            user_id = "",
            status = ""
        ))

        saleRepository.getAll()
            .let { logger.info("Mysql Sales: $it") }


        saleRepository.customQuery()
    }

    with(initPostgresDB(buildPostgresDataSource())) {
        saleRepository.truncate()

        saleRepository.getAll().let {
            logger.info("Postgres Sales: $it")
        }

        saleRepository.insert(
            (1..10).map {
                SalePostgres(
                    id = UUID.randomUUID(),
                    code = "",
                    user_id = "",
                    status = ""
                )
            }
        )

        saleRepository.getAll().let {
            logger.info("Postgres Sales: $it")
        }
    }
}
