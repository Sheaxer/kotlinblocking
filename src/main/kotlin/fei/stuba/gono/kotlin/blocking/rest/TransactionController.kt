package fei.stuba.gono.kotlin.blocking.rest

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
/***
 * REST controller for GET,POST,PUT and DELETE methods for ReportedOverlimitTransaction entities
 * on /reportedOverlimitTransaction.
 * REST kontrolér ktorý poskytuje prístup k zdroju ReportedOverlimitTransaction entitám cez metódy
 * GET, PUT, POST a DELETE na /reportedOverlimitTransaction endpointe.
 * @see ReportedOverlimitTransaction
 */
@Slf4j
@RestController
@RequestMapping("/reportedOverlimitTransaction")
class TransactionController @Autowired constructor(private val transactionService:
                                                   ReportedOverlimitTransactionService){
    /***
     * Returns single ReportedOverlimitTransaction based on its id.
     * Vráti entitu triedy ReportedOverlimitTransaction podľa zadaného id.
     * @see ReportedOverlimitTransaction
     * @param id idd of the requested ReportedOverlimitTransaction.
     *           id hľadanej entity.
     * @return requested instance of ReportedOverlimitTransaction.
     * hľadaná inštancia triedy ReportedOverlimitTransaction.
     * @throws ReportedOverlimitTransactionNotFoundException
     *     exception thrown if there is no instance entity with the given id.
     *     výnimka vyvolaná, ak neexistuje entita so zadaným id.
     */
    @GetMapping("/{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun getTransaction(@PathVariable  id : String) : ReportedOverlimitTransaction
    {
        return transactionService.getTransactionById(id).orElseThrow{
                ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")}
    }

    /***
     * PUT method - saves the entity with the given id (if an entity with the given id
     * existed before it will be overwritten).
     * PUT metóda - uloží entitu pod zadaným id (ak už entita so zaaaným id existovala, prepíše ju).
     *
     * @param id id that will represent the saved entity.
     *           id ktoré bude reprezentovať uloženú entitu.
     * @param transaction entity to be saved.
     *                    entita ktorá má byť uložená.
     * @return saved entity.
     */
    @PutMapping("/{id}",produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun putTransaction(@PathVariable id: String, @RequestBody  @Valid transaction:
    ReportedOverlimitTransaction):
            ReportedOverlimitTransaction
    {
        return transactionService.putTransaction(id,transaction)
        //return newTransaction
    }

    /***
     * POST method - generates new id and saves the entity with the new id.
     * POST metóda - generuje nové id a uloží entitu s týmto id.
     * @param newTransaction entity to be saved.entita, ktorá sa má uložiť.
     * @return saved entity.uložená entita.
     */
    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun postTransaction(@RequestBody @Valid newTransaction: ReportedOverlimitTransaction) :
            ReportedOverlimitTransaction
    {
        return transactionService.postTransaction(newTransaction)
    }

    /***
     * Deletes non-closed ReportedOverlimitTransaction with the request id from database.
     * Zmaže entitu so zadaným id z databázy, ak nemá stav CLOSED.
     * @see ReportedOverlimitTransaction
     * @see fei.stuba.gono.kotlin.pojo.State
     * @param id id of the entity that should be deleted.
     *           id entity, ktorá sa má zmazať.
     * @throws ReportedOverlimitTransactionNotFoundException exception thrown if there is no entity
     * with the given id. výnimka vyvolaná, ak entita so zadaným id neexistuje.
     * @throws ReportedOverlimitTransactionBadRequestException exception thrown if the entity
     * with given id cannot be deleted because its state is CLOSED.
     * výnimka vyvolaná, ak entita so zadaným id nemôže byť vymazaná, pretože jej stav je CLOSED.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(@PathVariable id : String)
    {
        if(!transactionService.deleteTransaction(id))
            throw ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")
    }

    /***
     * POST method - saves the entity with the given id.
     * @param id id that will represent the saved entity.
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    @PostMapping("/{id}", produces = ["application/json"])
    fun postTransaction(@PathVariable id: String,
                        @RequestBody transaction: @Valid ReportedOverlimitTransaction):
            ReportedOverlimitTransaction? {
        return putTransaction(id, transaction)
    }
}