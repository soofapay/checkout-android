package com.qubeans.checkout.utils

import android.app.Activity
import android.content.Context
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.text.DecimalFormat
import javax.crypto.Cipher

private const val PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwmhQ9FYyBpvZnnz8MDiP\n" +
        "+u3pliw6ve1NZ+b093znTTs7DBUeNH23O0f/WwUjrm26r+HTcS5Ky86EryJYo5qh\n" +
        "GWSq/c6BcqC1V9QSRMLt4BAh3JseiE8Noftqp0ONRlyAXL5A8kvYiigTczHJMLao\n" +
        "wdq8Sgw7fNUunZZZUaIQuNR0Nb60q+JOOnBvblu6GK6fQ5c9YFvxWpvvX/Sf69El\n" +
        "Gce+PEzuiM4ysJYOkkGfUS5Jlyfl7lRDhCyxtLNHIuSVwrTA1Om216xXvLdiOccs\n" +
        "j2Wjzqigp8/l3UZXtrb8AePn6Xyp3ncrSIzGnvYFthxReOcoWTcPPwvz+vRYHMx4\n" +
        "XEFI5kFFw/MD9JxiU4igsz9pR54WTwsYqdMHxi1MwgbzlqU2n/N4Cawy0x29v8tV\n" +
        "zgBTthqZVN35lNRHVqdhYpCjipvFcqCydPdMAT+C25kW01KYaqAQ/0qV58RV7N+G\n" +
        "xOWzfkSfRvEGza83vNNF9cdsBWQLmCIv9mLkLegj83JPUkgAcihR8Lhn6Ow10qsh\n" +
        "i1ysXQdnaGarSXXtXCn7zP+XNJd356+QUqX5W5528dL5i1jc1FU/0Ih7v5QrDNZJ\n" +
        "qliTUqnx9B3md4uTOn2G3rjUPtVkQcRHKyEoxpUoMWxat9U645E8ULy+bCZuBnXa\n" +
        "kMJm9kvGMbUbds8s6WhkK4UCAwEAAQ=="

fun encryptData(text: String): String {

    val publicBytes = Base64.decode(PUBLIC_KEY, Base64.DEFAULT)
    val keySpec = X509EncodedKeySpec(publicBytes)
    val keyFactory = KeyFactory.getInstance("RSA").generatePublic(keySpec)

    val cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, keyFactory)
    val encrypted: ByteArray = cipher.doFinal(text.toByteArray())

    return Base64.encodeToString(encrypted, Base64.DEFAULT)
}


fun genRandomString(n: Int): String {
    val alphaNumericString = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz")
    val builder = StringBuilder(n)
    for (i in 0 until n) {
        val index = (alphaNumericString.length * Math.random()).toInt()
        builder.append(alphaNumericString[index])
    }
    return builder.toString()
}

fun formatToMoney(value: Double?): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(value)
}

fun Activity.hideSoftKeyboard(view: View) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
