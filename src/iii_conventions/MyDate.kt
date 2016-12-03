package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int =
            when {
                year != other.year -> year - other.year
                month != other.month -> month - other.month
                else -> dayOfMonth - other.dayOfMonth
            }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        val current = this.start
        val end = this.endInclusive
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean = current <= end
            override fun next(): MyDate = current.nextDay()
        }
    }
}

operator fun DateRange.contains(other: MyDate) : Boolean = this.start < other && this.endInclusive > other



