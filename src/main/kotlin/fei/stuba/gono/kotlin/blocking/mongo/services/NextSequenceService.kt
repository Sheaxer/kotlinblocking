package fei.stuba.gono.kotlin.blocking.mongo.services

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Projections
import fei.stuba.gono.kotlin.blocking.mongo.repositories.*
import fei.stuba.gono.kotlin.blocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.pojo.*
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class NextSequenceService @Autowired constructor(private val mongoOperations: MongoOperations){

    private fun getNextSequence(seqName: String): String {
        val counter = mongoOperations.findAndModify(
                query(Criteria.where("_id").`is`(seqName)),
                Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                SequenceId::class.java)
        if (counter == null) {
            setNextSequence(seqName, 1.toString())
            return "1"
        }
        return counter.seq.toString()
    }

    private fun setNextSequence(seqName: String, value: String)
    {
         mongoOperations.findAndModify(
                query(Criteria.where("_id").`is`(seqName)),
                Update().set("seq",value.toLong()),
                options().returnNew(true).upsert(true),
                SequenceId::class.java
        )
    }

    fun getNewId(rep : CrudRepository<*,String>, sequenceName: String) : String
    {
        var newId = this.getNextSequence(sequenceName)
        if(rep.findById(newId).isPresent)
        {
          newId = when (rep) {
              is ReportedOverlimitTransactionRepository -> lastId(ReportedOverlimitTransaction::class.java)
              is ClientRepository -> lastId(Client::class.java)
              is EmployeeRepository -> lastId(Employee::class.java)
              is OrganisationUnitRepository -> lastId(OrganisationUnit::class.java)
              is AccountRepository -> lastId(Account::class.java)
              else -> throw Exception("NOT_SUPPORTED")
          }
        }
        return newId
    }

    private fun lastId(rep: Class<*>): String {
        return mongoOperations.execute(rep) { mongoCollection: MongoCollection<Document> ->
            val doc = mongoCollection.find().projection(Projections.include("_id"))
            val s = doc.map { document: Document ->
                try {
                    return@map document.getString("_id").toLong()
                } catch (e: NumberFormatException) {
                    return@map 0L
                }
            }
            var lastVal = 0L
            for (tmp in s) {
                lastVal = if (tmp > lastVal) tmp else lastVal
            }
            lastVal++
            lastVal.toString()
        }!!
    }

    fun needsUpdate(seqName: String, value: String)
    {
        try {
            val longVal = value.toLong()

            val tmp =mongoOperations.find(query(Criteria.where("_id").`is`(seqName)), SequenceId::class.java)
            if(tmp.isEmpty())
                tmp.add(SequenceId())
            if(longVal > tmp.get(0).seq)
                setNextSequence(seqName,value)
        }
        catch (ex: java.lang.NumberFormatException)
        { }
    }

}