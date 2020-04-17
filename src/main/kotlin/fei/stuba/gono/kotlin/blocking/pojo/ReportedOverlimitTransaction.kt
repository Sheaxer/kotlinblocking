package fei.stuba.gono.kotlin.blocking.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fei.stuba.gono.kotlin.blocking.json.*
import fei.stuba.gono.kotlin.blocking.validation.annotations.DaysBeforeDate
import fei.stuba.gono.kotlin.blocking.validation.annotations.MaxAmount
import fei.stuba.gono.kotlin.blocking.validation.annotations.ValidAccount
import fei.stuba.gono.kotlin.json.OffsetDateTimeDeserializer
import fei.stuba.gono.kotlin.json.OffsetDateTimeSerializer
import fei.stuba.gono.kotlin.pojo.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

//@Document(value = "ReportedOverlimitTransactions")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ReportedOverlimitTransaction {
    @Id
    var id: String?=null

    @NotNull(message = "ORDERCATEGORY_INVALID")
    var orderCategory: OrderCategory? =null

    var state: State? = null

    @NotNull(message="SOURCEACCOUNT_INVALID")
    @ValidAccount(message = "SOURCEACCOUNT_INVALID")
    var sourceAccount: Account? = null

    @DBRef
    @Valid
    @NotNull(message = "CLIENTID_NOT_VALID")
    @JsonDeserialize(using = ClientDeserializer::class)
    @JsonSerialize(using = ClientSerializer::class)
    var clientId: Client? = null

    @NotBlank(message = "IDENTIFICATIONID_INVALID")
    var identificationId: String? = null

    @NotNull
    @MaxAmount(message = "FIELD_INVALID")
    var amount: Money? = null

    @NotNull(message="VAULT_INVALID")
    @NotEmpty(message="VAULT_INVALID")
    var vault: List<Vault>? = null

    @JsonSerialize(using = OffsetDateTimeSerializer::class)
    @JsonDeserialize(using= OffsetDateTimeDeserializer::class)
    var modificationDate: OffsetDateTime? = null
    get() = field?.toInstant()?.atOffset(ZoneOffset.of(this.zoneOffset)) ?: field
    set(modDate) {
        field = modDate
        this.zoneOffset = modDate?.offset?.id ?: "+00:00"
    }

    @NotNull(message="TRANSFERDATE_INVALID")
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @DaysBeforeDate(message="FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var transferDate: Date? = null

    var note:String? =null

    @DBRef
    @NotNull(message="ORGANISATIONUNITID_NOT_VALID")
    @JsonSerialize(using = OrganisationUnitSerializer::class)
    @JsonDeserialize(using = OrganisationUnitDeserializer::class)
    var organisationUnitID: OrganisationUnit? = null

    @DBRef
    @NotNull(message="CREATEDBY_NOT_VALID")
    @JsonDeserialize(using= EmployeeDeserializer::class)
    @JsonSerialize(using = EmployeeSerializer::class)
    var createdBy: Employee? = null

    @JsonIgnore
    var zoneOffset:String = "+00:00"
}