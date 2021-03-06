package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.AccountRepository
import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
/***
 * Implementation of AccountService using CRUD operations and auto generated instance of
 * AccountRepository.
 * Implementácia AccountService pomocou CRUD operácií a automaticky generovanej
 * inštancie AccountRepository.
 * @see AccountRepository
 */
@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository) : AccountService {

    override fun getAccountByIban(iban: String): Optional<Account> = accountRepository.getAccountByIban(iban)


    override fun getAccountByLocalNumber(number: String): Optional<Account> = accountRepository.
            getAccountByLocalAccountNumber(number)
}