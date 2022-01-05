package controllers.helpers

import constants.INVALID_REQUEST_ERROR
import io.circe.Json
import io.circe.syntax._
import models.errors.validations.{ValidationError, ValidationObject}
import utils.circe.CirceImplicits

/**
  * Trait that contains helper methods to create Validation errors.
  */
trait Validatable extends CirceImplicits {

  /**
    * Create an Invalid request and converts it to Json in order to handle Validation errors.
    *
    * @param validationErrors List of errors.
    * @param errorType Type of error.
    * @return Json representation of the list of errors.
    */
  protected def validationErrorsAsInvalidRequestJson(
    validationErrors: List[ValidationError],
    errorType: String = INVALID_REQUEST_ERROR
  ): Json = validationErrorsAsInvalidRequest(validationErrors, errorType).asJson

  protected def validationErrorsAsInvalidRequest(
    validationErrors: List[ValidationError],
    errorType: String = INVALID_REQUEST_ERROR
  ): ValidationObject = ValidationObject(errorType = errorType, validationErrors = validationErrors)

}
