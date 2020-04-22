package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ReportedOverlimitTransactionRepository
import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import fei.stuba.gono.kotlin.errors.ReportedOverlimiTransactionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReportedOverlimitTransactionServiceImpl @Autowired constructor(private val
repository: ReportedOverlimitTransactionRepository) : ReportedOverlimitTransactionService
{
    override fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        return repository.insert(transaction)
    }

    override fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction> =
    repository.findById(id)


    override fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        transaction.id = id
        return repository.save(transaction)
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