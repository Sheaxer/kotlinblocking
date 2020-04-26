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
 * REST controller for GET,POST,PUT and DELETE methods for ReportedOverlimitTransaction entities.
 * @see ReportedOverlimitTransaction
 */
@Slf4j
@RestController
@RequestMapping("/reportedOverlimitTransaction")
class TransactionController @Autowired constructor(private val transactionService:
                                                   ReportedOverlimitTransactionService){
    /***
     * Returns single ReportedOverlimitTransaction based on its  id.
     * @see ReportedOverlimitTransaction
     * @param id Id of the requested ReportedOverlimitTransaction
     * @return requested instance of ReportedOverlimitTransaction
     * @throws ReportedOverlimitTransactionNotFoundException exception if there is no instance entity with the given id.
     */
    @GetMapping("/{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun getTransaction(@PathVariable  id : String) : ReportedOverlimitTransaction
    {
        return transactionService.getTransactionById(id).orElseThrow{
                ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")}
    }

    /***
     * PUT method - saves the entity with the given id.
     * @param id id that will represent the saved entity.
     * @param transaction entity to be saved.
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
     * @param newTransaction entity to be saved
     * @return saved entity.
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
     * @see ReportedOverlimitTransaction
     * @see fei.stuba.gono.kotlin.pojo.State
     * @param id id of ReportedOverlimitTransaction that should be deleted
     * @throws ReportedOverlimitTransactionNotFoundException if there is no entity with the given id.
     * @throws ReportedOverlimitTransactionBadRequestException if the entity with given id cannot be deleted because its
     * state is CLOSED.
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