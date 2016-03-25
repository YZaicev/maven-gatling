package peaktech

import java.util.Calendar

import io.gatling.http.Predef._
import sun.misc.BASE64Encoder

import scala.language.postfixOps

object Http {
  var url = System.getProperty("url")
  val consumerSecret = "fd7660e213150"

  if (url == null) {
    url = "https://vtqa.lfconnect.com/web/api"
  }

  val protocol = http
    .baseURL(url)
    .headers(
    Map("Content-Type" -> "application/json",
      "Accept" -> "application/json")
    )

  def getAuthorizationHeader(auth: String): String = {
    val today = Calendar.getInstance().getTime
    val timestamp = math.ceil(today.getTime / 1000).toInt

    val rnd = new scala.util.Random
    val range = 0 to 100000
    val nonce = range(rnd.nextInt(range length))

    val notAuthorizedHeader = "oauth_consumer_key=\"SILVERTREE\",oauth_timestamp=\"" +
      timestamp + "\",oauth_nonce=\"" + nonce + "\""

    val tokenSecret = auth.replaceAll(".*oauth_token_secret=\"(.*)\"", "$1")

    if (auth.length > 0) {
      return auth.replaceAll(", ", ",")
        .replaceAll(",oauth_token_secret=\"(.*)\"", "")
        .replaceAll("oauth_signature=\"(.*)\",", "") +
          "," + notAuthorizedHeader + ",oauth_signature=\"" + new BASE64Encoder().encode((consumerSecret + "&" + tokenSecret).getBytes).replace("\n", "") + "\""
    }

      "OAuth " + notAuthorizedHeader + ",oauth_signature=\"ZmQ3NjYwZTIxMzE1MA==\""
    }
}
