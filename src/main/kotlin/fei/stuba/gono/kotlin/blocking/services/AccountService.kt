package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Account
import java.util.*

interface AccountService {
    fun getAccountByIban(iban : String) : Optional<Account>
    fun getAccountByLocalNumber(number : String) :  Optional<Account>
}