package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "customSequences")
class SequenceId {
    @Id
    var id: String? = null

    var seq: Long = 0

}