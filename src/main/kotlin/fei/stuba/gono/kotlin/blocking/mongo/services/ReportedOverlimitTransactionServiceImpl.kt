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
/***
 * Implementation of ReportedOverlimitTransactionService for use with MongoDB.
 */
@Service
class ReportedOverlimitTransactionServiceImpl @Autowired constructor(private val
repository: ReportedOverlimitTransactionRepository, private val nextSequenceService: NextSequenceService) :
        ReportedOverlimitTransactionService
{
    /***
     * Name of the sequence containing maximal value of id that was used to save entity in the repository.
     */
    @Value("\${reportedOverlimitTransaction.transaction.sequenceName:customSequences}")
    private val sequenceName : String = "reportedOverlimitTransactionSequence"

    /***
     * Generates new id using NextSequenceService and saved entity with this id in the repository. Sets the
     * modification date of entity to the time of saving and sets the state to CREATED.
     * @see NextSequenceService
     * @see State
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    override fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        val newId = nextSequenceService.getNewId(repository,sequenceName)
        transaction.id = newId
        transaction.state = State.CREATED
        transaction.modificationDate = OffsetDateTime.now()
        return repository.save(transaction)
    }
    /***
     * Finds entity with the given id in the repository.
     * @param id id of entity.
     * @return Optional containing the entity or Optional.empty() if none found.
     */
    override fun getTransactionById(id: String): Optional<ReportedOverlimitTransaction> =
    repository.findById(id)

    /***
     * Saves the entity using the given id in the repository. Sets modification date to time of saving, and if
     * there was no entity with the given id before sets the state to CREATED. Notifies the NextSequenceService to
     * check if the id given is a new maximal value.
     * @param id id which will identify the saved entity.
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    override fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        transaction.id = id
        transaction.modificationDate = OffsetDateTime.now()
        nextSequenceService.needsUpdate(sequenceName,id)
        return repository.save(transaction)
    }
    /***
     * Deletes the entity with the given id if it exists in the repository and its state is not CLOSED.
     * @param id id of entity.
     * @return true if entity with given id was found, its state was not CLOSED and it was deleted.
     * @throws ReportedOverlimitTransactionBadRequestException in case the entity couldn't be deleted because its
     * state was CLOSED.
     */
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