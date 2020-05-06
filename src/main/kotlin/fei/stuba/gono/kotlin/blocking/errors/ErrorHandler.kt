package fei.stuba.gono.kotlin.blocking.errors

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

/***
 * Class implementing custom error handling.
 */
@RestControllerAdvice
class ErrorHandler {
    /***
     * Handles not found exception by returning the list containing the error code and sending the HTTP
     * NOT_FOUND 404 code.
     * @param ex caught exception.
     * @return List containing the error code.
     * @see ReportedOverlimitTransactionNotFoundException
     */
    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleReportedOverlimitTransactionNotFoundException
            (ex : ReportedOverlimitTransactionNotFoundException) : List<String?>
    {
        return listOf(ex.message)
    }
    /***
     * Handles the bad request exception by returning the list containing the error code and sends the HTTP
     * BAD_REQUEST 400 code.
     * @param ex caught exception
     * @return List containing the error code.
     * @see ReportedOverlimitTransactionBadRequestException
     */
    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleReportedOverlimitTransactionBadRequestException
            (ex : ReportedOverlimitTransactionBadRequestException) : List<String?>
    {
        return listOf(ex.message)
    }

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
     * Handles validation errors by transforming into JSON array and returns the error codes with HTTP code
     * BAD_REQUEST 400.
     * @param ex caught validation exception.
     * @return List of validation error messages.
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
     * Handles the HttpMessageNotReadableException
     * @param ex caught exception.
     * @return error code.
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMessageNotReadableException(
            ex: HttpMessageNotReadableException?): String? {
        return "MESSAGE_UNREADABLE"
    }
}