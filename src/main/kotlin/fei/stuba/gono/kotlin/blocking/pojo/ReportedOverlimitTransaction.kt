package fei.stuba.gono.kotlin.blocking.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fei.stuba.gono.kotlin.blocking.json.*
import fei.stuba.gono.kotlin.blocking.validation.annotations.*
import fei.stuba.gono.kotlin.json.OffsetDateTimeDeserializer
import fei.stuba.gono.kotlin.json.OffsetDateTimeSerializer
import fei.stuba.gono.kotlin.pojo.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.*

@Document(collection = "reportedOverlimitTransactions")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@CurrencyAndCategory(message = "CATEGORY_INVALID")
 class ReportedOverlimitTransaction {
    @Id
    var id: String? = null

    @get:NotNull(message = "ORDERCATEGORY_INVALID")
     var orderCategory: OrderCategory? = null

    var state: State? = null

    @get:NotNull(message = "SOURCEACCOUNT_INVALID")
    @get:ValidAccount(message = "SOURCEACCOUNT_INVALID")
    @get:OnlineAccount(message = "ACCOUNT_OFFLINE")
    @JsonDeserialize(using = AccountDeserializer::class)
     var sourceAccount: Account? = null

    @DBRef
    @get:NotNull(message = "CLIENTID_NOT_VALID")
    @JsonDeserialize(using = ClientDeserializer::class)
    @JsonSerialize(using = ClientSerializer::class)
     var clientId: Client? = null

    @get:NotBlank(message = "IDENTIFICATIONID_INVALID")
     var identificationId: String? = null

    @get:NotNull(message = "FIELD_INVALID")
    @get:MaxAmount(message = "FIELD_INVALID")
    @get:Limit(message = "LIMIT_EXCEEDED")
     var amount: Money?=null

    @get:NotEmpty(message = "VAULT_INVALID")
     var vault: List<Vault>? = null

    @JsonSerialize(using = OffsetDateTimeSerializer::class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer::class)
    var modificationDate: OffsetDateTime = OffsetDateTime.now()
        get() = field.toInstant().atOffset(ZoneOffset.of(zoneOffset))
        set(modDate) {
            field = modDate
            zoneOffset = modDate.offset.id
        }

    @get:NotNull(message = "TRANSFERDATE_INVALID")
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @get:DaysBeforeDate(message = "FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var transferDate: Date? = null

    var note: String? = null

    @DBRef
    @get:NotNull(message = "ORGANISATIONUNITID_NOT_VALID")
    @JsonSerialize(using = OrganisationUnitSerializer::class)
    @JsonDeserialize(using = OrganisationUnitDeserializer::class)
     var organisationUnitID: OrganisationUnit? = null

    @DBRef
    @get:NotNull(message = "CREATEDBY_NOT_VALID")
    @JsonDeserialize(using = EmployeeDeserializer::class)
    @JsonSerialize(using = EmployeeSerializer::class)
     var createdBy: Employee? = null

    @JsonIgnore
    var zoneOffset: String = "+00:00"
}