package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ReportedOverlimitTransactionRepository
import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReportedOverlimitTransactionServiceImpl @Autowired constructor(private val
repository: ReportedOverlimitTransactionRepository) : ReportedOverlimitTransactionService
{
    override fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        return repository.insert(transaction)
    }

    override fun getTransactionById(id: String): ReportedOverlimitTransaction? {
        return repository.findById(id).orElse(null)
    }

    override fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        transaction.id = id
        return repository.insert(transaction)
    }

    override fun deleteTransaction(id: String): Boolean {
        if(repository.existsById(id))
        {
            repository.deleteById(id)
            return true
        }
        return false

    }
}