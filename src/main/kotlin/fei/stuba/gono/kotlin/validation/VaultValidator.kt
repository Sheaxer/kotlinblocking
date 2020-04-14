package fei.stuba.gono.kotlin.validation

import fei.stuba.gono.kotlin.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.pojo.Vault
import fei.stuba.gono.kotlin.validation.annotations.ValidVault
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class VaultValidator : ConstraintValidator<ValidVault, ReportedOverlimitTransaction> {
    override fun isValid(p0: ReportedOverlimitTransaction?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return false
        var value: Double = 0.0
        p0.vault?.forEach {
            t-> value += t.nominalValue
        }
        return value == p0.amount?.amount
    }
}