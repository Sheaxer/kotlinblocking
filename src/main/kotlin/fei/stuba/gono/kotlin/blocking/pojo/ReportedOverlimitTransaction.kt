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
/***
 * Class representing ReportedOverlimitTransaction from FENiX - New FrontEnd solution API definition.
 */
@Document(collection = "reportedOverlimitTransactions")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@CurrencyAndCategory(message = "CATEGORY_INVALID")
 class ReportedOverlimitTransaction {
    /***
     * Internal identifier of order (provided as response after creation from BE)
     */
    @Id
    var id: String? = null

    /***
     * Order category determines whether reported overlimit transaction is withdraw in EUR or foreign currency.
     */
    @get:NotNull(message = "ORDERCATEGORY_INVALID")
     var orderCategory: OrderCategory? = null

    /***
     * State of order presented to user on FE, value is mapped based on provided BE technical states.
     */
    @get:NotNull(message = "STATE_INVALID")
    var state: State? = null

    /***
     * Account number of the client (type: IBAN with optional BIC or local account number) where
     * withdraw will be realised.
     */
    @get:NotNull(message = "SOURCEACCOUNT_INVALID")
    @get:ValidAccount(message = "SOURCEACCOUNT_INVALID")
    @get:OnlineAccount(message = "ACCOUNT_OFFLINE")
     var sourceAccount: AccountNO? = null

    /***
     * Object representing client who will realize withdraw. On frontend we have to show client name and dato of birth.
     */
    @DBRef
    @get:NotNull(message = "CLIENTID_NOT_VALID")
    @JsonDeserialize(using = ClientDeserializer::class)
    @JsonSerialize(using = ClientSerializer::class)
     var clientId: Client? = null

    /***
     * Id of client identification with which will realize withdraw. On frontend we have to show number of
     * identification.
     */
    @get:NotBlank(message = "IDENTIFICATIONID_INVALID")
     var identificationId: String? = null

    /***
     * Structure for vault. Detail information about withdrow amount.
     */
    @get:NotEmpty(message = "VAULT_INVALID")
    var vault: List<Vault>? = null

    /***
     * Withdraw amount in defined currency (only EUR for DOMESTIC) and with precision (embedded AMOUNT type).
     */
    @get:NotNull(message = "FIELD_INVALID")
    @get:MaxAmount(message = "FIELD_INVALID")
    @get:Limit(message = "LIMIT_EXCEEDED")
    @Valid
     var amount: Money?=null

    /***
     * Requested due date entered by client (have to be in near future, minimal D+3),
     * date when withdraw order should be realized from user account.
     * Default value could be current business day +3 ISO date format: YYYY-MM-DD.
     */
    @get:NotNull(message = "TRANSFERDATE_INVALID")
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @get:DaysBeforeDate(message = "FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var transferDate: Date? = null

    var note: String? = null

    /***
     * Modification date indicates the last update of order done by user or BE system
     * (read-only field provided by BE).
     * ISO dateTime format: YYYY-MM-DDThh:mm:ssZ
     */
    @JsonSerialize(using = OffsetDateTimeSerializer::class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer::class)
    var modificationDate: OffsetDateTime = OffsetDateTime.now()
        get() = field.toInstant().atOffset(ZoneOffset.of(zoneOffset))
        set(modDate) {
            field = modDate
            zoneOffset = modDate.offset.id
        }

    /***
     * Object representing organisation unit where client want to realize withdraw.
     */
    @DBRef
    @get:NotNull(message = "ORGANISATIONUNITID_NOT_VALID")
    @JsonSerialize(using = OrganisationUnitSerializer::class)
    @JsonDeserialize(using = OrganisationUnitDeserializer::class)
     var organisationUnitID: OrganisationUnit? = null

    /***
     * Object representing employer who entered an transaction. In this case report over limit withdraw.
     */
    @DBRef
    @get:NotNull(message = "CREATEDBY_NOT_VALID")
    @JsonDeserialize(using = EmployeeDeserializer::class)
    @JsonSerialize(using = EmployeeSerializer::class)
     var createdBy: Employee? = null

    @JsonIgnore
    private var zoneOffset: String = "+00:00"

}