package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository : MongoRepository<Account,String> {
    fun getAccountByIban(iban : String)
    fun getAccountByLocalAccountNumber(number: String)
}