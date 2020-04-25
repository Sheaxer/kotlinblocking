package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
interface AccountRepository : CrudRepository<Account,String> {
    fun getAccountByIban(iban : String) : Optional<Account>
    fun getAccountByLocalAccountNumber(number: String) : Optional<Account>
}