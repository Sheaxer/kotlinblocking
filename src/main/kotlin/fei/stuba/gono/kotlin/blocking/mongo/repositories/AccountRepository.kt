package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
/***
 * Interface extending CrudRepository for Account.
 * @see Account
 * @see CrudRepository
 */
@Repository
interface AccountRepository : CrudRepository<Account,String> {
    /***
     * Retrieves entity identified by the given IBAN.
     * @param iban IBAN of entity.
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     */
    fun getAccountByIban(iban : String) : Optional<Account>
    /***
     * Retrieves entity identified by the given Local Account Number.
     * @param number Local Account Number of entity.
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     */
    fun getAccountByLocalAccountNumber(number: String) : Optional<Account>
}