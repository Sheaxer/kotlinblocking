package fei.stuba.gono.kotlin.blocking.errors

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.ObjectError
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

/***
 * Class that implements custom error handling.
 * Trieda ktorá implementuje vlastné spravocanie výnimiek.
 */
@RestControllerAdvice
class ErrorHandler {
    /***
     * Handles ReportedOverlimitTransactionNotFoundException
     * by returning the error code and sending HTTP code NOT_FOUND - 404.
     * Spracúváva ReportedOverlimitTransactionNotFoundException výnimku
     * vrátením HTTP kódu 404 Not Found a chybovej hlášky v tele správy.
     * @param ex caught exception.
     *           odchytená výnimka.
     * @return The List containing the error message of ex.
     * Zoznam obsahujúcí chybovú hlášku v ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleReportedOverlimitTransactionNotFoundException
            (ex : ReportedOverlimitTransactionNotFoundException) : List<String?>
    {
        return listOf(ex.message)
    }

    /***
     * Handles ReportedOverlimitTransactionBadRequestException by returning
     * the error code and sending HTTP code BAD_REQUEST - 400.
     * Spracúvava ReportedOverlimitTransactionBadRequestException výnimku vrátením
     * HTTP kódu BAD_REQUEST - 400 a chybovej hlášky v tele správy.
     * @param ex caught exception.
     *           odchytená výnimka.
     * @return The list containing the error message of ex.
     * Zoznam, ktorý obsahuje chybovú hlášku v ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleReportedOverlimitTransactionBadRequestException
            (ex : ReportedOverlimitTransactionBadRequestException) : List<String?>
    {
        return listOf(ex.message)
    }

    /***
     * Handles validation errors that occur during put and post REST methods. Returns
     * HTTTP code BAD_REQUEST - 400 and list of validation errors.
     * Spracuváva validačné výnimky ktoré môžu nastať počas PUT a POST REST metód. Vracia
     * HTTP kód BAD_REQUEST - 400 a zoznam validačných chýb v tele odpovede.
     * @param ex caught validation exception.
     *           zachytená validačná výnimka.
     * @return List of validation error messages.
     * Zoznam validačných chyhových hlášok.
     * @see MethodArgumentNotValidException
     */
    @ExceptionHandler(org.springframework.validation.BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: org.springframework.validation.BindException) : List<String?>
    {
        return ex.bindingResult
                .allErrors.stream()
                .map { obj: ObjectError -> obj.defaultMessage }
                .collect(Collectors.toList())
    }

    /***
     * Handles validation errors that occur during put and post REST methods. Returns
     * HTTTP code BAD_REQUEST - 400 and list of validation errors.
     * Spracuváva validačné výnimky ktoré môžu nastať počas PUT a POST REST metód. Vracia
     * HTTP kód BAD_REQUEST - 400 a zoznam validačných chýb v tele odpovede.
     * @param ex caught validation exception.
     *           zachytená validačná výnimka.
     * @return List of validation error messages.
     * Zoznam validačných chyhových hlášok.
     * @see MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException) : List<String?>
    {
        return ex.bindingResult
                .allErrors.stream()
                .map { obj: ObjectError -> obj.defaultMessage }
                .collect(Collectors.toList())
    }

    /***
     * Handles the HttpRequestMethodNotSupportedException.
     * Spracuje HttpRequestMethodNotSupportedException výnimku.
     * @param ex caught exception.
     * @return "METHOD_NOT_ALLOWED" error code.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleMethonNotSupported(ex: HttpRequestMethodNotSupportedException?): String {
        return "METHOD_NOT_ALLOWED"
    }

    /***
     * Handles the HttpRequestMethodNotSupportedException.
     * Spracuje HttpRequestMethodNotSupportedException výnimku.
     * @param ex caught exception.
     * @return "MEDIATYPE_INVALID" error code.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTypeNotSupported(ex: HttpMediaTypeNotSupportedException?): String {
        return "MEDIATYPE_INVALID"
    }

    /***
     * Handles the HttpRequestMethodNotSupportedException.
     * Spracuje HttpRequestMethodNotSupportedException výnimku.
     * @param ex caught exception.
     * @return "ILLEGAL_ARGUMENT" error code.
     */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgument(ex: IllegalArgumentException?): String {
        return "ILLEGAL_ARGUMENT"
    }
}