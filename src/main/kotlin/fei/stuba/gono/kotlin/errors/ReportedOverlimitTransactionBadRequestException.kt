package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * Custom Exception used when ReportedOverlimitTransaction entity is not found.
 * Vlastná výnimka vyvolaná keď entita nebola nájdená.
 */
class ReportedOverlimitTransactionBadRequestException(message: String?)  : RuntimeException(message) {
    
}