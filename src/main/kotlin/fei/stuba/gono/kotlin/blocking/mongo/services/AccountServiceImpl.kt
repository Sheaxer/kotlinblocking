package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.AccountRepository
import fei.stuba.gono.kotlin.blocking.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository) : AccountService {

    override fun getAccountByIban(iban: String) = accountRepository.getAccountByIban(iban)

    override fun getAccountByLocalNumber(number: String) = accountRepository.getAccountByLocalAccountNumber(number)
}