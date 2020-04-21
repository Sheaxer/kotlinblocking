package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface AccountRepository : MongoRepository<Account,String> {
    fun getAccountByIban(iban : String) : Optional<Account>
    fun getAccountByLocalAccountNumber(number: String) : Optional<Account>
}