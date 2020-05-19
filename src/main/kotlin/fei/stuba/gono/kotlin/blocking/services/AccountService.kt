package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Account
import java.util.*

/***
 * Interface for marshalling and de-marshalling Account entities.
 * Rozhranie na marshalling a de-marshalling entít triedy Account.
 */
interface AccountService {

    /***
     *
     * Finds the entity with identified by the given IBAN.
     * Nájde entitu identifikovanú zadaným IBAN-om.
     * @param iban IBAN of the entity.
     *             IBAN identifikujúci entitu.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     * Optional obsahujúci entitu alebo Optional.empty() ak entita nebola nájdená.
     */
    fun getAccountByIban(iban : String) : Optional<Account>

    /***
     * Finds the entity with identified by the given Local Account Number.
     * Nájde entitu identifikovanú zadaným lokálnym číslom účtu.
     * @param number Local Account Number of the entity.
     *                    lokálne číslo účtu.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     * Optional obsahujúci entitu alebo Optional.empty() ak entita nebola nájdená.
     */
    fun getAccountByLocalNumber(number : String) :  Optional<Account>
}