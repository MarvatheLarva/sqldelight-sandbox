package com.mtlarva.sandbox.postgres.repository.sale.queries

import com.mtlarva.sandbox.postgres.repository.sale.SaleRepository
import com.mtlarva.sandbox.postgres.schemas.Sale

/**
 * Custom query to insert a batch of sales.
 */
fun SaleRepository.insert(sales: List<Sale>) {
    val sql = """
            INSERT INTO sale
            VALUES (
                ?, -- id
                ?, -- code
                ?, -- user_id
                ?  -- status
            )
        """.trimIndent()

    with(connection.prepareStatement(sql)) {
        sales.forEach { sale ->
            setObject(1, sale.id)
            setString(2, sale.code)
            setString(3, sale.user_id)
            setString(4, sale.status)

            addBatch()
        }

        executeBatch()
    }
}
