package fei.stuba.gono.kotlin.blocking.errors

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors


@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleReportedOverlimitTransactionNotFoundException
            (e : ReportedOverlimitTransactionNotFoundException) : List<String?>
    {
        return listOf(e.message)
    }

    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleReportedOverlimitTransactionBadRequestException
            (e : ReportedOverlimitTransactionNotFoundException) : List<String?>
    {
        return listOf(e.message)
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException) : List<String?>
    {
        return ex.bindingResult
                .allErrors.stream()
                .map { obj: ObjectError -> obj.defaultMessage }
                .collect(Collectors.toList())
    }
}