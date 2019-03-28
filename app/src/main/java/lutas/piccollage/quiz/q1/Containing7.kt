package lutas.piccollage.quiz.q1

class Containing7 {

    fun compute(num: Int): Int {
        if (num < 7)
            return 0

        val digits = Math.log10(num.toDouble()).toInt()
        // g(1) = 0
        // g(10) = 1
        val a = IntArray(digits + 2)
        a[0] = 0
        a[1] = 1

        for (i in 2..digits) {
            a[i] = a[i - 1] * 9 + Math.pow(10.0, (i - 1).toDouble()).toInt()
        }

        val p = Math.pow(10.0, digits.toDouble()).toInt()
        val msd = num / p

        return when {
            msd == 7 -> msd * a[digits] + num % p + 1
            msd > 7 -> (msd - 1) * a[digits] + p + compute(num % p)
            else -> (msd) * a[digits] + compute(num % p)
        }
    }

    /**
     * find g(10000) to assert
     */
    fun stupidWay(num: Int) {
        var sevens = 0
        for (i in 1..num) {
            val string = i.toString()
            if (string.contains('7')) {
                sevens++
            }
        }
        println("result: $sevens")
    }
}