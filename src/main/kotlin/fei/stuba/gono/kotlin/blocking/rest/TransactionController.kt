package fei.stuba.gono.kotlin.blocking.rest

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime
import javax.validation.Valid

@Slf4j
@RestController
@RequestMapping("/reportedOverlimitTransaction")
class TransactionController @Autowired constructor(private val transactionService:
                                                   ReportedOverlimitTransactionService){

    @GetMapping("/{id}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun getTransaction(@PathVariable  id : String) : ReportedOverlimitTransaction
    {
        return transactionService.getTransactionById(id).orElseThrow{
                ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")}
    }

    @PutMapping("/{id}",produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun putTransaction(@PathVariable id: String, @RequestBody  @Valid newTransaction:
    ReportedOverlimitTransaction):
            ReportedOverlimitTransaction
    {
        return transactionService.putTransaction(id,newTransaction)
        //return newTransaction
    }

    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun postTransaction(@RequestBody @Valid newTransaction: ReportedOverlimitTransaction) :
            ReportedOverlimitTransaction
    {
        return transactionService.postTransaction(newTransaction)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTransaction(@PathVariable id : String)
    {
        if(!transactionService.deleteTransaction(id))
            throw ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")
    }
}