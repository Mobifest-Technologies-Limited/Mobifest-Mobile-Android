package co.mobifest.mobile.utils

import java.util.*

class AmountFormatter {
    companion object {
        fun getDecimalFormattedString(value: String): String {
            val lst = StringTokenizer(value, ".")
            var str1 = value
            var str2 = ""
            if (lst.countTokens() > 1) {
                str1 = lst.nextToken()
                str2 = lst.nextToken()
            }
            var str3 = ""
            var i = 0
            var j = -1 + str1.length
            if (str1[-1 + str1.length] == '.') {
                j--
                str3 = "."
            }
            var k = j
            while (true) {
                if (k < 0) {
                    if (str2.isNotEmpty()) str3 = "$str3.$str2"
                    return str3
                }
                if (i == 3) {
                    str3 = ",$str3"
                    i = 0
                }
                str3 = str1[k].toString() + str3
                i++
                k--
            }
        }

        fun returnConcatenatedAmountCurrencyString(amount: String, currency: String): String {
            return if (amount == "0" || amount == "0.0") {
                "0.00 $currency"
            } else {
                "$amount $currency"
            }

        }

        fun concatenatePercentage(value: String?): String {
            return if (value != null) {
                "$value%"
            } else {
                "- - -"
            }
        }
    }
}