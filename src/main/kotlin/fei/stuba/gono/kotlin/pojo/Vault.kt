package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/***
 * Class representing structure for vault.
 * Detail information about withdraw amount.
 * Trieda reprezentujúca tzv. štruktúru "vault".
 * Podáva podrobnejšie informácie o výbere sumy.
 */
class Vault {
    /***
     * Type of tender.
     * Typ tendra.
     */
    @get:NotNull(message = "VAULTTYPE_INVALID")
    var type: VaultType? = null

    /***
     * Number tenders per type.
     * Počet tendrov typu
     */
    @get:Positive(message = "VAULTNUMBER_INVALID")
    var number: Int = 0

    /***
     * Nominal value (power) of tender.
     * Nominálna hodnota (sila) tendra.
     */
    @get:Positive(message = "VAULTNOMINALVALUE_INVALID")
    var nominalValue: Int = 0
}