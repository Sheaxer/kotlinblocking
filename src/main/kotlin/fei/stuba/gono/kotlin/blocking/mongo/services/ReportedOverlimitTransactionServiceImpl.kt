package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.ReportedOverlimitTransactionRepository
import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.services.ReportedOverlimitTransactionService
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.pojo.State
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class ReportedOverlimitTransactionServiceImpl @Autowired constructor(private val
repository: ReportedOverlimitTransactionRepository, private val nextSequenceService: NextSequenceService) :
        ReportedOverlimitTransactionService
{
    @Value("\${reportedOverlimitTransaction.transaction.sequenceName:customSequences}")
    private val sequenceName : String = "reportedOverlimitTransactionSequence"

    override fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        val newId = nextSequenceService.getNewId(repository,sequenceName)
        transaction.id = newId
        transaction.state = State.CREATED
        transaction.modificationDate = OffsetDateTime.now()
        return repository.save(transaction)
    }

    override fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction> =
    repository.findById(id)


    override fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        transaction.id = id
        transaction.modificationDate = OffsetDateTime.now()
        nextSequenceService.needsUpdate(sequenceName,id)
        return repository.save(transaction)
    }

    override fun deleteTransaction(id: String): Boolean {
        val transaction = repository.findById(id)
        if(transaction.isPresent)
        {
            val trans = transaction.get()
            if(trans.state != State.CLOSED)
            {
                repository.delete(trans)
                return true
            }
            else
                throw ReportedOverlimitTransactionBadRequestException("STATE_CLOSED")
        }
        else
            return false
    }
}