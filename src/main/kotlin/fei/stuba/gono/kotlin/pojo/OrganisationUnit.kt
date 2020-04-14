package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "organisationUnits")
class OrganisationUnit {

    @Id
    var id: String? = null
}