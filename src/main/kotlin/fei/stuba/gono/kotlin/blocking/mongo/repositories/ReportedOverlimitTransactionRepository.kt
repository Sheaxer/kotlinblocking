package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import org.springframework.data.mongodb.repository.MongoRepository

interface ReportedOverlimitTransactionRepository :
        MongoRepository<ReportedOverlimitTransaction,
        String> {
}