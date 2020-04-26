package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling ReportedOverlimitTransaction  entities.
 */
interface ReportedOverlimitTransactionService {
    /***
     * Generates new id and saves the entity.
     * @param transaction entity to be saved
     * @return saved entity.
     */
    fun postTransaction(transaction: ReportedOverlimitTransaction) : ReportedOverlimitTransaction

    /***
     * Retrieves the entity with the given id.
     * @param id id of entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction>

    /***
     * Saves the entity with the given id.
     * @param id id which will identify the saved entity.
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction


    /***
     * Deletes the entity identified by the given id.
     * @param id id of entity.
     * @return true if the entity was deleted, false it there was no entity to be deleted.
     * @throws ReportedOverlimitTransactionBadRequestException when the entity couldn't be deleted.
     */
    @Throws(ReportedOverlimitTransactionBadRequestException::class)
    fun deleteTransaction(id: String): Boolean

}