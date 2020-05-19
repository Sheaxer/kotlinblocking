package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending CrudRepository for ReportedOverlimitTransaction.
 * Rozhranie rozširujúce CrudRepository pre objekty triedy ReportedOverlimitTransaction.
 * @see ReportedOverlimitTransaction
 * @see CrudRepository
 */
@Repository
interface ReportedOverlimitTransactionRepository : CrudRepository<ReportedOverlimitTransaction, String> {}