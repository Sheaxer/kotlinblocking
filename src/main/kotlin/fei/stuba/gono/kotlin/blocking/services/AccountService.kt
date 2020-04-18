package fei.stuba.gono.kotlin.blocking.services

interface AccountService {
    fun getAccountByIban(iban : String)
    fun getAccountByLocalNumber(number : String)
}