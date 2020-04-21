package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.AccountRepository
import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository) : AccountService {

    override fun getAccountByIban(iban: String): Account?
    {
        return accountRepository.getAccountByIban(iban).orElse(null)
    }

    override fun getAccountByLocalNumber(number: String): Account? = accountRepository.
            getAccountByLocalAccountNumber(number).
            orElse(null)
}