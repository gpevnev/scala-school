package lectures.l5

object AuthorizationService {

  private val userDBMock: Set[AuthorizedUser] =
    Set(
      AuthorizedUser("admin", "admin", Admin),
      AuthorizedUser("me", "12345", Client),
      AuthorizedUser("foo", "bar", Moderator)
    )

  private def check(user: User): Option[AuthorizedUser] =
    userDBMock.find({
      case AuthorizedUser(user.login, user.password, _) => true
      case _ => false
    })


  def authorize(user: User): AuthorizedUser = {
    user match {
      case u: UnauthorizedUser => check(u).getOrElse(throw new IllegalArgumentException)
      case _ => throw new UserAlreadyAuthorizedException
    }
  }
}

class UserAlreadyAuthorizedException extends Exception
