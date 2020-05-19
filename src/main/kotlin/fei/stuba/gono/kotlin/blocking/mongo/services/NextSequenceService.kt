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
/***
 * Service that generates next id value for storing data in MongoDB.
 * Služba, ktorá generuje nasledujúcu hodnotu id použitú na uloženie entity do Mongo databázy.
 */
@Service
class NextSequenceService @Autowired constructor(private val mongoOperations: MongoOperations){

    /***
     * Increments the value of sequence with the given
     * sequence name.
     * Inkrementuje hodnotu sekvencie so zadaným menom.
     * @see SequenceId
     * @param seqName name of the sequence.
     *                názov sekvencie.
     * @return updated value of the sequence.
     * aktualizovná hodnota sekvencie.
     */
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
    /***
     * Sets value of sequence with the given name.
     * Nastaví hodnotu sekvencie so zadaným názvom.
     * @see SequenceId
     * @param seqName name of the sequence.
     *                názov sekvencie.
     * @param value value that the sequence will be set to.
     *              hodnota na ktorú sa sekvencia nastaví.
     */
    private fun setNextSequence(seqName: String, value: String)
    {
         mongoOperations.findAndModify(
                query(Criteria.where("_id").`is`(seqName)),
                Update().set("seq",value.toLong()),
                options().returnNew(true).upsert(true),
                SequenceId::class.java
        )
    }
    /***
     * Generates a new value of an id for saving new object in a database.
     * Updates maximal value
     * of sequence with the given name, checks if an entity with this id already exists in the repository.
     * If it does exist, function finds the actual maximal value of id used to store entities in the repository and
     * updates the sequence.
     * Generuje novú hodnotu id na uloženie nového objektu do databázy. Aktualizuje
     * maximálnu hodnotu sekvencie so zadaným menom, skontroluje či už existuje entita so zadaným id. Ak existuje,
     * využije ďalšiu metódu na získanie skutočnej maximálnej hodnoty id v zadanom repozitáry a aktualizuje hodnotu
     * sekvencie.
     * @param rep repository where the object will be saved.
     *            repozitár v ktorom bude objekt uložený.
     * @param sequenceName name of the sequence holding the id of last saved object.
     *                     názov sekvencie ktorá udržiava id posledného uloženého objektu.
     * @return value of id that should be used to save object in
     * the given repository.
     * hodntota id ktoré by malo byť použité na uloženie
     * objektu v zadanom repozitári.
     */
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

    /***
     * Calculates the maximal id that was used to save an object of the given class.
     * Transforms ids of all entities of the given class into long and finds the maximal value.
     * Získa maximálnu hodnotu id ktoré bolo použité na uloženie objektu zadanej triedy.
     * Transformuje id všetkých entít triedy do typu long a získa maximálnu hodnotu.
     * @param rep class of the entities, must not be null.
     *            trieda entít, nesmie byť null.
     * @return string value of the maximal id of saved entity of the given class.
     * hodnota maximálneho id použitého na uloženie entity zadanej triedy.
     */
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

    /***
     * Checks if the sequence with given name needs to update
     * its maximal value. If the given value is larger than the maximal value stored in
     * the sequence with the given name, it sets it to the new value.
     * Skontroluje, či sekvencia so zadaným názvom potrebuje aktualizovať
     * maximálnu hodnotu id. Ak je zadaná hodnota väčšia ako uložená maximálna hodnota, nastaví
     * sa táto uložená hodnota na novú.
     * @param seqName name of the sequence, must not be null.
     *                názov sekvencie, nesmie byť null.
     * @param value value to be checked against maximal id value, must not be null.
     *            hodnota oproti ktorej sa uložená maximálna hodnota porovná.
     */
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