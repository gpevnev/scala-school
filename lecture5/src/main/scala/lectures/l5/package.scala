package lectures

package object l5 {

  def mapForAuthorized[T](user: User, group: UserGroup)
                         (mapper: AuthorizedUser => T) : T = {

    user match {
      case au@AuthorizedUser(_, _, `group`) => mapper(au)
      case _ => throw new IllegalArgumentException
    }
  }
}
