package models.errors

trait ApplicationError

case class ExecutionError(description: String, t: Throwable) extends ApplicationError

case class AuthenticationError(errorMessage: String) extends ApplicationError

case class DataBaseError(msg: String) extends ApplicationError

case object EmptyResponse extends ApplicationError

case object NotFoundResponse extends ApplicationError

case class UnexpectedError(throwable: Throwable) extends ApplicationError

case class BadRequestError(errorMessage: String) extends ApplicationError

case class ClientError(errorMessage: String) extends ApplicationError

case class RemoteApiError(errorMessage: String) extends ApplicationError
