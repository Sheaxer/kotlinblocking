package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Account
import java.util.*
/***
 * Interface for marshalling and de-marshalling Account entities.
 */
interface AccountService {
    /***
     * Finds the entity with identified by the given IBAN.
     * @param iban IBAN of the entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getAccountByIban(iban : String) : Optional<Account>

    /***
     * Finds the entity with identified by the given Local Account Number.
     * @param number Local Account Number of the entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getAccountByLocalNumber(number : String) :  Optional<Account>
}