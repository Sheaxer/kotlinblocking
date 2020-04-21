package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Account

interface AccountService {
    fun getAccountByIban(iban : String) : Account?
    fun getAccountByLocalNumber(number : String) : Account?
}