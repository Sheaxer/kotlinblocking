package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/***
 * Class representing structure for vault. Detail information about withdraw amount.
 */
class Vault {
    @get:NotNull(message = "VAULTTYPE_INVALID")
    var type: VaultType? = null
    @get:Positive(message = "VAULTNUMBER_INVALID")
    var number: Int = 0
    @get:Positive(message = "VAULTNOMINALVALUE_INVALID")
    var nominalValue: Int = 0
}