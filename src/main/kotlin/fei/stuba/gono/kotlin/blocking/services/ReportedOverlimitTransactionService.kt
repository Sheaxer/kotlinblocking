package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import org.springframework.stereotype.Service
import java.util.*


interface ReportedOverlimitTransactionService {

    fun postTransaction(transaction: ReportedOverlimitTransaction) : ReportedOverlimitTransaction
    fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction>
    fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction
    fun deleteTransaction(id: String): Boolean

}