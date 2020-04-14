package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.ReportedOverlimitTransaction
import org.springframework.stereotype.Service

@Service
interface ReportedOverlimitTransactionService {

    fun postTransaction(transaction: ReportedOverlimitTransaction) : ReportedOverlimitTransaction
    fun getTransactionById(id: String): ReportedOverlimitTransaction?
    fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction
    fun deleteTransaction(id: String): Boolean

}