package lectures.l5

import org.scalatest.{FlatSpec, Matchers}

class AuthorizationServiceTest extends FlatSpec with Matchers {

  import AuthorizationService._

  it should "authorize admin" in {
    authorize(UnauthorizedUser("admin", "admin")) shouldBe AuthorizedUser("admin", "admin", Admin)
  }

  it should "throw exception for unknown user" in {
    an [IllegalArgumentException] should be thrownBy authorize(UnauthorizedUser("dada", "cafe"))
  }

  it should "throw exception for authorized user" in {
    an [UserAlreadyAuthorizedException] should be thrownBy authorize(AuthorizedUser("dada", "cafe", Client))
  }

  it should "map for authorized user" in {
    mapForAuthorized(AuthorizedUser("bar", "baz", Admin), Admin) (_.login) shouldBe "bar"
  }

  it should "not map for unauthorized user" in {
    an [IllegalArgumentException] should be thrownBy mapForAuthorized(UnauthorizedUser("bar", "baz"), Admin) (_.login)
  }
}