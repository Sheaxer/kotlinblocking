package fei.stuba.gono.kotlin.blocking.rest

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import fei.stuba.gono.kotlin.errors.ReportedOverlimiTransactionException
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
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
        return transactionService.getTransactionById(id) ?: throw ReportedOverlimiTransactionException("ID_NOT_FOUND")
    }

    @PutMapping("/{id}",produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun putTransaction(@PathVariable id: String, @RequestBody @Valid newTransaction: ReportedOverlimitTransaction):
            ReportedOverlimitTransaction
    {
        //println(newTransaction.amount?.amount)

        newTransaction.modificationDate = OffsetDateTime.now()
        newTransaction.id=id
        //return transactionService.putTransaction(id,newTransaction)
        return newTransaction
    }
}