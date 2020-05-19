package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
/***
 * Interface extending CrudRepository for Account.
 * Rozhranie rozširujúce CrudRepository pre objekty triedy Account.
 * @see Account
 * @see CrudRepository
 */
@Repository
interface AccountRepository : CrudRepository<Account,String> {
    /***
     * Finds the entity identified by the given IBAN.
     * Nájde entitu identifikovanú zadaným IBAN-om
     * @param iban IBAN of entity.
     *             IBAN entity
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita nebola nájdená.
     */
    fun getAccountByIban(iban : String) : Optional<Account>

    /***
     * Finds the entity identified by the given Local Account Number.
     * Nájde entitu identifikovanú lokálnym číslom účtu.
     * @param number Local Account Number of entity.
     *                           lokálne číslo účtu.
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita nebola nájdená.
     */
    fun getAccountByLocalAccountNumber(number: String) : Optional<Account>
}