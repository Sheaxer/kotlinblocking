package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling ReportedOverlimitTransaction entities.
 * Rozhranie na marshalling a de-marshalling entít triedy ReportedOverlimitTransaction
 */
interface ReportedOverlimitTransactionService {
    /***
     * Generates new id and saves the entity.
     * Generuje nové id a uloží entitu s týmto id.
     * @param transaction entity to be saved.
     *                    entita, ktorá sa má uložiť.
     * @return saved entity.
     * uložená entita.
     */
    fun postTransaction(transaction: ReportedOverlimitTransaction) : ReportedOverlimitTransaction

    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of entity.
     *           id entity.
     * @return Optional containing the entity or Optional.empty()
     * if no entity was found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita nebola nájdená.
     */
    fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction>

    /***
     * Saves the entity using the given id.
     * Uloží entitu pod použitým id.
     * @param id id which will identify the saved entity.
     *           id, ktoré bude reprezentovať uloženú entitu.
     * @param transaction entity to be saved.
     *                    entita, ktorá sa má uložiť.
     * @return saved entity.
     * uložená entita.
     */
    fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction


    /***
     * Deletes the entity identified by the given id.
     * Zmaže entitu identifikovanú zadaným id.
     * @param id id of entity.
     *           id entity.
     * @return true if the entity was deleted, false it no entity found.
     * true ak entita bola zmazaná, false ak entita nebola nájdená.
     * @throws ReportedOverlimitTransactionBadRequestException
     *     thrown when the entity couldn't be deleted.
     *     výnimka vyvolaná, ak entita nemohla byť zmazaná.
     */
    @Throws(ReportedOverlimitTransactionBadRequestException::class)
    fun deleteTransaction(id: String): Boolean

}