package kr.vanilage.main.log

class Logger {
    val logList = ArrayList<LogData>()

    fun addLog(log : LogData) {
        logList.add(log)
    }

    fun findLog(date : String?, time : String?, type : String?, target : String?, additional : String?) : ArrayList<LogData> {
        val logListCopy = logList

        for (i in logList) {
            if (date != null && i.date != date) {
                logListCopy.remove(i)
                continue
            }

            if (time != null && i.time != time) {
                logListCopy.remove(i)
                continue
            }

            if (type != null && i.type != type) {
                logListCopy.remove(i)
                continue
            }

            if (target != null && i.target != target) {
                logListCopy.remove(i)
                continue
            }

            if (additional != null && i.additional != additional) {
                logListCopy.remove(i)
                continue
            }
        }

        return logListCopy
    }
}