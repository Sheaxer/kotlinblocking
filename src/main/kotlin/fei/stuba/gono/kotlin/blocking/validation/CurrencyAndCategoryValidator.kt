package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.blocking.validation.annotations.CurrencyAndCategory
import fei.stuba.gono.kotlin.pojo.Currency
import fei.stuba.gono.kotlin.pojo.OrderCategory
import lombok.extern.slf4j.Slf4j
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
/***
 * Class implementing validation of CurrencyAndCategory annotation for
 * ReportedOverlimitTransaction.
 * Transaction cannot have Category DOMESTIC and use non EUR currency or have category FX and currency EUR.
 * Trieda implementujúca validáciu objektu triedy ReportedOverlimitTransaction s anotáciou
 * CurrencyAndCategory. Transakcia nesmie mať kategóriu (orderCategory premennú) OrderCategory.DOMESTIC a menu
 * (amount.currency) inú ako Currency.EUR alebo kategóriu FX a nie menu EUR.
 * @see CurrencyAndCategory
 */
@Slf4j
class CurrencyAndCategoryValidator : ConstraintValidator<CurrencyAndCategory,ReportedOverlimitTransaction> {
    override fun isValid(p0: ReportedOverlimitTransaction?, p1: ConstraintValidatorContext?): Boolean {
        if( p0?.orderCategory == null || p0.amount?.currency == null)
            return true
        else
        {
            if((p0.orderCategory ==OrderCategory.FX && p0.amount?.currency == Currency.EUR) ||
                    (p0.orderCategory == OrderCategory.DOMESTIC && p0.amount?.currency != Currency.EUR))
                return false

        }
        return true

    }
}