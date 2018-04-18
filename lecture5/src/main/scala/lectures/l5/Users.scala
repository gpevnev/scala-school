package lectures.l5

trait UserGroup

case object Client extends UserGroup
case object Moderator extends UserGroup
case object Admin extends UserGroup

trait User {
  val login: String
  val password: String
}

case class UnauthorizedUser(login: String, password: String) extends User
case class AuthorizedUser(login: String, password: String, group: UserGroup) extends User